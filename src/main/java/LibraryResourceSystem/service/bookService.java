package LibraryResourceSystem.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import LibraryResourceSystem.Entity.Book;
import LibraryResourceSystem.dto.ResponseStructure;
import LibraryResourceSystem.exception.IdNotFoundException;
import LibraryResourceSystem.exception.NoRecordAvailableException;
import LibraryResourceSystem.repository.BookRepository;

@Service
public class bookService {

	@Autowired
	private BookRepository bookRepository;

	public ResponseStructure<Book> saveBook(Book book) {
		ResponseStructure<Book> res = new ResponseStructure<Book>();
		res.setStatusCode(HttpStatus.CREATED.value());
		res.setMessage("Book save successfully");
		res.setData(bookRepository.save(book));
		return res;
	}

	public ResponseStructure<List<Book>> saveAllBook(List<Book> books) {
		List<Book> b = bookRepository.saveAll(books);
		ResponseStructure<List<Book>> res = new ResponseStructure<>();
		res.setStatusCode(HttpStatus.CREATED.value());
		res.setMessage("Book save successfully");
		res.setData(b);
		return res;
	}

	public ResponseStructure<List<Book>> getAllBooks() {
		List<Book> books = bookRepository.findAll();
		ResponseStructure<List<Book>> res = new ResponseStructure<List<Book>>();
		if (books.isEmpty()) {
			throw new NoRecordAvailableException("Data is not present");
		} else {
			res.setStatusCode(HttpStatus.OK.value());
			res.setMessage("All record retrieved successfully");
			res.setData(books);
			return res;
		}
	}

	public ResponseStructure<Book> getById(Integer id ) {
		Optional<Book> opt = bookRepository.findById(id);
		ResponseStructure<Book> res = new ResponseStructure<Book>();

		if (opt.isPresent()) {
			res.setStatusCode(HttpStatus.OK.value());
			res.setMessage("Fetch record successfully");
			res.setData(opt.get());
			return res;
		}
		throw new IdNotFoundException("Id does not exist");
	}
	
	public ResponseStructure<Book> updateBook(Book book){
		ResponseStructure<Book> res = new ResponseStructure<Book>();
//		case 1
		if(book.getId() == null) {
			throw new IdNotFoundException("Id must be passed to update");
		}
		//case 2
		Optional<Book> opt = bookRepository.findById(book.getId());
		if(opt.isPresent()) {
			bookRepository.save(book);
			res.setStatusCode(HttpStatus.OK.value());
			res.setMessage("Book record with id : "+ book.getId() + " updated ");
			res.setData(book);
			return res;
		}
		throw new IdNotFoundException("Book record with id: "+ opt.get() + "does not exist");
	}
	
	public ResponseStructure<Book> updateBook(Integer id, Map<String, Object> map){
		Optional<Book> opt = bookRepository.findById(id);
		ResponseStructure<Book> res = new ResponseStructure<Book>();
		
		if(opt.isPresent()) {
			Book book = opt.get();
			for(Map.Entry<String, Object>entry : map.entrySet()) {
				String key = entry.getKey
						();
				Object value = entry.getValue();
				
				switch (key) {
				case "title":
					book.setTitle((String )value);
					break;
				case "author":
					book.setAuthor((String )value);
					break;
				case "genre":
					book.setGenre((String )value);
					break;
				case "price":
					book.setPrice((Double )value);
					break;
				case "publishedYear":
					book.setPublisedYear((Integer)value);
					break;
				case "abailability":
					book.setAbailability((Boolean)value);
					break;
				}	
			}
			res.setStatusCode(HttpStatus.OK.value());
			res.setMessage("Book record with id : "+ book.getId() + " updated ");
			res.setData(book);
			return res;
		}
		throw new IdNotFoundException("Book record with id: "+ id + "does not exist");
	}
	public ResponseStructure<String> deleteBook( Integer id) {
		Optional<Book> opt = bookRepository.findById(id);
		ResponseStructure<String> res = new ResponseStructure<>();
		if(opt.isPresent()) {
			bookRepository.delete(opt.get());
			res.setStatusCode(HttpStatus.OK.value());
			res.setMessage("Book record with Id:"+id+" deleted" );
			res.setData("Success");
			return res;
		}
		/*
		res.setStatusCode(HttpStatus.NOT_FOUND.value());
		res.setMesssge("Book record with Id:"+id+" does not exists" );
		res.setData("Failure");
		return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
		*/
		throw new NoRecordAvailableException("No book found");
		
	}		
			
