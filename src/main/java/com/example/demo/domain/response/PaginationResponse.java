package com.example.demo.domain.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaginationResponse<T> {

    private int currentPage;
    private long totalItems;
    private int totalPages;
    private int pageSize;
    private List<T> data;

}
