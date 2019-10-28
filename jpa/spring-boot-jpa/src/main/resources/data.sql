DELETE FROM Book;
DELETE FROM Author;

INSERT INTO Author(id, name) VALUES (1, 'Стругацкий');
INSERT INTO Author(id, name) VALUES (2, 'Уэллс');
INSERT INTO Author(id, name) VALUES (3, 'Пушкин');


INSERT INTO Book(id, name, author_id)   VALUES (100, 'Трудно быть Богом', 1);
INSERT INTO Book(id, name, author_id)   VALUES (101, 'Машина времени', 2);
INSERT INTO Book(id, name, author_id)   VALUES (102, 'Онегин', 3);



