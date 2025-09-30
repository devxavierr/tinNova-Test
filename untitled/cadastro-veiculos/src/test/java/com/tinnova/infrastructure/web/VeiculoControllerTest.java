package com.tinnova.infrastructure.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinnova.application.VeiculoService;
import com.tinnova.domain.model.Veiculo;
import com.tinnova.infrastructure.web.dto.VeiculoRequest;
import com.tinnova.infrastructure.web.mapper.VeiculoWebMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(VeiculoController.class)
class VeiculoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VeiculoService veiculoService;

    @MockBean
    private VeiculoWebMapper mapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveCriarVeiculo() throws Exception {
        VeiculoRequest request = new VeiculoRequest();
        request.veiculo = "Civic";
        request.marca = "Honda";
        request.ano = 2020;
        request.cor = "prata";

        Veiculo veiculo = new Veiculo("Civic", "Honda", 2020, "prata", null, false);
        veiculo.setId(1L);

        when(mapper.toVeiculo(any())).thenReturn(veiculo);
        when(veiculoService.criar(any())).thenReturn(veiculo);

        mockMvc.perform(post("/veiculos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.veiculo").value("Civic"));
    }

    @Test
    void deveListarVeiculos() throws Exception {
        Veiculo veiculo = new Veiculo("Civic", "Honda", 2020, "prata", null, false);
        when(veiculoService.listar(null, null, null)).thenReturn(Arrays.asList(veiculo));

        mockMvc.perform(get("/veiculos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].veiculo").value("Civic"));
    }

    @Test
    void deveBuscarPorId() throws Exception {
        Veiculo veiculo = new Veiculo("Civic", "Honda", 2020, "prata", null, false);
        veiculo.setId(1L);
        when(veiculoService.buscarPorId(1L)).thenReturn(Optional.of(veiculo));

        mockMvc.perform(get("/veiculos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.veiculo").value("Civic"));
    }

    @Test
    void deveRetornar404QuandoNaoEncontrar() throws Exception {
        when(veiculoService.buscarPorId(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/veiculos/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void deveDeletarVeiculo() throws Exception {
        when(veiculoService.deletar(1L)).thenReturn(true);

        mockMvc.perform(delete("/veiculos/1"))
                .andExpect(status().isNoContent());
    }
}