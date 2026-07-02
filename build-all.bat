rem $env:DOCKER_BUILDKIT=0
rem docker rm -f $(docker ps -aq)
FOR /f %%i IN ('docker ps -aq') DO docker rm -f %%i
FOR /f %%i IN ('docker images -aq') DO docker rmi -f %%i

cd api-gateway
call .\mvnw clean package -DskipTests

cd ../bicicleta-service
call .\mvnw clean package -DskipTests

cd ../categoria-service
call .\mvnw clean package -DskipTests

cd ../cliente-service
call .\mvnw clean package -DskipTests

cd ../empleado-service
call .\mvnw clean package -DskipTests

cd ../facturacion-service
call .\mvnw clean package -DskipTests

cd ../inventario-service
call .\mvnw clean package -DskipTests

cd ../notificacion-service
call .\mvnw clean package -DskipTests

cd ../producto-service
call .\mvnw clean package -DskipTests

cd ../proveedor-service
call .\mvnw clean package -DskipTests

cd ../reparacion-service
call .\mvnw clean package -DskipTests