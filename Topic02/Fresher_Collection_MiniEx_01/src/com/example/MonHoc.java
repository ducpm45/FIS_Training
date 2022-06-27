package com.example;

public class MonHoc implements Cloneable {
    private String ten;
    private int tcLT;
    private int tcTH;

    public MonHoc(String ten, int tcLT, int tcTH) {
        this.ten = ten;
        this.tcLT = tcLT;
        this.tcTH = tcTH;
    }

    public boolean equals(Object that) {
        //Hai mon duoc goi la bang nhau neu cung ten mon
        if (that instanceof MonHoc) {
            return ((MonHoc) that).ten.equals(this.ten);
        }
        return false;
    }

    public String getTen() {
        return ten;
    }

    public int getTcLT() {
        return tcLT;
    }

    public int getTcTH() {
        return tcTH;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
