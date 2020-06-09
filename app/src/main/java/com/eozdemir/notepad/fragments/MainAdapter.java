package com.eozdemir.notepad.fragments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eozdemir.notepad.R;

import io.realm.Realm;
import io.realm.RealmList;

class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    private Realm mRealm;
    RealmList<String> mNote;

    public MainAdapter(Realm realm, RealmList<String> note) {
        mRealm = realm;
        mNote = note;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        mRealm = Realm.getDefaultInstance();
        mRealm.beginTransaction();
        mRealm.commitTransaction();

        if (mNote != null) {
            holder.mNoteHolder.setText(mNote.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mNote.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mNoteHolder;
        public TextView mDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mNoteHolder = (TextView) itemView.findViewById(R.id.mNoteHolder);
            mDate = (TextView) itemView.findViewById(R.id.mDate);
        }


    }
}
