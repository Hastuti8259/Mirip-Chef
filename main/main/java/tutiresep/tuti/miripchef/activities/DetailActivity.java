package tutiresep.tuti.miripchef.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import tutiresep.tuti.miripchef.R;
import tutiresep.tuti.miripchef.fragments.BahanFragment;
import tutiresep.tuti.miripchef.fragments.LangkahFragment;
import tutiresep.tuti.miripchef.models.RecipeModel;
import tutiresep.tuti.miripchef.utils.TinyDB;

public class DetailActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ImageView imageView,tabBg;
    private CollapsingToolbarLayout collapsingToolbar;
    private TabPagerAdapter tabPagerAdapter;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    FirebaseDatabase database;
    DatabaseReference myRef, myResep ;
    List<RecipeModel> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        final TinyDB tinydb = new TinyDB(getApplicationContext());
        final RecipeModel model = new RecipeModel();

        Intent getIntent= getIntent();
        final Bundle b = getIntent.getExtras();

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("daftar_resep");
        Log.d("TAG-firebase", myRef.toString());

        imageView= (ImageView) findViewById(R.id.backdrop);
        tabBg= (ImageView) findViewById(R.id.tabBg);
        collapsingToolbar=(CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        list = new ArrayList<RecipeModel>();


        if (b!=null){
            Log.d("TAG-if-1", "masuk if pertama");

            myRef.child(b.getString("posisi")).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.

                    RecipeModel value = dataSnapshot.getValue(RecipeModel.class);
                    RecipeModel model = new RecipeModel();
                    String judul = value.getJudul();
                    String kategori = value.getKategori();
                    String bahan = value.getBahan();
                    String langkah = value.getLangkah();
                    tinydb.putString("bahan", bahan);
                    tinydb.putString("langkah", langkah);
                    model.setJudul(judul);
                    model.setKategori(kategori);
                    model.setBahan(bahan);
                    model.setLangkah(langkah);
                    list.add(model);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            /*myResep = myRef.child(b.getString("posisi"))
            Query idQuery = myRef.orderByChild("id").equalTo(b.getString("posisi"));
            idQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    Log.d("TAG-datasnapshot1", dataSnapshot.toString());

                    list = new ArrayList<RecipeModel>();
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                        Log.d("TAG-datasnapshot2", dataSnapshot1.toString());

                        RecipeModel value = dataSnapshot1.getValue(RecipeModel.class);
                        RecipeModel model = new RecipeModel();
                        String judul = value.getJudul();
                        String bahan = value.getBahan();
                        String langkah = value.getLangkah();
                        tinydb.putString("judul", judul);
                        tinydb.putString("bahan", bahan);
                        tinydb.putString("langkah", langkah);
                        model.setJudul(judul);
                        model.setBahan(bahan);
                        model.setLangkah(langkah);
                        list.add(model);

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                    // Failed to read value
                    Log.w("Hello", "Failed to read value.", databaseError.toException());
                }
            });*/
        }

        if(b!=null)
        {
            Log.d("TAG-judul", b.getString("judul"));
            String dataJudul = b.getString("judul");
            collapsingToolbar.setTitle(dataJudul);
        }

        setToolbar();
        setImage();

        mViewPager= (ViewPager) findViewById(R.id.viewpager);
        mTabLayout= (TabLayout) findViewById(R.id.detail_tabs);
        tabPagerAdapter=new TabPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(tabPagerAdapter);
        mTabLayout.setTabsFromPagerAdapter(tabPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                /*if(position==0){
                    Glide.with(DetailActivity.this).load(R.drawable.onet).into(imageView);
                    Glide.with(DetailActivity.this).load(R.drawable.oneb).into(tabBg);
                } else {
                    Glide.with(DetailActivity.this).load(R.drawable.twot).into(imageView);
                    Glide.with(DetailActivity.this).load(R.drawable.twob).into(tabBg);
                }*/

                imageView.invalidate();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

//        Log.d("TAG-model", list.get(0).getJudul());
        Log.d("TAG-tinydb", tinydb.getString("bahan"));

    }

    private void setToolbar() {
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }

    private void setImage() {
        Glide.with(this).load(R.drawable.onet).into(imageView);
        Glide.with(DetailActivity.this).load(R.drawable.oneb).into(tabBg);
    }

    class TabPagerAdapter extends FragmentStatePagerAdapter {

        final TinyDB tinydb = new TinyDB(getApplicationContext());

        public TabPagerAdapter(FragmentManager fm){
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            if(position==0){
                String dataLangkah = tinydb.getString("langkah");
                LangkahFragment sampleFragment=LangkahFragment.newInstance(dataLangkah);
                return sampleFragment;
            } else {
                String dataBahan = tinydb.getString("bahan");
                BahanFragment sampleFragment=BahanFragment.newInstance(dataBahan);
                return sampleFragment;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if(position==0){
                return "Langkah";
            } else {
                return "Bahan";
            }
        }
    }

}
