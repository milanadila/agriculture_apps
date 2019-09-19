insert into Users (Id, user_name, password, active)
values('u001', 'milan', '$2a$12$dtai13LIIuD/HEmYFXPLlurRLPCNm99LXVJZ96zgyzemdX8UH0twy', true);

insert into Users (Id, user_name, password, active)
values('u002', 'roy', '$2a$12$n8UqNfE2azdRd4qvWsg63eRhV3F18Vve1qkZieHVRdZydOJVIXuYq', true);

insert into Roles (Id, role_name)
values('adm', 'ROLE_ADMIN');

insert into Roles (Id, role_name)
values('cst', 'ROLE_CUSTOMER');

insert into User_Role (id_user, id_roles)
values('u001', 'adm');

insert into User_Role (id_user, id_roles)
values('u002', 'cst');

select user_name, password, active as enabled from Users where user_name = 'milan';

select u.user_name, r.role_name as authority from Users u join User_Role ur on u.Id = ur.id_user join Roles r on ur.id_roles = r.Id where u.user_name = 'milan';

insert into User (User_Id, username, Balance)
values (1,'Milan',500000);

insert into User (User_Id, username, Balance)
values (2,'Adila',600000);

insert into User (User_Id, username, Balance)
values (3,'Amalia',550000);

insert into User (User_Id, username, Balance)
values (4,'Milano',450000);

insert into User (User_Id, username, Balance)
values (5,'Roy',450000);

insert into User (User_Id, username, Balance)
values (6,'Kiyoshi',1000);

insert into Product (Product_Id, Name, Price, Stock_Product)
values (1,'Wortel',2000,100);

insert into Product (Product_Id, Name, Price, Stock_Product)
values (2,'Buncis',1000,90);

insert into Product (Product_Id, Name, Price, Stock_Product)
values (3,'Kol',3000,56);

insert into Product (Product_Id, Name, Price, Stock_Product)
values (4,'Tomat',1000,50);

insert into Product (Product_Id, Name, Price, Stock_Product)
values (5,'Kentang',4000,70);

insert into Product (Product_Id, Name, Price, Stock_Product)
values (6,'Paprika',5000,80);

insert into Product (Product_Id, Name, Price, Stock_Product)
values (7,'Ginseng',5000,3);

--insert into Product (Product_Id, Name, Price, Stock_Product)
--values (8,'Cabe',1000,3);
--
--insert into Product (Product_Id, Name, Price, Stock_Product)
--values (9,'Timun',2000,3);
--
--insert into Product (Product_Id, Name, Price, Stock_Product)
--values (10,'Jamur',3000,3);
--
--insert into Product (Product_Id, Name, Price, Stock_Product)
--values (11,'Pokcoi',2000,3);
--
--insert into Product (Product_Id, Name, Price, Stock_Product)
--values (12,'Brokoli',4000,3);
--
--insert into Product (Product_Id, Name, Price, Stock_Product)
--values (13,'Sawi',2000,3);
--
--insert into Product (Product_Id, Name, Price, Stock_Product)
--values (14,'Terong',5000,3);
--
--insert into Product (Product_Id, Name, Price, Stock_Product)
--values (15,'Melinjo',1000,3);


insert into Transaction (Transaction_Id, User_Id, Product_Id, User_Name, Product_Name, Total_Transaction, Created_Date, Qty_Product, Balance_User)
values (1, 1, 1, 'Milan', 'Wortel', 20000, '2019-06-17', 10, 430000);

insert into Transaction (Transaction_Id, User_Id, Product_Id, User_Name, Product_Name, Total_Transaction, Created_Date, Qty_Product, Balance_User)
values (2, 2, 3, 'Adila', 'Buncis', 10000, '2019-06-17', 10, 590000);
