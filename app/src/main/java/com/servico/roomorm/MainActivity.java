package com.servico.roomorm;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = MainActivity.class.getName();

    ListView listContato;
    ArrayList<UserOrm> arrayListUser;
    ArrayAdapter<UserOrm> arrayAdapterUser;
    FloatingActionButton fab, fab2, fab3;
    UserDao userDao;
    UserOrm userOrm;

    private AppDatabase db;


    //RecyclerView listContato;
    //RecyclerView.Adapter adapter;
    //List<UserOrm> arrayListUser;
    //ArrayAdapter<User> arrayAdapterUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listContato = (ListView) findViewById(R.id.listContato);
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "contatos")
                .allowMainThreadQueries()
                .build();


        final List<UserOrm> userOrms =  db.userDao().getAll();

        /*listContato = (RecyclerView) findViewById(R.id.listContato);
        listContato.setLayoutManager(new LinearLayoutManager(this));
        adapter = new UserAdpt(userOrms);
        listContato.setAdapter(adapter);*/

        //fab = findViewById(R.id.fab); nova maneira
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, User.class);
                startActivity(i);
                //startActivity(new Intent(MainActivity.this, User.class)); nova maneira
                //Log.i(TAG, "Funciona!");
            }
        });

        listContato.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                UserOrm envioUser = (UserOrm) arrayAdapterUser.getItem(position);
                Intent i = new Intent(MainActivity.this, User.class);
                i.putExtra("usuario-enviado", envioUser);
                startActivity(i);
            }
        });

        listContato.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                registerForContextMenu(view);
                userOrm = arrayAdapterUser.getItem(position);
                openContextMenu(view);
                return false;
            }
        });
    }

    public void escreveLista(){
        arrayListUser =(ArrayList<UserOrm>) db.userDao().getAll();


        if (listContato != null){
            arrayAdapterUser = new ArrayAdapter<UserOrm>(MainActivity.this,
                    android.R.layout.simple_list_item_1, arrayListUser);
            listContato.setAdapter(arrayAdapterUser);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        escreveLista();
    }
}
