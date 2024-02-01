package ma.youcode.usac_last;

import ma.youcode.usac_last.security.auth.AuthenticationService;
import ma.youcode.usac_last.security.auth.RegisterRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static ma.youcode.usac_last.security.user.Role.ADMIN;

@SpringBootApplication
public class UsacLastApplication {

    public static void main(String[] args) {

        SpringApplication.run(UsacLastApplication.class, args);
    }

        @Bean
        public CommandLineRunner commandLineRunner(
                AuthenticationService service
	) {
            return args -> {
                var admin = RegisterRequest.builder()
                        .firstname("Amina")
                        .lastname("Elkabli")
                        .email("amina.elkabli1@gmail.com")
                        .password("password")
                        .role(ADMIN)
                        .build();
                System.out.println("Admin token: " + service.register(admin).getToken());



            };
        }
    }


