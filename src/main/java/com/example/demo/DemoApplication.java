package com.example.demo;

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
import java.io.FileWriter;
import java.io.IOException;



@SpringBootApplication
public class DemoApplication {

	@Autowired
	private Config config; // Mapeia app.json.*

	@Autowired
	private EmailService emailService; // Injeta a classe de serviço de envio de e-mail


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args).getBean(DemoApplication.class).run();
	}

	public void run() {
		System.out.println("URL JSON configurada: " + config.getUrl());

		// Criando o controlador de ID do Gerador
		IdGeneratorController idGeneratorController = new IdGeneratorController(config.getIdGenerator());

		// Fazer a requisição GET e obter os dados JSON
		RestTemplate restTemplate = new RestTemplate();
		try {
			String jsonResponse = restTemplate.getForObject(config.getUrl(), String.class);

			// Desserializar o JSON para uma lista de objetos Produto
			ObjectMapper objectMapper = new ObjectMapper();
			List<Product> products = objectMapper.readValue(jsonResponse, new TypeReference<List<Product>>() {
			});

			// Agrupar os itens com base na chave única
			Map<String, Product> groupedProducts = new HashMap<>();
			for (Product product : products) {
				String key = product.getId() + "-" + product.getPower() + "-" + product.getProduct();
				if (groupedProducts.containsKey(key)) {
					Product existingProduct = groupedProducts.get(key);
					existingProduct.setQt(existingProduct.getQt() + 1);
				} else {
					product.setQt(1);
					groupedProducts.put(key, product);
				}
			}

			// Gerar a tabela em CSV
			File csvFile = CsvGenerator.generateProductTable(groupedProducts, idGeneratorController, "tabela_geradores.csv");

			// Enviar e-mail com o CSV como anexo
			emailService.sendEmail(csvFile);

		} catch (Exception e) {
			System.err.println("Erro ao processar os dados JSON: " + e.getMessage());
		}
	}
}