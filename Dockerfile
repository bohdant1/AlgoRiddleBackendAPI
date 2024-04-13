#FROM amazoncorretto:21
#ENV SPRING_PROFILES_ACTIVE=env
#WORKDIR /
#COPY build/libs/*.jar app.jar
#EXPOSE 8080
#ENTRYPOINT ["java","-jar","/app.jar"]

FROM amazoncorretto:21 AS build
WORKDIR /app
COPY build.gradle .
COPY settings.gradle .
COPY gradlew .
COPY gradle ./gradle
COPY src ./src
RUN ./gradlew build -x test

# Use a smaller base image for the final container
FROM amazoncorretto:21-alpine
WORKDIR /app
COPY --from=build /app/build/libs/*.jar /app/app.jar
CMD ["java", "-jar", "/app/app.jar"]