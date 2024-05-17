package com.taller3.parqueaderito;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Entity
public class Entry {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String placa;
    private Long id;

  
    private Date hora_entrada;
    private Date hora_salida;

     public Entry(String placa,  Date hora_entrada, Date hora_salida, Long id) {
        this.placa = placa;
               this.hora_entrada = hora_entrada;
        this.hora_salida = hora_salida;
        this.id=id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

  

    public Date getHora_entrada() {
        return hora_entrada;
    }

    public void setHora_entrada(Date hora_entrada) {
        this.hora_entrada = hora_entrada;
    }

    public Date getHora_salida() {
        return hora_salida;
    }

    public void setHora_salida(Date hora_salida) {
        this.hora_salida = hora_salida;
    }

    public interface EntryRepository extends JpaRepository<Entry, Long> {
    }


}

 