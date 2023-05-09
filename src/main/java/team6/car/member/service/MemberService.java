package team6.car.member.service;

import team6.car.member.DTO.LoginDto;
import team6.car.member.DTO.MemberProfileDto;
import team6.car.member.DTO.ReportDto;
import team6.car.member.domain.Complaint;
import team6.car.member.domain.Member;
import team6.car.member.DTO.UserDto;

import java.util.List;

public interface MemberService {
    Member register(UserDto userDto) throws Exception;
    Member login(String email, String password) throws Exception;
    List<MemberProfileDto> getMemberById(Long id) throws Exception;
    Complaint report(ReportDto reportDto) throws Exception;
}
