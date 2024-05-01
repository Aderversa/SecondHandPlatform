package com.onezhan.controller;

import com.auth0.jwt.interfaces.Claim;
import com.onezhan.pojo.Book;
import com.onezhan.pojo.Result;
import com.onezhan.service.BookService;
import com.onezhan.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookService bookService;
    @PostMapping("/add")
    public Result addBook(@RequestBody Book book) {
        Map<String, Claim> claims = ThreadLocalUtil.get();
        Integer id = claims.get("id").asInt();
        book.setSellerId(id);
        if (!StringUtils.hasLength(book.getDetail())) {
            book.setDetail("这个用户很懒，什么都没有写");
        }
        bookService.addBook(book);
        return Result.success();
    }
    @GetMapping("/list")
    public Result bookList() {
        return Result.success(bookService.getAll());
    }
}
