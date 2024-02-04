FROM gradle AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM eclipse-temurin:17-jdk-focal
COPY --from=build /home/gradle/src/build/libs/consumer-1.jar /app/consumer-1.jar
ENTRYPOINT ["java", "-jar", "app/consumer-1.jar"]