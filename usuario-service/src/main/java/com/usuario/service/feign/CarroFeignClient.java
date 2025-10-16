package com.usuario.service.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.usuario.service.models.Carro;

@FeignClient(name = "carro-services", url = "http://localhost:8002")
public interface CarroFeignClient {
    @PostMapping("/api/carro/save")
    public Carro saveCarro(@RequestBody Carro carro);

    @GetMapping("/api/carro/usuario/{usuarioId}")
    public List<Carro> carrosByUser(@PathVariable long usuarioId);
}
