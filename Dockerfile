FROM amazoncorretto:21
ENV SPRING_PROFILES_ACTIVE=env
WORKDIR /
COPY build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]