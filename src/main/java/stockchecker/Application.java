package stockchecker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    /*
        TODO
        1) Set up Spring project and be able to run locally and hit simple /health endpoint that returns 200
        2) Set up additional endpoints
        3) Set up database and be able to run locally and read/write
        4) Set up client that Spring project uses to read write
     */

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
