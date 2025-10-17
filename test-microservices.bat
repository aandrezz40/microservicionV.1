@echo off
echo ========================================
echo TESTING MICROSERVICES CONNECTIVITY
echo ========================================

echo.
echo 1. Starting Config Service (Port 8084)...
start "Config Service" cmd /k "cd config-service && mvn spring-boot:run"

echo.
echo Waiting 20 seconds for Config Service to start...
timeout /t 20 /nobreak > nul

echo.
echo 2. Starting Eureka Service (Port 8761)...
start "Eureka Service" cmd /k "cd eureka-service && mvn spring-boot:run"

echo.
echo Waiting 15 seconds for Eureka Service to start...
timeout /t 15 /nobreak > nul

echo.
echo 3. Starting Gateway Service (Port 9090)...
start "Gateway Service" cmd /k "cd gateway-service && mvn spring-boot:run"

echo.
echo Waiting 10 seconds for Gateway Service to start...
timeout /t 10 /nobreak > nul

echo.
echo 4. Starting Usuario Service (Port 8081)...
start "Usuario Service" cmd /k "cd usuario-service && mvn spring-boot:run"

echo.
echo Waiting 15 seconds for Usuario Service to start...
timeout /t 15 /nobreak > nul

echo.
echo 4. Starting Carro Service (Port 8082)...
start "Carro Service" cmd /k "cd carro-services && mvn spring-boot:run"

echo.
echo Waiting 15 seconds for Carro Service to start...
timeout /t 15 /nobreak > nul

echo.
echo 5. Starting Moto Service (Port 8083)...
start "Moto Service" cmd /k "cd moto-services && mvn spring-boot:run"

echo.
echo Waiting 20 seconds for all services to start...
timeout /t 20 /nobreak > nul

echo.
echo ========================================
echo TESTING ENDPOINTS
echo ========================================

echo.
echo Testing Usuario Service endpoints...
curl -X GET http://localhost:8081/api/usuario
echo.
curl -X POST http://localhost:8081/api/usuario/save -H "Content-Type: application/json" -d "{\"nombre\":\"Juan\",\"email\":\"juan@test.com\"}"

echo.
echo.
echo Testing Carro Service endpoints...
curl -X GET http://localhost:8082/api/carro
echo.
curl -X POST http://localhost:8082/api/carro/save -H "Content-Type: application/json" -d "{\"marca\":\"Toyota\",\"modelo\":\"Corolla\",\"usuarioId\":1}"

echo.
echo.
echo Testing Moto Service endpoints...
curl -X GET http://localhost:8003/api/moto
echo.
curl -X POST http://localhost:8003/api/moto/save -H "Content-Type: application/json" -d "{\"marca\":\"Honda\",\"modelo\":\"CBR\",\"usuarioId\":1}"

echo.
echo.
echo Testing cross-service communication...
curl -X GET http://localhost:8001/api/usuario/carros/1
echo.
curl -X GET http://localhost:8001/api/usuario/motos/1

echo.
echo ========================================
echo TEST COMPLETED
echo ========================================
pause
