package ir.danialchoopan.notepad.fragments;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import ir.danialchoopan.notepad.AddNoteActivity;
import ir.danialchoopan.notepad.R;
import ir.danialchoopan.notepad.UpdateActivity;
import ir.danialchoopan.notepad.adapter.AdapterRecyclerViewNoteList;
import ir.danialchoopan.notepad.database.model.NoteDataModel;
import ir.danialchoopan.notepad.database.table_adapter.TableAdapterNotes;

public class NoteFragment extends Fragment {
    RecyclerView recycler_show_notes;
    TextView lbl_msg_empty;
    FloatingActionButton fbtn_add_note;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view_fragment = inflater.inflate(R.layout.fragment_note, container, false);
        recycler_show_notes = view_fragment.findViewById(R.id.recycler_show_notes);
        //cast
        lbl_msg_empty = view_fragment.findViewById(R.id.lbl_msg_empty);
        fbtn_add_note = view_fragment.findViewById(R.id.fbtn_add_note);
        //end cast
        fbtn_add_note.setOnClickListener(view -> {
            startActivity(new Intent(getActivity(), AddNoteActivity.class));
        });
        ArrayList<NoteDataModel> noteDataModels = new TableAdapterNotes(getActivity()).all();
        if (noteDataModels.size() == 0) {
            lbl_msg_empty.setVisibility(View.VISIBLE);
        } else {
            lbl_msg_empty.setVisibility(View.GONE);
        }
        return view_fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        final TableAdapterNotes tableAdapterNotes = new TableAdapterNotes(getActivity());
        ArrayList<NoteDataModel> noteDataModels = tableAdapterNotes.all();
        if (noteDataModels.size() == 0) {
            lbl_msg_empty.setVisibility(View.VISIBLE);
        } else {
            lbl_msg_empty.setVisibility(View.GONE);
        }
        recycler_show_notes.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler_show_notes.setAdapter(new AdapterRecyclerViewNoteList(getActivity(), noteDataModels, new AdapterRecyclerViewNoteList.Listener() {
            @Override
            public void onItemClick(NoteDataModel noteDataModel) {
                Intent intent_update_note = new Intent(getActivity(), UpdateActivity.class);
                intent_update_note.putExtra("note_id", noteDataModel.getId());
                startActivity(intent_update_note);
            }

            @Override
            public void onItemLongClick(NoteDataModel noteDataModel) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                alertDialog.setTitle("پیام برنامه");
                alertDialog.setMessage("ایا از حذف این نوشته مطمئن هستید؟");
                alertDialog.setPositiveButton("بله", (dialogInterface, i) -> {
                    tableAdapterNotes.delete(noteDataModel.getId());
                    Toast.makeText(getActivity(), "نوشته شما با موفیت حدف گردید", Toast.LENGTH_SHORT).show();
                    getFragmentManager()
                            .beginTransaction()
                            .detach(NoteFragment.this)
                            .attach(NoteFragment.this)
                            .commit();
                });
                alertDialog.setNegativeButton("نه شوخی کردم", (dialogInterface, i) -> {
                });
                alertDialog.show();
            }
        }));
    }
}