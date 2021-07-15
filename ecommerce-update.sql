drop table if exists public.Users cascade;
drop table if exists public.User_Payment cascade;
drop table if exists public.User_Address cascade;
drop table if exists public.User_Status cascade;
drop table if exists public.Role cascade;
drop table if exists public.User_role cascade;
drop table if exists public.Image cascade;
drop table if exists public.Orders cascade;
drop table if exists public.Order_Details cascade;
drop table if exists public.Order_Status cascade;
drop table if exists public.Sessions cascade;
drop table if exists public.Product cascade;
drop table if exists public.Product_Image cascade;
drop table if exists public.Category cascade;
drop table if exists public.Category_Image cascade;
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
	is_deleted boolean not null,
	created_at timestamp not null,
	modified_at timestamp not null,
	status int not null
);

create table User_Status (
	user_status_id serial not null primary key,
	status varchar(255) not null
);

create table User_Payment (
	user_payment_id serial not null primary key,
	id_user int not null,
	payment_type varchar(255) not null,
	provider varchar(255) not null,
	account_no int not null,
	expiry date not null
);

create table User_Address (
	user_address_id serial not null primary key,
	id_user int not null,
	address text not null,
	city varchar(255) not null,
	postal_code varchar(255) not null,
	country varchar(255) not null
);

create table Image (
	image_id serial not null primary key,
	url varchar(255) not null,
	image_desc text not null,
	created_at timestamp not null,
	modified_at timestamp not null,
	is_deleted boolean not null
);

create table Role (
	role_id serial not null primary key,
	role_name varchar(255) not null,
	created_at timestamp not null,
	modified_at timestamp not null,
	is_deleted boolean not null
);

create table User_Role (
	id_user int,
	id_role int,
	primary key(id_user, id_role)
);

create table Orders (
	order_id serial not null primary key,
	id_user int not null,
	total decimal not null,
	id_payment int not null,
	status int not null,
	created_at timestamp not null,
	modified_at timestamp not null
);

create table Order_Status (
	order_status_id serial not null primary key,
	status varchar(255) not null,
	created_at timestamp not null,
	modified_at timestamp not null,
	is_deleted boolean not null
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
	quantity int not null,
	price decimal not null,
	id_discount int,
	created_at timestamp,
	modified_at timestamp,
	is_deleted boolean not null default false
);

create table Product_Image (
	id_product int,
	id_image int,
	primary key(id_product, id_image)
);

create table Category (
	category_id serial not null primary key,
	category_name varchar(255) not null,
	created_at timestamp,
	modified_at timestamp,
	is_deleted boolean not null default false
);

create table Category_Image (
	id_category int,
	id_image int,
	primary key(id_category, id_image)
);

create table Discount (
	discount_id serial not null primary key,
	discount_name varchar(255) not null,
	discount_desc text not null,
	discount_percent decimal not null,
	active boolean not null,
	created_at timestamp not null,
	modified_at timestamp not null,
	is_deleted boolean not null
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
			
alter table user_address
add constraint address_user_fk
				foreign key (id_user)
				references users(user_id);
			
alter table users
add constraint user_status_fk
				foreign key (status)
				references user_status(user_status_id);
			
alter table user_role
add constraint user_role_fk1
				foreign key (id_user)
				references users(user_id);
			
alter table user_role
add constraint user_role_fk2
				foreign key (id_role)
				references role(role_id);

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
			
alter table orders 
add constraint order_payment_fk
				foreign key (id_payment)
				references user_payment(user_payment_id);
			
alter table orders 
add constraint order_status_fk
				foreign key (status)
				references order_status(order_status_id);
				
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
add constraint product_discount_fk
				foreign key (id_discount)
				references discount(discount_id);
				
alter table product_image
add constraint product_image_fk1
				foreign key (id_product)
				references product(product_id);
				
alter table product_image
add constraint product_image_fk2
				foreign key (id_image)
				references image(image_id);
			
alter table category_image
add constraint category_image_fk1
				foreign key (id_category)
				references category(category_id);
				
alter table category_image
add constraint category_image_fk2
				foreign key (id_image)
				references image(image_id);