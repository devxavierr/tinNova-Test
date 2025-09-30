package com.tinnova.application;

import com.tinnova.domain.model.Veiculo;
import com.tinnova.domain.port.VeiculoRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VeiculoService {
    private final VeiculoRepository veiculoRepository;

    public VeiculoService(VeiculoRepository veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }

    public Veiculo criar(Veiculo veiculo) {
        veiculo.setCreatedAt(LocalDateTime.now());
        veiculo.setUpdatedAt(LocalDateTime.now());
        return veiculoRepository.save(veiculo);
    }

    public List<Veiculo> listar(String marca, Integer ano, String cor) {
        if (marca != null || ano != null || cor != null) {
            return veiculoRepository.findByFilters(marca, ano, cor);
        }
        return veiculoRepository.findAll();
    }

    public Optional<Veiculo> buscarPorId(Long id) {
        return veiculoRepository.findById(id);
    }

    public Optional<Veiculo> atualizar(Long id, Veiculo veiculo) {
        if (!veiculoRepository.existsById(id)) {
            return Optional.empty();
        }
        veiculo.setId(id);
        veiculo.setUpdatedAt(LocalDateTime.now());
        return Optional.of(veiculoRepository.save(veiculo));
    }

    public Optional<Veiculo> atualizarParcial(Long id, Veiculo dadosParciais) {
        Optional<Veiculo> veiculoExistente = veiculoRepository.findById(id);
        if (veiculoExistente.isEmpty()) {
            return Optional.empty();
        }
        
        Veiculo veiculo = veiculoExistente.get();
        if (dadosParciais.getVendido() != null) {
            veiculo.setVendido(dadosParciais.getVendido());
        }
        veiculo.setUpdatedAt(LocalDateTime.now());
        return Optional.of(veiculoRepository.save(veiculo));
    }

    public boolean deletar(Long id) {
        if (!veiculoRepository.existsById(id)) {
            return false;
        }
        veiculoRepository.deleteById(id);
        return true;
    }
}