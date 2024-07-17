package codes.matthem.graphqldemo;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public record Author(Integer id, String name) {
    public static List<Author> authors = Arrays.asList(
            new Author(1, "Matthew Hemingway"),
            new Author(2, "JK Rowling"),
            new Author(3, "God"));

    public static Optional<Author> getAuthorById(Integer id) {
        return authors.stream()
                .filter(b -> id.equals(b.id))
                .findFirst();
    }
}
