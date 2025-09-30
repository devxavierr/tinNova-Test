package com.tinnova.infrastructure.persistence;

import com.tinnova.domain.model.Veiculo;
import com.tinnova.infrastructure.persistence.entity.VeiculoEntity;
import com.tinnova.infrastructure.persistence.repository.VeiculoJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VeiculoRepositoryImplTest {

    @Mock
    private VeiculoJpaRepository jpaRepository;

    @InjectMocks
    private VeiculoRepositoryImpl veiculoRepository;

    private VeiculoEntity entity;
    private Veiculo veiculo;

    @BeforeEach
    void setUp() {
        entity = new VeiculoEntity();
        entity.setId(1L);
        entity.setVeiculo("Civic");
        entity.setMarca("Honda");
        entity.setAno(2020);
        entity.setCor("prata");
        entity.setVendido(false);

        veiculo = new Veiculo("Civic", "Honda", 2020, "prata", null, false);
        veiculo.setId(1L);
    }

    @Test
    void deveSalvarVeiculo() {
        when(jpaRepository.save(any(VeiculoEntity.class))).thenReturn(entity);

        Veiculo resultado = veiculoRepository.save(veiculo);

        assertNotNull(resultado);
        assertEquals("Civic", resultado.getVeiculo());
        verify(jpaRepository).save(any(VeiculoEntity.class));
    }

    @Test
    void deveBuscarPorId() {
        when(jpaRepository.findById(1L)).thenReturn(Optional.of(entity));

        Optional<Veiculo> resultado = veiculoRepository.findById(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Civic", resultado.get().getVeiculo());
    }

    @Test
    void deveListarTodos() {
        when(jpaRepository.findAll()).thenReturn(Arrays.asList(entity));

        List<Veiculo> resultado = veiculoRepository.findAll();

        assertEquals(1, resultado.size());
        assertEquals("Civic", resultado.get(0).getVeiculo());
    }

    @Test
    void deveBuscarComFiltros() {
        when(jpaRepository.findByFilters("Honda", 2020, "prata")).thenReturn(Arrays.asList(entity));

        List<Veiculo> resultado = veiculoRepository.findByFilters("Honda", 2020, "prata");

        assertEquals(1, resultado.size());
        assertEquals("Honda", resultado.get(0).getMarca());
    }

    @Test
    void deveVerificarSeExiste() {
        when(jpaRepository.existsById(1L)).thenReturn(true);

        boolean existe = veiculoRepository.existsById(1L);

        assertTrue(existe);
    }

    @Test
    void deveDeletar() {
        veiculoRepository.deleteById(1L);

        verify(jpaRepository).deleteById(1L);
    }
}