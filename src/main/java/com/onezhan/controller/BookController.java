package com.onezhan.controller;

import com.auth0.jwt.interfaces.Claim;
import com.onezhan.pojo.Book;
import com.onezhan.pojo.Result;
import com.onezhan.service.BookService;
import com.onezhan.util.ThreadLocalUtil;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Validated
@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookService bookService;
    @PostMapping("/add")
    public Result addBook(@RequestBody Book book) {
        Map<String, Claim> claims = ThreadLocalUtil.get();
        Integer id = claims.get("id").asInt();
        String sellerName = claims.get("username").asString();
        book.setSellerId(id);
        book.setSellerName(sellerName);
        if (!StringUtils.hasLength(book.getDetail())) {
            book.setDetail("这个用户很懒，什么都没有写");
        }
        if(book.getTypeId() == null) {
            book.setTypeId(1);
        }
        bookService.addBook(book);
        return Result.success();
    }
    @GetMapping("/list")
    public Result bookList() {
        return Result.success(bookService.getAll());
    }
    @GetMapping("/search")
    public Result bookSearch(String content) {
        if(!StringUtils.hasLength(content)) {
            return Result.error("搜索文本不能为空");
        }
        return Result.success(bookService.searchBooks(content));
    }
    @GetMapping("/sort")
    public Result bookSort(@NotNull Integer mode, @RequestBody List<Book> books) {
        return switch (mode) {
            case 1 -> Result.success(bookService.sortByPriceASC(books));
            case 2 -> Result.success(bookService.sortByPriceDESC(books));
            default -> Result.error("未定义参数");
        };
    }
}
