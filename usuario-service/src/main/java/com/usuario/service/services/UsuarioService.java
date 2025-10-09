package com.usuario.service.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.HttpClientErrorException;

import com.usuario.service.repository.UsuarioRepository;
import com.usuario.service.entity.Usuario;
import com.usuario.service.models.Carro;
import com.usuario.service.models.Moto;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.ArrayList;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RestTemplate restTemplate;

    public List<Carro> getCarros(long usuarioId){
        try {
            System.out.println("[DEBUG] Llamando a carro-service para usuario: " + usuarioId);
            List<Carro> carros = restTemplate.getForObject("http://localhost:8002/api/carro/usuario/" + usuarioId, List.class);
            return carros != null ? carros : new ArrayList<>();
        } catch (ResourceAccessException e) {
            System.err.println("[ERROR] No se pudo conectar con carro-service: " + e.getMessage());
            return new ArrayList<>();
        } catch (HttpClientErrorException e) {
            System.err.println("[ERROR] Error HTTP al llamar carro-service: " + e.getStatusCode() + " - " + e.getMessage());
            return new ArrayList<>();
        } catch (Exception e) {
            System.err.println("[ERROR] Error inesperado al llamar carro-service: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<Moto> getMotos(long usuarioId){
        try {
            System.out.println("[DEBUG] Llamando a moto-service para usuario: " + usuarioId);
            List<Moto> motos = restTemplate.getForObject("http://localhost:8003/api/moto/usuario/" + usuarioId, List.class);
            return motos != null ? motos : new ArrayList<>();
        } catch (ResourceAccessException e) {
            System.err.println("[ERROR] No se pudo conectar con moto-service: " + e.getMessage());
            return new ArrayList<>();
        } catch (HttpClientErrorException e) {
            System.err.println("[ERROR] Error HTTP al llamar moto-service: " + e.getStatusCode() + " - " + e.getMessage());
            return new ArrayList<>();
        } catch (Exception e) {
            System.err.println("[ERROR] Error inesperado al llamar moto-service: " + e.getMessage());
            return new ArrayList<>();
        }
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
