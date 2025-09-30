package com.tinnova.domain.model;

import java.time.LocalDateTime;

public class Veiculo {
    private Long id;
    private String veiculo;
    private String marca;
    private Integer ano;
    private String cor;
    private String descricao;
    private Boolean vendido;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Veiculo() {}

    public Veiculo(String veiculo, String marca, Integer ano, String cor, String descricao, Boolean vendido) {
        this.veiculo = veiculo;
        this.marca = marca;
        this.ano = ano;
        this.cor = cor;
        this.descricao = descricao;
        this.vendido = vendido != null ? vendido : false;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getVeiculo() { return veiculo; }
    public void setVeiculo(String veiculo) { this.veiculo = veiculo; }
    
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    
    public Integer getAno() { return ano; }
    public void setAno(Integer ano) { this.ano = ano; }
    
    public String getCor() { return cor; }
    public void setCor(String cor) { this.cor = cor; }
    
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    
    public Boolean getVendido() { return vendido; }
    public void setVendido(Boolean vendido) { this.vendido = vendido; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}