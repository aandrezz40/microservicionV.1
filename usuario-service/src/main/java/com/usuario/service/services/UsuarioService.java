package com.usuario.service.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.usuario.service.repository.UsuarioRepository;
import com.usuario.service.entity.Usuario;
import com.usuario.service.models.Carro;
import com.usuario.service.models.Moto;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RestTemplate restTemplate;

    public List<Carro> getCarros(long usuarioId){
        List<Carro> carros = restTemplate.getForObject("http://localhost:8002/api/carro/usuario/" + usuarioId, List.class);
        return carros;
    }

    public List<Moto> getMotos(long usuarioId){
        List<Moto> motos = restTemplate.getForObject("http://localhost:8003/api/moto/usuario/" + usuarioId, List.class);
        return motos;
    }

    public List<Usuario> getAll(){
        return usuarioRepository.findAll();
    }

    public Usuario getUsuarioById(long id){
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario saveUsuario(Usuario usuario){
        Usuario nuevoUsuario = usuarioRepository.save(usuario);
        return nuevoUsuario;
    }


}
