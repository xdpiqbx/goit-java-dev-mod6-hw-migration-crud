package com.xdpiqbx.db.DataModels;

public class YoungestEldestWorker {
    private final String type;
    private final String name;
    private final String birthday;

    public YoungestEldestWorker(String type, String name, String birthday) {
        this.type = type;
        this.name = name;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "YoungestEldestWorkers{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", birthday='" + birthday + '\'' +
                '}';
    }
}
