FROM maven:3.6-jdk-8 AS build
COPY ten-pin-roll/src /home/app/ten-pin-roll/src
COPY ten-pin-roll/pom.xml /home/app/ten-pin-roll
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml package

FROM openjdk:8
COPY --from=build /home/app/ten-pin-roll/target/ten-pin-roll-1.0-SNAPSHOT.jar /usr/local/lib/ten-pin-roll.jar
ENV SCOREBOARD_PATH=/home/app/game.txt
ENTRYPOINT ["java","-jar","/usr/local/lib/ten-pin-roll.jar"]