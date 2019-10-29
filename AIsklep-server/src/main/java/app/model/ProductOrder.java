package app.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product_order")
@Data
public class ProductOrder {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = { CascadeType.ALL }, mappedBy = "productOrder")
    private List<ProductOrderProduct> products = new ArrayList<>();
}
