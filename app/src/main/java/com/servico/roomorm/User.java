package com.servico.roomorm;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import static android.os.Build.VERSION_CODES.O;

/**
 * Created by João Victor Firmino on 03/12/2017.
 */

public class User extends AppCompatActivity {

    EditText edtNome, edtSobre, edtTelefone, edtEmail, edtCPF;
    ListView listContato;
    Button fabSalvar;
    FloatingActionButton fabDelete;
    UserOrm user, altuser;
    UserDao userDAO;

    UserAdpt userAdpt;
    List<UserOrm> arrayListUser;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        edtNome = (EditText) findViewById(R.id.edtNome);
        edtSobre = (EditText) findViewById(R.id.edtSobre);
        edtTelefone = (EditText) findViewById(R.id.edtTelefone);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtCPF = (EditText) findViewById(R.id.edtCPF);
        fabSalvar = (Button) findViewById(R.id.fabSalvar);
        //fabSalvar = (FloatingActionButton) findViewById(R.id.fabSalvar);
        listContato = (ListView) findViewById(R.id.listContato);
        fabDelete = (FloatingActionButton) findViewById(R.id.fabDelete);

        final AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "contatos")
                .allowMainThreadQueries()
                .build();

        final Intent i = getIntent();
        altuser = (UserOrm) i.getSerializableExtra("usuario-enviado");

        if (altuser != null) {
            fabSalvar.setText("Alterar");
            edtNome.setText(altuser.getNome());
            edtSobre.setText(altuser.getSobrenome());
            edtTelefone.setText(altuser.getTelefone());
            edtEmail.setText(altuser.getEmail());
            edtCPF.setText(altuser.getCpf());

        } else {
          fabSalvar.setText("Salvar");
       }


        fabSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserOrm userOrm = new UserOrm(edtNome.getText().toString(), edtSobre.getText().toString(), edtTelefone.getText().toString(),
                        edtEmail.getText().toString(), edtCPF.getText().toString());
                    if(altuser != null){
                        userOrm.setId(altuser.getId());
                        db.userDao().update(userOrm);
                    }else{
                        db.userDao().insertAll(userOrm);
                    }

                Intent i = new Intent(User.this, MainActivity.class);
                startActivity(i);
                finish();

            }
        });

        fabDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    db.userDao().delete(altuser);
                Intent i = new Intent(User.this, MainActivity.class);
                startActivity(i);
            }
        });

    }

    private void alert(String s){
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}