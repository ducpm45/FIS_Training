package vn.fis.finaltest_ordermanagementsystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import vn.fis.finaltest_ordermanagementsystem.exception.*;

import java.time.LocalDateTime;

import static vn.fis.finaltest_ordermanagementsystem.constant.Constant.*;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {CustomerNotFoundException.class})
    protected ResponseEntity<ErrorMessage> handlerCustomerNotFoundException(CustomerNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorMessage.builder().timestamp(LocalDateTime.now()).code(CUSTOMER_NOT_FOUND)
                        .message(e.getMessage()).build());
    }

    @ExceptionHandler(value = {MobileExistedException.class})
    protected ResponseEntity<ErrorMessage> handlerMobileExistedException(MobileExistedException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorMessage.builder().timestamp(LocalDateTime.now()).code(MOBILE_IS_EXISTED)
                        .message(e.getMessage()).build());
    }

    @ExceptionHandler(value = {OrderNotFoundException.class})
    protected ResponseEntity<ErrorMessage> handlerOrderNotFoundException(OrderNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorMessage.builder().timestamp(LocalDateTime.now()).code(ORDER_NOT_FOUND)
                        .message(e.getMessage()).build());
    }

    @ExceptionHandler(value = {ProductNotFoundException.class})
    protected ResponseEntity<ErrorMessage> handlerProductNotFoundException(ProductNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorMessage.builder().timestamp(LocalDateTime.now()).code(PRODUCT_NOT_FOUND)
                        .message(e.getMessage()).build());
    }

    @ExceptionHandler(value = {ProductNotEnoughtException.class})
    protected ResponseEntity<ErrorMessage> handlerProductNotEnoughtException(ProductNotEnoughtException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorMessage.builder().timestamp(LocalDateTime.now()).code(PRODUCT_NOT_ENOUGHT)
                        .message(e.getMessage()).build());
    }

    @ExceptionHandler(value = {CanNotDeletePaidStatusOrderException.class})
    protected ResponseEntity<ErrorMessage> handlerCanNotDeleteCreatedStatusOrderException(
            CanNotDeletePaidStatusOrderException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorMessage.builder().timestamp(LocalDateTime.now()).code(ORDER_CAN_NOT_DELETE)
                        .message(e.getMessage()).build());
    }

    @ExceptionHandler(value = {CanOnlyAddOrderItemToCreatedOrderException.class})
    protected ResponseEntity<ErrorMessage> handlerCanOnlyAddOrderItemToCreatedOrderException(
            CanOnlyAddOrderItemToCreatedOrderException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorMessage.builder().timestamp(LocalDateTime.now())
                        .code(CAN_ONLY_ADD_ORDER_ITEM_TO_CREATED_ORDER)
                        .message(e.getMessage()).build());
    }

    @ExceptionHandler(value = {OrderItemNotFoundException.class})
    protected ResponseEntity<ErrorMessage> handlerOrderItemNotFoundException(OrderItemNotFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorMessage.builder().timestamp(LocalDateTime.now()).code(ORDER_ITEM_NOT_FOUND)
                        .message(e.getMessage()).build());
    }

    @ExceptionHandler(value = {CanOnlyRemoveOrderItemOnCreatedOrderException.class})
    protected ResponseEntity<ErrorMessage> handlerCanOnlyRemoveOrderItemOnCreatedOrderException(
            CanOnlyRemoveOrderItemOnCreatedOrderException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorMessage.builder().timestamp(LocalDateTime.now())
                        .code(CAN_ONLY_REMOVE_ORDER_ITEM_ON_CREATED_ORDER)
                        .message(e.getMessage()).build());
    }
    @ExceptionHandler(value = {CanOnlyUpdateStatusForCreatedOrder.class})
    protected ResponseEntity<ErrorMessage> handlerCanOnlyUpdateStatusForCreatedOrder(
            CanOnlyUpdateStatusForCreatedOrder e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorMessage.builder().timestamp(LocalDateTime.now())
                        .code(CAN_ONLY_UPDATE_STATUS_FOR_CREATED_ORDER)
                        .message(e.getMessage()).build());
    }
}
