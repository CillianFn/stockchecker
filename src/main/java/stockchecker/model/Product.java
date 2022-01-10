package stockchecker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    private String id;
    private String name;
    private String description;

    public Product(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format(
                "Product[id=%s, name='%s', description='%s']",
                id, name, description);
    }
}
