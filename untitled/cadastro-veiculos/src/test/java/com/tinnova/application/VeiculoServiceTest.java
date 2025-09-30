package com.tinnova.application;

import com.tinnova.domain.model.Veiculo;
import com.tinnova.domain.port.VeiculoRepository;
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
class VeiculoServiceTest {

    @Mock
    private VeiculoRepository veiculoRepository;

    @InjectMocks
    private VeiculoService veiculoService;

    private Veiculo veiculo;

    @BeforeEach
    void setUp() {
        veiculo = new Veiculo("Civic", "Honda", 2020, "prata", "Sedan", false);
        veiculo.setId(1L);
    }

    @Test
    void deveCriarVeiculo() {
        when(veiculoRepository.save(any(Veiculo.class))).thenReturn(veiculo);

        Veiculo resultado = veiculoService.criar(veiculo);

        assertNotNull(resultado);
        assertEquals("Civic", resultado.getVeiculo());
        verify(veiculoRepository).save(any(Veiculo.class));
    }

    @Test
    void deveListarTodosVeiculos() {
        List<Veiculo> veiculos = Arrays.asList(veiculo);
        when(veiculoRepository.findAll()).thenReturn(veiculos);

        List<Veiculo> resultado = veiculoService.listar(null, null, null);

        assertEquals(1, resultado.size());
        verify(veiculoRepository).findAll();
    }

    @Test
    void deveListarComFiltros() {
        List<Veiculo> veiculos = Arrays.asList(veiculo);
        when(veiculoRepository.findByFilters("Honda", 2020, "prata")).thenReturn(veiculos);

        List<Veiculo> resultado = veiculoService.listar("Honda", 2020, "prata");

        assertEquals(1, resultado.size());
        verify(veiculoRepository).findByFilters("Honda", 2020, "prata");
    }

    @Test
    void deveBuscarPorId() {
        when(veiculoRepository.findById(1L)).thenReturn(Optional.of(veiculo));

        Optional<Veiculo> resultado = veiculoService.buscarPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Civic", resultado.get().getVeiculo());
    }

    @Test
    void deveAtualizarVeiculo() {
        when(veiculoRepository.existsById(1L)).thenReturn(true);
        when(veiculoRepository.save(any(Veiculo.class))).thenReturn(veiculo);

        Optional<Veiculo> resultado = veiculoService.atualizar(1L, veiculo);

        assertTrue(resultado.isPresent());
        verify(veiculoRepository).save(any(Veiculo.class));
    }

    @Test
    void deveAtualizarParcial() {
        when(veiculoRepository.findById(1L)).thenReturn(Optional.of(veiculo));
        when(veiculoRepository.save(any(Veiculo.class))).thenReturn(veiculo);

        Veiculo patch = new Veiculo();
        patch.setVendido(true);

        Optional<Veiculo> resultado = veiculoService.atualizarParcial(1L, patch);

        assertTrue(resultado.isPresent());
        verify(veiculoRepository).save(any(Veiculo.class));
    }

    @Test
    void deveDeletarVeiculo() {
        when(veiculoRepository.existsById(1L)).thenReturn(true);

        boolean resultado = veiculoService.deletar(1L);

        assertTrue(resultado);
        verify(veiculoRepository).deleteById(1L);
    }
}