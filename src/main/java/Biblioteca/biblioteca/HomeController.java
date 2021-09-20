package Biblioteca.biblioteca;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;

public class HomeController {

    private final BookService bookService;

    @Autowired
    public HomeController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/orario")
    public String home( Model m) {
        m.addAttribute("now", new Date() );
        return "orario";
    }

    @GetMapping("/addNewBook")
    public String addbook(Model model) {
        model.addAttribute( "formBook", new BookDTO());
        return "addBook";
    }

    @PostMapping("/addNewBook")
    public String loginPost(
            @ModelAttribute("formBook") String formBook,
            // WARN: BindingResult *must* immediately follow the Command.
            // https://stackoverflow.com/a/29883178/1626026
            BindingResult bindingResult,
            Model model,
            RedirectAttributes ra ) {

        if(this.bookService.verifyTiolo(formBook)){
            if(bindingResult.hasErrors())
            ra.addFlashAttribute("formLibro", formBook );
            System.out.println("Registarzione avvenuta con successo");
            return "redirect:/regSucc";
        } else {
            return "formLogin";
        }
    }


    @PostMapping("/addNewBook")
    public String foobarPost(
            @ModelAttribute("formLibro") BookDTO formBook,
            // WARN: BindingResult *must* immediately follow the Command.
            // https://stackoverflow.com/a/29883178/1626026
            BindingResult bindingResult,
            Model model,
            RedirectAttributes ra ) {


        if ( bindingResult.hasErrors() || this.bookService.verifyTiolo(formBook.getTitolo())) {
            if(bindingResult.hasErrors()){
                for(ObjectError temp :bindingResult.getAllErrors()){
                    System.out.println("Errore trovato: nome "+temp.getObjectName()+
                            ";codice "+temp.getCode()+"; messaggio "+temp.getDefaultMessage());
                }}

            return "addNewBook";
        }

        this.bookService.saveBook(formBook);

        ra.addFlashAttribute("formutente", formBook);

        return "redirect:/datiSalvatiForm";
    }

    @GetMapping("/regSuccess")
    public String regSucc(
            @ModelAttribute("formLogin") BookDTO formBook,
            Model model) {
        return "regSucc";
    }

    @GetMapping("/datiSalvatiForm")
    public String formresult(
            @ModelAttribute("formBook") Book formBook,
            Model model) {

        return "datiSalvatiForm";
    }



}
