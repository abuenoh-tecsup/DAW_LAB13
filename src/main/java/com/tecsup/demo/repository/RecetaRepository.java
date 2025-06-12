package com.tecsup.demo.repository;

import com.tecsup.demo.model.Receta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecetaRepository extends JpaRepository<Receta, Long> {
}
