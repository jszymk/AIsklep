package app.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product")
@Data
public class Product {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

	@Column(name = "description")
    private String description;

	@Column(name = "image_path")
    private String imagePath;

	@ManyToOne
	@JoinColumn(name = "category_id")
    private ProductCategory category;

    @OneToMany(cascade = { CascadeType.ALL }, mappedBy = "product")
    private List<ProductOrderProduct> productOrders = new ArrayList<>();
}
