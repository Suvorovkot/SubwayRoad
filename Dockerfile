FROM openjdk:jre-alpine
WORKDIR /opt/docker
ADD --chown=daemon:daemon opt /opt
USER daemon
ENTRYPOINT ["/opt/docker/bin/subwayroad"]
CMD []
USER root
RUN ["apk", "add", "--no-cache", "bash"]
