package com.onezhan.service.impl;

import com.onezhan.mapper.BookTypeMapper;
import com.onezhan.pojo.BookType;
import com.onezhan.service.BookTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookTypeServiceImpl implements BookTypeService {
    @Autowired
    BookTypeMapper bookTypeMapper;
    @Override
    public void addType(String typeName) {
        bookTypeMapper.addType(typeName);
    }

    @Override
    public List<BookType> getAllType() {
        return bookTypeMapper.getAllType();
    }

    @Override
    public BookType findByTypeName(String typeName) {
        return bookTypeMapper.findByTypeName(typeName);
    }
}
