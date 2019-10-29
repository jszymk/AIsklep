package app.model;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Data
public class User {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "is_active")
    @Type(type = "yes_no")
    private Boolean isActive;

    @OneToMany(cascade = { CascadeType.ALL }, mappedBy = "user")
    private List<ProductOrder> orders = new ArrayList<>();

    @Column(name = "register_token")
    private String registerToken;
}
