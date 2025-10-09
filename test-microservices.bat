@echo off
echo ========================================
echo TESTING MICROSERVICES CONNECTIVITY
echo ========================================

echo.
echo 1. Starting Usuario Service (Port 8001)...
start "Usuario Service" cmd /k "cd usuario-service && mvn spring-boot:run"

echo.
echo Waiting 15 seconds for Usuario Service to start...
timeout /t 15 /nobreak > nul

echo.
echo 2. Starting Carro Service (Port 8002)...
start "Carro Service" cmd /k "cd carro-services && mvn spring-boot:run"

echo.
echo Waiting 15 seconds for Carro Service to start...
timeout /t 15 /nobreak > nul

echo.
echo 3. Starting Moto Service (Port 8003)...
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
curl -X GET http://localhost:8001/api/usuario
echo.
curl -X POST http://localhost:8001/api/usuario/save -H "Content-Type: application/json" -d "{\"nombre\":\"Juan\",\"email\":\"juan@test.com\"}"

echo.
echo.
echo Testing Carro Service endpoints...
curl -X GET http://localhost:8002/api/carro
echo.
curl -X POST http://localhost:8002/api/carro/save -H "Content-Type: application/json" -d "{\"marca\":\"Toyota\",\"modelo\":\"Corolla\",\"usuarioId\":1}"

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
