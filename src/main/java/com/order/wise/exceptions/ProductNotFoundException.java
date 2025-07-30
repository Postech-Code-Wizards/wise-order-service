package com.order.wise.exceptions;

public class ProductNotFoundException extends RuntimeException {

  public ProductNotFoundException(String message, Exception e) {
    super(message, e);
  }

  public ProductNotFoundException(String message) {
    super(message);
  }

}
