package dev.davivieira;


//import net.lecousin.reactive.data.relational.repository.LcR2dbcRepositoryFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
/*
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;
*/
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.web.reactive.config.EnableWebFlux;

//import io.r2dbc.spi.ConnectionFactory;

import java.io.IOException;


//import net.lecousin.reactive.data.relational.h2.H2Configuration;


/*Because R2DBC repository support is enabled in our Spring Boot application by default
(spring.data.r2dbc.repositories.enabled=true), so that the @EnableR2dbcRepositories is not necessary.*/

//@EnableR2dbcRepositories
/*@EnableR2dbcRepositories(repositoryFactoryBeanClass = LcR2dbcRepositoryFactoryBean.class)
@Import(H2Configuration.class) // here you can change depending on the database you are using*/
@SpringBootApplication
public class App {

    /*
     * Spring Data R2DBC ConnectionFactoryInitializer provides a convenient way to configure and initialize
     * a connection factory for a reactive database connection in a Spring application.
     * It will scan data.sql in the classpath, execute SQL script to initialize the database when
     * the database is connected.
     */
    /*@Bean
    ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {

        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(connectionFactory);

        ClassPathResource cpr  =new ClassPathResource("data.sql");
        try {
            System.out.println("Absolute Path: " + cpr.getFile().getCanonicalPath());
        } catch (IOException e) {
            System.out.println("Absolute Path: " + e.getMessage());
        }

        initializer.setDatabasePopulator(new ResourceDatabasePopulator(new ClassPathResource("data.sql")));
        return initializer;
    }*/

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
