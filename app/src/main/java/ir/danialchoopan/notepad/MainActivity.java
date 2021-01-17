package ir.danialchoopan.notepad;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.sql.Time;
import java.util.Calendar;

import ir.danialchoopan.notepad.fragments.NoteFragment;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawer_layout_main_activity;
    NavigationView nav_menu_main_activity;
    Toolbar toolbar_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //cast
        drawer_layout_main_activity = findViewById(R.id.drawer_layout_main_activity);
        nav_menu_main_activity = findViewById(R.id.nav_menu_main_activity);
        toolbar_main = findViewById(R.id.toolbar_main);
        //end cast
        setSupportActionBar(toolbar_main);
        ActionBarDrawerToggle actionBarDrawerToggle =
                new ActionBarDrawerToggle(MainActivity.this, drawer_layout_main_activity, toolbar_main, R.string.open, R.string.close);
        drawer_layout_main_activity.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        //set note fragment to frame layout
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_fragment, new NoteFragment()).commit();
//        Toast.makeText(this, Calendar.getInstance().getTime().toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        if (drawer_layout_main_activity.isDrawerOpen(GravityCompat.START)) {
            drawer_layout_main_activity.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}