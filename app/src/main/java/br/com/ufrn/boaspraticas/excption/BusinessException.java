package br.com.ufrn.boaspraticas.excption;

public class BusinessException extends RuntimeException {
    public BusinessException(String message) { super(message); }
}