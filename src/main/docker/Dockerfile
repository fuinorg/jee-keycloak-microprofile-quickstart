FROM fuinorg/wildfly-keycloak-microprofile
MAINTAINER ${company}

COPY maven/${project.build.finalName}.${project.packaging} ${JBOSS_HOME}/standalone/deployments

# BEGIN Patch Yasson
COPY yasson-1.0.2.jar $JBOSS_HOME/modules/system/layers/base/org/eclipse/yasson/main
RUN sed -i -e 's/yasson-1.0.1.jar/yasson-1.0.2.jar/' $JBOSS_HOME/modules/system/layers/base/org/eclipse/yasson/main/module.xml
RUN rm $JBOSS_HOME/modules/system/layers/base/org/eclipse/yasson/main/yasson-1.0.1.jar
# END Patch Yasson
