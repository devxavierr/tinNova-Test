package com.tinnova.infrastructure.web.mapper;

import com.tinnova.domain.model.Veiculo;
import com.tinnova.infrastructure.web.dto.VeiculoRequest;
import com.tinnova.infrastructure.web.dto.VeiculoPatchRequest;
import org.springframework.stereotype.Component;

@Component
public class VeiculoWebMapper {
    
    public Veiculo toVeiculo(VeiculoRequest request) {
        return new Veiculo(request.veiculo, request.marca, request.ano, 
                          request.cor, request.descricao, request.vendido);
    }
    
    public Veiculo toPatchVeiculo(VeiculoPatchRequest request) {
        Veiculo veiculo = new Veiculo();
        veiculo.setVendido(request.vendido);
        return veiculo;
    }
}