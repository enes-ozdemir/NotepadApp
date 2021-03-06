package com.eozdemir.notepad.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.eozdemir.notepad.MainActivity;
import com.eozdemir.notepad.interfaces.IOnNoteListener;
import com.eozdemir.notepad.R;
import com.eozdemir.notepad.adapters.MainAdapter;
import com.eozdemir.notepad.model.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import org.greenrobot.eventbus.EventBus;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class FragmentMain extends Fragment implements SearchView.OnQueryTextListener, IOnNoteListener {

    private static String TAG = "MainPage";

    private RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    private FloatingActionButton mAddNote;
    private Toolbar mToolbar;
    private Realm mRealm;
    private RealmList<String> note;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.main_layout, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rvmain);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        setAddNote(view);
        setToolbar(view);
        readData();

        final SwipeRefreshLayout pullToRefresh = view.findViewById(R.id.pullToRefresh);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.detach(FragmentMain.this).attach(FragmentMain.this).commit();
                pullToRefresh.setRefreshing(false);
            }
        });

        return view;
    }

    private void readData() {

        mRealm = Realm.getDefaultInstance();
        mRealm.beginTransaction();
        mRealm.commitTransaction();

        RealmResults<Note> mNote = mRealm.where(Note.class).findAll();

        note = new RealmList<String>();

        for (int i = 0; i < mNote.size(); i++) {
            note.add(mNote.get(i).getNote().get(0).toString());
        }

        mAdapter = new MainAdapter(mRealm, note, this);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void setAddNote(View view) {

        mAddNote = view.findViewById(R.id.addNote);
        mAddNote.setRippleColor((getResources().getColor(R.color.design_default_color_primary_dark)));

        mAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).setViewPager(1);
                EventBus.getDefault().post(new FragmentAddNote.DeleteNote());

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
        ((MainActivity) getActivity()).getMenuInflater().inflate(R.menu.menu, menu);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public void onNoteClick(int position) {
        ((MainActivity) getActivity()).setViewPager(1);
    }
}
