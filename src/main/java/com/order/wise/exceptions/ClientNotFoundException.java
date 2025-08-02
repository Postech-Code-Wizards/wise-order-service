package com.order.wise.exceptions;

public class ClientNotFoundException extends RuntimeException {

  public ClientNotFoundException(String message, Exception e) {
    super(message, e);
  }

}
