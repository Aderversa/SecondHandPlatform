package com.onezhan.service.impl;

import com.onezhan.mapper.BookMapper;
import com.onezhan.pojo.Book;
import com.onezhan.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookMapper bookMapper;
    @Override
    public void addBook(Book book) {
        bookMapper.addBook(book);
    }

    @Override
    public List<Book> getAll() {
        return bookMapper.getAll();
    }
}
