CREATE SCHEMA `web` ;
use web;
-- tao bảng tài khoản
create table account(
	id int primary key auto_increment,
    fullname varchar(50) not null,
    username varchar(50) not null unique,
    password varchar(50) not null,
    email varchar(50) not null unique,
    is_admin int not null
);

-- thêm trước 3 admin
insert into account(fullname, username, password, email, is_admin) values('Nguyễn Minh Hiếu', 'admin1', 'c4ca4238a0b923820dcc509a6f75849b', 'admin1@gmail.com', 1);
insert into account(fullname, username, password, email, is_admin) values('Minh Hiếu', 'admin2', 'c4ca4238a0b923820dcc509a6f75849b', 'admin2@gmail.com', 1);

-- tạo bảng danh mục
create table category (
	id int primary key auto_increment,
    name varchar(50) 
);

-- thêm trước các danh mục
insert into category(name) values('Sách Học thuật');
insert into category(name) values('Sách Hướng dẫn');
insert into category(name) values('Sách Truyện tranh');
insert into category(name) values('Sách Y học');
insert into category(name) values('Sách Khoa học viễn tưởng');
insert into category(name) values('Sách Lịch sử');
insert into category(name) values('Sách Kỹ năng sống');
insert into category(name) values('Sách Chính trị – pháp luật');
insert into category(name) values('Sách Khoa học công nghệ – Kinh tế');
insert into category(name) values('Sách Văn học nghệ thuật');
insert into category(name) values('Sách Văn hóa xã hội – Lịch sử');
insert into category(name) values('Sách Giáo trình');
insert into category(name) values('Sách Truyện, tiểu thuyết');
insert into category(name) values('Sách Tâm lý, tâm linh, tôn giáo');
insert into category(name) values('Sách Thiếu nhi');

-- tạo bảng sách
create table book(
	id int primary key auto_increment, 
    title varchar(100) not null ,
    author varchar(50) not null,
    description text, 
    releasedate date not null,
    pages int,
    cover varchar(500),
    categoryid int ,
    is_availabel int,
    foreign key (categoryid) references category(id) ON DELETE  NO ACTION ON UPDATE  NO ACTION
);

-- tạo bảng hóa đơn
create table bill(
	id int primary key auto_increment,
    saleday date,
    receiver varchar(50) not null,
    tel_receiver varchar(20) not null,
    address_receiver varchar(200) not null,
    email_receiver varchar(100) not null,
    zipcode varchar(10) not null,
    accountid int not null,
    foreign key (accountid) references account(id)
);

-- tạo bảng join với bill
create table billdetail(
	id int primary key auto_increment, 
    qty int not null,
    bookid int not null,
    billid int not null,
    foreign key (billid) references bill(id) ON DELETE CASCADE ON UPDATE CASCADE,
    foreign key (bookid) references book(id)
);

-- tạo bảng giỏ hàng
create table cart (
	id int primary key,
    accountid int not null,
    foreign key (accountid) references account(id)
);

-- tạo bảng join với cart
create table cartitem(
	cartid int not null,
    bookid int not null,
    quantity int not null,
    foreign key (cartid) references cart(id)  ON DELETE  NO ACTION ON UPDATE  NO ACTION,
    foreign key (bookid) references book(id)  ON DELETE  NO ACTION ON UPDATE  NO ACTION
);

-- tạo bảng đánh giá
create table review (
	id int primary key auto_increment,
    created date not null,
    content text not null,
    star float not null,
    accountid int not null,
    bookid int not null,
	foreign key (accountid) references account(id)  ON DELETE  NO ACTION ON UPDATE  NO ACTION,
    foreign key (bookid) references book(id)  ON DELETE  NO ACTION ON UPDATE  NO ACTION
);