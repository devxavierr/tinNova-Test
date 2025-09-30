package com.tinnova.infrastructure.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

@Schema(description = "Dados para criação/atualização de veículo")
public class VeiculoRequest {
    @Schema(description = "Nome/modelo do veículo", example = "Civic EX")
    @NotBlank(message = "Veículo é obrigatório")
    public String veiculo;
    
    @Schema(description = "Marca do veículo", example = "Honda")
    @NotBlank(message = "Marca é obrigatória")
    public String marca;
    
    @Schema(description = "Ano do veículo", example = "2020")
    @NotNull(message = "Ano é obrigatório")
    @Min(value = 1900, message = "Ano deve ser maior que 1900")
    @Max(value = 2025, message = "Ano não pode ser maior que 2025")
    public Integer ano;
    
    @Schema(description = "Cor do veículo", example = "prata")
    @NotBlank(message = "Cor é obrigatória")
    public String cor;
    
    @Schema(description = "Descrição do veículo", example = "Sedã revisado, único dono")
    @Size(max = 1000, message = "Descrição deve ter no máximo 1000 caracteres")
    public String descricao;
    
    @Schema(description = "Status de venda", example = "false")
    public Boolean vendido;
}