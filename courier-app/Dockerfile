# For Java 11
FROM openjdk:11-jre

### install app
RUN mkdir /var/lib/courier-app
WORKDIR /var/lib/courier-app
ARG JAR_FILE=./build/libs/courier-app.jar
COPY ${JAR_FILE} app.jar

### run app
RUN echo -e '#!/bin/sh \n java -jar app.jar' > run.sh
RUN chmod +x run.sh
CMD ./run.sh
