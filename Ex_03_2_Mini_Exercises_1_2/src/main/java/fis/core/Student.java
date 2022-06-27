package fis.core;

import java.math.BigDecimal;
import java.util.UUID;

public class Student {
    private String id; // Mã sv, mặc định là 1 chuỗi có 3 kí tự
    private String fullName; // Tên đầy đủ của sv
    private String paymentCode; // Mã thanh toán, mặc định là 1 chuỗi có 6 kí tự
    private BigDecimal amount; // số tiền học sv cần phải nộp

    /**
     * Constructor ko tham số
     */
    public Student() {
    }

    /**
     * Constructor nhận 2 tham số, tên và số tiền cần nộp, id và paymentCode tạo random khi
     * khởi tạo đối tượng sv
     * @param fullName
     * @param amount
     */
    public Student(String fullName, BigDecimal amount) {
        this.id = UUID.randomUUID().toString().replace("-", "").substring(0, 3);
        this.fullName = fullName;
        this.paymentCode = UUID.randomUUID().toString().replace("-", "").substring(0, 6);
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", paymentCode='" + paymentCode + '\'' +
                ", amount=" + amount +
                '}';
    }

    /**
     * Getters and Setters
     */
    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPaymentCode() {
        return paymentCode;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
