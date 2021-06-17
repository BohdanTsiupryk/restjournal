create table if not exists usr
(
    id bigint not null
        constraint usr_pkey
            primary key,
    email varchar(255),
    name varchar(255),
    password varchar(255),
    role varchar(255)
);

insert into usr (id, email, name, password, role) values
(1, 'test@test.com', '1', '1', 'STUDENT')
