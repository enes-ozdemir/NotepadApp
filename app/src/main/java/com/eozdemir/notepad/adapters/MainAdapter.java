package com.eozdemir.notepad.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eozdemir.notepad.interfaces.IOnNoteListener;
import com.eozdemir.notepad.R;

import io.realm.Realm;
import io.realm.RealmList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    private Realm mRealm;
    private RealmList<String> mNote;
    private IOnNoteListener mOnNoteListener;

    public MainAdapter(Realm realm, RealmList<String> note, IOnNoteListener onNoteListener) {
        mRealm = realm;
        mNote = note;
        mOnNoteListener=onNoteListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_layout, parent, false);

        return new ViewHolder(view,mOnNoteListener);
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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        IOnNoteListener mOnNoteListener;
        private TextView mNoteHolder;
        //private TextView mDate;


        public ViewHolder(@NonNull View itemView, IOnNoteListener mOnNoteListener) {
            super(itemView);

            mNoteHolder = (TextView) itemView.findViewById(R.id.mNoteHolder);
            //mDate = (TextView) itemView.findViewById(R.id.mDate);
            this.mOnNoteListener = mOnNoteListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mOnNoteListener.onNoteClick(getAdapterPosition());

        }
    }

}

