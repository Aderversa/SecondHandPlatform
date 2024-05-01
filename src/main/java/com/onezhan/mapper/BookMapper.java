package com.onezhan.mapper;

import com.onezhan.pojo.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookMapper {
    void addBook(Book book);
    List<Book> getAll();
}
