# İlk Çalıştırma

1. Sistemde Maven kurulmuş ve path'e eklenmiş olmalıdır.
2. Sistemde JAVA v11+ kurulmuş olmalıdır.
3. application.properties içinde spring.datasource.url isteğe göre değiştirilir.
4. postgresql sunucusunun çalıştığı ve spring.datasource.url de belirtilen DB nin create edilmiş olduğu teyit edilir.
5. `mvn clean spring-boot:run` komutu çalıştırılır.

# Test Çalıştırma

`mvn clean test` komutu çalıştırılır.

# Docker Image Oluşturma Run Etme
`mvn clean package` ile jar dosyası export edilir.

`docker build -f Dockerfile -t todo-server:latest .` docker image oluşturulur

`docker run todo-server:latest` ile image run edilir.

#Swagger
Uygulama çalıştırıldıktan sonra `http://localhost:8082/swagger-ui/index.html#/`


Spring boot yardımları için [tıklayınız.](HELP.md)
