package com.tecif.myfav;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Item> items;
    private RecyclerView rv;
    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        items = new ArrayList<>();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        rv = (RecyclerView) findViewById(R.id.itemsList);
        progressDialog(getString(R.string.cargando),getString(R.string.cargando_datos));
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        Firebase myFirebaseRef = new Firebase("https://myfav-1a498.firebaseio.com/Restaurantes/Tecif");
        myFirebaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                                         @Override
                                                         public void onDataChange(DataSnapshot snapshot) {
                                                             for (DataSnapshot child : snapshot.getChildren()) {

                                                                 items.add(
                                                                         new Item(
                                                                                 child.child("titulo").getValue().toString(),
                                                                                 child.child("descripcion").getValue().toString(),
                                                                                 child.child("urlPhoto").getValue().toString(),
                                                                                 Float.parseFloat(child.child("rank").getValue().toString())));
                                                             }
                                                             initializeAdapter();
                                                         }

                                                         @Override
                                                         public void onCancelled(FirebaseError firebaseError) {
                                                             progress.dismiss();
                                                         }

                                                     }
        );
    }

    public void progressDialog(String titulo, String desc){
        progress = new ProgressDialog(this);
        progress.setTitle(titulo);
        progress.setMessage(desc);
        progress.show();
    }

    private void initializeAdapter() {
        Adapter adapter = new Adapter(items);
        rv.setAdapter(adapter);
        progress.dismiss();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_acerca) {
            Snackbar.make(this.findViewById(android.R.id.content), "Desarrollado por Jorge Erick Fabián Villegas", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
