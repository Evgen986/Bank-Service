create table if not exists client
(
    client_id  bigserial primary key,
    username   varchar(255) not null unique,
    password   varchar(255) not null,
    firstname  varchar(100) not null,
    lastname   varchar(100) not null,
    patronymic varchar(100),
    birthday   date         not null
);

create table if not exists bank_account
(
    account_id bigserial primary key,
    client_id  bigint not null,
    balance    decimal(12, 2) check ( balance >= 0 ),
    unique (account_id, client_id),
    constraint fk_client_bank_account foreign key (client_id) references client (client_id) on delete cascade on update no action
);

create table if not exists phone
(
    phone_id     bigserial primary key,
    client_id    bigint  not null,
    phone_number varchar not null unique,
    unique (client_id, phone_number),
    constraint fk_client_phone foreign key (client_id) references client (client_id) on delete cascade on update no action
);

create table if not exists email
(
    email_id  bigserial primary key,
    client_id bigint  not null,
    email     varchar not null unique,
    unique (client_id, email),
    constraint fk_client_email foreign key (client_id) references client (client_id) on delete cascade on update no action
);