package codes.matthem.graphqldemo;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.context.annotation.Import;
import org.springframework.graphql.test.tester.GraphQlTester;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

@Import(BookService.class)
@GraphQlTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private GraphQlTester graphQlTester;

    @Mock
    private BookService bookService;

    @Test
    void canGetBooks() {
        when(bookService.books()).thenReturn(Book.books);

        graphQlTester
                .documentName("books")
                .execute()
                .path("books")
                .entityList(BookAuthor.class)
                .hasSize(4)
                .satisfies(b -> {
                    b.stream().forEach(ba -> {
                        assertThat(ba.id()).isNotNegative();
                        assertThat(ba.name()).isNotBlank();
                        assertThat(ba.pageCount()).isNotZero();
                        assertThat(ba.author()).isNotNull();
                        assertThat(ba.author().id()).isNotNegative();
                        assertThat(ba.author().name()).isNotBlank();
                    });
                });
    }

    @Test
    void canGetBookById() {
        Author expectedAuthor = new Author(3, "God");
        Book expectedBook = new Book(1, "Bible", 1200, expectedAuthor.id());

        when(bookService.bookById(expectedBook.id())).thenReturn(Optional.of(expectedBook));

        graphQlTester
                .documentName("bookById")
                .variable("id", expectedBook.id())
                .execute()
                .path("bookById")
                .hasValue()
                .entity(BookAuthor.class)
                // .matchesJsonStrictly(
                // "{\"id\":\"1\",\"name\":\"Bible\",\"pageCount\":1200,\"author\":{\"id\":\""
                // + expectedAuthor.id() + "\",\"name\":\"" + expectedAuthor.name() + "\"}}");
                .satisfies(bookById -> {
                    assertThat(bookById.id().equals(expectedBook.id()));
                    assertThat(bookById.name().equals(expectedBook.name()));
                    assertThat(bookById.pageCount().equals(expectedBook.pageCount()));
                    assertThat(bookById.author().id().equals(expectedAuthor.id()));
                    assertThat(bookById.author().name().equals(expectedAuthor.name()));
                });
    }
}
