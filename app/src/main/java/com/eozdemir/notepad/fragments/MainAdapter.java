package com.eozdemir.notepad.fragments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.eozdemir.notepad.R;

import io.realm.Realm;
import io.realm.RealmList;

class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder>{



    RealmList<String> mNote;

    public MainAdapter(RealmList<String> note) {
        mNote = note;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_layout,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mNoteHolder.setText(mNote.get(position));

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mNoteHolder;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mNoteHolder = (TextView) itemView.findViewById(R.id.textView);

        }


    }
}
