package vn.fis.finaltest_ordermanagementsystem.exception;

public class CanOnlyAddOrderItemToCreatedOrderException extends Exception {
    public CanOnlyAddOrderItemToCreatedOrderException(String message) {
        super(message);
    }
}
