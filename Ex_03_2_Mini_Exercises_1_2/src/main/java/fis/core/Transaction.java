package fis.core;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private TransactionType type; // Loại tiền nộp, mặc định là HOCPHIKI1
    private LocalDate date; // Ngày nộp tiền, mặc định là thời điểm hiện tại
    private BigDecimal amount; // số tiền mà sv chuyển vào tài khoản nhà trường qua ngân hàng
    private String message; // tin nhắn chuyển khoản, định dạng "Mã nộp tiền học" + "_" + "Tên đầy đủ sv"

    /**
     * Constructor
     */
    public Transaction() {
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "type=" + type +
                ", date=" + date +
                ", amount=" + amount +
                ", message='" + message + '\'' +
                '}';
    }

    public Transaction(BigDecimal amount, String message) {
        this.type = TransactionType.HOCPHIKI1;
        this.date = LocalDate.now();
        this.amount = amount;
        this.message = message;
    }

    /**
     * Getters and Setters
     */
    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDateTime(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
