package stockchecker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import stockchecker.model.Product;
import stockchecker.storage.ProductRepository;

@SpringBootApplication
public class Application implements CommandLineRunner {
    @Autowired
    private ProductRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        repository.deleteAll();

        // save a couple of customers
        repository.save(new Product("Alice", "Smith"));
        repository.save(new Product("Bob", "Smith"));

        // fetch all customers
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (Product product : repository.findAll()) {
            System.out.println(product);
        }
        System.out.println();

        // fetch an individual customer
        System.out.println("Product found with findByName('Product'):");
        System.out.println("--------------------------------");
        System.out.println(repository.findByName("Alice"));
    }
}
