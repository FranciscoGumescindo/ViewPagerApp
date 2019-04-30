package com.android.gumeoficial.viewpagerapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.gumeoficial.viewpagerapp.Model.Movie;
import com.android.gumeoficial.viewpagerapp.R;
import com.google.firestore.v1.TargetOrBuilder;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends PagerAdapter{

    Context context;
    List<Movie> movieList;
    LayoutInflater inflater;

    public MyAdapter(Context context, List<Movie> movieList) {
        this.context = context;
        this.movieList = movieList;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return movieList.size();

    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ((ViewPager)container).removeView((View)object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        //Inflate view
        View view = inflater.inflate(R.layout.view_pager_item,container,false);

        //View
        ImageView movie_image  = (ImageView)view.findViewById(R.id.movie_image);
        TextView movie_title = (TextView)view.findViewById(R.id.movie_title);
        TextView movie_description  = (TextView)view.findViewById(R.id.movie_description);
        FloatingActionButton btn_fav = (FloatingActionButton)view.findViewById(R.id.btn_fav);

        //Evento
        btn_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Film Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        //Set data
        Picasso.get().load(movieList.get(position).getImage()).into(movie_image);
        movie_title.setText(movieList.get(position).getName());
        movie_description.setText(movieList.get(position).getDescription());


        container.addView(view);//Dont forged i1t
        return view;


    }
}
