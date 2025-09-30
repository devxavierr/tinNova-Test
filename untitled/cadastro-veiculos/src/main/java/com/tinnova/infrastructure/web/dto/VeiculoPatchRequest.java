package com.tinnova.infrastructure.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados para atualização parcial de veículo")
public class VeiculoPatchRequest {
    @Schema(description = "Status de venda do veículo", example = "true")
    public Boolean vendido;
}