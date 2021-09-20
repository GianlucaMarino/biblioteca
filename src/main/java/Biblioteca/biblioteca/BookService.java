package Biblioteca.biblioteca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class BookService {
    HashMap<String, BookDAO> similDB;
    private final BookDAOImpl userRepository;


    @Autowired
    public BookService(BookDAOImpl usrRep) {
        this.userRepository = usrRep;
        //this.similDB = new HashMap<String, Utente>();
    }

    public boolean verifyTiolo(String titolo) {
        List<Book> tmp = new ArrayList<Book>(this.userRepository.findBook(titolo));

        if(tmp.isEmpty())
            return false;

        return true;
    }


    public boolean saveBook(BookDTO libro) {
        if(!this.verifyTiolo(libro.getTitolo())){
            Book bo = new Book(libro.titolo, libro.autore, libro.annoPB, libro.link);
            this.userRepository.insertBook(bo);
            return true;
        }
        return false; //c'è già un utente
    }

    public List<Book> getAllUsers(){
        return new ArrayList(this.userRepository.findAll());
    }

}
