package com.example.AppPrototipo;

import com.example.AppPrototipo.business.UserMgr;
import com.example.AppPrototipo.business.entities.User;
import com.example.AppPrototipo.business.exceptions.InvalidInformation;
import com.example.AppPrototipo.business.exceptions.UserAlreadyExsists;
import com.example.AppPrototipo.persistence.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AppPrototipoApplicationTests {

	@Test
	void contextLoads() {

		/*UserMgr userMgr = new UserMgr();

		try {
			userMgr.addUser("Luis", "Luis.125@gmail.com");
		} catch (InvalidInformation | UserAlreadyExsists invalidInformation) {
			System.out.println("Error");
		}

		try {
			userMgr.addUser("Luis", "Luis.125@gmail.com");
			System.out.println("Error");
		} catch (InvalidInformation | UserAlreadyExsists invalidInformation) {
			System.out.println("OK");
		}

		try {
			userMgr.addUser("Juan", "Luis.125@gmail.com");
			System.out.println("Error");
		} catch (InvalidInformation | UserAlreadyExsists invalidInformation) {
			System.out.println("OK");
		}

		try {
			userMgr.addUser("Pepe", "Pepe.125@gmail.com");
			System.out.println("Error");
		} catch (InvalidInformation | UserAlreadyExsists invalidInformation) {
			System.out.println("OK");
		}
*/



	}

}
