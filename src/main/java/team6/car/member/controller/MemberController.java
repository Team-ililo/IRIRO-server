package team6.car.member.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor //의존성 주입
@RestController // annotation 역할 :  spring bean으로 해당 클래스를 spring-container에서 관리
public class MemberController {
    private final MemberServiceImpl memberService;
    private final MemberRepository memberRepository;


    @ApiOperation(value="회원가입")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "name",
                    value = " 이름",
                    required = true,
                    dataType = "string"

            ),
            @ApiImplicitParam(
                    name = "phone_number",
                    value = " 핸드폰 번호",
                    required = true,
                    dataType = "string"
            ),
            @ApiImplicitParam(
                    name = "vehicle_number",
                    value = " 차량 번호",
                    required = true,
                    dataType = "string"
            ),
            @ApiImplicitParam(
                    name = "vehicle_model",
                    value = " 차량 모델명",
                    required = true,
                    dataType = "string"
            ),
            @ApiImplicitParam(
                    name = "vehicle_color",
                    value = " 차량 색상",
                    required = true,
                    dataType = "string"
            ),
            @ApiImplicitParam(
                    name = "apartment_name",
                    value = " 아파트 이름",
                    required = true,
                    dataType = "string"
            ),
            @ApiImplicitParam(
                    name = "address",
                    value = " 동/호수",
                    required = true,
                    dataType = "string"
            ),
            @ApiImplicitParam(
                    name = "email",
                    value = " 이메일 아이디",
                    required = true,
                    dataType = "string"
            ),
            @ApiImplicitParam(
                    name = "password",
                    value = " 비밀번호",
                    required = true,
                    dataType = "string"
            ),
            @ApiImplicitParam(
                    name = "pw_check",
                    value = " 비밀번호 재입력",
                    required = true,
                    dataType = "string"
            ),
            @ApiImplicitParam(
                    name = "device_id",
                    value = " 디바이스 ID",
                    required = true,
                    dataType = "long"
            ),
    })
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
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "email",
                    value = " 이메일 아이디",
                    required = true,
                    dataType = "string"
            ),
            @ApiImplicitParam(
                    name = "password",
                    value = "비밀번호",
                    required = true,
                    dataType = "string"
            )
    })
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
        Message message = new Message();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        if (member.getPassword().equals(password)){
            session.setAttribute("member", member);
            message.setStatus(StatusEnum.OK);
            message.setMessage("로그인 성공");
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
        List<MemberProfileDto> member = memberService.getMemberById(id);
        Message message = new Message();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        message.setStatus(StatusEnum.OK);
        message.setMessage("회원 정보 조회 성공");
        message.setData(member);
        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }

    @ApiOperation(value="신고하기",notes="비매너 주민 신고 기능")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "id",
                    value = "회원 id",
                    required = true,
                    dataType = "long"
            ),
            @ApiImplicitParam(
                    name = "complaint_contents",
                    value = " 신고 내용",
                    required = true,
                    dataType = "string"
            ),
            @ApiImplicitParam(
                    name = "vehicle_number",
                    value = "신고 대상의 차량 번호",
                    required = true,
                    dataType = "string"
            )
    })
    @ApiResponses({
            @ApiResponse(code = 200,message = "OK(신고하기 성공)"),
            @ApiResponse(code = 400, message="BAD_REQUEST"),
            @ApiResponse(code = 404, message="NOT_FOUND"),
            @ApiResponse(code = 500, message="INTERNAL_SERVER_ERROR")
    })
    @PostMapping("/members/{id}/complaints") //신고하기
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
        List<getReportDto> report = memberService.getReportInfo(id);
        Message message = new Message();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        message.setStatus(StatusEnum.OK);
        message.setMessage("신고 내용 조회 성공");
        message.setData(report);
        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }
}
