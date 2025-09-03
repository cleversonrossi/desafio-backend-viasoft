package br.com.viasoft.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.viasoft.dto.EmailAwsDTO;
import br.com.viasoft.dto.EmailOciDTO;
import br.com.viasoft.dto.EmailRequestDTO;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;

import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Value("${mail.integracao}")
    private String integracao;

    private final ObjectMapper objectMapper;
    private final Validator validator;

    public EmailService(ObjectMapper objectMapper, Validator validator) {
        this.objectMapper = objectMapper;
        this.validator = validator;
    }

    public void processarEmail(EmailRequestDTO dto) throws Exception {
        if ("AWS".equalsIgnoreCase(integracao)) {
            validarEImprimir(toAws(dto));
        } else if ("OCI".equalsIgnoreCase(integracao)) {
            validarEImprimir(toOci(dto));
        } else {
            throw new IllegalArgumentException("Configuração inválida em mail.integracao");
        }
    }

    private <T> void validarEImprimir(T obj) throws Exception {
        Set<ConstraintViolation<T>> violations = validator.validate(obj);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        logger.info("Objeto serializado:");
        logger.info(json);
    }

    private EmailAwsDTO toAws(EmailRequestDTO dto) {
        EmailAwsDTO aws = new EmailAwsDTO();
        aws.setRecipient(dto.getDestinatarioEmail());
        aws.setRecipientName(dto.getDestinatarioNome());
        aws.setSender(dto.getRemetenteEmail());
        aws.setSubject(dto.getAssunto());
        aws.setContent(dto.getConteudo());
        return aws;
    }

    private EmailOciDTO toOci(EmailRequestDTO dto) {
        EmailOciDTO oci = new EmailOciDTO();
        oci.setRecipientEmail(dto.getDestinatarioEmail());
        oci.setRecipientName(dto.getDestinatarioNome());
        oci.setSenderEmail(dto.getRemetenteEmail());
        oci.setSubject(dto.getAssunto());
        oci.setBody(dto.getConteudo());
        return oci;
    }
}