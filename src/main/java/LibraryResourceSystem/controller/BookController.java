package LibraryResourceSystem.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import LibraryResourceSystem.Entity.Book;
import LibraryResourceSystem.dto.ResponseStructure;
import LibraryResourceSystem.service.bookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/book")
public class BookController {
	
	@Autowired
	private bookService bookService;
	
	
//	Insert a record
//	Method --> T save(T ref);
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Book>> saveBook(@RequestBody Book book) {
		return new ResponseEntity<>(bookService.saveBook(book),HttpStatus.CREATED);
	}
	
//	Insert multiple record
//	Method --> List<T> saveAll(List<T> ref);
	
	@PostMapping("/all")
	public ResponseEntity<ResponseStructure<List<Book>>> saveAllBooks(@RequestBody List<Book> books) {
		return new ResponseEntity<>(bookService.saveAllBook(books),HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure <List<Book>>> getAllBooks(){
		return new ResponseEntity<>(bookService.getAllBooks(),HttpStatus.OK);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Book>> getById(@PathVariable Integer id) {
		return new ResponseEntity<>(bookService.getById(id),HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<Book>> updateBook(@RequestBody Book book) {
		return new ResponseEntity<>(bookService.updateBook(book),HttpStatus.OK);
	
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<ResponseStructure<Book>> updateBook(@PathVariable Integer id, @RequestBody Map<String, Object> map) {
		return new ResponseEntity<>(bookService.updateBook(id, map),HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteBook(@PathVariable Integer id) {
		return new ResponseEntity<>(bookService.deleteBook(id),HttpStatus.OK);
	}
	
	@GetMapping("/author/{author}")
	public ResponseEntity<ResponseStructure<List<Book>>> getBooksByAuthor(@PathVariable String author) {
		 return new ResponseEntity<>(bookService.getBookByAuthor(author), HttpStatus.OK);
	}
	
	@GetMapping("/{title}/{author}")
	public ResponseEntity<ResponseStructure<Book>> getBooksByTitleAndAuthor(@PathVariable String title ,@PathVariable String author) {
		return new ResponseEntity<>(bookService.getBookByTitleAndAuthor(title, author), HttpStatus.OK);
	}
	
//	@GetMapping("/books/{price}")
//	public ResponseEntity<ResponseStructure<List<Book>>> getBooksGreaterThanPrice(@PathVariable double price) {
//		return new ResponseEntity<>(bookService.getBo)
//	}
	

//	findbyPriceBetween
	
	@GetMapping("/{startprice}/{endprice}")
	public ResponseEntity<ResponseStructure<List<Book>>> getBooksGreaterThanPrice(@PathVariable double startprice, @PathVariable double endprice) {
		return new ResponseEntity<>(bookService.getBookBetweenPrice(startprice, endprice), HttpStatus.OK);		
	}
	
	
	//getByAvailibility
	@GetMapping("/availibility") 
	public ResponseEntity<ResponseStructure<List<Book>>> getBooksByAvailability() {
		return new ResponseEntity<>(bookService.getBookByAvailability(), HttpStatus.OK);	
	}
	
	@GetMapping("/year/{year}") 
	public ResponseEntity<ResponseStructure<List<Book>>> getBooksByPublishedYear(@PathVariable Integer year) {
		return new ResponseEntity<>(bookService.getBookByPublishedYear(year), HttpStatus.OK);	
	}
	
	@GetMapping("/genre/{genre}") 
	public ResponseEntity<ResponseStructure<List<Book>>> getBooksByGenre(@PathVariable String genre) {
		return new ResponseEntity<>(bookService.getBookByGenre(genre), HttpStatus.OK);		
	}
}




















