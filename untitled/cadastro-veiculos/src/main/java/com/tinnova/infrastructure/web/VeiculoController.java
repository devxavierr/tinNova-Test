package com.tinnova.infrastructure.web;

import com.tinnova.application.VeiculoService;
import com.tinnova.domain.model.Veiculo;
import com.tinnova.infrastructure.web.dto.VeiculoRequest;
import com.tinnova.infrastructure.web.dto.VeiculoPatchRequest;
import com.tinnova.infrastructure.web.mapper.VeiculoWebMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/veiculos")
@Tag(name = "Veículos", description = "API para gerenciamento de veículos")
public class VeiculoController {
    private final VeiculoService veiculoService;
    private final VeiculoWebMapper mapper;

    public VeiculoController(VeiculoService veiculoService, VeiculoWebMapper mapper) {
        this.veiculoService = veiculoService;
        this.mapper = mapper;
    }

    @PostMapping
    @Operation(summary = "Criar veículo", description = "Cria um novo veículo")
    @ApiResponse(responseCode = "201", description = "Veículo criado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos")
    public ResponseEntity<Veiculo> criar(@Valid @RequestBody VeiculoRequest request) {
        Veiculo veiculo = mapper.toVeiculo(request);
        Veiculo criado = veiculoService.criar(veiculo);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @GetMapping
    @Operation(summary = "Listar veículos", description = "Lista veículos com filtros opcionais")
    @ApiResponse(responseCode = "200", description = "Lista de veículos")
    @ApiResponse(responseCode = "204", description = "Nenhum veículo encontrado")
    public ResponseEntity<List<Veiculo>> listar(
            @Parameter(description = "Filtrar por marca") @RequestParam(required = false) String marca,
            @Parameter(description = "Filtrar por ano") @RequestParam(required = false) Integer ano,
            @Parameter(description = "Filtrar por cor") @RequestParam(required = false) String cor) {
        List<Veiculo> veiculos = veiculoService.listar(marca, ano, cor);
        if (veiculos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(veiculos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar veículo por ID", description = "Retorna um veículo específico")
    @ApiResponse(responseCode = "200", description = "Veículo encontrado")
    @ApiResponse(responseCode = "404", description = "Veículo não encontrado")
    public ResponseEntity<Veiculo> buscarPorId(
            @Parameter(description = "ID do veículo") @PathVariable Long id) {
        Optional<Veiculo> veiculo = veiculoService.buscarPorId(id);
        return veiculo.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar veículo", description = "Atualiza completamente um veículo")
    @ApiResponse(responseCode = "200", description = "Veículo atualizado")
    @ApiResponse(responseCode = "404", description = "Veículo não encontrado")
    @ApiResponse(responseCode = "400", description = "Dados inválidos")
    public ResponseEntity<Veiculo> atualizar(
            @Parameter(description = "ID do veículo") @PathVariable Long id, 
            @Valid @RequestBody VeiculoRequest request) {
        Veiculo veiculo = mapper.toVeiculo(request);
        Optional<Veiculo> atualizado = veiculoService.atualizar(id, veiculo);
        return atualizado.map(ResponseEntity::ok)
                        .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Atualizar parcialmente", description = "Atualiza parcialmente um veículo (ex: marcar como vendido)")
    @ApiResponse(responseCode = "200", description = "Veículo atualizado")
    @ApiResponse(responseCode = "404", description = "Veículo não encontrado")
    public ResponseEntity<Veiculo> atualizarParcial(
            @Parameter(description = "ID do veículo") @PathVariable Long id, 
            @RequestBody VeiculoPatchRequest request) {
        Veiculo dadosParciais = mapper.toPatchVeiculo(request);
        Optional<Veiculo> atualizado = veiculoService.atualizarParcial(id, dadosParciais);
        return atualizado.map(ResponseEntity::ok)
                        .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar veículo", description = "Remove um veículo")
    @ApiResponse(responseCode = "204", description = "Veículo deletado")
    @ApiResponse(responseCode = "404", description = "Veículo não encontrado")
    public ResponseEntity<Void> deletar(
            @Parameter(description = "ID do veículo") @PathVariable Long id) {
        boolean deletado = veiculoService.deletar(id);
        return deletado ? ResponseEntity.noContent().build() 
                       : ResponseEntity.notFound().build();
    }
}