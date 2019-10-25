package com.example.architechureexample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

//changed Accordingly

public class NoteAdapter extends ListAdapter<Note, NoteAdapter.NoteHolder> {
    private OnItemClickListener listener;

//    private List<Note> notes= new ArrayList<>();   // list paased in super claas

   public NoteAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Note> DIFF_CALLBACK = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(Note oldItem, Note newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(Note oldItem, Note newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) &&
                    oldItem.getDescription().equals(newItem.getDescription()) &&
                    oldItem.getPriority() == newItem.getPriority();
        }
    };






    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item,parent,false);
        return new NoteHolder(itemView);
    }





    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {

//        Note currentNote = notes.get(position);

        Note currentNote = getItem(position);
        holder.textViewtitle.setText(currentNote.getTitle());
        holder.textViewDescription.setText(currentNote.getDescription());
        holder.textViewPriorit.setText(String.valueOf(currentNote.getPriority()));



    }







    public Note getNoteAt(int position) {
        return getItem(position);
    }


    //list will take care off it check
//
//    @Override
//    public int getItemCount() {
//        return notes.size();
//    }
//
//
//    public  void setNotes(List<Note> notes){
//        this.notes=notes;
//        notifyDataSetChanged();
//    }







    class NoteHolder extends  RecyclerView.ViewHolder{
        private TextView textViewtitle;
        private TextView textViewDescription;
        private  TextView textViewPriorit;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);

            textViewtitle=itemView.findViewById(R.id.title);
            textViewDescription=itemView.findViewById(R.id.description);
            textViewPriorit=itemView.findViewById(R.id.prirotity);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }







    public interface OnItemClickListener {
        void onItemClick(Note note);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}
