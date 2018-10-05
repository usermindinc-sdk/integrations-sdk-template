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

#Uncomment one these lines to build a docker image that will run outside of Kubernetes -
#Otherwise Kubernetes will place the file
#COPY src/main/resources/config-staging.yaml /app/config/config.yaml
#COPY src/main/resources/config-prod.yaml /app/config/config.yaml

ENTRYPOINT ["/app/start"]

#This should be the port appropriate to the service
EXPOSE 8089
