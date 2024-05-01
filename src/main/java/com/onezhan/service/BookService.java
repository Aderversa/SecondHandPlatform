package com.onezhan.service;

import com.onezhan.pojo.Book;
import com.onezhan.pojo.Result;

import java.util.List;

public interface BookService {
    void addBook(Book book);

    List<Book> getAll();
}
