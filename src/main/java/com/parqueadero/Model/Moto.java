package com.parqueaderito.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Moto {
    @Id
    private String placa;
    private LocalDateTime horaEntrada;
    private LocalDateTime horaSalida;
    private boolean llevaCasco;
    private double totalPagar;

    // Getters y setters
}