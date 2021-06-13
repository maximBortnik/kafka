# For Java 11
FROM openjdk:11-jre

### install app
RUN mkdir /var/lib/client-app
WORKDIR /var/lib/client-app
ARG JAR_FILE=./build/libs/client-app.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080

### run app
RUN echo -e '#!/bin/sh \n java -jar app.jar' > run.sh
RUN chmod +x run.sh
CMD ./run.sh
