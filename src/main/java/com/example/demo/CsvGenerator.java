package com.example.demo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class CsvGenerator {
    /**
     * Gera um arquivo CSV com uma tabela baseada em produtos agrupados.
     *
     * @param groupedProducts Produtos agrupados com base no ID único.
     * @param idGeneratorController Controlador responsável por gerar o ID do gerador.
     * @param fileName Nome do arquivo CSV a ser gerado.
     * @return Retorna um objeto File representando o arquivo gerado.
     * @throws IOException Exceção no caso de erro ao gerar o arquivo.
     */
    public static File generateProductTable(Map<String, Product> groupedProducts,
                                            IdGeneratorController idGeneratorController,
                                            String fileName) throws IOException {
        // Criar arquivo
        File csvFile = new File(fileName);
        try (FileWriter writer = new FileWriter(csvFile)) {
            // Escrever cabeçalho da tabela
            writer.append("ID_GERADOR,Potencia do Gerador,ID_Produto,Nome_do_produto,Quantidade\n");

            // Populando a tabela com dados
            for (Product product : groupedProducts.values()) {
                int geradorId = idGeneratorController.controler_id_generator(product.getPower()); // Obter ID do gerador
                String linha = String.format("%d,%d,%d,%s,%d\n",
                        geradorId,
                        product.getPower(),
                        product.getId(),
                        product.getProduct(),
                        product.getQt()
                );
                writer.append(linha);
            }

            System.out.println("Tabela CSV gerada com sucesso: " + csvFile.getAbsolutePath());
        }

        return csvFile; // Retornar o arquivo criado
    }
}