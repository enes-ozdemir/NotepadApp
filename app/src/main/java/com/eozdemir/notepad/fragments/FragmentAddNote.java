package com.eozdemir.notepad.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.eozdemir.notepad.MainActivity;
import com.eozdemir.notepad.R;
import com.eozdemir.notepad.model.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class FragmentAddNote extends Fragment {

    private static String TAG = "AddNote";

    ImageButton btnBack;
    ImageButton btnDelete;
    FloatingActionButton fabSave;
    EditText mEditText;
    Toolbar mToolbar;
    Realm mRealm;
    private RealmList<String> note;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.add_note_layout, container, false);

        btnBack = view.findViewById(R.id.btn_back);
        btnDelete = view.findViewById(R.id.btn_delete);
        fabSave = view.findViewById(R.id.fabSave);
        mEditText = view.findViewById(R.id.etNote);

        mRealm = Realm.getDefaultInstance();
        mRealm.beginTransaction();
        mRealm.commitTransaction();
        RealmResults<Note> mNote = mRealm.where(Note.class).findAll();

        mEditText.setText(mNote.get(1).getNote().get(0).toString());

        setToolbar(view);

        fabSave.setRippleColor((getResources().getColor(R.color.design_default_color_primary_dark)));

        Bundle bundle =this.getArguments();
        if(bundle!=null) {
            String data= bundle.getString("key");
            mEditText.setText(data);
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).setViewPager(0);
            }
        });

        fabSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                note = new RealmList<>();
                mRealm = Realm.getDefaultInstance();
                mRealm.beginTransaction();
                mRealm.commitTransaction();
                note.add(mEditText.getText().toString());
                mRealm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        Note mNote = realm.createObject(Note.class);
                        mNote.setNote(note);
                        //mNote.setDate(new Date().toString());
                    }
                });
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditText.setText("");
                ((MainActivity)getActivity()).setViewPager(0);
                Toast.makeText(getContext(),R.string.note_deleted, Toast.LENGTH_SHORT).show();

            }
        });

        return view;
    }

    private void setToolbar(View view) {
        mToolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        setHasOptionsMenu(true);


    }


}