	public ResponseStructure<List<Book>> getBookByAuthor(String author) {
		List<Book> books =  bookRepository.findByAuthor(author);
		
		ResponseStructure<List<Book>> res = new ResponseStructure<List<Book>>();
		if(books.isEmpty()) {
			throw new NoRecordAvailableException("No book found with author: "+author);
		}
		
		res.setStatusCode(HttpStatus.OK.value());
		res.setMessage("All records retrieved successfullly with author: "+author);
		res.setData(books);
		return res;
		
	}	
	public ResponseStructure<Book> getBookByTitleAndAuthor(String title,String author) {
		Optional<Book>  opt = bookRepository.findByTitleAndAuthor(title, author);
		
		ResponseStructure<Book> res = new ResponseStructure<>();
		if(opt.isEmpty()) {
			throw new NoRecordAvailableException("No book found with author: "+author);
		}
		
		res.setStatusCode(HttpStatus.OK.value());
		res.setMessage("All records retrieved successfullly with author: "+author);
		res.setData(opt.get());
		return res;
		
	}	
	
	
	/*
	public ResponseStructure<Book> getBookGreaterThanPrice(String title,String author) {
		Optional<Book>  opt = bookRepository.findByTitleAndAuthor(title, author);
		
		ResponseStructure<Book> res = new ResponseStructure<>();
		if(opt.isEmpty()) {
			throw new NoRecordAvailibleException("No book found with author: "+author);
		}
		
		res.setStatusCode(HttpStatus.OK.value());
		res.setMessage("All records retrieved successfullly with author: "+author);
		res.setData(opt.get());
		return res;
		
	}	
	*/
	
	public ResponseStructure<List<Book>> getBookBetweenPrice(double startprice,double endprice) {
		List<Book> books =  bookRepository.findByPriceBetween(startprice, endprice);
		
		ResponseStructure<List<Book>> res = new ResponseStructure<List<Book>>();
		if(books.isEmpty()) {
			throw new NoRecordAvailableException("No book found b/w price: "+startprice+" and "+endprice);
		}
		
		res.setStatusCode(HttpStatus.OK.value());
		res.setMessage("all books found b/w price: "+startprice+" and "+endprice);
		res.setData(books);
		return res;	
		
		
	}
	
	public ResponseStructure<List<Book>> getBookByAvailability() {
		List<Book> books = bookRepository.getByAvailability();

	    ResponseStructure<List<Book>> res = new ResponseStructure<>();

	    if (books.isEmpty()) {
	        throw new NoRecordAvailableException("No books found");
	    }

	    res.setStatusCode(HttpStatus.OK.value());
	    res.setMessage("All books found");
	    res.setData(books);

	    return res;
	}
	
	public ResponseStructure<List<Book>> getBookByPublishedYear(Integer year) {
		List<Book> books =  bookRepository.getByYear(year);
		
		ResponseStructure<List<Book>> res = new ResponseStructure<List<Book>>();
		if(books.isEmpty()) {
			throw new NoRecordAvailableException("No books found");
		}
		
		res.setStatusCode(HttpStatus.OK.value());
		res.setMessage("all books found");
		res.setData(books);
		return res;	
	}
	
	public ResponseStructure<List<Book>> getBookByGenre(String genre) {
		List<Book> books =  bookRepository.getByGenre(genre);
		
		ResponseStructure<List<Book>> res = new ResponseStructure<List<Book>>();
		if(books.isEmpty()) {
			throw new NoRecordAvailableException("No books found with genre: "+genre);
		}
		
		res.setStatusCode(HttpStatus.OK.value());
		res.setMessage("all books found with genre: "+genre);
		res.setData(books);
		return res;	
	}
	
	public ResponseStructure<Page<Book>> getByPagination(int pageNumber, int pageSize){
		Page<Book> page = bookRepository.findAll(PageRequest.of(pageNumber,pageSize));
		ResponseStructure<Page<Book>> res = new ResponseStructure<>();
		
		if(page.isEmpty()){
			throw new NoRecordAvailableException("Page is empty");
		}
		else {
			res.setStatusCode(HttpStatus.OK.value());
			res.setMessage("Record retrieve from page no: " + pageNumber);
			res.setData(page);
			return res;
		}
	}
	
	public ResponseStructure<List<Book>> getBySorting(String fieldName){
		List<Book> books = bookRepository.findAll(Sort.by("").ascending());
		ResponseStructure<List<Book>> res = new ResponseStructure<List<Book>>();
		if(books.isEmpty()) {
			throw new NoRecordAvailableException("Record is not present");
		}
		else {
			res.setStatusCode(HttpStatus.OK.value());
			res.setMessage("Record is retrieve successfully");
			res.setData(books);
			return res;
		}
	}
	
	public ResponseStructure<Page<Book>> getByPaginationAndSorting(int pageNumber, int pageSize, String fieldName){
		Page<Book> page = bookRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by("").descending()));
		
		ResponseStructure<Page<Book>> res = new ResponseStructure<Page<Book>>(); 
		if(page.isEmpty()) {
			throw new NoRecordAvailableException("Record is not present");
		}
		else {
			res.setStatusCode(HttpStatus.OK.value());
			res.setMessage("Record is retrieve successfully");
			res.setData(page);
			return res;

		}
	}
}
