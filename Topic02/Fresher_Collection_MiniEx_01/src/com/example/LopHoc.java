package com.example;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class LopHoc {
    private String ten;
    private String giaoVien;
    private List<SinhVien> dsLop = new ArrayList<SinhVien>();

    public LopHoc(String ten, String giaoVien) {
        this.ten = ten;
        this.giaoVien = giaoVien;
    }

    public boolean them(SinhVien svMoi) {
        return dsLop.add(svMoi);
    }

    //TODO Cau 4
    public String inDiem() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nDanh Sach Diem Lop : ").append(getTen());
        sb.append("\nGiao Vien Chu Nhiem : ").append(getGiaoVien());
        sb.append("\nSTT\t\tMSSV\t\tTen\t\t\t\tDiemTB\t\tXep Loai");
        AtomicInteger count = new AtomicInteger(1);
        dsLop.forEach(d -> {
            sb.append("\n" + count + "\t\t").append(d.getMssv() + "\t\t").append(d.getTen() + "\t\t\t\t")
                    .append(d.tinhDiemTrungBinh() + "\t\t").append(d.xepLoai());
            count.getAndIncrement();
        });
        return sb.toString();
    }

    //TODO Cau 5
    public List<SinhVien> top10() {
        return dsLop.stream()
                .sorted(Comparator.comparing(SinhVien::tinhDiemTrungBinh).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }

    //TODO Cau 6
    public List<SinhVien> sinhVienYeu() {
        return dsLop.stream()
                .filter(d -> d.xepLoai().equals("YEU"))
                .collect(Collectors.toList());
    }

    public String getTen() {
        return ten;
    }

    public String getGiaoVien() {
        return giaoVien;
    }

    public List<SinhVien> getDsLop() {
        return dsLop;
    }
}
