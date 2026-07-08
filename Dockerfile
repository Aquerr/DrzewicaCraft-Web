FROM eclipse-temurin:25-jre

ARG VERSION=0.0.0
ARG COMMMIT_ID=HEAD

LABEL maintainer="Aquerr"
LABEL description="DrzewicaCraft Website"
LABEL version="${VERSION}-${COMMMIT_ID}"

RUN apt-get update \
 && apt-get install --update -y gosu \
 && rm -rf /var/lib/apt/lists/* \
    gosu nobody true

RUN mkdir -p /opt/app/config  \
    && mkdir -p /opt/app/data  \
    && mkdir -p /opt/app/logs

ENV APP_USER=appuser
ENV APP_GROUP=appgroup
ENV LANG="en_US.UTF-8"
ENV LANGUAGE="en_US:en"
ENV LC_ALL="en_US.UTF-8"

WORKDIR /opt/app

COPY drzewicacraft-backend/target/drzewicacraft-backend-1.0.0-SNAPSHOT.jar ./drzewicacraft-web.jar
COPY --chmod=755 entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh

USER root

EXPOSE 8083

ENTRYPOINT ["/entrypoint.sh"]
CMD ["java", "-jar", "/opt/app/drzewicacraft-app.jar"]