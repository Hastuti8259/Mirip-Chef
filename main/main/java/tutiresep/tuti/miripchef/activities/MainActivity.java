package tutiresep.tuti.miripchef.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import tutiresep.tuti.miripchef.R;
import tutiresep.tuti.miripchef.adapters.RecyclerAdapter;
import tutiresep.tuti.miripchef.models.RecipeModel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef ;
    List<RecipeModel> list;
    RecyclerView recycle;
    Button view;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycle = (RecyclerView) findViewById(R.id.recycle);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("daftar_resep");
        Log.d("TAG-firebase", myRef.toString());

        /*setSupportActionBar(toolbar);*/

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                list = new ArrayList<RecipeModel>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    Log.d("TAG-datasnapshot1", dataSnapshot1.toString());

                    RecipeModel value = dataSnapshot1.getValue(RecipeModel.class);
                    RecipeModel model = new RecipeModel();
                    String judul = value.getJudul();
                    String kategori = value.getKategori();
                    String bahan = value.getBahan();
                    String langkah = value.getLangkah();
                    model.setJudul(judul);
                    model.setKategori(kategori);
                    model.setBahan(bahan);
                    model.setLangkah(langkah);
                    list.add(model);

                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Hello", "Failed to read value.", error.toException());
            }
        });

        openAdapter();

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
        if (id == R.id.action_update) {
            openAdapter();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void openAdapter(){

        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(list, MainActivity.this);
        RecyclerView.LayoutManager recyce = new GridLayoutManager(MainActivity.this, 2);
        /// RecyclerView.LayoutManager recyce = new LinearLayoutManager(MainActivity.this);
        // recycle.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recycle.setLayoutManager(recyce);
        recycle.setItemAnimator(new DefaultItemAnimator());
        recycle.setAdapter(recyclerAdapter);

    }
}
