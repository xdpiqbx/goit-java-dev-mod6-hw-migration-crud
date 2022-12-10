package com.xdpiqbx.db.DataModels;

public class LongestProject {
    private final String name;
    private final int monthCount;

    public LongestProject(String name, int monthCount) {
        this.name = name;
        this.monthCount = monthCount;
    }

    @Override
    public String toString() {
        return "MaxProjectCountClient{" +
                "name='" + name + '\'' +
                ", month_count=" + monthCount +
                '}';
    }
}
