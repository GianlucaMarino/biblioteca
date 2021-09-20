package Biblioteca.biblioteca;


    import lombok.Data;

import javax.validation.constraints.Size;


    @Data
    public class BookDTO {


        @Size(min = 3, max = 100, message
                = "About Me must be between 3 and 200 characters")
        String titolo;
        @Size(min = 3, max = 100, message
                = "About Me must be between 3 and 200 characters")
        String autore;
        String annoPB;
        String link;
    }

