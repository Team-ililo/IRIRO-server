package team6.car.member.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
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
            @ApiResponse(code = 500, message="INTERNAL_SERVER_ERROR")
    })
    @PostMapping("/members")//회원가입
    public ResponseEntity<?> register(@RequestBody UserDto userDto) throws Exception {
        Member member = memberService.register(userDto);
        Message message = new Message();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        message.setStatus(StatusEnum.OK);
        message.setMessage("회원가입 성공");
        message.setData(member);
        return new ResponseEntity<>(message, headers, HttpStatus.OK);
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
