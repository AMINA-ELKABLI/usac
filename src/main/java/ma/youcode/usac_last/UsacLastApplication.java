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


    }


