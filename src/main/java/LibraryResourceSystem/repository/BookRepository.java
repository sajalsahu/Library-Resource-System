package LibraryResourceSystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RestController;

import LibraryResourceSystem.Entity.Book;

@RestController
public interface BookRepository extends JpaRepository<Book, Integer> {
	
	List<Book> findByAuthor(String author);

	Optional<Book> findByTitleAndAuthor(String title, String author);

	List<Book> findByPriceGreaterThan(double price);

	List<Book> findByPriceBetween(double start, double end);

	@Query("select b from Book b where b.abailability = true")
	List<Book> getByAvailability();

	@Query("select b from Book b where b.publisedYear = ?1")
	List<Book> getByYear(Integer publishedYear);

	@Query("select b from Book b where b.genre = :genre")
	List<Book> getByGenre(String genre);
}
