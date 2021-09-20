package Biblioteca.biblioteca;

import java.util.List;

    public interface BookDAO {
        List<Book> findAll();

        void insertBook(Book book);

        void updateBook(Book book);

        void executeUpdateBook(Book book);

        void deleteBook(Book book);
    }

