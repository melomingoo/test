# kakao
webflux

#Build 파일 JAR 파일 생성 
1. local docker 환경 구축
2. IntelliJ에서 Spring Build하여 jar 파일 생성


#컨테이너로 감싸기 
3. Dockerfile 생성 
FROM adoptopenjdk/openjdk11  #JDK11에 사용할 경량하된 JDK 

ARG JAR_FILE_PATH=target/*.jar #Target폴더 내 빌드된 Jar파일

COPY ${JAR_FILE_PATH} app.jar # qusrudgkf app.jar 파일 

EXPOSE 1234   # Docker 노출할 Port 

ENTRYPOINT ["java", "-jar", "app.jar"]

CMD ["./mvnw", "clean", "package"]

4. docker build -t prodoneo .

5. 생성된  Docker file 

REPOSITORY       TAG       IMAGE ID       CREATED        SIZE
prodoneo         latest    d41317dec829   24 hours ago   457MB
docker-example   0.0.1     665a434b2bb8   26 hours ago   437MB
<none>           <none>    808c56ad6326   26 hours ago   437MB
  
6. Docker run prodneo
C:\Users\mingoo>docker run prodoneo

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v2.6.1)
