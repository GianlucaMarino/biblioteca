CREATE TABLE biblioteca
(
  titolo varchar(100) NOT NULL,
  autore varchar(100) NOT NULL,
  annoPB varchar(100) NOT NULL,
  bookID SERIAL PRIMARY KEY,
  link varchar(100) DEFAULT NULL
);