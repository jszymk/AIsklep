package app.dto.products;

import lombok.Data;

@Data
public class ProductOrderProductDTO {
    private Long quantity;
    private ProductDTO product;
}
