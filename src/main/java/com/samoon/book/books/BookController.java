package com.samoon.book.books;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("books")
public class BookController {
    @Autowired
    BookRepository bookRepository;
    
    @GetMapping
    public Collection<Book> get(){
        return bookRepository.findAll();
    }

    @PostMapping
    public String save(Book book){
        bookRepository.save(book);
        return "Book saved!";
    }

    @GetMapping("/{id}")
    public Optional<Book> getById(@PathVariable long id){
        return bookRepository.findById(id);
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable long id){
        bookRepository.deleteById(id);
         return "Book deleted!";
    }

     @PutMapping("/{id}")
    public String update(@PathVariable long id, @RequestBody Book book){
        Optional<Book> bookInDb = bookRepository.findById(id);
        if(bookInDb.get() != null ){
            book.setId(bookInDb.get().getId());
            bookRepository.save(book);
             return "Book updated!";
        }
        
         return "Book not present!";
        
    }


}
