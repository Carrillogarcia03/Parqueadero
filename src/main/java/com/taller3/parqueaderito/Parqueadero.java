package com.taller3.parqueaderito;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;


@RestController
@RequestMapping("/motos")
public class Parqueadero {
    private static final double TARIFA = 2500;

    @Autowired
    private EntryRepository repositorio;

    @PostMapping("/")
    public void addEntry(@RequestBody Entry entry) {
        entry.setHora_entrada(new Date());
        repositorio.save(entry);
    }

    @PostMapping("/Calcular")
    public double costo(@RequestBody Entry entry) {
        Date auxentrada = entry.getHora_entrada();
        Date auxsalida = entry.getHora_salida();

        if (auxentrada == null || auxsalida == null) {
            return 0;
        } else {
            System.out.println(" Hora de entrada y salida se ingresaron correctamente.");
        }
        long duracion = auxentrada.getTime() - auxsalida.getTime();
        long auxduracion= TimeUnit.MINUTES.convert(duracion, TimeUnit.MILLISECONDS);
        double costito=auxduracion*TARIFA;

        return costito;   
        
    }
    @DeleteMapping("/Eliminar")
    public void eliminar(@PathVariable String placa){
        repositorio.deleteById(placa);
    }
    @GetMapping("/")
    public List<Entry> getAllEntries() {
        return repositorio.findAll();
    }

    @GetMapping("/placa")
    public Entry getEntryByPlaca(@PathVariable String placa) {
        return repositorio.findById(placa).orElse(null);
    }
    @GetMapping("/parqueadero")
    public String mostrarParqueadero(Model model) {
        model.addAttribute("entries", repositorio.findAll());
        return "parqueadero";
    }
    private static final int MAX_PLACAS = 10;
    private List<String> placasIngresadas = new ArrayList<>();

    @PostMapping("/ingresarPlacas")
    public List<String> ingresarPlacas(@RequestBody List<String> placas) {
        List<String> resultados = new ArrayList<>();

        for (String placa : placas) {
            if (placasIngresadas.size() >= MAX_PLACAS) {
                resultados.add("Se ha alcanzado el límite máximo de placas. No se puede ingresar más.");
                return resultados;
            }

            if (placa == null || placa.isEmpty()) {
                resultados.add("La placa no puede ser nula o vacía");
                continue;
            }

            if (placasIngresadas.contains(placa)) {
                resultados.add("La placa " + placa + " ya está registrada en el parqueadero.");
                continue;
            }

            if (!validarPlaca(placa)) {
                resultados.add("Placa no válida, recuerda que solo pueden ingresar placas de carros");
                continue;
            }

            if (!picoYPlaca(placa)) {
                resultados.add("La placa " + placa + " no está permitida debido a la restricción de pico y placa");
                continue;
            }

            placasIngresadas.add(placa);
            resultados.add("¡Bienvenido! La placa " + placa + " está permitida");
        }

        return resultados;
    }

    private boolean validarPlaca(String placa) {
        return placa.matches("[A-Z]{3}[0-9]{2} [A-Z]{1}") || placa.matches("[A-Z]{3}[0-9]");
    }

    private boolean picoYPlaca(String placa) {
        char digito = placa.charAt(placa.length() - 1);
        return digito != '1' && digito != '4';
    }
}



