FROM openjdk:11

EXPOSE 8083

ADD target/DevOps_Project-SNAPSHOT-01.jar DevOps_Project.jar

ENTRYPOINT ["java", "-jar", "/DevOps_Project.jar"]