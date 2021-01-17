package ir.danialchoopan.notepad.adapter;

import android.content.Context;
import android.os.Build;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ir.danialchoopan.notepad.R;
import ir.danialchoopan.notepad.database.model.NoteDataModel;

public class AdapterRecyclerViewNoteList extends RecyclerView.Adapter<AdapterRecyclerViewNoteList.View_holder> {
    Context m_context;
    ArrayList<NoteDataModel> noteDataModels;
    Listener listener;

    public AdapterRecyclerViewNoteList(Context m_context, ArrayList<NoteDataModel> noteDataModels, Listener listener) {
        this.m_context = m_context;
        this.noteDataModels = noteDataModels;
        this.listener = listener;
    }

    @NonNull
    @Override
    public View_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new View_holder(LayoutInflater.from(m_context).inflate(R.layout.row_recycler_view_note_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull View_holder holder, int position) {
        if (noteDataModels.get(position).getText_body().length() > 10) {
            holder.row_lbl_title.setText(noteDataModels.get(position).getText_body().substring(0, 10) + " ...");
        } else {
            holder.row_lbl_title.setText(noteDataModels.get(position).getText_body());
        }
        holder.row_lbl_time.setText(noteDataModels.get(position).getCreated_at());
        holder.itemView.setOnClickListener(view -> {
            listener.onItemClick(noteDataModels.get(position));
        });
        holder.itemView.setOnLongClickListener(view -> {
            listener.onItemLongClick(noteDataModels.get(position));
            return false;
        });
    }

    @Override
    public int getItemCount() {
        return noteDataModels.size();
    }

    static class View_holder extends RecyclerView.ViewHolder {
        TextView row_lbl_title;
        TextView row_lbl_time;

        public View_holder(@NonNull View itemView) {
            super(itemView);
            row_lbl_title = itemView.findViewById(R.id.row_lbl_title);
            row_lbl_time = itemView.findViewById(R.id.row_lbl_time);
        }

    }

    public interface Listener {
        void onItemClick(NoteDataModel noteDataModel);

        void onItemLongClick(NoteDataModel noteDataModel);
    }
}
