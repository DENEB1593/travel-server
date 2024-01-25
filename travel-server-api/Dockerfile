FROM gradle:7.6-jdk AS build

COPY build.gradle settings.gradle  ./

FROM openjdk:17.0.1-jdk-slim AS run

RUN adduser --system --group app-user

COPY --from=build --chown=travel:travel build/libs/travel-server-api-*.jar travel-server-api.jar

EXPOSE 8080
USER travel

CMD ["java", "-jar", "travel-server-api.jar"]