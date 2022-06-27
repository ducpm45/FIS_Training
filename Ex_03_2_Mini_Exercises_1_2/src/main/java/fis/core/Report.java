package fis.core;

import java.math.BigDecimal;

public class Report {
    private String id; // mã sv nợ tiền học
    private String name; // tên đầy đủ của sv
    private BigDecimal debt; // số tiền học còn thiếu nợ

    /**
     * Constructor
     */
    public Report() {
    }

    @Override
    public String toString() {
        return "Report{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", debt=" + debt +
                '}';
    }

    /**
     * Getters and Setters
     */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getDebt() {
        return debt;
    }

    public void setDebt(BigDecimal debt) {
        this.debt = debt;
    }
}
