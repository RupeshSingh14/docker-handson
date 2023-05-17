FROM gradle:jdk17-jammy AS BUILDER
WORKDIR build
COPY src src
COPY build.gradle build.gradle
#COPY /gradle/wrapper/ /gradle/wrapper/
COPY settings.gradle settings.gradle
RUN gradle build -x test
#RUN $PWD

#-------------------------------------
FROM bellsoft/liberica-openjdk-alpine:17.0.6
WORKDIR /rupesh/app
EXPOSE 8111
COPY --from=BUILDER /home/gradle/build/build/libs/*.jar app.jar
CMD ["java", "-jar", "app.jar"]