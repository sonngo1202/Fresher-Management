FROM openjdk:16

COPY target/fresher-manager-0.0.1-SNAPSHOT.jar /frehser-manager-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "/frehser-manager-0.0.1-SNAPSHOT.jar"]