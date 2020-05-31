package com.genpact.library;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.genpact.library.entities.Book;
import com.genpact.library.entities.Library;
import com.genpact.library.resource.BookController;
import com.genpact.library.resource.LibraryController;

@SpringBootTest
@AutoConfigureMockMvc
class LibraryApplicationTests extends AbstractTest {

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	LibraryController libraryController;
	

	@MockBean
	BookController bookController;
	
	 @Test
	   public void getLibraries() throws Exception {
	       Library library = new Library();
	       library.setLibraryName("test");
	       library.setDescription("test");
	       
	       List<Library> libraries = new ArrayList<>();
	       libraries.add(library);
	       Page<Library> page = new PageImpl<>(libraries);
	       when(libraryController.getAllLibraries(PageRequest.of(0,2))).thenReturn(page);
	        mockMvc.perform(MockMvcRequestBuilders.get("/libraries"))
	                .andExpect(status().isOk());
	   }
	 
	 @Test
	   public void getBooksByLibraryId() throws Exception {
	       Book book = new Book();
	       book.setAuthorName("test");
	       book.setBookName("test");
	       book.setContent("test");
	       
	       List<Book> books = new ArrayList<>();
	       books.add(book);
	       Page<Book> page = new PageImpl<>(books);
	       when(bookController.getAllBooksByLibraryId(1L, PageRequest.of(0,2))).thenReturn(page);
	        mockMvc.perform(MockMvcRequestBuilders.get("/libraries/1/books"))
	                .andExpect(status().isOk());
	   }
	 
	 @Test
	   public void createLibrary() throws Exception {
		 Library library = new Library();
	       library.setLibraryName("test");
	       library.setDescription("test");
	       String json= mapToJson(library);
	       when(libraryController.createLibrary(library)).thenReturn(library);
	        mockMvc.perform(MockMvcRequestBuilders.post("/libraries").contentType(MediaType.APPLICATION_JSON_VALUE).content(json))
	                .andExpect(status().isOk());
	   }
	 
	 @Test
	   public void createBook() throws Exception {
		 Book book = new Book();
	       book.setAuthorName("test");
	       book.setBookName("test");
	       book.setContent("test");
	       String json= mapToJson(book);
	       when(bookController.addBook(1L, book)).thenReturn(book);
	        mockMvc.perform(MockMvcRequestBuilders.post("/libraries/1/books").contentType(MediaType.APPLICATION_JSON_VALUE).content(json))
	                .andExpect(status().isOk());
	   }
	 
	 @Test
	   public void updateLibrary() throws Exception {
		 Library library = new Library();
	       library.setLibraryName("test");
	       library.setDescription("test");
	       String json= mapToJson(library);
	       when(libraryController.updateLibrary(1L, library)).thenReturn(library);
	        mockMvc.perform(MockMvcRequestBuilders.put("/libraries/1").contentType(MediaType.APPLICATION_JSON_VALUE).content(json))
	                .andExpect(status().isOk());
	   }
	 
	 @Test
	   public void updateBook() throws Exception {
		 Book book = new Book();
	       book.setAuthorName("test");
	       book.setBookName("test");
	       book.setContent("test");
	       String json= mapToJson(book);
	       when(bookController.updateBook(1L, 1L, book)).thenReturn(book);
	        mockMvc.perform(MockMvcRequestBuilders.put("/libraries/1/books/1").contentType(MediaType.APPLICATION_JSON_VALUE).content(json))
	                .andExpect(status().isOk());
	   }
	 
	 
	 @Test
	   public void deleteLibrary() throws Exception {
		 this.mockMvc.perform(MockMvcRequestBuilders
		            .delete("/libraries/{id}", "1")
		            .contentType(MediaType.APPLICATION_JSON))
		            .andExpect(status().isOk());
	   }
	 
	 @Test
	   public void deleteBook() throws Exception {
		 this.mockMvc.perform(MockMvcRequestBuilders
		            .delete("/libraries/{id}/books/{bookId}", "1","1")
		            .contentType(MediaType.APPLICATION_JSON))
		            .andExpect(status().isOk());
	   }
	 
	}
