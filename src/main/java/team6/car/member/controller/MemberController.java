package team6.car.member.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;
import team6.car.apartment.repository.ApartmentRepository;
import team6.car.device.repository.DeviceRepository;
import team6.car.member.DTO.*;
import team6.car.member.domain.Complaint;
import team6.car.member.domain.Member;
import team6.car.member.repository.MemberRepository;
import team6.car.member.response.Message;
import team6.car.member.response.StatusEnum;
import team6.car.member.service.MemberServiceImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team6.car.vehicle.domain.Vehicle;
import team6.car.vehicle.repository.VehicleRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@RequiredArgsConstructor //의존성 주입
@RestController // annotation 역할 :  spring bean으로 해당 클래스를 spring-container에서 관리
public class MemberController {
    private final MemberServiceImpl memberService;
    private final MemberRepository memberRepository;
    private final ApartmentRepository apartmentRepository;
    private final VehicleRepository vehicleRepository;
    private final DeviceRepository deviceRepository;

    @ApiOperation(value="회원가입")
    @ApiResponses({
            @ApiResponse(code = 200,message = "OK(회원가입 성공)"),
            @ApiResponse(code = 400, message="BAD_REQUEST"),
            @ApiResponse(code = 404, message="NOT_FOUND"),
            @ApiResponse(code = 500, message="INTERNAL_SERVER_ERROR"),
            @ApiResponse(code = 409,message="CONFLICT")
    })
    @PostMapping("/members")//회원가입
    public ResponseEntity<Message> register(@RequestBody UserDto userDto) throws Exception {
        try {
            List<String> emptyFields = new ArrayList<>();

            // 입력하지 않은 정보가 있는지 확인
            if (StringUtils.isEmpty(userDto.getVehicle_model())) {
                emptyFields.add("차량 모델");
            }
            if (StringUtils.isEmpty(userDto.getName())) {
                emptyFields.add("이름");
            }
            if (StringUtils.isEmpty(userDto.getApartment_name())) {
                emptyFields.add("아파트 이름");
            }
            if (StringUtils.isEmpty(userDto.getEmail())) {
                emptyFields.add("이메일 아이디");
            }
            if (StringUtils.isEmpty(userDto.getAddress())) {
                emptyFields.add("주소");
            }
            if (StringUtils.isEmpty(userDto.getPhone_number())) {
                emptyFields.add("핸드폰 번호");
            }
            if (StringUtils.isEmpty(userDto.getVehicle_number())) {
                emptyFields.add("차량 번호");
            }
            if (StringUtils.isEmpty(userDto.getVehicle_color())) {
                emptyFields.add("차량 색상");
            }
            if (StringUtils.isEmpty(userDto.getPassword())) {
                emptyFields.add("비밀번호");
            }
            if (StringUtils.isEmpty(userDto.getPw_check())) {
                emptyFields.add("비밀번호 재확인");
            }
            if (userDto.getDevice_id() == null) {
                emptyFields.add("디바이스 ID");
            }

            if (!emptyFields.isEmpty()) {
                String errorMessage = "다음 필드를 입력하세요: " + String.join(", ", emptyFields);
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage);
            }
            // 입력하지 않은 정보가 있는지 확인
            if (userDto.getVehicle_model() == null || userDto.getName() == null || userDto.getApartment_name() == null || userDto.getEmail() == null || userDto.getAddress() == null || userDto.getPhone_number() == null || userDto.getVehicle_number() == null || userDto.getVehicle_color() == null || userDto.getPassword() == null || userDto.getPw_check() == null || userDto.getDevice_id() == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "입력하지 않은 정보가 있는지 확인하세요.");
            }

