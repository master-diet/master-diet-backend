#
# Build stage
#
FROM maven:3.6.3-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN cd /home/app && mvn dependency:go-offline
RUN mvn -f /home/app/pom.xml clean package dependency:resolve

#
# Package stage
#
FROM openjdk:11-jre-slim
COPY --from=build /home/app/target/master-diet-backend-application.jar /usr/local/lib/master-diet-backend-application.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/usr/local/lib/master-diet-backend-application.jar"]