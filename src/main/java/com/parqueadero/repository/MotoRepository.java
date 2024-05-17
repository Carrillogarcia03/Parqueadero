package com.parqueaderito.repository;

import com.parqueaderito.Model.Moto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MotoRepository extends JpaRepository<Moto, String> {
}
