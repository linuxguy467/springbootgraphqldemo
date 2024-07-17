package codes.matthem.graphqldemo;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public record Book(Integer id, String name, Integer pageCount, Integer authorId) {

    public static List<Book> books = Arrays.asList(
            new Book(1, "Bible", 1200, 3),
            new Book(2, "Harry Potter", 700, 2),
            new Book(3, "Foobar", 100, 1),
            new Book(4, "Spring Boot", 344, 2));

    public static Optional<Book> getBookById(Integer id) {
        return books.stream()
                .filter(b -> id.equals(b.id))
                .findFirst();
    }
}
