package tutiresep.tuti.miripchef.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import tutiresep.tuti.miripchef.R;
import tutiresep.tuti.miripchef.activities.DetailActivity;
import tutiresep.tuti.miripchef.models.RecipeModel;

/**
 * Created by Rumah on 5/27/2017.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyHoder>{
    List<RecipeModel> list;
    Context context;

    public RecyclerAdapter(List<RecipeModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyHoder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.card,parent,false);
        MyHoder myHoder = new MyHoder(view);



        return myHoder;
    }

    @Override
    public void onBindViewHolder(MyHoder holder, final int position) {
        final RecipeModel model = list.get(position);
        holder.judul.setText(model.getJudul());
        holder.kategori.setText(model.getKategori());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, DetailActivity.class);
                String pos= String.valueOf(position);
                i.putExtra("posisi", pos);
                i.putExtra("judul", model.getJudul());
                i.putExtra("bahan", model.getBahan());
                i.putExtra("langkah", model.getLangkah());

                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {

        int arr = 0;

        try{
            if(list.size()==0){

                arr = 0;

            }
            else{

                arr=list.size();
            }



        }catch (Exception e){



        }

        return arr;

    }

    class MyHoder extends RecyclerView.ViewHolder{
        TextView judul,kategori;
        CardView cardView;

        public MyHoder(final View itemView) {
            super(itemView);
            judul = (TextView) itemView.findViewById(R.id.tv_judul);
            kategori = (TextView) itemView.findViewById(R.id.tv_kategori);
            cardView = (CardView)  itemView.findViewById(R.id.card_view);

        }
    }
}
