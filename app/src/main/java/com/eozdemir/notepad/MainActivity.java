package com.eozdemir.notepad;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.eozdemir.notepad.adapters.FragmentAdapter;
import com.eozdemir.notepad.fragments.FragmentAddNote;
import com.eozdemir.notepad.fragments.FragmentMain;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    private FragmentAdapter mFragmentAdapter;
    private ViewPager mViewPager;
    Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFragmentAdapter = new FragmentAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.containter);
        setupViewPager(mViewPager);
        mRealm = Realm.getDefaultInstance();
        mRealm.beginTransaction();
        mRealm.commitTransaction();


    }



    private void setupViewPager(ViewPager viewPager) {

        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentMain(), "MainPage");
        adapter.addFragment(new FragmentAddNote(), "AddNote");
        viewPager.setAdapter(adapter);

    }

    public void setViewPager(int fragmentNumber) {

        mViewPager.setCurrentItem(fragmentNumber);
    }

}
