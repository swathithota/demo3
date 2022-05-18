package com.example.demo3.exception;

public class NotValidRequest extends RuntimeException{
    public NotValidRequest(String msg){ super(msg);}
}
