drop table if exists public.Users cascade;
drop table if exists public.User_Payment cascade;
drop table if exists public.Orders cascade;
drop table if exists public.Order_Details cascade;
drop table if exists public.Sessions cascade;
drop table if exists public.Product cascade;
drop table if exists public.Product_Inventory cascade;
drop table if exists public.Category cascade;
drop table if exists public.Discount cascade;
drop table if exists public.Cart cascade;

create table Users (
	user_Id serial not null primary key,
	userName varchar(255) not null,
	password text not null,
	first_name varchar(255) not null,
	last_name varchar(255) not null,
	email varchar(255) not null,
	phone int not null,
	address text not null,
	role varchar(10) not null,
	created_at timestamp not null,
	modified_at timestamp not null
);

create table User_Payment (
	user_payment_id serial not null primary key,
	id_user int not null,
	payment_type varchar(255) not null,
	provider varchar(255) not null,
	account_no int not null,
	expiry date not null
);

create table Orders (
	order_id serial not null primary key,
	id_user int not null,
	total decimal not null,
	id_payment int not null,
	created_at timestamp not null,
	modified_at timestamp not null
);

create table Order_Details (
	order_detail_id serial not null primary key,
	id_order int not null,
	id_product int not null,
	quantity int not null,
	created_at timestamp not null,
	modified_at timestamp not null
);

create table Sessions (
	session_id serial not null primary key,
	id_user int not null,
	total decimal not null,
	created_at timestamp not null,
	modified_at timestamp not null
);

create table Product (
	product_id serial not null primary key,
	product_Name varchar(255) not null,
	product_Desc text not null,
	id_category int not null,
	id_inventory int not null,
	price decimal not null,
	id_discount int not null,
	created_at timestamp not null,
	modified_at timestamp not null,
	deleted_at timestamp not null
);

create table Product_Inventory (
	product_inventory_id serial not null primary key,
	quantity int not null,
	created_at timestamp not null,
	modified_at timestamp not null,
	deleted_at timestamp not null
);

create table Category (
	category_id serial not null primary key,
	category_name varchar(255) not null,
	created_at timestamp not null,
	modified_at timestamp not null,
	deleted_at timestamp not null
);

create table Discount (
	discount_id serial not null primary key,
	discount_name varchar(255) not null,
	discount_desc text not null,
	discount_percent decimal not null,
	active boolean not null,
	created_at timestamp not null,
	modified_at timestamp not null,
	deleted_at timestamp not null
);

create table Cart (
	cart_id serial not null primary key,
	id_session int not null,
	id_product int not null,
	quantity int not null,
	created_at timestamp not null,
	modified_at timestamp not null
);

alter table user_payment 
add constraint payment_user_fk
				foreign key (id_user)
				references users(user_id);
				
alter table order_details 
add constraint details_order_fk
				foreign key (id_order)
				references orders(order_id);
				
alter table order_details 
add constraint details_product_fk
				foreign key (id_product)
				references product(product_id);
				
alter table orders 
add constraint order_user_fk
				foreign key (id_user)
				references users(user_id);
				
alter table sessions 
add constraint session_user_fk
				foreign key (id_user)
				references users(user_id);
				
alter table cart 
add constraint cart_session_fk
				foreign key (id_session)
				references sessions(session_id);
				
alter table cart 
add constraint cart_product_fk
				foreign key (id_product)
				references product(product_id);
				
alter table product 
add constraint product_category_fk
				foreign key (id_category)
				references category(category_id);
				
alter table product 
add constraint product_inventory_fk
				foreign key (id_inventory)
				references product_inventory(product_inventory_id);
				
alter table product 
add constraint product_discount_fk
				foreign key (id_discount)
				references discount(discount_id);