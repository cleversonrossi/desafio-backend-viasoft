package br.com.viasoft.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.viasoft.dto.EmailRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = "mail.integracao=AWS")
class EmailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private EmailRequestDTO validRequest;

    @BeforeEach
    void setup() {
        validRequest = new EmailRequestDTO();
        validRequest.setDestinatarioEmail("destinatario@teste.com");
        validRequest.setDestinatarioNome("Destinatario");
        validRequest.setRemetenteEmail("remetente@teste.com");
        validRequest.setAssunto("Assunto válido");
        validRequest.setConteudo("Mensagem válida");
    }

    @Test
    void deveRetornar204QuandoRequisicaoValida() throws Exception {
        mockMvc.perform(post("/api/email")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validRequest)))
                .andExpect(status().isNoContent());
    }

    @Test
    void deveRetornar400QuandoAssuntoMaiorQue120() throws Exception {
        validRequest.setAssunto("A".repeat(121)); // 121 chars

        mockMvc.perform(post("/api/email")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.assunto").value("O campo assunto deve ter no máximo 120 caracteres"));
    }

    @Test
    void deveRetornar400QuandoRecipientMaiorQue45() throws Exception {
        String emailGrande = "a".repeat(38) + "@teste.com";
        validRequest.setDestinatarioEmail(emailGrande);

        mockMvc.perform(post("/api/email")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.destinatarioEmail")
                    .value("O campo destinatarioEmail deve ter no máximo 45 caracteres"));
    }

    @Test
    void deveRetornar400ComMultiplosErrosDeValidacao() throws Exception {
        validRequest.setDestinatarioEmail("um_email_muito_longo_que_passou_do_limite@teste.com");
        validRequest.setAssunto("B".repeat(130));

        mockMvc.perform(post("/api/email")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.recipient").value("O campo recipient deve ter no máximo 45 caracteres"))
                .andExpect(jsonPath("$.subject").value("O campo subject deve ter no máximo 120 caracteres"));
    }
}