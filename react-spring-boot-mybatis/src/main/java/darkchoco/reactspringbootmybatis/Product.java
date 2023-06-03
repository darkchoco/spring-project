package darkchoco.reactspringbootmybatis;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product {

    private int product_code;
    private String product_name;
    private String description;
    private BigDecimal price;
    private String filename;
}
