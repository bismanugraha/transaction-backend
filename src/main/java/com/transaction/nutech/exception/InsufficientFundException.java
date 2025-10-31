package com.transaction.nutech.exception;

public class InsufficientFundException extends RuntimeException {
  public InsufficientFundException(String message) {
    super(message);
  }
}
