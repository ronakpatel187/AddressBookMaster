create table tblt_address_book(
    id bigint auto_increment  primary key,
    name varchar(63)
);

create table tblt_book_contact_dtls(
    id bigint auto_increment primary key,
    firstname varchar(63),
    lastname varchar(63),
    phonenumber varchar(10),
    bookid bigint
);