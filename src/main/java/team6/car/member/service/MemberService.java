package team6.car.member.service;

import team6.car.member.DTO.LoginDto;
import team6.car.member.domain.Member;
import team6.car.member.DTO.UserDto;

public interface MemberService {
    Member register(UserDto userDto) throws Exception;
    Member login(String email, String password) throws Exception;
}
