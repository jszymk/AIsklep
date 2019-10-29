CREATE TABLE product_order_product
(
     id bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
     product_order_id bigint not null,
     product_id bigint not null,
     quantity bigint not null,
     foreign key(product_id) REFERENCES product(id),
     foreign key(product_order_id) references product_order(id)
)
;