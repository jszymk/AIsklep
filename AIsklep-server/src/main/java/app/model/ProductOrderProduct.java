package app.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "product_order_product")
@Data
public class ProductOrderProduct {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private String id;

    @Column(name = "quantity")
    private Long quantity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "product_order_id")
    private ProductOrder productOrder;
}
