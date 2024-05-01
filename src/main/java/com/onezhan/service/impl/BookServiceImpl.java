package com.onezhan.service.impl;

import com.onezhan.mapper.BookMapper;
import com.onezhan.pojo.Book;
import com.onezhan.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
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

    @Override
    public List<Book> searchBooks(String content) {
        // 模糊查询name含有content的书
        return bookMapper.searchBooks('%'+content+'%');
    }

    @Override
    public List<Book> sortByPriceASC(List<Book> books) {
        books.sort(Comparator.comparing(Book::getPrice));
        return books;
    }

    @Override
    public List<Book> sortByPriceDESC(List<Book> books) {
        books.sort(Comparator.comparing(Book::getPrice).reversed());
        return books;
    }
}
