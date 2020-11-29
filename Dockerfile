FROM openjdk:11
ADD target/covid19-mysql.jar covid19-mysql.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "covid19-mysql.jar"]