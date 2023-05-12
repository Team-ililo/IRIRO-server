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

@Api(tags = {"MemberController"})
@RequiredArgsConstructor //의존성 주입
@RestController // annotation 역할 :  spring bean으로 해당 클래스를 spring-container에서 관리
public class MemberController {
    private final MemberServiceImpl memberService;
    private final MemberRepository memberRepository;

    @ApiOperation(value = "회원가입", notes = "회원가입을 한다")
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
    @ApiOperation(value = "로그인", notes = "로그인을 한다")
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

    @ApiOperation(value="로그아웃", notes="로그아웃을 한다")
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

    @ApiOperation(value = "내 정보 조회", notes = "내 정보를 조회한다")
    @ApiImplicitParam(name = "member_id", value = "회원 아이디")
    @GetMapping("/members/{member_id}") //회원 정보 조회
    public ResponseEntity<?> getMemberById(@PathVariable Long member_id) throws Exception {
        List<MemberProfileDto> member = memberService.getMemberById(member_id);
        Message message = new Message();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        message.setStatus(StatusEnum.OK);
        message.setMessage("회원 정보 조회 성공");
        message.setData(member);
        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }

    @ApiOperation(value = "신고하기", notes = "다른 차량을 신고한다")
    @PostMapping("/members/{member_id}/complaints") //신고하기
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

    @ApiOperation(value = "신고 내용 조회", notes = "나의 신고 당한 내역을 조회한다")
    @ApiImplicitParam(name = "member_id", value = "회원 아이디")
    @GetMapping("/members/{member_id}/complaints") //신고 내용 조회
    public ResponseEntity<?> getReportInfo(@PathVariable Long member_id) throws Exception {
        List<getReportDto> report = memberService.getReportInfo(member_id);
        Message message = new Message();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        message.setStatus(StatusEnum.OK);
        message.setMessage("신고 내용 조회 성공");
        message.setData(report);
        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }
}
