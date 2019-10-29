CREATE TABLE user
(    
     id bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
     password varchar(255) not null,
     email varchar(255) not null,
     address varchar(255) not null,
     phone varchar(255),
     is_active varchar(1) default 'N' not null,
     register_token varchar(255)
)
;