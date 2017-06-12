package tutiresep.tuti.miripchef.activities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
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
        }

        if(b!=null)
        {
            Log.d("TAG-judul", b.getString("judul"));
            String dataJudul = b.getString("judul");
            collapsingToolbar.setTitle(dataJudul);
        }

        setToolbar();
        setImage(b.getString("gambar"));

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

    private void setImage(String string) {
        Glide.with(this).load(getImage(string)).asBitmap().error(R.drawable.onet).into(imageView);
        /*Glide.with(DetailActivity.this).load(R.drawable.oneb).into(tabBg);*/
    }

    public int getImage(String imageName) {

        int drawableResourceId = this.getResources().getIdentifier(imageName, "drawable", this.getPackageName());

        return drawableResourceId;
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
