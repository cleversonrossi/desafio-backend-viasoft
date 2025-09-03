package br.com.viasoft.controller;

import br.com.viasoft.dto.EmailRequestDTO;
import br.com.viasoft.service.EmailService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    private final EmailService service;

    public EmailController(EmailService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> enviarEmail(@RequestBody @Valid EmailRequestDTO dto) {
        try {
            service.processarEmail(dto);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    } 
}
