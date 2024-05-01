package com.onezhan.service;

import com.onezhan.pojo.Book;

import java.util.List;

public interface BookService {
    void addBook(Book book);

    List<Book> getAll();

    List<Book> searchBooks(String content);

    List<Book> sortByPriceASC(List<Book> books);

    List<Book> sortByPriceDESC(List<Book> books);
}
