package app.dto.products;

import lombok.Data;

import java.util.List;

@Data
public class ProductOrderDTO {
    private Long id;
    private List<ProductOrderProductDTO> products;
}
