package br.com.viasoft.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EmailRequestDTO {

    @NotBlank(message = "O campo destinatarioEmail não deve estar em branco")
    @Email(message = "O campo destinatarioEmail deve ser um e-mail válido")
    private String destinatarioEmail;

    @NotBlank(message = "O campo destinatarioNome não deve estar em branco")
    private String destinatarioNome;

    @NotBlank(message = "O campo remetenteEmail não deve estar em branco")
    @Email(message = "O campo remetenteEmail deve ser um e-mail válido")
    private String remetenteEmail;

    @NotBlank(message = "O campo assunto não deve estar em branco")
    @Size(max = 120, message = "O campo assunto deve ter no máximo 120 caracteres")
    private String assunto;

    @NotBlank(message = "O campo conteudo não deve estar em branco")
    @Size(max = 256, message = "O campo conteudo deve ter no máximo 256 caracteres")
    private String conteudo;

    // getters e setters
    public String getDestinatarioEmail() { return destinatarioEmail; }
    public void setDestinatarioEmail(String e) { this.destinatarioEmail = e; }

    public String getDestinatarioNome() { return destinatarioNome; }
    public void setDestinatarioNome(String n) { this.destinatarioNome = n; }

    public String getRemetenteEmail() { return remetenteEmail; }
    public void setRemetenteEmail(String r) { this.remetenteEmail = r; }

    public String getAssunto() { return assunto; }
    public void setAssunto(String a) { this.assunto = a; }

    public String getConteudo() { return conteudo; }
    public void setConteudo(String c) { this.conteudo = c; }
}
