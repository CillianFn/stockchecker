package stockchecker.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
public class Product {
    // TODO work on required fields, e.g. id as UUID, name & id required, desc not required, look at idempotent PUT request
    @Id
    public String id;

    public String name;
    public String description;

    public Product(String id) {
        this.id = id;
    }

    public Product(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format(
                "Product[id=%s, name='%s', description='%s']",
                id, name, description);
    }
}
