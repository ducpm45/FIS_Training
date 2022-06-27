package com.example;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SinhVienTest {

    @org.junit.jupiter.api.Test
    void tinhDiemTrungBinh() {
        Diem diem1 = new Diem(new MonHoc("Java", 3,4), 8);
        Diem diem2 = new Diem(new MonHoc("CTDL&&GT", 4,3), 9);
        Diem diem3 = new Diem(new MonHoc("C++", 4,5), 7);
        Diem diem4 = new Diem(new MonHoc("GDTC", 3,4), 8);
        SinhVien sv1 = new SinhVien("a", "aaa");
        Set<Diem> monDaHoc = new HashSet<>();
        monDaHoc.add(diem1);monDaHoc.add(diem2);
        monDaHoc.add(diem3);monDaHoc.add(diem4);
        sv1.setMonDaHoc(monDaHoc);

        System.out.println(sv1.tinhDiemTrungBinh());
    }


    @Test
    void bangDiem() {
        Diem diem1 = new Diem(new MonHoc("Java", 3,4), 8);
        Diem diem2 = new Diem(new MonHoc("CTDL&&GT", 4,3), 9);
        Diem diem3 = new Diem(new MonHoc("C++", 4,5), 7);
        Diem diem4 = new Diem(new MonHoc("GDTC", 3,4), 8);
        SinhVien sv1 = new SinhVien("a", "aaa");
        Set<Diem> monDaHoc = new HashSet<>();
        monDaHoc.add(diem1);monDaHoc.add(diem2);
        monDaHoc.add(diem3);monDaHoc.add(diem4);
        sv1.setMonDaHoc(monDaHoc);

        System.out.println(sv1.bangDiem());
    }

    @Test
    void xepLoai() {
        Diem diem1 = new Diem(new MonHoc("Java", 3,4), 8);
        Diem diem2 = new Diem(new MonHoc("CTDL&&GT", 4,3), 9);
        Diem diem3 = new Diem(new MonHoc("C++", 4,5), 7);
        Diem diem4 = new Diem(new MonHoc("GDTC", 3,4), 8);
        SinhVien sv1 = new SinhVien("a", "aaa");
        Set<Diem> monDaHoc = new HashSet<>();
        monDaHoc.add(diem1);monDaHoc.add(diem2);
        monDaHoc.add(diem3);monDaHoc.add(diem4);
        sv1.setMonDaHoc(monDaHoc);
        System.out.println(sv1.xepLoai());
    }
}