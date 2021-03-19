package nd.Spring.spring2;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class BooksController {
    @GetMapping("/books")
    public List<Books> getAllBooks(){
        return Arrays.asList(new Books(1,"Hello World","Prajyot"),
                new Books(2, "Dragon Ball Z", "Master Roshi"),
                new Books(3,"Naruto Uzumaki", "Minato Namikaze"),
                new Books(4, "Demon Slayer", "Tanjiro"),
                new Books(5, "Pokemon", "Bot Pikachu"),
                new Books(6, "Demon Slayer", "Jeriko"),
                new Books(7, "asdas layer", "asdasas"));
    }
}
