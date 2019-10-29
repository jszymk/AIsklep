package app.dto.products;

import lombok.Data;

@Data
public class ProductSearchDTO {
    private Long categoryId;
    private String searchPhrase;
}
