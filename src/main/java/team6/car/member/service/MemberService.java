package team6.car.member.service;

import team6.car.member.DTO.*;
import team6.car.member.domain.Complaint;
import team6.car.member.domain.Member;

public interface MemberService {
    Member register(UserDto userDto) throws Exception;
    /*Member login(String email, String password) throws Exception;*/
    MemberProfileDto getMemberById(Long id) throws Exception;
    Member updateNumberOfComplaints(Long memberId);
    Complaint report(ReportDto reportDto) throws Exception;
    GetReportDto getReportInfo(Long id) throws Exception;
}
