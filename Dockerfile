FROM quay.io/wildfly/wildfly:31.0.0.Final-jdk20

USER root
WORKDIR /opt/jboss
RUN wget https://repo1.maven.org/maven2/org/postgresql/postgresql/42.2.5/postgresql-42.2.5.jar
USER jboss

ADD wildfly-docker-config.sh /
ADD commands.cli /

RUN /wildfly-docker-config.sh

ADD target/*.war /opt/jboss/wildfly/standalone/deployments/