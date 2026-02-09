package auca.ac.rw.question1libraryapi.controller.library;

import auca.ac.rw.question1libraryapi.model.library.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final List<Book> books = new ArrayList<>();

    public BookController() {
        books.add(new Book(1L, "Clean Code", "Robert Martin", "978-0132350884", 2008));
        books.add(new Book(2L, "Effective Java", "Joshua Bloch", "978-0134685991", 2018));
        books.add(new Book(3L, "Spring in Action", "Craig Walls", "978-1617294945", 2018));
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(books);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book found = findBookById(id);
        if (found == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404
        }
        return ResponseEntity.ok(found);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooksByTitle(@RequestParam String title) {
        List<Book> results = new ArrayList<>();

        for (Book b : books) {
            if (b.getTitle() != null &&
                    b.getTitle().toLowerCase().contains(title.toLowerCase())) {
                results.add(b);
            }
        }

        return ResponseEntity.ok(results);
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book newBook) {

        if (newBook.getId() == null) {
            newBook.setId(getNextId());
        }

        books.add(newBook);
        return ResponseEntity.status(HttpStatus.CREATED).body(newBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        Book found = findBookById(id);
        if (found == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404
        }

        books.remove(found);
        return ResponseEntity.noContent().build(); // 204
    }


    private Book findBookById(Long id) {
        for (Book b : books) {
            if (b.getId() != null && b.getId().equals(id)) {
                return b;
            }
        }
        return null;
    }

    private long getNextId() {
        long max = 0;
        for (Book b : books) {
            if (b.getId() != null && b.getId() > max) {
                max = b.getId();
            }
        }
        return max + 1;
    }
}
