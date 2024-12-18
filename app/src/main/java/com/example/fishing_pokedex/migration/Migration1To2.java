package com.example.fishing_pokedex.migration;

import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class Migration1To2 extends Migration {
    public Migration1To2() {
        super(1, 2);
    }

    @Override
    public void migrate(SupportSQLiteDatabase database) {
        database.execSQL("ALTER TABLE fish_table ADD COLUMN photoPath TEXT");
    }
}

