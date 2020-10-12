package Presenter;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.TP2_Mobile.R;
import com.google.android.material.navigation.NavigationView;
import Model.Mod_DBHelper;

import java.util.ArrayList;

public class Pres_TableauDeBord extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView;
    private StudentAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Student> list;
    private Mod_DBHelper dataBase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBase = new Mod_DBHelper(this);


        populateList();
        layoutManager = new LinearLayoutManager(this);
        adapter = new StudentAdapter(list);

        setContentView(R.layout.activity_tableau_de_bord);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        buildRecycleView();
    }

    private void populateList() {
        list = new ArrayList<Student>();

        //Test avec 2 étudiants de la Base de donnée (API)
        list.add(new Student(dataBase.GetDataColumn(Mod_DBHelper.Table.USERS, "2", "nom")
                + " " +dataBase.GetDataColumn(Mod_DBHelper.Table.USERS, "2", "prenom") ,90));
        list.add(new Student(dataBase.GetDataColumn(Mod_DBHelper.Table.USERS, "3", "nom")
                + " " +dataBase.GetDataColumn(Mod_DBHelper.Table.USERS, "3", "prenom") ,90));

        //Ajouter d'autres étudiants
        list.add(new Student ("Antoine Ho", 50));
        list.add(new Student("Kha Pham", 93));
        list.add(new Student("Luke Noodley", 70));
        list.add(new Student("Demetrious Johnson", 30));
        list.add(new Student("Tom Jerry", 100));
        list.add(new Student("Damien DeGaule", 76));
        list.add(new Student("Dwayne Johnson", 60));
        list.add(new Student("Vin Diesel", 60));
    }

    private void buildRecycleView() {
        recyclerView = findViewById(R.id.recycleview_tab_bord);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new StudentAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                notifyItemSelected(position , list.get(position));
            }

            @Override
            public void onDeleteClick(int position) {

            }

        });
    }

    private void notifyItemSelected(int position, Student student) {
        Toast.makeText(this.getBaseContext(),list.get(position).getName(),Toast.LENGTH_SHORT).show();
        openActivity2(student);
    }

    private void openActivity2(Student student) {
        Intent intent = new Intent(getApplicationContext(),
                Pres_ParamEtudiant.class);
        intent.putExtra("ETUDIANT", student);
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         //Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_searchview, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });



        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.logout) {
            dataBase.DisconnectUser();
            Intent intent = new Intent(getApplicationContext(),
                    Pres_LoginPage.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_accueil) {

        }
        else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }




}
