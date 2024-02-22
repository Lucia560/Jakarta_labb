FROM quay.io/wildfly/wildfly:31.0.0.Final-jdk20
ADD target/*.war /opt/jboss/wildfly/standalone/deployments/