package com.example.ahmedhassan.smartcollege;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnAnnouncementFinished {
    String reg_url = StudentLogin.session.getUrl() + "AnnouncementManagement";//router IP:192.168.1.4
    ArrayList<Announcement> announceList;

    public void setListener(OnAnnouncementFinished listener) {
        this.listener = listener;
    }

    OnAnnouncementFinished listener;
    ListView listView;
    LayoutInflater inflater;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //AnnouncementAdapter adapter =new AnnouncementAdapter(this,)
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);
        TextView name = (TextView) header.findViewById(R.id.studentName);
        TextView id = (TextView) header.findViewById(R.id.studentID);
        Log.e("name", StudentLogin.session.getStudentName());
        Log.e("id", StudentLogin.session.getID());
        name.setText(StudentLogin.session.getStudentName());
        id.setText(StudentLogin.session.getID());


        announceList = new ArrayList<Announcement>();

        listView = (ListView) findViewById(R.id.announcementsListView);


        AnnouncementTask announcementTask = new AnnouncementTask();
        announcementTask.setListener(this);
        announcementTask.execute(reg_url);
        int len = announceList.size();
        /*AnnouncementAdapter adapter=new AnnouncementAdapter(getApplicationContext(),announceList);

       listView.setAdapter(adapter);*/

    }

    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        /*
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            //Intent intent = new Intent(MainActivity.this, MainActivity.class);
        } else {
            super.onBackPressed();
            //Intent intent = new Intent(MainActivity.this, MainActivity.class);
        }*/
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_profile) {


            Intent intent = new Intent(this, Profile.class);
            String data = getIntent().getStringExtra("data");
            intent.putExtra("newdata", "direct==" + data);
            startActivity(intent);
        } else if (id == R.id.nav_addCourses) {
            Intent intent = new Intent(this, AddCourses.class);
            startActivity(intent);
        } else if (id == R.id.nav_viewCourse) {
            Intent intent = new Intent(this, ViewCourses.class);
            startActivity(intent);

        } else if (id == R.id.nav_viewsemester) {
            Intent intent = new Intent(this, ViewAllCourses.class);
            startActivity(intent);

        } else if (id == R.id.nav_contacts) {
            Intent intent = new Intent(this, ContactsActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_change_password) {
            Intent intent = new Intent(this, ChangePassword.class);
            startActivity(intent);
        } else if (id == R.id.nav_logout) {
            Intent intent = new Intent(this, StudentLogin.class);
            startActivity(intent);
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
            FetchAnnouncment fetchAnnouncment = new FetchAnnouncment(listener);
            fetchAnnouncment.execute(reg_url);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void announcementFinished(ArrayList<Announcement> list) {
        this.announceList = list;
        AnnouncementAdapter adapter = new AnnouncementAdapter(getApplicationContext(), list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, Announcement_Details.class);
                intent.putExtra("announce", "" + announceList.get(position).getTitle() + "==" +
                        announceList.get(position).getDate() + "==" + announceList.get(position).getBody());
                startActivity(intent);
            }
        });
        /*AnnouncementTask announcementTask=new AnnouncementTask();
        announcementTask.execute(reg_url);*/

    }


}

