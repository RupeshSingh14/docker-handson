create table product(
    id serial PRIMARY KEY,
    name VARCHAR(50),
    price numeric(10,2) NOT NULL   
);

insert into product(name, price) 
values
('tv', 100.12),
('iphone', 300),
('ipad', 250.3);