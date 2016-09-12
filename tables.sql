-- DROP TABLE IF EXISTS shop, product, products_shop, ratings, usertable, listproductitem;

CREATE TABLE "usertable"
(
  id uuid NOT NULL,
  username character varying(250) NOT NULL,
  password character varying(250) NOT NULL,
  CONSTRAINT pk_userid PRIMARY KEY (id),
  CONSTRAINT unq_username UNIQUE (username)
)
WITH (
  OIDS=FALSE
);


CREATE TABLE product
(
  product_id uuid NOT NULL,
  product_name character varying(250) NOT NULL,
  CONSTRAINT pk_product_id PRIMARY KEY (product_id)
)
WITH (
  OIDS=FALSE
);

CREATE TABLE shop
(
  shop_id uuid NOT NULL,
  shop_name character varying(250) NOT NULL,
  latitude double precision,
  longtitude double precision,
  CONSTRAINT pk_shop_id PRIMARY KEY (shop_id)
)
WITH (
  OIDS=FALSE
);


CREATE TABLE products_shop
(
  product_id uuid NOT NULL,
  shop_id uuid NOT NULL,
  value integer NOT NULL,
  CONSTRAINT fk_product_id FOREIGN KEY (product_id)
      REFERENCES product (product_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_shop_id FOREIGN KEY (shop_id)
      REFERENCES shop (shop_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

CREATE TABLE "listproductitem"
(
  id uuid NOT NULL,
  user_id uuid NOT NULL,
  shop_id uuidL,
  product_id uuid NOT NULL,
  completed boolean,
  rated boolean,
  real_value integer,
  CONSTRAINT pk_id PRIMARY KEY (id),
  CONSTRAINT fk_list_product_id FOREIGN KEY (product_id)
      REFERENCES product (product_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_list_shop_id FOREIGN KEY (shop_id)
      REFERENCES shop (shop_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_list_user_id FOREIGN KEY (user_id)
      REFERENCES "usertable" (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

   
CREATE TABLE ratings
(
  list_product_item_id uuid NOT NULL,
  value integer NOT NULL,
  note character varying(2500),
  CONSTRAINT fk_list_product_item_id FOREIGN KEY (list_product_item_id)
      REFERENCES "listproductitem" (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
  
  
  
  
  










INSERT INTO product(product_id, product_name) VALUES ('3ff70b61-9f8a-4bc7-8402-091e8e6c7618',  'glass');


INSERT INTO shop(shop_id, shop_name, latitude, longtitude) VALUES ('33a70b61-9f8a-4bc7-8402-091e8e6c7618',  'mall', '37.971421', '23.726166');

INSERT INTO shop(shop_id, shop_name, latitude, longtitude) VALUES ('43a70b61-9f8a-4bc7-8402-091e8e6c7618',  'blah', '37.971423', '23.726168');

INSERT INTO products_shop(product_id, shop_id, value) VALUES ('3ff70b61-9f8a-4bc7-8402-091e8e6c7618', '33a70b61-9f8a-4bc7-8402-091e8e6c7618', '3');
INSERT INTO products_shop(product_id, shop_id, value) VALUES ('3ff70b61-9f8a-4bc7-8402-091e8e6c7618', '43a70b61-9f8a-4bc7-8402-091e8e6c7618', '2');
