            // 이메일 중복 확인
            if (memberRepository.findByEmail(userDto.getEmail()).isPresent()) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "회원 정보가 존재합니다.");
            }

            // 비밀번호 일치 확인
            if (!Objects.equals(userDto.getPassword(), userDto.getPw_check())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다.");
            }

            // 차량 중복 확인
            if (vehicleRepository.findByVehicleNumber(userDto.getVehicle_number()).isPresent()) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "등록된 차량입니다.");
            }

            // 디바이스 중복 확인
            if (deviceRepository.findById(userDto.getDevice_id()).isPresent()) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "등록된 디바이스입니다.");
            }

            // 주소 중복 확인
            if (memberRepository.findByAddress(userDto.getAddress()).isPresent()) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "동일한 주소가 이미 등록되었습니다.");
            }

            // 핸드폰번호 중복 확인
            if (memberRepository.findByPhoneNumber(userDto.getPhone_number()).isPresent()) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "등록된 핸드폰번호입니다.");
            }

            //회원가입 로직 수행
            Member member = memberService.register(userDto);

            //회원가입 성공 시 응답 생성
            Message responseMessage = new Message();
            responseMessage.setStatus(StatusEnum.OK);
            responseMessage.setMessage("회원가입 성공");
            responseMessage.setData(member);

            return ResponseEntity.ok(responseMessage);
        } catch (ResponseStatusException ex) {
            // 예외 처리
            Message responseMessage = new Message();
            responseMessage.setStatus(StatusEnum.BAD_REQUEST);
            responseMessage.setMessage(ex.getReason());
            responseMessage.setData(null);

            return ResponseEntity.status(ex.getStatus()).body(responseMessage);
        } catch (Exception e) {
            // 기타 예외 처리
            Message responseMessage = new Message();
            responseMessage.setStatus(StatusEnum.INTERNAL_SERVER_ERROR);
            responseMessage.setMessage("회원가입 중 오류가 발생했습니다.");
            responseMessage.setData(null);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
        }
    }
    @ApiOperation(value="로그인")
    @ApiResponses({
            @ApiResponse(code = 200,message = "OK(로그인 성공)"),
            @ApiResponse(code = 400, message="BAD_REQUEST(이메일과 비밀번호를 확인해주세요)"),
            @ApiResponse(code = 404, message="NOT_FOUND"),
            @ApiResponse(code = 500, message="INTERNAL_SERVER_ERROR")
    })
    @PostMapping("/members/login") //로그인
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto, HttpSession session) throws Exception{
        String email = loginDto.getEmail();
        String password = loginDto.getPassword();
        Member member = memberRepository.findByEmail(email).orElseThrow();
        Vehicle vehicle = vehicleRepository.findByMemberId(member.getMember_id()).orElseThrow(()->new RuntimeException("차량 정보를 찾을 수 없습니다."));
        Message message = new Message();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        if (member.getPassword().equals(password)){
            session.setAttribute("member", member);
            message.setStatus(StatusEnum.OK);
            message.setMessage("로그인 성공");
            ReturnId returnId = new ReturnId(member.getMember_id(), member.getApartment().getApartment_name(), vehicle.getDevice().getDevice_id(), vehicle.getVehicle_id());
            message.setData(returnId);
            return new ResponseEntity<>(message, headers, HttpStatus.OK);
        } else {
            message.setStatus(StatusEnum.NOT_FOUND);
            message.setMessage("이메일과 비밀번호를 확인해주세요");
            return new ResponseEntity<>(message, headers, HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value="로그아웃")
    @ApiResponses({
            @ApiResponse(code = 200,message = "OK(로그아웃 성공)"),
            @ApiResponse(code = 400, message="BAD_REQUEST"),
            @ApiResponse(code = 404, message="NOT_FOUND"),
            @ApiResponse(code = 500, message="INTERNAL_SERVER_ERROR")
    })
    @GetMapping("/members/logout")
    public ResponseEntity<?> logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }
        Message message = new Message();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        message.setStatus(StatusEnum.OK);
        message.setMessage("로그아웃 성공");
        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }

    @ApiOperation(value="회원 정보 조회")
    @ApiImplicitParam(
            name = "id",
            value = "회원 id",
            required = true,
            dataType = "long",
            paramType = "path"
    )
    @ApiResponses({
            @ApiResponse(code = 200,message = "OK(회원 정보 조회 성공)"),
            @ApiResponse(code = 400, message="BAD_REQUEST"),
            @ApiResponse(code = 404, message="NOT_FOUND"),
            @ApiResponse(code = 500, message="INTERNAL_SERVER_ERROR")
    })
    @GetMapping("/members/{id}") //회원 정보 조회
    public ResponseEntity<?> getMemberById(@PathVariable Long id) throws Exception {
        MemberProfileDto member = memberService.getMemberById(id);
        Message message = new Message();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        message.setStatus(StatusEnum.OK);
        message.setMessage("회원 정보 조회 성공");
        message.setData(member);
        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }

    @ApiOperation(value="신고하기",notes="비매너 주민 신고 기능")
    @ApiResponses({
            @ApiResponse(code = 200,message = "OK(신고하기 성공)"),
            @ApiResponse(code = 400, message="BAD_REQUEST"),
            @ApiResponse(code = 404, message="NOT_FOUND"),
            @ApiResponse(code = 500, message="INTERNAL_SERVER_ERROR")
    })
    @PostMapping("/members/complaints") //신고하기
    public ResponseEntity<?> report(@RequestBody ReportDto reportDto) throws Exception {
        Complaint complaint = memberService.report(reportDto);
        Message message = new Message();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        message.setStatus(StatusEnum.OK);
        message.setMessage("신고하기 성공");
        message.setData(complaint);
        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }

    @ApiOperation(value="신고 내용 조회",notes="본인의 신고 당한 내역 조회")
    @ApiImplicitParam(
            name = "id",
            value = "회원 id",
            required = true,
            dataType = "long",
            paramType = "path"
    )
    @ApiResponses({
            @ApiResponse(code = 200,message = "OK(신고 내용 조회 성공)"),
            @ApiResponse(code = 400, message="BAD_REQUEST"),
            @ApiResponse(code = 404, message="NOT_FOUND"),
            @ApiResponse(code = 500, message="INTERNAL_SERVER_ERROR")
    })
    @GetMapping("/members/{id}/complaints") //신고 내용 조회
    public ResponseEntity<?> getReportInfo(@PathVariable Long id) throws Exception {
        GetReportDto report = memberService.getReportInfo(id);
        Message message = new Message();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        message.setStatus(StatusEnum.OK);
        message.setMessage("신고 내용 조회 성공");
        message.setData(report);
        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }
}
