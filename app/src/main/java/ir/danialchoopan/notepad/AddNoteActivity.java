package ir.danialchoopan.notepad;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import java.util.Calendar;
import java.util.Objects;

import ir.danialchoopan.notepad.database.model.NoteDataModel;
import ir.danialchoopan.notepad.database.table_adapter.TableAdapterNotes;

public class AddNoteActivity extends AppCompatActivity {
    EditText edt_note_add;
    Toolbar toolbar_add_note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        //cast
        edt_note_add = findViewById(R.id.edt_note_add);
        toolbar_add_note = findViewById(R.id.toolbar_add_note);
        //end cast
        setSupportActionBar(toolbar_add_note);
        setTitle("Add note");
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (!edt_note_add.getText().toString().trim().equals("")) {
            TableAdapterNotes tableAdapterNotes = new TableAdapterNotes(AddNoteActivity.this);
            NoteDataModel noteDataModel = new NoteDataModel();
            noteDataModel.setText_body(edt_note_add.getText().toString());
            noteDataModel.setUpdated_at(Calendar.getInstance().getTime().toString());
            noteDataModel.setCreated_at(Calendar.getInstance().getTime().toString());
            if (tableAdapterNotes.insert(noteDataModel)) {
                Toast.makeText(AddNoteActivity.this, "نوشته شما با موفیت ذخیره شد", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(AddNoteActivity.this, "مشکلی در ذخیره کرده پیش آمده است", Toast.LENGTH_SHORT).show();
            }
        }
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}