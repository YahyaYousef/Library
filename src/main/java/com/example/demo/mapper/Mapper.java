package com.example.demo.mapper;

public interface Mapper<A,B> {

    B mapTO(A a);

    A mapFrom(B b);

}
