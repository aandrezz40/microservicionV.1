package com.usuario.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.usuario.service.models.Moto;
import java.util.List;

@FeignClient(name = "moto-services", url = "http://localhost:8003")
public interface MotoFeignClient {
    @PostMapping("/api/moto/save")
    public Moto saveMoto(@RequestBody Moto moto);

    @GetMapping("/api/moto/usuario/{usuarioId}")
    public List<Moto> motoByUser(@PathVariable long usuarioId);
 
}
