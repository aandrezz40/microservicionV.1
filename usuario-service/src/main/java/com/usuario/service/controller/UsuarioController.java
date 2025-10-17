package com.usuario.service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Map;

import com.usuario.service.services.UsuarioService;
import com.usuario.service.entity.Usuario;
import com.usuario.service.models.Carro;
import com.usuario.service.models.Moto;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsuarios(){
        List<Usuario> usuarios = usuarioService.getAll();
        if(usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUserById(@PathVariable long id){
        Usuario usuario = usuarioService.getUsuarioById(id);
        if(usuario == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }

    @PostMapping("/save")
    public ResponseEntity<Usuario> saveUsuario(@RequestBody Usuario usuario){
        Usuario usuarioGuardado = usuarioService.saveUsuario(usuario);
        return ResponseEntity.ok(usuarioGuardado);
    }

    @GetMapping("/carros/{usuarioId}")
    public ResponseEntity<List<Carro>> getCarrosByUsuarioId(@PathVariable long usuarioId){
        List<Carro> carros = usuarioService.getCarros(usuarioId);
        if(carros.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(carros);
    }
    @GetMapping("/motos/{usuarioId}")
    public ResponseEntity<List<Moto>> getMotosByUsuarioId(@PathVariable long usuarioId){
        List<Moto> motos = usuarioService.getMotos(usuarioId);
        if(motos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(motos);
    }
    @PostMapping("/saveCarro/{idUsuario}")
    public ResponseEntity<Carro> saveCarro(@PathVariable long idUsuario, @RequestBody Carro carro){
        Carro nuevoCarro = usuarioService.saveCarro(idUsuario, carro);
        return ResponseEntity.ok(nuevoCarro);
    }
    @PostMapping("/saveMoto/{idUsuario}")
    public ResponseEntity<Moto> saveMoto(@PathVariable long idUsuario, @RequestBody Moto moto){
        Moto nuevaMoto = usuarioService.saveMoto(idUsuario, moto);
        return ResponseEntity.ok(nuevaMoto);
    }





















    @GetMapping("getUserAndVehiculos/{usuarioId}")
    public ResponseEntity<Map<String, Object>> getUserAndVehiculos(@PathVariable long usuarioId){
        Map<String, Object> datos = usuarioService.getUserAndVehiculos(usuarioId);
        return ResponseEntity.ok(datos);
    }
}
