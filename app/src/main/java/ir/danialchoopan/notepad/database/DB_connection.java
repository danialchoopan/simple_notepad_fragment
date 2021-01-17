package ir.danialchoopan.notepad.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

abstract public class DB_connection extends SQLiteOpenHelper {
    Context m_context;
    public DB_connection(@Nullable Context context) {
        super(context, "db_notes", null, 1);
        this.m_context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql_create_table_notes=
                "CREATE TABLE IF NOT EXISTS `tbl_notes`" +
                        " ( `id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT , " +
                        "`text` TEXT NOT NULL ," +
                        "`updated_at` VARCHAR NOT NULL " +
                        ",`created_at` VARCHAR NOT NULL) ";
        sqLiteDatabase.execSQL(sql_create_table_notes);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql_drop_table_notes="DROP TABLE IF EXISTS `tbl_notes`";
        sqLiteDatabase.execSQL(sql_drop_table_notes);
    }
}
