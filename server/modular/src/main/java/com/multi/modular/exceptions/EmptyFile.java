package com.multi.modular.exceptions;

public class EmptyFile extends RuntimeException {
    public EmptyFile(){
        super("The file is empty"); 
    }
}
