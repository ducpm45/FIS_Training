package com.example;

import java.io.Serializable;

public class Diem implements Comparable<Diem>, Serializable, Cloneable {
    private MonHoc mon;
    private int diem;

    public Diem(MonHoc mon, int diem) {
        this.mon = mon;
        this.diem = diem;
    }

    public boolean equals(Object that) {
        // Hai diem duoc goi la bang nhau neu cua cung mot mon
        if (that instanceof Diem) {
            return this.mon.equals(((Diem) that).mon);
        }
        return false;
    }

    @Override
    public int compareTo(Diem o) {
        return this.getDiem()-o.getDiem();
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public Diem clone() throws CloneNotSupportedException {
        Diem diemClone = (Diem) super.clone();
        diemClone.mon = (MonHoc) diemClone.mon.clone();
        return diemClone;
    }

    public MonHoc getMon() {
        return mon;
    }

    public int getDiem() {
        return diem;
    }
}
