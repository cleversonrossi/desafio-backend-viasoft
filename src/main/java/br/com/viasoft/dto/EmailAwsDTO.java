package br.com.viasoft.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EmailAwsDTO {

    @NotBlank(message = "O campo recipient não deve estar em branco")
    @Email(message = "O campo recipient deve ser um e-mail válido")
    @Size(max = 45, message = "O campo recipient deve ter no máximo 45 caracteres")
    private String recipient;

    @NotBlank(message = "O campo recipientName não deve estar em branco")
    @Size(max = 60, message = "O campo recipientName deve ter no máximo 60 caracteres")
    private String recipientName;

    @NotBlank(message = "O campo sender não deve estar em branco")
    @Email(message = "O campo sender deve ser um e-mail válido")
    @Size(max = 45, message = "O campo sender deve ter no máximo 45 caracteres")
    private String sender;

    @NotBlank(message = "O campo subject não deve estar em branco")
    @Size(max = 120, message = "O campo subject deve ter no máximo 120 caracteres")
    private String subject;

    @NotBlank(message = "O campo content não deve estar em branco")
    @Size(max = 256, message = "O campo content deve ter no máximo 256 caracteres")
    private String content;
}
