create database bus_management;
use bus_management;

create table Users(u_id int Identity(1,1) primary key not null,username varchar(20) not null,pass_word varchar(8) not null,phone_number numeric not null ,
fname varchar (20),email varchar (50) not null);
select * from Users;

update Users set pass_Word = 'ABC123' where u_id = 1;
insert into Users(username,pass_word,phone_number,fname,email) values ('User1','ABC123',1234567891,'Aditi','user1@gmail.com');
insert into Users(username,pass_word,phone_number,fname,email) values ('User2','PQR123',1357924681,'Swarali','user2@gmail.com');
insert into Users(username,pass_word,phone_number,fname,email) values ('User3','ABC456',1237894562,'Mugdha','user3@gmail.com');
insert into Users(username,pass_word,phone_number,fname,email) values ('User4','PQR456',5678912340,'Mrun','user4@gmail.com');
insert into Users(username,pass_word,phone_number,fname,email) values ('User5','ABC789',9178453210,'Chinmay','user5@gmail.com');
insert into Users(username,pass_word,phone_number,fname,email) values ('User6','PQR789',8867453218,'Raj','user6@gmail.com');
insert into Users(username,pass_word,phone_number,fname,email) values ('User7','XYZ123',7766554433,'Simran','user7@gmail.com');
insert into Users(username,pass_word,phone_number,fname,email) values ('User8','XYZ456',6543789210,'Anjali','user8@gmail.com');
insert into Users(username,pass_word,phone_number,fname,email) values ('User9','XYZ789',5987612340,'Rahul','user9@gmail.com');
insert into Users(username,pass_word,phone_number,fname,email) values ('User10','ABCXYZ',2344009987,'SRK','user10@gmail.com');

create table Bus(b_id int primary key not null, fromCity varchar(20),toCity varchar(20), 
AC_OR_nonAC varchar (10),Seater_OR_Sleeper varchar(20),max_seats int not null,booked_seats int not null,
base_fare money not null, bus_time time not null, bus_date date not null);
Select * from Bus;

insert into Bus values ('10','Nashik','Nagpur','AC','Seater',45,10,550,'9:00','2021-01-01');
insert into Bus values ('15','Nashik','Nagpur','Non-AC','Seater',45,23,600,'10:00','2021-01-01');
insert into Bus values ('20','Nashik','Nagpur','AC','Seater',45,15,550,'9:00','2021-01-02');
insert into Bus values ('25','Nashik','Nagpur','Non-AC','Seater',45,10,600,'10:00','2021-01-02');
insert into Bus values ('30','Nashik','Kolhapur','AC','Sleeper',35,15,430,'7:30','2021-01-01');
insert into Bus values ('35','Nashik','Kolhapur','Non-AC','Sleeper',10,15,330,'18:30','2021-01-01');
insert into Bus values ('40','Nashik','Kolhapur','AC','Seater',35,15,330,'14:30','2021-01-02');
insert into Bus values ('45','Nashik','Kolhapur','Non-AC','Sleeper',35,15,390,'18:30','2021-01-02');
insert into Bus values ('50','Alibag','Nagpur','AC','Sleeper',45,3,650,'15:30','2021-01-01');
insert into Bus values ('55','Alibag','Nagpur','Non-AC','Seater',35,5,450,'14:45','2021-01-01');
insert into Bus values ('60','Alibag','Nagpur','AC','Sleeper',45,7,670,'13:40','2021-01-02');
insert into Bus values ('65','Alibag','Nagpur','Non-AC','Seater',35,5,620,'14:45','2021-01-02');
insert into Bus values ('70','Alibag','Kolhapur','Non-AC','Sleeper',55,45,570,'19:00','2021-01-01');
insert into Bus values ('75','Alibag','Kolhapur','AC','Seater',45,12,600,'14:30','2021-01-01');
insert into Bus values ('80','Alibag','Kolhapur','Non-AC','Seater',55,22,510,'19:00','2021-01-02');
insert into Bus values ('85','Alibag','Kolhapur','AC','Seater',45,24,720,'8:30','2021-01-02');
insert into Bus values ('90','Mumbai','Nagpur','Non-AC','Sleeper',35,14,570,'17:15','2021-01-01');
insert into Bus values ('95','Mumbai','Nagpur','AC','Seater',55,2,410,'12:30','2021-01-01');
insert into Bus values ('100','Mumbai','Nagpur','Non-AC','Sleeper',35,14,370,'9:15','2021-01-02');
insert into Bus values ('105','Mumbai','Nagpur','AC','Seater',55,2,415,'15:30','2021-01-02');
insert into Bus values ('110','Mumbai','Kolhapur','Non-AC','Seater',45,0,510,'18:30','2021-01-01');
insert into Bus values ('115','Mumbai','Kolhapur','AC','Sleeper',45,0,640,'17:30','2021-01-01');
insert into Bus values ('120','Mumbai','Kolhapur','Non-AC','Seater',45,0,525,'18:30','2021-01-02');
insert into Bus values ('125','Mumbai','Kolhapur','AC','Sleeper',45,0,5850,'17:30','2021-01-02');

