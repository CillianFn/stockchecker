package stockchecker.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Stock {

    // TODO - review schema, partition key, required fields etc.
    private Product product;
    private int amount;
    private boolean inStock;

    public Stock(Product product, int amount, boolean inStock) {
        this.product = product;
        this.amount = amount;
        this.inStock = inStock;
    }
    private void setInStock() {
        inStock = amount > 0 ? true : false;
    }

    @Override
    public String toString() {
        return String.format(
                "Stock[Product id=%s, amount='%s', inStock='%s']",
                product.getId(), amount, inStock);
    }
}
