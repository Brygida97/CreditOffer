# Wypełnienie danymi tabeli bank
INSERT INTO banks (name)
VALUES ('');
INSERT INTO banks (name)
VALUES ('Alior Bank');
INSERT INTO banks (name)
VALUES ('Bank Millenium');
INSERT INTO banks (name)
VALUES ('Bank Pekao');
INSERT INTO banks (name)
VALUES ('PKO BP');
INSERT INTO banks (name)
VALUES ('Santander');

# Wypełnienie danymi tabeli types_of_loans
INSERT INTO type (name_Type)
VALUES ('');
INSERT INTO type (name_Type)
VALUES ('kredyt ratalny');
INSERT INTO type (name_Type)
VALUES ('kredyt hipoteczny');
INSERT INTO type (name_Type)
VALUES ('pożyczka gotówkowa');

# Wypełnienie danymi tabeli offers
INSERT INTO offers (name, minimum_amount, maximum_amount, rrso, commission_percent,
                    period_in_months, url, bank_id, type_id)
VALUES ('', 0, 0, 0, 0, 0,
        '', 0, 0);
INSERT INTO offers (name, minimum_amount, maximum_amount, rrso, commission_percent,
                               period_in_months, url, bank_id, type_id)
VALUES ('Kredyt hipoteczny „Megahipoteka”', 1000.00, 1000000.00, 10.89, 5.00, 360,
        'https://www.aliorbank.pl/klienci-indywidualni/kredyty-hipoteczne/kredyt-megahipoteka.html', 1, 2);
INSERT INTO offers (name, minimum_amount, maximum_amount, rrso, commission_percent,
                               period_in_months, url, bank_id, type_id)
VALUES ('Pożyczka Internetowa', 500.00, 200000.00, 12.57, 2.00, 60,
        'https://www.aliorbank.pl/klienci-indywidualni/kredyty-i-pozyczki/pozyczka-internetowa.html', 1, 3);
INSERT INTO offers (name, minimum_amount, maximum_amount, rrso, commission_percent,
                               period_in_months, url, bank_id, type_id)
VALUES ('Kredyt ratalny standardowy w sklepach tradycyjnych', 300.00, 30000.00, 14.35, 2.00, 60,
        'https://www.aliorbank.pl/klienci-indywidualni/kredyty-i-pozyczki/kredyty-ratalne/kredyt-ratalny-standardowy-w-sklepach-tradycyjnych.html',
        1, 1);
INSERT INTO offers (name, minimum_amount, maximum_amount, rrso, commission_percent,
                               period_in_months, url, bank_id, type_id)
VALUES ('Pożyczka Hipoteczna', 1000.00, 1000000.00, 11.02, 3.00, 360,
        'https://www.bankmillennium.pl/klienci-indywidualni/kredyty-hipoteczne/pozyczka-hipoteczna', 2, 2);
INSERT INTO offers (name, minimum_amount, maximum_amount, rrso, commission_percent,
                               period_in_months, url, bank_id, type_id)
VALUES ('Pożyczka online w promocji', 1000.00, 40000.00, 12.63, 1.00, 70,
        'https://www.bankmillennium.pl/klienci-indywidualni/pozyczki/pozyczka-online', 2, 3);
INSERT INTO offers (name, minimum_amount, maximum_amount, rrso, commission_percent,
                               period_in_months, url, bank_id, type_id)
VALUES ('Rodzinny kredyt mieszkaniowy', 20000.00, 2800000.00, 10.13, 1.00, 300,
        'https://www.pekao.com.pl/klient-indywidualny/wlasne-mieszkanie-lub-dom/kredyt-bez-wkladu-wlasnego.html', 3, 2);
INSERT INTO offers (name, minimum_amount, maximum_amount, rrso, commission_percent,
                               period_in_months, url, bank_id, type_id)
VALUES ('Pożyczka z wnioskiem przez internet', 1000.00, 250000.00, 12.00, 2.00, 120,
        'https://www.pekao.com.pl/klient-indywidualny/pozyczanie-gotowki/pozyczka-na-dowolny-cel.html', 3, 3);
INSERT INTO offers (name, minimum_amount, maximum_amount, rrso, commission_percent,
                               period_in_months, url, bank_id, type_id)
VALUES ('KREDYT HIPOTECZNY WŁASNY KĄT', 1000.00, 1000000.00, 9.65, 6.00, 420,
        'https://www.pkobp.pl/klienci-indywidualni/kredyty-hipoteczne/wlasny-kat-hipoteczny/', 4, 2);
INSERT INTO offers (name, minimum_amount, maximum_amount, rrso, commission_percent,
                               period_in_months, url, bank_id, type_id)
VALUES ('Twoja pierwsza pożyczka ', 1500.00, 300000.00, 9.82, 3.00, 24,
        'https://www.pkobp.pl/klienci-indywidualni/pozyczki/', 4, 3);
INSERT INTO offers (name, minimum_amount, maximum_amount, rrso, commission_percent,
                               period_in_months, url, bank_id, type_id)
VALUES ('Oferta "Nowy kredyt gotówkowy"', 1000.00, 30000.00, 17.21, 1.00, 120,
        'https://www.santander.pl/klient-indywidualny/kredyty/nowy-kredyt-gotowkowy?santag-camp=advnav-menu-kredyty_ofertaNTP_0523',
        5, 3);

# Wypełnienie danymi tabeli types_of_loans

INSERT INTO client (email, first_name, second_name)
VALUES ('');
INSERT INTO client (email, first_name, second_name)
VALUES ('krzywdziakbrygida97@gmail.com','test1','test1');
INSERT INTO client (email, first_name, second_name)
VALUES ('CreditOffer1@outlook.com','test2','test2');
INSERT INTO client (email, first_name, second_name)
VALUES ('CreditOffer1@outlook.com','test3','test3');


