-- Active: 1678367692083@@127.0.0.1@3306@fastfooddb
CREATE DATABASE fastfoodDB;

use fastfoodDB;

CREATE TABLE Food_Category(
    ID_Category int not null auto_increment primary key,
    Category_Name varchar(32) not null unique
);

INSERT into food_category(Category_Name)
values('Напитки'),
('Бургеры'),
('Завтрак'),
('Кафе'),
('Картофель и салаты');

select * from food_category;

CREATE TABLE Food(
    ID_Food int not null auto_increment primary key,
    Food_Name varchar(32) not null unique,
    Food_Description varchar(1000) not null,
    Food_Price decimal(5,2) not null,
    Category_ID int not null,
    foreign key (Food_Info_ID) references Food_Info (ID_Food_Info),
    foreign key (Category_ID) references Food_Category (ID_Category)
);

insert into food(Food_Name, Food_Description, Food_Price, Food_Photo, Category_ID, Food_Info_ID)
values('Чикен Премьер Грибной', 'Сочная куриная котлета в хрустящей панировке, нежный грибной соус, ломтик сыра Чеддер, ароматный бекон, свежий салат, кусочки жареного хрустящего лука и карамелизированная булочка с кунжутом', 129, 'https://vkusnoitochka.ru/resize/202x202/upload/iblock/4fc/w89tevuk6ga7xe1y42zq05phpbm3w10n/large.png', 2, 1);

select * from food;

CREATE TABLE FastFood_User(
    ID_User int not null auto_increment primary key,
    Login varchar(50) not null unique,
    Password_User varchar(50) not null
);

CREATE TABLE Stocks(
    ID_Stock int not null auto_increment primary key,
    Discount int not null,
    Food_ID int not null,
    End_Date DATE,
    foreign key (Food_ID) references Food (ID_Food)
);

INSERT INTO Stocks(Discount, Food_ID, End_Date)
values(5, 1,'2023-09-22');

select * from stocks;

CREATE TABLE Composition(
    ID_Composition int not null auto_increment primary key,
    Composition_Name varchar(100) not null unique
);

insert into Composition(Composition_Name)
values('Булочка для гамбургеров из пшеничной муки с кунжутом'),
('Котлета куриная «Пикантная»'),
('Лук сушеный обжаренный'),
('Салат Айсберг мелкой нарезки'),
('Соус на основе растительных масел Грибной');

select * from composition;

CREATE TABLE Food_Composition(
    ID_Food_Composition int not null auto_increment primary key,
    Food_ID int not null,
    Composition_ID int not null,
    foreign key (Food_ID) references Food (ID_Food),
    foreign key (Composition_ID) references Composition (ID_Composition)
);

insert into food_composition(Food_ID, Composition_ID)
values(2,1);

select * from food_composition;

CREATE TABLE Allergens(
    ID_Allergen int not null auto_increment primary key,
    Allergen_Name varchar(500) not null
);

INSERT INTO allergens(Allergen_Name)
values('Глютен'),
('Молоко'),
('Кунжут'),
('Может содержать следы сои, сельдерея и горчицы');

CREATE TABLE Food_Allergens(
    ID_Food_Allergen int not null auto_increment primary key,
    Food_ID int not null,
    Allergen_ID int not null,
    foreign key (Food_ID) references Food (ID_Food),
    foreign key (Allergen_ID) references Allergens (ID_Allergen)
);

INSERT INTO food_allergens(Food_ID, Allergen_ID)
values(2,1);

select * from food_allergens;