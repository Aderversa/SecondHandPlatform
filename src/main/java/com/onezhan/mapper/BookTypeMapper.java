package com.onezhan.mapper;

import com.onezhan.pojo.BookType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookTypeMapper {
    void addType(String typeName);
    List<BookType> getAllType();
    BookType findByTypeName(String typeName);
}
