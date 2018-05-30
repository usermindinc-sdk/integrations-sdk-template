FROM usermindinc/java:oraclejdk-8

#Stack commands to reduce layer creation
RUN apt-get update \
    && apt-get install jq

#ARG variables are only available during the build
#ARG JAR_FILE=$JAR_FILE
ARG JAR_FILE

#ENV variables are available as environment variables in the running container later
ENV JAR_FILE=$JAR_FILE

#Workdir is the destination in the docker container for the copied files that have relative directories
#The source is the same directory as the Dockerfile
WORKDIR /app

ADD target/${JAR_FILE} ${JAR_FILE}
COPY docker/start start


COPY src/main/resources/config.yaml /app/config.yaml

ENTRYPOINT ["/app/start", "server", "/app/config.yaml"]

#This should be the port appropriate to the service
EXPOSE 8089
