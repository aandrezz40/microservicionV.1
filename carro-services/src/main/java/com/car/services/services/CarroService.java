package com.car.services.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car.services.entity.Carro;
import com.car.services.repository.CarroRepository;

@Service
public class CarroService {
    @Autowired
    private CarroRepository carroRepository;

    public List<Carro> getAll(){
        return carroRepository.findAll();
    }

    public Carro getCarroById(long id){
        return carroRepository.findById(id).orElse(null);
    }

    public Carro saveCarro(Carro carro){
        Carro nuevoCarro = carroRepository.save(carro);
        return nuevoCarro;
    }

    public List<Carro> getCarrosByUsuarioId(long usuarioId){
        return carroRepository.findByUsuarioId(usuarioId);
    }
}
