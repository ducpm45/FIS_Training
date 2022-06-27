package com.fis.sprint_3.core;

public enum Rank {
    TRAINEE(1),
    JUNIOR(2),
    SENIOR(3),
    INSPECTOR(4),
    CHIEF_INSPECTOR(5);

    private final int code;

    Rank(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
