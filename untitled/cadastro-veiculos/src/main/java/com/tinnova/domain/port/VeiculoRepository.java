package com.tinnova.domain.port;

import com.tinnova.domain.model.Veiculo;
import java.util.List;
import java.util.Optional;

public interface VeiculoRepository {
    Veiculo save(Veiculo veiculo);
    Optional<Veiculo> findById(Long id);
    List<Veiculo> findAll();
    List<Veiculo> findByFilters(String marca, Integer ano, String cor);
    void deleteById(Long id);
    boolean existsById(Long id);
}