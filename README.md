# 🚗 Microservicios Tutorial

Este proyecto implementa una arquitectura de microservicios con Spring Boot que incluye:

- **usuario-service** (Puerto 8001) - Gestión de usuarios
- **carro-services** (Puerto 8002) - Gestión de vehículos tipo carro
- **moto-services** (Puerto 8003) - Gestión de vehículos tipo moto

## 🏗️ Arquitectura

```
┌─────────────────┐    HTTP/REST    ┌─────────────────┐
│  usuario-service│ ──────────────► │  carro-services │
│   (Puerto 8001) │                 │   (Puerto 8002) │
└─────────────────┘                 └─────────────────┘
         │                                   ▲
         │ HTTP/REST                         │
         ▼                                   │
┌─────────────────┐    HTTP/REST    ┌─────────────────┐
│  moto-services  │ ◄────────────── │                 │
│   (Puerto 8003) │                 │                 │
└─────────────────┘                 └─────────────────┘
```

## 🚀 Cómo Ejecutar

### Opción 1: Script Automático (Recomendado)
```bash
# Ejecuta el script que inicia todos los servicios y hace pruebas
test-microservices.bat
```

### Opción 2: Manual
```bash
# Terminal 1 - Usuario Service
cd usuario-service
mvn spring-boot:run

# Terminal 2 - Carro Service  
cd carro-services
mvn spring-boot:run

# Terminal 3 - Moto Service
cd moto-services
mvn spring-boot:run
```

## 📡 Endpoints Disponibles

### Usuario Service (Puerto 8001)
- `GET /api/usuario` - Listar todos los usuarios
- `GET /api/usuario/{id}` - Obtener usuario por ID
- `POST /api/usuario/save` - Crear nuevo usuario
- `GET /api/usuario/carros/{usuarioId}` - Obtener carros de un usuario
- `GET /api/usuario/motos/{usuarioId}` - Obtener motos de un usuario

### Carro Service (Puerto 8002)
- `GET /api/carro` - Listar todos los carros
- `GET /api/carro/{id}` - Obtener carro por ID
- `POST /api/carro/save` - Crear nuevo carro
- `GET /api/carro/usuario/{usuarioId}` - Obtener carros por usuario

### Moto Service (Puerto 8003)
- `GET /api/moto` - Listar todas las motos
- `GET /api/moto/{id}` - Obtener moto por ID
- `POST /api/moto/save` - Crear nueva moto
- `GET /api/moto/usuario/{usuarioId}` - Obtener motos por usuario

## 🧪 Pruebas de Conectividad

### Crear Usuario
```bash
curl -X POST http://localhost:8001/api/usuario/save \
  -H "Content-Type: application/json" \
  -d '{"nombre":"Juan","email":"juan@test.com"}'
```

### Crear Carro
```bash
curl -X POST http://localhost:8002/api/carro/save \
  -H "Content-Type: application/json" \
  -d '{"marca":"Toyota","modelo":"Corolla","usuarioId":1}'
```

### Crear Moto
```bash
curl -X POST http://localhost:8003/api/moto/save \
  -H "Content-Type: application/json" \
  -d '{"marca":"Honda","modelo":"CBR","usuarioId":1}'
```

### Obtener Vehículos de Usuario (Comunicación Cross-Service)
```bash
# Obtener carros del usuario 1
curl http://localhost:8001/api/usuario/carros/1

# Obtener motos del usuario 1  
curl http://localhost:8001/api/usuario/motos/1
```

## 🔧 Configuración

### Bases de Datos
- Cada microservicio usa H2 en memoria
- Consola H2 disponible en: `http://localhost:{puerto}/h2-console`
- JDBC URL: `jdbc:h2:mem:testdb`
- Usuario: `sa`
- Contraseña: (vacía)

### Timeouts
- Timeout de conexión: 5 segundos
- Timeout de lectura: 10 segundos
- Manejo de errores implementado

## ⚠️ Consideraciones para Producción

1. **Service Discovery**: Implementar Eureka Server
2. **Load Balancing**: Configurar Ribbon o similar
3. **Circuit Breaker**: Agregar Hystrix o Resilience4j
4. **Bases de Datos**: Cambiar de H2 a PostgreSQL/MySQL
5. **Logging**: Implementar log centralizado
6. **Monitoring**: Agregar Actuator y Micrometer

## 🐛 Troubleshooting

### Error de Conexión
- Verificar que todos los servicios estén ejecutándose
- Comprobar que los puertos no estén ocupados
- Revisar logs de consola para errores específicos

### Error 404
- Verificar que los endpoints estén correctos
- Comprobar que el servicio esté completamente iniciado

### Timeout
- Los servicios pueden tardar hasta 30 segundos en iniciar completamente
- Verificar logs para confirmar que Spring Boot terminó de cargar
