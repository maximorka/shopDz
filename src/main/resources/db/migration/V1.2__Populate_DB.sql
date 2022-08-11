INSERT INTO action_product(rule, price)
VALUES
(3,1.0),
(6,0.8);
SELECT * FROM action_product;

INSERT INTO product(name, price, action)
VALUES
('A',1.25,1),
('B',4.25,null),
('C',1.0,2),
('D',0.75,null);
SELECT * FROM product;


