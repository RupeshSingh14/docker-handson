FROM bellsoft/liberica-openjdk-alpine:17.0.6

WORKDIR /rupesh/app

COPY /build/libs/*.jar app.jar

EXPOSE 8112

CMD ["java", "-jar", "app.jar"]