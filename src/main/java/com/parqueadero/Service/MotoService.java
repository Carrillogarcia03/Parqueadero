package com.parqueaderito.Service;

package com.parqueadero.service;

import com.parqueadero.model.Moto;
import com.parqueadero.repository.MotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MotoService {

    @Autowired
    private MotoRepository motoRepository;

    public List<Moto> getAllMotos() {
        return motoRepository.findAll();
    }

    public Optional<Moto> getMotoById(String placa) {
        return motoRepository.findById(placa);
    }

    public Moto saveMoto(Moto moto) {
        return motoRepository.save(moto);
    }

    public void deleteMoto(String placa) {
        motoRepository.deleteById(placa);
    }

    public Moto updateMoto(String placa, LocalDateTime horaSalida) {
        Optional<Moto> optionalMoto = motoRepository.findById(placa);
        if (optionalMoto.isPresent()) {
            Moto moto = optionalMoto.get();
            moto.setHoraSalida(horaSalida);
            // Calcular el total a pagar (simplificado)
            moto.setTotalPagar(100.0); // Este valor debe ser calculado basado en la lógica de negocio
            return motoRepository.save(moto);
        }
        return null;
    }
}
