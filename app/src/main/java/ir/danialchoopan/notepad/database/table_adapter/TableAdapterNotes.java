package ir.danialchoopan.notepad.database.table_adapter;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import ir.danialchoopan.notepad.database.DB_connection;
import ir.danialchoopan.notepad.database.model.NoteDataModel;

public class TableAdapterNotes extends DB_connection {
    public TableAdapterNotes(@Nullable Context context) {
        super(context);
    }

    public ArrayList<NoteDataModel> all() {
        ArrayList<NoteDataModel> noteDataModels = new ArrayList<>();
        SQLiteDatabase readableDatabase = this.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor = readableDatabase.rawQuery("SELECT * FROM `tbl_notes`", null);
        if (cursor.moveToFirst()) {
            do {
                NoteDataModel noteDataModel = new NoteDataModel();
                noteDataModel.setId(cursor.getInt(cursor.getColumnIndex("id")));
                noteDataModel.setText_body(cursor.getString(cursor.getColumnIndex("text")));
                noteDataModel.setUpdated_at(cursor.getString(cursor.getColumnIndex("updated_at")));
                noteDataModel.setCreated_at(cursor.getString(cursor.getColumnIndex("created_at")));
                noteDataModels.add(noteDataModel);
            } while (cursor.moveToNext());
        }
        return noteDataModels;
    }

    public NoteDataModel find_by_id(int id) {
        NoteDataModel noteDataModel = new NoteDataModel();
        SQLiteDatabase readableDatabase = this.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor = readableDatabase.rawQuery("SELECT * FROM `tbl_notes` WHERE `id`=" + id, null);
        if (cursor.moveToFirst()) {
            noteDataModel.setId(cursor.getInt(cursor.getColumnIndex("id")));
            noteDataModel.setText_body(cursor.getString(cursor.getColumnIndex("text")));
            noteDataModel.setUpdated_at(cursor.getString(cursor.getColumnIndex("updated_at")));
            noteDataModel.setCreated_at(cursor.getString(cursor.getColumnIndex("created_at")));
        }
        return noteDataModel;
    }

    public boolean insert(NoteDataModel noteDataModel) {
        SQLiteDatabase writableDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("text", noteDataModel.getText_body());
        contentValues.put("updated_at", noteDataModel.getUpdated_at());
        contentValues.put("created_at", noteDataModel.getCreated_at());
        long tbl_notes_result = writableDatabase.insert("tbl_notes", null, contentValues);
        return tbl_notes_result != 0;
    }

    public void delete(int id) {
        SQLiteDatabase writableDatabase = this.getWritableDatabase();
        writableDatabase.execSQL("DELETE FROM `tbl_notes` WHERE `id`=" + id);
    }

    public boolean update(NoteDataModel noteDataModel) {
        SQLiteDatabase writableDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("text", noteDataModel.getText_body());
        contentValues.put("updated_at", noteDataModel.getUpdated_at());
        long tbl_notes_result = writableDatabase.update("tbl_notes", contentValues, "`id`=?"
                , new String[]{String.valueOf(noteDataModel.getId())});
        return tbl_notes_result != 0;
    }
}
