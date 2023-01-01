# BUILD STAGE
FROM gradle:jdk17 as build
RUN mkdir /build
WORKDIR /build
COPY ./ /build

RUN gradle build

# RUN STAGE
FROM openjdk:17-alpine
WORKDIR /opt/app
COPY --from=build /build/build/libs /opt/app/
ENTRYPOINT ["java","-jar", "authentication-server-0.0.1-SNAPSHOT.jar"]
