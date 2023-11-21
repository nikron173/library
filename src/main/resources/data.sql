INSERT INTO author (name) VALUES ('Анна Смит');
INSERT INTO author (name) VALUES ('Павел Иванов');
INSERT INTO author (name) VALUES ('Елена Козлова');
INSERT INTO author (name) VALUES ('Александр Петров');
INSERT INTO author (name) VALUES ('Виктория Морозова');
INSERT INTO author (name) VALUES ('Дмитрий Сидоров');
INSERT INTO author (name) VALUES ('Ольга Николаева');
INSERT INTO author (name) VALUES ('Иван Кузнецов');

INSERT INTO genre (name) VALUES ('Фэнтези');
INSERT INTO genre (name) VALUES ('Приключения');
INSERT INTO genre (name) VALUES ('Романтика');
INSERT INTO genre (name) VALUES ('Фантастический боевик');
INSERT INTO genre (name) VALUES ('Мистика');
INSERT INTO genre (name) VALUES ('Фантастический триллер');
INSERT INTO genre (name) VALUES ('Детская фэнтези');
INSERT INTO genre (name) VALUES ('Исторический роман');

INSERT INTO book (title, price, author_id, genre_id) VALUES
    ('Приключения в Волшебном лесу', 709.15, (SELECT id FROM author WHERE name='Анна Смит'),
     (SELECT id FROM genre WHERE name='Фэнтези'));

INSERT INTO book (title, price, author_id, genre_id) VALUES
    ('Остров загадок', 837.35, (SELECT id FROM author WHERE name='Павел Иванов'),
     (SELECT id FROM genre WHERE name='Приключения'));

INSERT INTO book (title, price, author_id, genre_id) VALUES
    ('В поисках любви', 591.93, (SELECT id FROM author WHERE name='Елена Козлова'),
     (SELECT id FROM genre WHERE name='Романтика'));

INSERT INTO book (title, price, author_id, genre_id) VALUES
    ('Путь воина', 932.47, (SELECT id FROM author WHERE name='Александр Петров'),
     (SELECT id FROM genre WHERE name='Фантастический боевик'));

INSERT INTO student (name, email, birth_day) VALUES ('Nikita Moiseev', 'nikron@gamil.com', '1997-08-30');