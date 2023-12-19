package com.example.demo.config;

import com.example.demo.domain.response.PaginationResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


@Service
public class PageMapper<T> {

    public PaginationResponse<T> convert(Page<T> page){
        PaginationResponse<T> paginationResponse= new PaginationResponse<>();
        paginationResponse.setCurrentPage(page.getNumber());
        paginationResponse.setTotalItems(page.getTotalElements());
        paginationResponse.setTotalPages(page.getTotalPages());
        paginationResponse.setPageSize(page.getSize());
        paginationResponse.setData(page.getContent());
        return paginationResponse;
    }

}
