package stockchecker.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Stock {

    @Id
    private String id;
    private Product product;
    private int amount;
    private boolean inStock;

    public Stock(Product product, int amount, boolean inStock) {
        this.product = product;
        this.id = product.getId();
        this.amount = amount;
        this.inStock = inStock;
    }

    public Stock(Product product) {
        this.product = product;
        this.id = product.getId();
        this.amount = 1;
        this.inStock = true;
    }

    public void setAmount(int amount) {
        if (amount > 0) {
            this.amount = amount;
            this.inStock = true;
        } else {
            this.amount = 0;
            this.inStock = false;
        }
    }

    @Override
    public String toString() {
        return String.format(
                "Stock[Product id=%s, amount='%s', inStock='%s']",
                product.getId(), amount, inStock);
    }
}
