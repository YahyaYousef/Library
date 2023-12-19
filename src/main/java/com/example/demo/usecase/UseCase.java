package com.example.demo.usecase;

public interface UseCase<Request,Response> {

    public Response execute(Request request);
}
