CREATE TABLE product_order
(
     id bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
     user_id bigint not null,
     foreign key(user_id) REFERENCES user(id)
)
;