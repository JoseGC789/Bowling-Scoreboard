FROM maven:3.6-jdk-8 AS build
COPY ten-pin-scoreboard/src /home/app/ten-pin-scoreboard/src
COPY ten-pin-scoreboard/pom.xml /home/app/ten-pin-scoreboard
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml package

FROM openjdk:8
COPY --from=build /home/app/ten-pin-scoreboard/target/ten-pin-scoreboard-1.0-SNAPSHOT.jar /usr/local/lib/ten-pin-scoreboard.jar
ENV SCOREBOARD_PATH=/home/app/game.txt
ENTRYPOINT ["java","-jar","/usr/local/lib/ten-pin-scoreboard.jar"r]