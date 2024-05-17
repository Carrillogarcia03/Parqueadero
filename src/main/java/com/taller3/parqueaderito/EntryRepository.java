package com.taller3.parqueaderito;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
public class EntryRepository {
    private String placa;
    private Date hora_entrada;
    private Date hora_salida;


public EntryRepository (){

}

public EntryRepository (String placa, Date hora_entrada, Date hora_salida){
this.placa =placa;
this.hora_entrada=hora_entrada;
this.hora_salida=hora_salida;
}
public String getPlaca(){
    return placa;
}
public void setPlaca(String placa){
  this.placa=placa;
}
public Date getHora_entrada(){
    return hora_entrada;
}
public void setHora_entrada(Date hora_entrada){
    this.hora_entrada=hora_entrada;
}
public Date getHora_salida(){
    return hora_salida;
}
public void setHora_salida(Date hora_salida){
    this.hora_salida=hora_salida;
}

public interface EntryEntity extends JpaRepository<EntryRepository, Long> {
}
}