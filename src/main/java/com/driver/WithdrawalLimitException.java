package com.driver;

public class WithdrawalLimitException extends Exception{
    WithdrawalLimitException(String message){
        super(message);
    }
}
