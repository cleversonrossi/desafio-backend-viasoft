package br.com.viasoft.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EmailOciDTO {

    @NotBlank(message = "O campo recipientEmail não deve estar em branco")
    @Email(message = "O campo recipientEmail deve ser um e-mail válido")
    @Size(max = 40, message = "O campo recipientEmail deve ter no máximo 40 caracteres")
    private String recipientEmail;

    @NotBlank(message = "O campo recipientName não deve estar em branco")
    @Size(max = 50, message = "O campo recipientName deve ter no máximo 50 caracteres")
    private String recipientName;

    @NotBlank(message = "O campo senderEmail não deve estar em branco")
    @Email(message = "O campo senderEmail deve ser um e-mail válido")
    @Size(max = 40, message = "O campo senderEmail deve ter no máximo 40 caracteres")
    private String senderEmail;

    @NotBlank(message = "O campo subject não deve estar em branco")
    @Size(max = 100, message = "O campo subject deve ter no máximo 100 caracteres")
    private String subject;

    @NotBlank(message = "O campo body não deve estar em branco")
    @Size(max = 250, message = "O campo body deve ter no máximo 250 caracteres")
    private String body;
}
