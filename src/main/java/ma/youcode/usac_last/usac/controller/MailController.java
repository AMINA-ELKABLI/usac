package ma.youcode.usac_last.usac.controller;

import jakarta.validation.Valid;
import ma.youcode.usac_last.usac.model.entities.MailStructure;
import ma.youcode.usac_last.usac.service.impl.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mail")
public class MailController {
    @Autowired
    private EmailService emailService;
    @PostMapping("/send/{mail}")
    public ResponseEntity<String> sendMail(@PathVariable("mail") String mail, @Valid @RequestBody MailStructure mailStructure) {
        try {
            emailService.sendSimpleEmail(mail, mailStructure);
            return ResponseEntity.ok("Successfully sent the mail");
        } catch (Exception e) {
            // Log l'exception ici avec votre logger préféré
            return ResponseEntity.internalServerError().body("Failed to send the mail");
        }
    }
}
