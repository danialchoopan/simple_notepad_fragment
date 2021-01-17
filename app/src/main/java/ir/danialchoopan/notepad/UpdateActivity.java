package ir.danialchoopan.notepad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

import ir.danialchoopan.notepad.database.model.NoteDataModel;
import ir.danialchoopan.notepad.database.table_adapter.TableAdapterNotes;

public class UpdateActivity extends AppCompatActivity {
    Toolbar toolbar_update_note;
    EditText edt_note_update;
    int note_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        note_id = getIntent().getIntExtra("note_id", 0);
        //cast
        toolbar_update_note = findViewById(R.id.toolbar_update_note);
        edt_note_update = findViewById(R.id.edt_note_update);
        //end cast
        setSupportActionBar(toolbar_update_note);
        setTitle("Update Note");
        TableAdapterNotes tableAdapterNotes = new TableAdapterNotes(UpdateActivity.this);
        NoteDataModel note_model = tableAdapterNotes.find_by_id(note_id);
        edt_note_update.setText(note_model.getText_body());
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (edt_note_update.getText().toString().equals("")) {
            finish();
        } else {
            TableAdapterNotes tableAdapterNotes = new TableAdapterNotes(UpdateActivity.this);
            NoteDataModel noteDataModel = new NoteDataModel();
            noteDataModel.setId(note_id);
            noteDataModel.setText_body(edt_note_update.getText().toString());
            noteDataModel.setUpdated_at(Calendar.getInstance().getTime().toString());
            if (tableAdapterNotes.update(noteDataModel)) {
                Toast.makeText(UpdateActivity.this, "نوشته شما با موفیت بروزرسانی شد", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(UpdateActivity.this, "مشکلی در ذخیره کرده پیش آمده است", Toast.LENGTH_SHORT).show();
            }
        }
    }
}