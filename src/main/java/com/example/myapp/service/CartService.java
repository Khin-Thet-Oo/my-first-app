package com.example.myapp.service;


import com.example.myapp.dao.AuthorDao;
import com.example.myapp.dao.CategoryDao;
import com.example.myapp.ds.Book;
import com.example.myapp.ds.BookDto;
import com.example.myapp.ds.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CartService {
    @Autowired
    private Cart cart;
    @Autowired
    private AuthorDao authorDao;
    @Autowired
    private CategoryDao categoryDao;

    public BookDto toDto(Book book){
        return  new BookDto(
                book.getId(),
                book.getTitle(),
                book.getPrice(),
                book.getYearPublished(),
                book.getDescription(),
                book.getImgUrl(),
                book.getAuthor().getId(),
                book.getCategory().getId()
        );
    }

    public  Book toEntity(BookDto bookDto){
        Book book=new Book();
        book.setId(bookDto.getId());
        book.setYearPublished(bookDto.getYearPublished());
        book.setAuthor(authorDao.findById(bookDto.getAuthorId()).get());
        book.setCategory(categoryDao.findById(bookDto.getCategoryId()).get());
        book.setPrice(bookDto.getPrice());
        book.setTitle(bookDto.getTitle());
        book.setDescription(bookDto.getDescription());
        book.setImgUrl(bookDto.getImgUrl());
        return book;
    }
    public void addToCart(Book book){
        cart.addToCart(toDto(book));
    }
    public Set<BookDto> listCart(){
        return  cart.getBookDtos();
    }
    public int cartSize(){
        return  cart.cardSize();
    }
    public void remove(BookDto bookDto){
        cart.removeBookFromCart(bookDto);
    }
    public void clearCart(){
        cart.clearCart();
    }
}
