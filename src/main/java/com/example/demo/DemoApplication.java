package com.example.demo;

import com.example.demo.config.Config;
import com.example.demo.entity.Product;
import com.example.demo.service.EmailService;
import com.example.demo.util.CsvGenerator;
import com.example.demo.util.IdGeneratorController;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.File;

/**
 * Classe principal da aplicação Spring Boot.
 *
 * Esta aplicação realiza as seguintes operações:
 * 1. Realiza uma requisição GET para uma URL configurada no `application.properties`.
 * 2. Pega os dados JSON recebidos em objetos do tipo `Product`.
 * 3. Agrupa os produtos recebidos com base em uma chave única gerada.
 * 4. Gera um arquivo CSV com os dados agrupados.
 * 5. Envia o arquivo gerado como anexo de e-mail para um destinatário configurado.
 */
@SpringBootApplication
public class DemoApplication {

	/**
	 * Classe de configuração que fornece os valores do arquivo de propriedades,
	 * sendo usada para obter a URL e outras configurações relacionadas.
	 */
	@Autowired
	private Config config;

	/**
	 * Serviço responsável pelo envio do e-mail.
	 */
	@Autowired
	private EmailService emailService;

	/**
	 * Metodo principal que inicializa a aplicação Spring Boot.
	 *
	 * @param args Argumentos de linha de comando.
	 */
	public static void main(String[] args) {
		// Inicializa o contexto Spring e executa o método run() da classe DemoApplication
		SpringApplication.run(DemoApplication.class, args).getBean(DemoApplication.class).run();
	}

	/**
	 * Metodo principal de execução da lógica do programa.
	 *
	 * Realiza o processamento completo, desde obter os dados JSON, processá-los em uma estrutura de dados,
	 * criar um arquivo CSV com os resultados e enviá-lo via e-mail.
	 */
	public void run() {
		System.out.println("URL JSON configurada: " + config.getUrl());

		// Configura o controlador de ID com o valor inicial vindo da configuração
		IdGeneratorController idGeneratorController = new IdGeneratorController(config.getIdGenerator());

		// Inicializa o componente do Spring para realizar a requisição REST
		RestTemplate restTemplate = new RestTemplate();

		try {
			// Faz a requisição HTTP GET para a URL configurada
			String jsonResponse = restTemplate.getForObject(config.getUrl(), String.class);

			// Mapeia o JSON retornado em uma lista de objetos `Product`
			ObjectMapper objectMapper = new ObjectMapper();
			List<Product> products = objectMapper.readValue(jsonResponse, new TypeReference<List<Product>>() {});

			// Agrupa os produtos utilizando uma chave única (ID + Potência + Nome do Produto)
			Map<String, Product> groupedProducts = new HashMap<>();
			for (Product product : products) {
				String key = product.getId() + "-" + product.getPower() + "-" + product.getProduct();
				if (groupedProducts.containsKey(key)) {
					// Atualiza a quantidade se já existir no grupo
					Product existingProduct = groupedProducts.get(key);
					existingProduct.setQt(existingProduct.getQt() + 1);
				} else {
					// Define a quantidade inicial para novos produtos no grupo
					product.setQt(1);
					groupedProducts.put(key, product);

					// Gera a quantidade utilizando o ID do gerador
					idGeneratorController.controler_id_generator(product.getPower());
				}
			}

			// Obtém a quantidade total de IDs gerados
			int totalGeneratedIds = idGeneratorController.getTotalGeneratedIds();

			// Gera o arquivo CSV com os dados agrupados
			File csvFile = CsvGenerator.generateProductTable(groupedProducts, idGeneratorController, config.getNameCsv());

			// Gera a mensagem personalizada para o corpo do e-mail
			String originalBody = config.getBodyMailHtml(); // Obtém o texto original definido no properties
			String customizedBody = String.format(originalBody, totalGeneratedIds); // Substitui o placeholder com a quantidade processada

			// Atualiza o corpo do e-mail nas configurações do emailService
			emailService.sendEmail(csvFile, customizedBody);

		} catch (Exception e) {
			// Captura erros e exibe uma mensagem no console para depuração
			System.err.println("Erro ao processar os dados JSON: " + e.getMessage());
		}
	}
}