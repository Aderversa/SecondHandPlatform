package com.onezhan.controller;

import com.auth0.jwt.interfaces.Claim;
import com.onezhan.mapper.BookTypeMapper;
import com.onezhan.pojo.BookType;
import com.onezhan.pojo.Result;
import com.onezhan.pojo.User;
import com.onezhan.service.BookTypeService;
import com.onezhan.service.UserService;
import com.onezhan.util.ThreadLocalUtil;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/type")
@CrossOrigin // 支持跨域，axios可以访问下面的接口哦
public class BookTypeController {
    @Autowired
    UserService userService;
    @Autowired
    BookTypeService bookTypeService;

    @PostMapping("/add")
    public Result addType(@NotEmpty String typeName) {
        Map<String, Claim> claims = ThreadLocalUtil.get();
        String username = claims.get("username").asString();
        User user = userService.getByUserName(username);
        if(user.getPower() != 0) {
            return Result.error("权限不足");
        }
        BookType bookType = bookTypeService.findByTypeName(typeName);
        if(bookType != null) {
            return Result.error("该分类已存在");
        }
        bookTypeService.addType(typeName);
        return Result.success();
    }
    @GetMapping("/list")
    public Result listType() {
        List<BookType> list = bookTypeService.getAllType();
        return Result.success(list);
    }
}
