package com.newbiecoder45.task_manager.exceptions;

public class DuplicateTaskException extends RuntimeException{
    public DuplicateTaskException(String message){
        super(message);
    }
    
}