insert into Bus values ('130','Nashik','Nagpur','AC','Seater',45,10,550,'9:00','2021-01-03');
insert into Bus values ('135','Nashik','Kolhapur','AC','Sleeper',35,15,430,'7:30','2021-01-03');
insert into Bus values ('140','Alibag','Nagpur','AC','Sleeper',45,3,650,'15:30','2021-01-03');
insert into Bus values ('145','Alibag','Kolhapur','Non-AC','Seater',55,45,570,'19:00','2021-01-03');
insert into Bus values ('150','Mumbai','Nagpur','Non-AC','Sleeper',35,14,570,'17:15','2021-01-03');
insert into Bus values ('155','Mumbai','Kolhapur','Non-AC','Seater',45,0,525,'18:30','2021-01-03');


SELECT IDENT_CURRENT('Booking_Details') as bk_id;
create table Booking_Details(bk_id int identity (1001,1) primary key not null ,U_id int foreign key references Users(u_id),
B_id int foreign key references Bus(b_id),date_of_travel date not null, no_of_seats int,total_fare money,
card_no numeric(18) not null, cvv int not null);
select * from Booking_Details;

insert into Booking_Details(u_id,b_id,date_of_travel,no_of_seats,total_fare,card_no,cvv) values (10,100,'2021-01-01',2,1100,1243567983125645,123);
insert into Booking_Details(u_id,b_id,date_of_travel,no_of_seats,total_fare,card_no,cvv) values (9,90,'2021-01-03',1,450,8312564566689765,456);
insert into Booking_Details(u_id,b_id,date_of_travel,no_of_seats,total_fare,card_no,cvv) values (8,80,'2021-01-02',1,750,3125645124356798,789);
insert into Booking_Details(u_id,b_id,date_of_travel,no_of_seats,total_fare,card_no,cvv) values (7,70,'2021-01-01',2,1700,2564512435679831,224);
insert into Booking_Details(u_id,b_id,date_of_travel,no_of_seats,total_fare,card_no,cvv) values (6,60,'2021-01-03',1,650,85648654179831,795);
insert into Booking_Details(u_id,b_id,date_of_travel,no_of_seats,total_fare,card_no,cvv) values (5,50,'2021-01-02',3,2850,5645679831251243,478);
insert into Booking_Details(u_id,b_id,date_of_travel,no_of_seats,total_fare,card_no,cvv) values (4,40,'2021-01-02',1,650,2564543567983112,258);
insert into Booking_Details(u_id,b_id,date_of_travel,no_of_seats,total_fare,card_no,cvv) values (3,30,'2021-01-03',1,350,5679831225645,245);
insert into Booking_Details(u_id,b_id,date_of_travel,no_of_seats,total_fare,card_no,cvv) values (2,20,'2021-01-01',2,1500,6798312564509435,450);
insert into Booking_Details(u_id,b_id,date_of_travel,no_of_seats,total_fare,card_no,cvv) values (1,10,'2021-01-01',2,1100,7983125645208356,630);


create table Ticket_Details(BK_id int foreign key references Booking_Details(bk_id),Bk_run int,fname varchar(20),
age int,seat_no int,primary key(BK_id,Bk_run));
select * from Ticket_Details;

insert into Ticket_Details values (1001,1,'Aditi',15,12);
insert into Ticket_Details values (1001,2,'Swarali',18,13);
insert into Ticket_Details values (1002,1,'Manju',38,4);
insert into Ticket_Details values (1003,1,'Sidharth',33,1);
insert into Ticket_Details values (1004,1,'Vivek',40,15); 
insert into Ticket_Details values (1004,2,'Vrunda',36,16);
insert into Ticket_Details values (1005,1,'Salman',54,22);
insert into Ticket_Details values (1006,1,'Abhishek',42,5);
insert into Ticket_Details values (1006,2,'Aaishwarya',45,6);
insert into Ticket_Details values (1006,3,'Aaradhya',8,7);
insert into Ticket_Details values (1007,1,'Amir',54,45);
insert into Ticket_Details values (1008,1,'Ananya',23,2);
insert into Ticket_Details values (1009,1,'Varun',35,28);
insert into Ticket_Details values (1009,2,'Natasha',32,29);
insert into Ticket_Details values (1010,1,'Akshay',50,13);
insert into Ticket_Details values (1010,2,'Twinkle',47,14);

--Queries
alter table Bus 
add constraint bookedseats_vs_maxseats check (booked_seats <= max_seats);

select * from Bus
where max_seats = booked_seats;












