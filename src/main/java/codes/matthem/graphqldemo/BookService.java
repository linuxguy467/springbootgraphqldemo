package codes.matthem.graphqldemo;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class BookService {
    public List<Book> books() {
        return Book.books;
    }

    public Optional<Book> bookById(Integer id) {
        return Book.getBookById(id);
    }

    public Optional<Author> author(Book book) {
        return Author.getAuthorById(book.authorId());
    }
}
