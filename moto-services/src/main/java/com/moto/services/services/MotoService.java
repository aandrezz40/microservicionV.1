package com.moto.services.services;

import com.moto.services.entity.Moto;
import com.moto.services.repository.MotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotoService {
    @Autowired
    private MotoRepository motoRepository;

    public List<Moto> getAll(){
        return motoRepository.findAll();
    }

    public Moto getMotoById(long id){
        return motoRepository.findById(id).orElse(null);
    }

    public Moto saveMoto(Moto moto){
        Moto nuevaMoto = motoRepository.save(moto);
        return nuevaMoto;
    }

    public List<Moto> getMotosByUsuarioId(long usuarioId){
        return motoRepository.findByUsuarioId(usuarioId);
    }
}
