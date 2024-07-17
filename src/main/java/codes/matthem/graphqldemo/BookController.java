package codes.matthem.graphqldemo;

import java.util.List;
import java.util.Optional;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Controller
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @QueryMapping
    public List<Book> books() {
        return bookService.books();
    }

    @QueryMapping
    public Optional<Book> bookById(@Argument Integer id) {
        return bookService.bookById(id);
    }

    @SchemaMapping
    public Optional<Author> author(Book book) {
        return bookService.author(book);
    }
}
