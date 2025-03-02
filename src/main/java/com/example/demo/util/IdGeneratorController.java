package com.example.demo.util;

import java.util.HashMap;
import java.util.Map;


public class IdGeneratorController {


    private int currentIdGenerator; // ID inicial passado via config
    private final Map<Integer, Integer> potenciaToIdMap; // Mapeia a potência para o ID correspondente

    // Construtor (inicializa com o valor inicial do gerador)
    public IdGeneratorController(int initialIdGenerator) {
        this.currentIdGenerator = initialIdGenerator;
        this.potenciaToIdMap = new HashMap<>();
    }

    /**
     * Gera e recupera o ID do gerador para uma potência específica.
     *
     * @param potencia potência em W
     * @return ID gerado associado à potência
     */
    public int controler_id_generator(int potencia) {
        // Verifica se já existe um ID associado à potência
        if (potenciaToIdMap.containsKey(potencia)) {
            return potenciaToIdMap.get(potencia); // Retorna o ID já associado
        }

        // Caso a potência não tenha ID associado, gera um novo ID
        int newId = currentIdGenerator;
        potenciaToIdMap.put(potencia, newId);

        // Incrementa o ID para a próxima potência diferente
        currentIdGenerator++;

        return newId;
    }

    /**
     * Retorna a quantidade de IDs únicos gerados.
     *
     * @return número total de IDs gerados
     */
    public int getTotalGeneratedIds() {
        return potenciaToIdMap.size();
    }


    /**
     * Optional: Exibe o mapeamento atual de potências para IDs.
     */
    public void printMap() {
        System.out.println("Mapeamento de Potências para IDs:");
        for (Map.Entry<Integer, Integer> entry : potenciaToIdMap.entrySet()) {
            System.out.printf("Potência: %d W -> ID: %d%n", entry.getKey(), entry.getValue());
        }
    }

}
