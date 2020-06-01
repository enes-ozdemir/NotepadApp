package com.eozdemir.notepad.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eozdemir.notepad.MainActivity;
import com.eozdemir.notepad.R;
import com.eozdemir.notepad.model.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class FragmentMain extends Fragment implements SearchView.OnQueryTextListener{

    private static String TAG = "MainPage";

    private RecyclerView mRecyclerView;
    private FloatingActionButton mAddNote;
    private Toolbar mToolbar;
    private Realm mRealm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.main_layout, container, false);

        mRecyclerView = view.findViewById(R.id.rvmain);

        setAddNote(view);
        setToolbar(view);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        try {
            //todo Debug
            mRealm.beginTransaction();
            mRealm = Realm.getDefaultInstance();
            readData();
        }catch (Exception e) {
            Log.e(TAG,"Null");
        }

        return view;

    }

    private void readData() {

        RealmResults<Note> mNotes = mRealm.where(Note.class).findAll();
        //TODO teker teker rcyleview'e atıcaz bunları
        Toast.makeText(getContext(),mNotes.toString(),Toast.LENGTH_SHORT).show();




    }

    private void setAddNote(View view) {
        mAddNote = view.findViewById(R.id.addNote);
        mAddNote.setRippleColor((getResources().getColor(R.color.design_default_color_primary_dark)));


        mAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).setViewPager(1);


                try {
                    readData();
                }catch (Exception e) {
                    Log.e(TAG,"Null");
                }
            }
        });

    }

    private void setToolbar(View view) {

        mToolbar = view.findViewById(R.id.toolbar);
        mToolbar.setTitle(R.string.app_name);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.textPrimaryColor));
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        ((MainActivity)getActivity()).getMenuInflater().inflate(R.menu.menu,menu);

        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
