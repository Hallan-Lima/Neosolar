package com.example.demo.service;

import com.example.demo.config.EmailConfig;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Classe de serviço responsável pelo envio de e-mails na aplicação.
 *
 * Essa classe utiliza o JavaMailSender, configurado em conjunto com a classe
 * {@link EmailConfig}, para enviar e-mails com suporte à inclusão de anexos.
 */
@Service
public class EmailService {

    /**
     * Componente do Spring responsável por enviar e-mails.
     */
    @Autowired
    private JavaMailSender mailSender;

    /**
     * Classe de configuração que fornece detalhes dos parâmetros de envio de e-mail,
     * como destinatário, assunto e corpo da mensagem.
     */
    @Autowired
    private EmailConfig emailConfig;

    /**
     * Envia um e-mail com um anexo no formato CSV.
     *
     * Este metodo valida a existência do arquivo anexado, lê seu conteúdo,
     * cria a mensagem de e-mail com os dados especificados na configuração e a envia.
     *
     * @param csvFile Arquivo CSV que será anexado ao e-mail.
     *                Deve ser um arquivo existente e válido.
     * @throws IllegalArgumentException se o arquivo CSV for nulo ou inexistente.
     */
    public void sendEmail(File csvFile, String body) {
        if (body == null) {
            body = emailConfig.getSubject();
        }
        // Valida se o arquivo é válido
        if (csvFile == null || !csvFile.exists()) {
            throw new IllegalArgumentException("Arquivo CSV inexistente ou inválido.");
        }

        try {
            // Lê o conteúdo do arquivo CSV como bytes
            byte[] fileContent = Files.readAllBytes(csvFile.toPath());
            ByteArrayResource csvResource = new ByteArrayResource(fileContent);

            // Cria a mensagem de e-mail
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            // Obtemos a data e hora atuais
            LocalDateTime now = LocalDateTime.now();
            String formattedDateTime = now.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
            String dynamicSubject = emailConfig.getSubject() +" - " + formattedDateTime;

            // Preenche os campos do e-mail com as informações configuradas
            helper.setTo(emailConfig.getTo()); // Define o destinatário
            helper.setSubject(dynamicSubject); // Define o assunto
            helper.setText(body, true); // Define o corpo do e-mail (texto simples)

            // Anexa o arquivo CSV
            helper.addAttachment(emailConfig.getNameCsv(), csvResource);

            // Envia o e-mail
            mailSender.send(message);
            System.out.println("E-mail com anexo enviado com sucesso!");

        } catch (MessagingException e) {
            // Trata erros relacionados ao envio de e-mails
            System.err.println("Erro ao enviar o e-mail: " + e.getMessage());
        } catch (IOException e) {
            // Trata erros na leitura do arquivo
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}