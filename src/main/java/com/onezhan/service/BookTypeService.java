package com.onezhan.service;

import com.onezhan.pojo.BookType;

import java.util.List;

public interface BookTypeService {
    void addType(String typeName);

    List<BookType> getAllType();

    BookType findByTypeName(String typeName);
}
