package br.com.erudio.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import br.com.erudio.dto.BookDTO;
import br.com.erudio.mapper.DozerMapper;
//import br.com.erudio.model.Book;
import br.com.erudio.proxy.CambioProxy;
import br.com.erudio.repository.BookRepository;
import br.com.erudio.response.Cambio;

@RestController
@RequestMapping("book-service")
public class BookController {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private BookRepository repository;
	
	@Autowired
	private CambioProxy proxy;
	
	@GetMapping(value = "/{id}/{currency}")	
	public BookDTO findBook(
			@PathVariable("id") Long id,
			@PathVariable("currency") String currency
			) {
		
//        var book = repository.getById(id);
        var book = repository.findById(id).get();
		if (book == null) throw new RuntimeException("Book not Found");
				
		var cambio = proxy.getCambio(book.getPrice(), "USD", currency);
		
		var port = environment.getProperty("local.server.port");
		book.setEnvironment(port + " FEIGN");
		book.setPrice(cambio.getConvertedValue());
        var bookDTO = DozerMapper.parseObject(book, BookDTO.class);
		return bookDTO;
	}
	
	@GetMapping(value = "/v1/{id}/{currency}")	
	public BookDTO findBookV1(
			@PathVariable("id") Long id,
			@PathVariable("currency") String currency
			) {
		
//      var book = repository.getById(id);
        var book = repository.findById(id).get();
		if (book == null) throw new RuntimeException("Book not Found");
		
		HashMap<String, String> params = new HashMap<>();
		params.put("amount", book.getPrice().toString());
		params.put("from", "USD");
		params.put("to", currency);
		
		var response = new RestTemplate()
				.getForEntity("http://localhost:8000/cambio-service/"
						+ "{amount}/{from}/{to}", 
						Cambio.class,
						params);
		
		var cambio = response.getBody();
		
		var port = environment.getProperty("local.server.port");
		book.setEnvironment(port);
		book.setPrice(cambio.getConvertedValue());
//		return book;
        var bookDTO = DozerMapper.parseObject(book, BookDTO.class);
        return bookDTO;
	}
}
