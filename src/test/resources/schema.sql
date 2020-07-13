DROP TABLE IF EXISTS bank;
DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS deposit;
DROP index IF EXISTS bank_pk;

CREATE TABLE bank
(
    id integer serial NOT NULL,
    name varchar(1000),
    bic varchar(1000),
    CONSTRAINT bank_pk PRIMARY KEY (id)
);

CREATE TABLE customer
(
    id  int serial not null
        constraint customer_pk
            primary key,
    name                  varchar,
    short_name            varchar,
    address               varchar,
    form_of_incorporation varchar
);

create unique index customer_pk
    on customer (id);

CREATE TABLE deposit
(
    id  int serial   not null
        constraint deposit_pk
            primary key,
    customer_id           integer not null ,
    bank_id               integer not null,
    open_date             timestamp ,
    percent               float,
    term                  integer
);

create unique index deposit_pk
    on deposit (id);