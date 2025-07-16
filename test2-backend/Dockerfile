FROM openjdk:11-jdk-slim

WORKDIR /app

# 로컬에서 빌드된 .jar 파일 복사
COPY build/libs/test2-backend-1.0.0.jar app.jar

EXPOSE 8080

# Spring Boot 실행
ENTRYPOINT ["java", "-jar", "app.jar"]
