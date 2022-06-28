package vn.fis.finaltest_ordermanagementsystem.exception;

public class CanOnlyUpdateStatusForCreatedOrder extends Exception {
    public CanOnlyUpdateStatusForCreatedOrder(String message) {
        super(message);
    }
}
