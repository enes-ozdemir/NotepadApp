package com.eozdemir.notepad.fragments;

import android.os.Bundle;
import android.util.Log;
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
import com.eozdemir.notepad.interfaces.Updateable;
import com.eozdemir.notepad.model.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class FragmentAddNote extends Fragment implements Updateable {

    private static String TAG = "AddNote";

    ImageButton btnBack;
    ImageButton btnDelete;
    FloatingActionButton fabSave;
    EditText mEditText;
    Toolbar mToolbar;
    static Realm mRealm;
    private static RealmList<String> note;
    static int position;


    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getFragmentManager() != null) {

            getFragmentManager()
                    .beginTransaction()
                    .detach(this)
                    .attach(this)
                    .commit();

         /*  FragmentTransaction ft = getFragmentManager().beginTransaction();
           if (Build.VERSION.SDK_INT >= 26) {
               ft.setReorderingAllowed(false);
           }
           ft.detach(this).attach(this).commit();

*/
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.add_note_layout, container, false);

        btnBack = view.findViewById(R.id.btn_back);
        btnDelete = view.findViewById(R.id.btn_delete);
        fabSave = view.findViewById(R.id.fabSave);
        mEditText = view.findViewById(R.id.etNote);
        EventBus.getDefault().register(this);

        mRealm = Realm.getDefaultInstance();
        mRealm.beginTransaction();
        mRealm.commitTransaction();

        ////////////////////////////////////

        RealmResults<Note> mNote = mRealm.where(Note.class).findAll();

        mNote.get(position);

        //mEditText.setText(mNote.get(Integer.valueOf(String.valueOf(mEditText.getText()))).getNote().get(0));


        mEditText.setText(mNote.get(position).getNote().get(0));

        //////////////////////////////////////
        setToolbar(view);

        fabSave.setRippleColor((getResources().getColor(R.color.design_default_color_primary_dark)));

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String data = bundle.getString("key");
            mEditText.setText(data);
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).setViewPager(0);
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
                ((MainActivity) getActivity()).setViewPager(0);
                Toast.makeText(getContext(), R.string.note_deleted, Toast.LENGTH_SHORT).show();

            }
        });

        return view;
    }

    private void setToolbar(View view) {
        mToolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        setHasOptionsMenu(true);


    }

    @Subscribe
    public void onMessageEvent(MessageEvent event) {
    }

    public int Enes(int position) {
        return position;
    }

    @Override
    public void update() {

    }

    public static class MessageEvent {
        public MessageEvent(int positionn) {

            Log.e("ASD", String.valueOf(position));
            position = positionn;

        }

    }
}
