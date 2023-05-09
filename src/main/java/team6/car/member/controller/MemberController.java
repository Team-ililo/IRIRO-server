package team6.car.member.controller;

import lombok.RequiredArgsConstructor;
import team6.car.member.DTO.MemberProfileDto;
import team6.car.member.DTO.ReportDto;
import team6.car.member.domain.Complaint;
import team6.car.member.domain.Member;
import team6.car.member.DTO.UserDto;
import team6.car.member.response.Message;
import team6.car.member.response.StatusEnum;
import team6.car.member.service.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team6.car.member.DTO.LoginDto;

import java.nio.charset.Charset;
import java.util.List;

@RequiredArgsConstructor //의존성 주입
@RestController // annotation 역할 :  spring bean으로 해당 클래스를 spring-container에서 관리
public class MemberController {
    @Autowired
    private final MemberServiceImpl memberService;

    @PostMapping("/members") //회원가입
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

    @PostMapping("/members/login") //로그인
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) throws Exception {
        String email = loginDto.getEmail();
        String password = loginDto.getPassword();
        Member member = memberService.login(email, password);

        Message message = new Message();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        message.setStatus(StatusEnum.OK);
        message.setMessage("로그인 성공");
        message.setData(member);
        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }

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
}
