package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Classe de configuração responsável por mapear as propriedades relacionadas ao sistema de email,
 * configuradas no arquivo `application.properties` com o prefixo `spring.mail`.
 *
 * Essas configurações são utilizadas para envio de emails com suporte a autenticação
 * e detalhes como assunto, corpo, destinatários e anexos.
 */
@Component
@ConfigurationProperties(prefix = "spring.mail") // Prefixo para o mapeamento das propriedades
public class EmailConfig {

    /**
     * Endereço do servidor SMTP responsável pelo envio de email.
     */
    private String host;

    /**
     * Endereço de email do destinatário.
     */
    private String to;

    /**
     * Assunto do email enviado.
     */
    private String subject;

    /**
     * Corpo da mensagem de email.
     */
    private String body;

    /**
     * Porta do servidor SMTP (ex.: 587 para conexões STARTTLS).
     */
    private int port;

    /**
     * Nome de usuário utilizado para autenticação no servidor SMTP.
     */
    private String username;

    /**
     * Senha utilizada para autenticação no servidor SMTP.
     */
    private String password;

    /**
     * Nome do arquivo CSV que será anexado ao email.
     */
    private String nameCsv;

    /**
     * Indica se a autenticação no servidor SMTP é necessária.
     */
    private boolean auth;

    /**
     * Indica se o uso do protocolo STARTTLS está habilitado para conexões seguras.
     */
    private boolean starttlsEnabled;

    /* Getters e Setters */

    /**
     * Recupera o endereço do servidor SMTP.
     *
     * @return Host (endereço) do servidor SMTP.
     */
    public String getHost() {
        return host;
    }

    /**
     * Define o endereço do servidor SMTP.
     *
     * @param host Host (endereço) do servidor SMTP.
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * Recupera o endereço de email do destinatário.
     *
     * @return Endereço de email do destinatário.
     */
    public String getTo() {
        return to;
    }

    /**
     * Define o endereço de email do destinatário.
     *
     * @param to Endereço de email do destinatário.
     */
    public void setTo(String to) {
        this.to = to;
    }

    /**
     * Recupera o assunto do email.
     *
     * @return Assunto do email.
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Define o assunto do email.
     *
     * @param subject Assunto do email a ser enviado.
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Recupera o corpo do email.
     *
     * @return Conteúdo do corpo do email.
     */
    public String getBody() {
        return body;
    }

    /**
     * Define o corpo do email.
     *
     * @param body Conteúdo do corpo do email.
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * Recupera a porta do servidor SMTP.
     *
     * @return Porta usada pelo servidor SMTP.
     */
    public int getPort() {
        return port;
    }

    /**
     * Define a porta do servidor SMTP.
     *
     * @param port Porta usada pelo servidor SMTP.
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * Recupera o nome de usuário para autenticação no servidor SMTP.
     *
     * @return Nome de usuário configurado.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Define o nome de usuário para autenticação no servidor SMTP.
     *
     * @param username Nome de usuário para autenticação.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Recupera a senha para autenticação no servidor SMTP.
     *
     * @return Senha usada para autenticação.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Define a senha para autenticação no servidor SMTP.
     *
     * @param password Senha usada para autenticação.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Recupera o nome do arquivo CSV que será anexado ao email.
     *
     * @return Nome do arquivo CSV.
     */
    public String getNameCsv() {
        return nameCsv;
    }

    /**
     * Define o nome do arquivo CSV que será anexado ao email.
     *
     * @param nameCsv Nome do arquivo CSV.
     */
    public void setNameCsv(String nameCsv) {
        this.nameCsv = nameCsv;
    }

    /**
     * Indica se a autenticação no servidor SMTP está habilitada.
     *
     * @return {@code true} se a autenticação estiver habilitada; {@code false} caso contrário.
     */
    public boolean isAuth() {
        return auth;
    }

    /**
     * Define se a autenticação no servidor SMTP deve estar habilitada.
     *
     * @param auth {@code true} para habilitar a autenticação; {@code false} caso contrário.
     */
    public void setAuth(boolean auth) {
        this.auth = auth;
    }

    /**
     * Indica se o protocolo STARTTLS está habilitado.
     *
     * @return {@code true} se o STARTTLS estiver habilitado; {@code false} caso contrário.
     */
    public boolean isStarttlsEnabled() {
        return starttlsEnabled;
    }

    /**
     * Define se o protocolo STARTTLS deve ser habilitado.
     *
     * @param starttlsEnabled {@code true} para habilitar o STARTTLS; {@code false} caso contrário.
     */
    public void setStarttlsEnabled(boolean starttlsEnabled) {
        this.starttlsEnabled = starttlsEnabled;
    }
}