package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Classe responsável por mapear as propriedades de configuração do arquivo
 * `application.properties` com prefixo `app.json`.
 *
 * As propriedades configuradas aqui podem ser usadas para configurar a URL de origem
 * de dados e o parâmetro inicial do ID do gerador.
 */
@Component
@ConfigurationProperties(prefix = "app.json") // Prefixo no arquivo de propriedades que será mapeado nesta classe
public class Config {

    /**
     * URL externa para a qual a aplicação fará a requisição (por exemplo, para um serviço REST).
     */
    private String url;

    /**
     * Valor inicial do identificador do gerador de IDs (usado como base para geração de identificadores únicos).
     */
    private int idGenerator;

    /**
     * Mensagem personalizada a ser enviada no email.
     */
    private String bodyMailHtml;

    /**
     * Nome do arquivo Csv.
     */
    private String nameCsv;

    /**
     * Recupera o texto da mensagem.
     *
     * @return Mensagem a ser enviada no corpo do email.
     */
    public String getBodyMailHtml() {
        return bodyMailHtml;
    }

    /**
     * Define o texto da mensagem.
     *
     * @param bodyMailHtml Mensagem a ser enviada no corpo do email.
     */
    public void setBodyMailHtml(String bodyMailHtml) {
        this.bodyMailHtml = bodyMailHtml;
    }

    /**
     * Recupera o valor do gerador de IDs configurado.
     *
     * @return Valor inicial do ID do gerador.
     */
    public int getIdGenerator() {
        return idGenerator;
    }

    /**
     * Define o valor inicial do gerador de IDs.
     *
     * @param idGenerator Valor inicial para o gerador de identificadores.
     */
    public void setIdGenerator(int idGenerator) {
        this.idGenerator = idGenerator;
    }

    /**
     * Recupera a URL configurada para requisições externas.
     *
     * @return URL definida no arquivo de configuração.
     */
    public String getUrl() {
        return url;
    }


    /**
     * Define a URL para requisições externas.
     *
     * @param url URL para ser configurada.
     */
    public void setUrl(String url) {
        this.url = url;
    }


    /**
     * Recupera o nome do arquivo CSV configurado.
     *
     * @return Nome do CSV definida no arquivo de configuração.
     */
    public String getNameCsv() {
        return nameCsv;
    }

    /**
     * Define o nome do CSV.
     *
     * @param nameCsv Nome do CSV para ser configurada.
     */
    public void setNameCsv(String nameCsv) {
        this.nameCsv = nameCsv;
    }
}