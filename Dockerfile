FROM ibm-semeru-runtimes:open-17-jdk-focal

ADD ./target/blog-0.0.1-SNAPSHOT.jar .

CMD ["java", "-jar","blog-0.0.1-SNAPSHOT.jar"]

ENV SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/blog
ENV SPRING_DATASOURCE_USERNAME=admin
ENV SPRING_DATASOURCE_PASSWORD=admin
ENV SPRING_JPA_PROPERTIES_HIBERNATE_DIALECT=org.hibernate.dialect.MySQLDialect
ENV SPRING_JPA_HIBERNATE_DDL-AUTO=update


EXPOSE 8080