package com.eozdemir.notepad;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.eozdemir.notepad.adapters.FragmentAdapter;
import com.eozdemir.notepad.fragments.FragmentAddNote;
import com.eozdemir.notepad.fragments.FragmentMain;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);
        mRealm = Realm.getDefaultInstance();
        mRealm.beginTransaction();
        mRealm.commitTransaction();
    }

    private void setupViewPager(ViewPager viewPager) {

        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(), 0);
        adapter.addFragment(new FragmentMain(), "MainPage");
        adapter.addFragment(new FragmentAddNote(), "AddNote");
        viewPager.setAdapter(adapter);

    }

    public void setViewPager(int fragmentNumber) {
        mViewPager.setCurrentItem(fragmentNumber);
    }

}
