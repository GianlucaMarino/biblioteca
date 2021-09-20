package Biblioteca.biblioteca;


import lombok.Data;

import java.math.BigInteger;
@Data
public class Book {
    String titolo;
    String autore;
    BigInteger bookID;
    String annoPB;
    String link;


    public Book(String titolo, String autore, String annoPB, String link) {

    }
}