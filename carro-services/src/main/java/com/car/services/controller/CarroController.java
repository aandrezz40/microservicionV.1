package com.car.services.controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.car.services.entity.Carro;
import com.car.services.services.CarroService;

@RestController
@RequestMapping("/api/carro")
public class CarroController {
    
    @Autowired
    private CarroService carroService;

    @GetMapping
    public ResponseEntity<List<Carro>> getAllCarros(){
        List<Carro> carros = carroService.getAll();
        if(carros.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(carros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carro> getCarroById(@PathVariable long id){
        Carro carro = carroService.getCarroById(id);
        if(carro == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(carro);
    }

    @PostMapping("/save")
    public ResponseEntity<Carro> saveCarro(@RequestBody Carro carro){
        System.out.println("[DEBUG] Endpoint /carro/save alcanzado. Carro recibido: " + carro);
        Carro carroGuardado = carroService.saveCarro(carro);
        return ResponseEntity.ok(carroGuardado);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Carro>> getCarrosByIdUser(@PathVariable long usuarioId){
        List<Carro> carros =  carroService.getCarrosByUsuarioId(usuarioId);
        if(carros.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(carros);
    }
}
