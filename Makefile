build:
	docker build -t ten-pin-scoreboard -f ten-pin-scoreboard/src/docker/Dockerfile .

run:
	docker run --rm -itv ${SCOREBOARD_PATH}:/home/app/game.txt ten-pin-scoreboard:latest ten-pin-scoreboard

package:
	mvn clean package

native:
	java -D${SCOREBOARD_PATH} -jar ten-pin-scoreboard/target/ten-pin-scoreboard-1.0-SNAPSHOT.jar