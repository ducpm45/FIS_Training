package com.example;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class SinhVien {
    private String mssv;
    private String ten;

    private Set<Diem> monDaHoc = new HashSet<Diem>();

    public SinhVien(String mssv, String ten) {
        this.mssv = mssv;
        this.ten = ten;
    }

    public boolean themDiem(Diem diemMoi) {
        return this.monDaHoc.add(diemMoi);
    }

    //TODO Cau 1
    public double tinhDiemTrungBinh() {
        double tongDiem = monDaHoc.stream()
                .mapToDouble(d -> d.getDiem() * (d.getMon().getTcTH() + d.getMon().getTcLT()))
                .sum();
        int tongTinChi = monDaHoc.stream()
                .mapToInt(d -> d.getMon().getTcLT() + d.getMon().getTcTH())
                .sum();
        return tongDiem / tongTinChi;
    }


    //TODO Cau 2
    public String bangDiem() {

        StringBuilder sb = new StringBuilder();
        sb.append("MSSV : ").append(getMssv());
        sb.append("\nTen : ").append(getTen());
        sb.append("\nDanh Sach Diem");
        sb.append("\nSTT\tTen Mon\t\t\t\t\tDiem\t\t\tSo Tin Chi");
        AtomicInteger count = new AtomicInteger(1);
        monDaHoc.forEach(d -> {
            sb.append("\n").append(count).append("\t").append(d.getMon().getTen()).append("\t\t\t\t\t")
                    .append(d.getDiem()).append("\t\t\t").append(d.getMon().getTcTH() + d.getMon().getTcLT());
            count.getAndIncrement();
        });
        sb.append("\nDiem Trung Binh   ").append(tinhDiemTrungBinh());
        return sb.toString();
    }


    //TODO Cau 3
    public String xepLoai() {
        double diemTB = tinhDiemTrungBinh();
        String loai = "";
        if(diemTB >= 8) {
            loai = "GIOI";
        } else if (diemTB >= 7) {
            loai = "KHA";
        } else if (diemTB >= 6) {
            loai = "TB-KHA";
        } else if (diemTB >= 5) {
            loai = "TB";
        } else {
            loai = "YEU";
        }
        return loai;
    }

    public String getMssv() {
        return mssv;
    }

    public String getTen() {
        return ten;
    }

    public Set<Diem> getMonDaHoc() {
        return monDaHoc;
    }

    public void setMonDaHoc(Set<Diem> monDaHoc) {
        this.monDaHoc = monDaHoc;
    }
}
