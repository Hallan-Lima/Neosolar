package com.example.demo;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;


@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender; // Injeta o JavaMailSender configurado com o application.properties

    @Autowired
    private EmailConfig emailConfig;

    /**
     * Envia um e-mail simples.
     */
    public void sendEmail(File csvFile) {
        if (csvFile == null || !csvFile.exists()) {
            throw new IllegalArgumentException("Arquivo CSV inexistente ou inválido.");
        }

        try {
            // Ler o arquivo como um array de bytes
            byte[] fileContent = Files.readAllBytes(csvFile.toPath());
            ByteArrayResource csvResource = new ByteArrayResource(fileContent);

            // Criar e configurar a mensagem de e-mail
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(emailConfig.getTo());
            helper.setSubject(emailConfig.getSubject());
            helper.setText(emailConfig.getBody(), false); // Corpo do e-mail

            // Anexar o CSV como recurso
            helper.addAttachment("dados.csv", csvResource);

            // Enviar o e-mail
            mailSender.send(message);
            System.out.println("E-mail com anexo enviado com sucesso!");

        } catch (MessagingException e) {
            System.err.println("Erro ao enviar o e-mail: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}