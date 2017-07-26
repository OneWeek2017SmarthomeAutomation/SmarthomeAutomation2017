package com.microsoft.smarthomeautomation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.microsoft.smarthomeautomation.DTO.Actions;
import com.microsoft.smarthomeautomation.Fragments.ActionsFragment;
import com.microsoft.smarthomeautomation.Fragments.ProgramListFragment;


public class BaseActivity extends AppCompatActivity implements ActionsFragment.OnListFragmentInteractionListener {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    replaceFragment(new ActionsFragment());
                    return true;
                case R.id.navigation_dashboard:
                    replaceFragment(new ProgramListFragment());
                    return true;
                case R.id.navigation_notifications:
                    Toast.makeText(getBaseContext(), "Coming soon", Toast.LENGTH_SHORT);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        replaceFragment(new ActionsFragment());
    }

    @Override
    public void onListFragmentInteraction(Actions.Action item) {

    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

}
