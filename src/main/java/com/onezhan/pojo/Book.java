package com.onezhan.pojo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.ISBN;
import org.hibernate.validator.constraints.URL;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class Book {
    private Integer id;
    @NotEmpty
    private String name;
    @Null
    private Integer typeId;
    @NotNull
    private String price;
    @ISBN
    private String isbn;
    @URL
    private String img;
    @Pattern(regexp = "^[\\s\\S]{0,500}$") // 500字以内的所有字符
    private String detail;
    private LocalDate releaseTime;
    private Integer sellerId;
    private Integer purchased;
}
