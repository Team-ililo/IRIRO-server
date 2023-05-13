package team6.car;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication//@ SpringBootApplication 어노테이션은 @ComponentScan이라는 어노테이션을 포함하고 있다.
//@ComponentScan 어노테이션은 해당 클래스와 같은 Level의 패키지의 하위 클래스들에 대해 컴포넌트 스캔을 진행하면서 객체를 스프링 빈에 등록한다.
public class CarApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarApplication.class, args);
	}
	
}
