package com.usuario.service.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.usuario.service.repository.UsuarioRepository;
import com.usuario.service.entity.Usuario;
import com.usuario.service.feign.CarroFeignClient;
import com.usuario.service.feign.MotoFeignClient;
import com.usuario.service.models.Carro;
import com.usuario.service.models.Moto;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CarroFeignClient carroFeignClient;

    @Autowired
    private MotoFeignClient motoFeignClient;

    public List<Carro> getCarros(long usuarioId){
            List<Carro> carros = restTemplate.getForObject("http://localhost:8002/api/carro/usuario/" + usuarioId, List.class);
            return carros != null ? carros : new ArrayList<>();
    }

    public List<Moto> getMotos(long usuarioId){
            List<Moto> motos = restTemplate.getForObject("http://localhost:8003/api/moto/usuario/" + usuarioId, List.class);
            return motos != null ? motos : new ArrayList<>();
    }

    public Carro saveCarro (long usuarioId, Carro carro){
        carro.setUsuarioId(usuarioId);
        Carro nuevoCarro = carroFeignClient.saveCarro(carro);
        return nuevoCarro;
    }

    public Moto saveMoto(long idUsuario, Moto moto){
        moto.setUsuarioId(idUsuario);
        Moto nuevaMoto = motoFeignClient.saveMoto(moto);
        return nuevaMoto;
    }

    public Map<String, Object> getUserAndVehiculos(long usuarioId){
        Map<String, Object> datos = new HashMap<>();

        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
        if(usuario == null){
            datos.put("Mensaje", "Usuario no encontrado");
            return datos;
        }
        datos.put("Usuario", usuario);

        List<Carro> carros = carroFeignClient.carrosByUser(usuarioId);
        if(carros.isEmpty()){
            datos.put("carros", "El usuarios no tiene carros");
        }else{
            datos.put("carros", carros);
        }

        List<Moto> motos = motoFeignClient.motoByUser(usuarioId);
        if(motos.isEmpty()){
            datos.put("Motos", "El usuarios no tiene motos");
        }else{
            datos.put("Motos", motos);
        }

        return datos;

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
