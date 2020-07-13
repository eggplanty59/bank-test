create table deposit
(
    id          serial not null
        constraint deposit_pk
            primary key,
    customer_id integer,
    bank_id     integer,
    open_date   date,
    percent     double precision,
    term        integer
);

alter table deposit
    owner to postgres;

INSERT INTO public.deposit (id, customer_id, bank_id, open_date, percent, term) VALUES (1, 1, 5, '2020-05-21', 7.5, 10);
INSERT INTO public.deposit (id, customer_id, bank_id, open_date, percent, term) VALUES (2, 2, 6, '2020-04-21', 6.5, 12);
INSERT INTO public.deposit (id, customer_id, bank_id, open_date, percent, term) VALUES (3, 1, 8, '2019-12-30', 10.5, 8);