INSERT INTO public.bank ( name, bic) VALUES ('VTB', '123456789');
INSERT INTO public.bank ( name, bic) VALUES ('Sber', '987654321');
INSERT INTO public.bank ( name, bic) VALUES ('Alfa', '147852369');
INSERT INTO public.bank ( name, bic) VALUES ('Sov', '247852369');

INSERT INTO public.customer (name, short_name, address, form_of_incorporation) VALUES ('ODK', 'ODK', 'Moscow', 'PAO' );
INSERT INTO public.customer (name, short_name, address, form_of_incorporation) VALUES ('OAK', 'OAK', 'Perm', 'AO');
INSERT INTO public.customer (name, short_name, address, form_of_incorporation) VALUES ('Rostech', 'Rostech', 'Omsk', 'OOO');
INSERT INTO public.customer (name, short_name, address, form_of_incorporation) VALUES ('S7', 'S7', 'St. Petersburg', 'PAO');

INSERT INTO public.deposit (customer_id, bank_id, open_date, percent, term) VALUES (1, 1, parsedatetime('2020-05-14 19:00', 'yyyy-MM-dd hh:mm'), 5.5, 10);
INSERT INTO public.deposit (customer_id, bank_id, open_date, percent, term) VALUES (1, 2, parsedatetime('2019-10-20 19:00', 'yyyy-MM-dd hh:mm'), 5.4, 6);
INSERT INTO public.deposit (customer_id, bank_id, open_date, percent, term) VALUES (1, 3, parsedatetime('2020-02-01 19:00', 'yyyy-MM-dd hh:mm'), 5.5, 12);
INSERT INTO public.deposit (customer_id, bank_id, open_date, percent, term) VALUES (2, 1, parsedatetime('2020-01-15 19:00', 'yyyy-MM-dd hh:mm'), 5.0, 6);
INSERT INTO public.deposit (customer_id, bank_id, open_date, percent, term) VALUES (2, 1, parsedatetime('2019-05-23 19:00', 'yyyy-MM-dd hh:mm'), 3.8, 9);
INSERT INTO public.deposit (customer_id, bank_id, open_date, percent, term) VALUES (2, 2, parsedatetime('2020-01-29 19:00', 'yyyy-MM-dd hh:mm'), 5.9, 8);

