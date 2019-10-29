CREATE TABLE product
(
     id bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
     name varchar(255) not null,
     price numeric(10, 2) not null,
     description varchar(1000) not null,
     image_path varchar(1000),
     category_id bigint not null,
     foreign key(category_id) references product_category(id)
)
;