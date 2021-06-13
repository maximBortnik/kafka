# For Java 11
FROM openjdk:11-jre

### install app
RUN mkdir /var/lib/pizzeria-app
WORKDIR /var/lib/pizzeria-app
ARG JAR_FILE=./build/libs/pizzeria-app.jar
COPY ${JAR_FILE} app.jar

### run app
RUN echo -e '#!/bin/sh \n java -jar app.jar' > run.sh
RUN chmod +x run.sh
CMD ./run.sh
