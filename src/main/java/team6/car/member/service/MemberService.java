package team6.car.member.service;

import team6.car.member.DTO.*;
import team6.car.member.domain.Complaint;
import team6.car.member.domain.Member;

import java.util.List;

public interface MemberService {
    Member register(UserDto userDto) throws Exception;
    Member login(String email, String password) throws Exception;
    List<MemberProfileDto> getMemberById(Long id) throws Exception;
    Complaint report(ReportDto reportDto) throws Exception;
    List<getReportDto> getReportInfo(Long id) throws Exception;
}
