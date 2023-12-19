package com.example.demo.domain.request;

import com.example.demo.domain.dto.BookDto;
import com.example.demo.domain.dto.CategoryDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookRequestBody {

    private String title;
    private String url;
    private Long categoryId;

}
