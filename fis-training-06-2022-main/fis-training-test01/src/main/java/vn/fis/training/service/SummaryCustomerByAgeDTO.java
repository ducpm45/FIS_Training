package vn.fis.training.service;

public class SummaryCustomerByAgeDTO {
    private int age;
    private int count;

    /**
     * Constructor
     */
    public SummaryCustomerByAgeDTO() {
    }

    /**
     * Getters and Setters
     */
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
