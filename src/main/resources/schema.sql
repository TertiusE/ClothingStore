create table if not exists items (
    id identity,
    name varchar2(50) not null,
    brand varchar2(50) not null,
    itemyear int not null,
    price double not null
);