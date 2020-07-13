create table customer
(
    id                    serial not null
        constraint customer_pk
            primary key,
    name                  varchar,
    short_name            varchar,
    address               varchar,
    form_of_incorporation varchar
);

alter table customer
    owner to postgres;

INSERT INTO public.customer (id, name, short_name, address, form_of_incorporation) VALUES (1, 'ODK', 'ODK', 'Perm', 'AO');
INSERT INTO public.customer (id, name, short_name, address, form_of_incorporation) VALUES (2, 'PMZ', 'PMZ', 'Krasnokamsk', 'AO');