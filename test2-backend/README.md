## Eclipse에서 Docker로 Back-end 실행하기

### 사전 준비


1. `Eclipse`에서 프로젝트 빌드

   - `Gradle Tasks View` 열기

     ```
     Window > Show View > Gradle Tasks
     ```
   - `build` 또는 `bootJar` 실행하여 `.jar` 파일 생성
   
   
2. 빌드 결과 확인

   - `build/libs/` 폴더에 `.jar` 생성 확인

   - 예: `test2-backend-1.0.0.jar`
   
  
   
### 📦 Docker 빌드 및 실행


1. Dockerfile 작성 (프로젝트 루트 위치)

```dockerfile
FROM openjdk:11-jdk-slim

WORKDIR /app

COPY build/libs/test2-backend-1.0.0.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
```


2. Eclipse 터미널 (또는 외부 터미널)에서 명령어 실행

```bash
docker build -t test2-backend .
docker run -p 8080:8080 test2-backend
```