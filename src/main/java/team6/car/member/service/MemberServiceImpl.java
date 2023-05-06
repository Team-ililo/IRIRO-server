package team6.car.member.service;

import lombok.RequiredArgsConstructor;
import team6.car.apartment.domain.Apartment;
import team6.car.member.domain.Member;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team6.car.member.DTO.UserDto;
import team6.car.device.domain.Device;
import team6.car.vehicle.domain.Vehicle;
import team6.car.apartment.repository.ApartmentRepository;
import team6.car.device.repository.DeviceRepository;
import team6.car.vehicle.repository.VehicleRepository;
import team6.car.member.repository.MemberRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service //서비스 스프링 빈으로 등록
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final ApartmentRepository apartmentRepository;
    private final DeviceRepository deviceRepository;
    private final VehicleRepository vehicleRepository;

    /**회원 가입**/
    @Override
    public Member register(UserDto userDto) throws Exception {
        /**
         * 이미 존재하는 이메일로 회원가입 요청 시 -> 예외 발생
         */
        if(memberRepository.findByEmail(userDto.getEmail()).isPresent()){
            throw new Exception("존재하는 회원입니다.");
        }
        //비밀번호 같은지 확인
        if(!Objects.equals(userDto.getPassword(), userDto.getPw_check())){
            throw new Exception("비밀번호가 틀립니다.");
        }

        //아파트 정보 저장
        Apartment apartment = new Apartment();
        apartment.setApartment_name(userDto.getApartment_name());
        apartmentRepository.save(apartment);

        //사용자 정보 저장
        Member member = new Member();
        member.setApartment(apartment); //FK 설정
        member.setName(userDto.getName());
        member.setPhone_number(userDto.getPhone_number());
        member.setEmail(userDto.getEmail());
        member.setPassword(userDto.getPassword());
        member.setAddress(userDto.getAddress());
        //validateDuplicateMember(member); //중복회원검사
        memberRepository.save(member);

        //디바이스 정보 저장
        Device device = new Device();
        device.setDevice_id(userDto.getDevice_id());
        deviceRepository.save(device);

        //차량 정보 저장
        Vehicle vehicle = new Vehicle();
        vehicle.setMember(member); //FK
        vehicle.setDevice(device); //FK
        vehicle.setVehicle_number(userDto.getVehicle_number());
        vehicle.setVehicle_model(userDto.getVehicle_model());
        vehicle.setVehicle_color(userDto.getVehicle_color());
        vehicleRepository.save(vehicle);

        return member;
    }

    /**로그인**/
    public Member login(String email, String password) throws Exception {
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new Exception("존재하지 않는 회원입니다."));
        //비밀번호 같은지 확인
        if(!Objects.equals(password, member.getPassword())){
            throw new Exception("아이디 또는 비밀번호를 확인해주세요.");
        }
        // 사용자가 사는 아파트의 정보를 가져온다.
        Apartment apartment = apartmentRepository.findById(member.getApartment().getApartment_id())
                .orElseThrow(() -> new RuntimeException("아파트 정보를 찾지 못했습니다."));

        return member;
    }


    /**전체 회원 조회**/
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

    /**회원 id 로 정보 조회**//*
    public Member getMemberById(Long id) {
        Optional<Member> member = memberRepository.findById(id);
        if(member.isPresent()) {
            return member.get();
        } else {
            throw new MemberNotFoundException("Member not found with id: " + id);
        }
    }*/
}
