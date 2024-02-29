insert into client (username, password, name, birthday)
values ('ivan11', '123', 'Иванов Иван Иванович', '1990-01-31'),
       ('serg22', '123', 'Сергеев Сергей Сергеевич', '1992-03-18');

insert into bank_account (client_id, balance)
values (1, 100.00),
       (2, 150.00);

insert into phone (client_id, phone_number)
values (1, '+7-911-999-99-99'),
       (1, '+7-918-888-88-88'),
       (2, '+7-938-555-55-55');

insert into email (client_id, email)
values (1, 'ivan@mail.ru'),
       (2, 'serg@mail.ru'),
       (2, 'serg@yandex.ru');