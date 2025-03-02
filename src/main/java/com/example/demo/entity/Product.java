package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Classe que representa um produto, mapeando os atributos de uma fonte externa (JSON)
 * e utilizados internamente na aplicação.
 *
 * Esta entidade é utilizada para modelar os dados do produto, incluindo informações
 * como categoria, ID, potência, nome e quantidade.
 */
public class Product {

    /**
     * Categoria do produto.
     *
     * Esta propriedade será mapeada no JSON como `Categoria`.
     */
    @JsonProperty("Categoria")
    private String category;

    /**
     * ID único do produto.
     *
     * Esta propriedade será mapeada no JSON como `Id`.
     */
    @JsonProperty("Id")
    private int id;

    /**
     * Potência do produto em watts.
     *
     * Esta propriedade será mapeada no JSON como `Potencia em W`.
     */
    @JsonProperty("Potencia em W")
    private int power;

    /**
     * Nome ou identificação textual do produto.
     *
     * Esta propriedade será mapeada no JSON como `Produto`.
     */
    @JsonProperty("Produto")
    private String product;

    /**
     * Quantidade do produto.
     * Representa o número de itens disponíveis ou agrupados.
     */
    private int qt;

    /* Getters e Setters */

    /**
     * Recupera a categoria do produto.
     *
     * @return Categoria do produto.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Define a categoria do produto.
     *
     * @param category Categoria do produto.
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Recupera o ID do produto.
     *
     * @return ID único do produto.
     */
    public int getId() {
        return id;
    }

    /**
     * Define o ID do produto.
     *
     * @param id ID único do produto.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Recupera a potência do produto.
     *
     * @return Potência do produto em watts.
     */
    public int getPower() {
        return power;
    }

    /**
     * Define a potência do produto.
     *
     * @param power Potência do produto em watts.
     */
    public void setPower(int power) {
        this.power = power;
    }

    /**
     * Recupera o nome ou identificação textual do produto.
     *
     * @return Nome do produto.
     */
    public String getProduct() {
        return product;
    }

    /**
     * Define o nome do produto.
     *
     * @param product Nome do produto.
     */
    public void setProduct(String product) {
        this.product = product;
    }

    /**
     * Recupera a quantidade do produto.
     *
     * @return Quantidade do produto.
     */
    public int getQt() {
        return qt;
    }

    /**
     * Define a quantidade do produto.
     *
     * @param qt Quantidade do produto.
     */
    public void setQt(int qt) {
        this.qt = qt;
    }

    /**
     * Ideal para depuração e logs. Exibe os valores de todos os atributos do produto.
     *
     * @return Uma string que representa o objeto `Product`.
     */
    @Override
    public String toString() {
        return "Product{" +
                "category='" + category + '\'' +
                ", id=" + id +
                ", power=" + power +
                ", product='" + product + '\'' +
                ", qt=" + qt +
                '}';
    }
}