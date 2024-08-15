FROM amazoncorretto:17
ADD target/portfolio-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ARG DBUSER
ENV DBUSER=root
ARG DBPASSWORD
ENV DBPASSWORD=root
CMD ["java","-jar","-Dspring.profiles.active=production", "app.jar"]