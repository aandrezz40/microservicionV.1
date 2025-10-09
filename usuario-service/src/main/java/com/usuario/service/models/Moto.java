package com.usuario.service.models;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Moto {
    private Long id;
    private String marca;
    private String modelo;
    private Long usuarioId;
}
