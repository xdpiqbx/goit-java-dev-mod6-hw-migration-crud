package com.xdpiqbx.db.services;

import com.xdpiqbx.common.Helper;
import org.flywaydb.core.Flyway;

public class DatabaseMigrateService {
    public static void migrateDatabase(){
        Flyway flyway = Flyway
                .configure()
                .dataSource(Helper.env("DB_URL"), null, null)
                .load();
        flyway.migrate();
    }
}