create table bank
(
    id   serial not null
        constraint bank_pk
            primary key,
    name varchar,
    bic  varchar(9)
);

alter table bank
    owner to postgres;

INSERT INTO public.bank (id, name, bic) VALUES (5, 'VTB', '7564784');
INSERT INTO public.bank (id, name, bic) VALUES (6, 'VTB', '435654212');
INSERT INTO public.bank (id, name, bic) VALUES (4, 'VTB24', '435654215');
INSERT INTO public.bank (id, name, bic) VALUES (7, 'Alfa', '789456123');
INSERT INTO public.bank (id, name, bic) VALUES (8, 'Malina', '435654214');