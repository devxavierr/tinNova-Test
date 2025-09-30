package com.tinnova.infrastructure.persistence;

import com.tinnova.domain.model.Veiculo;
import com.tinnova.domain.port.VeiculoRepository;
import com.tinnova.infrastructure.persistence.entity.VeiculoEntity;
import com.tinnova.infrastructure.persistence.repository.VeiculoJpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class VeiculoRepositoryImpl implements VeiculoRepository {
    private final VeiculoJpaRepository jpaRepository;

    public VeiculoRepositoryImpl(VeiculoJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Veiculo save(Veiculo veiculo) {
        VeiculoEntity entity = toEntity(veiculo);
        VeiculoEntity saved = jpaRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<Veiculo> findById(Long id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<Veiculo> findAll() {
        return jpaRepository.findAll().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Veiculo> findByFilters(String marca, Integer ano, String cor) {
        return jpaRepository.findByFilters(marca, ano, cor).stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return jpaRepository.existsById(id);
    }

    private VeiculoEntity toEntity(Veiculo veiculo) {
        VeiculoEntity entity = new VeiculoEntity();
        entity.setId(veiculo.getId());
        entity.setVeiculo(veiculo.getVeiculo());
        entity.setMarca(veiculo.getMarca());
        entity.setAno(veiculo.getAno());
        entity.setCor(veiculo.getCor());
        entity.setDescricao(veiculo.getDescricao());
        entity.setVendido(veiculo.getVendido());
        entity.setCreatedAt(veiculo.getCreatedAt());
        entity.setUpdatedAt(veiculo.getUpdatedAt());
        return entity;
    }

    private Veiculo toDomain(VeiculoEntity entity) {
        Veiculo veiculo = new Veiculo();
        veiculo.setId(entity.getId());
        veiculo.setVeiculo(entity.getVeiculo());
        veiculo.setMarca(entity.getMarca());
        veiculo.setAno(entity.getAno());
        veiculo.setCor(entity.getCor());
        veiculo.setDescricao(entity.getDescricao());
        veiculo.setVendido(entity.getVendido());
        veiculo.setCreatedAt(entity.getCreatedAt());
        veiculo.setUpdatedAt(entity.getUpdatedAt());
        return veiculo;
    }
}