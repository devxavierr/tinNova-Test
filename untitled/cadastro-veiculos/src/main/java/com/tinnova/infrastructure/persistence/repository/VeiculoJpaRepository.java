package com.tinnova.infrastructure.persistence.repository;

import com.tinnova.infrastructure.persistence.entity.VeiculoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface VeiculoJpaRepository extends JpaRepository<VeiculoEntity, Long> {
    
    @Query("SELECT v FROM VeiculoEntity v WHERE " +
           "(:marca IS NULL OR v.marca = :marca) AND " +
           "(:ano IS NULL OR v.ano = :ano) AND " +
           "(:cor IS NULL OR v.cor = :cor)")
    List<VeiculoEntity> findByFilters(@Param("marca") String marca, 
                                     @Param("ano") Integer ano, 
                                     @Param("cor") String cor);
}