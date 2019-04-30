package com.android.gumeoficial.viewpagerapp;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.android.gumeoficial.viewpagerapp.Adapter.MyAdapter;
import com.android.gumeoficial.viewpagerapp.Interface.IFireStoreLoadDone;
import com.android.gumeoficial.viewpagerapp.Model.Movie;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IFireStoreLoadDone {

    ViewPager viewPager;
    MyAdapter myAdapter;

    IFireStoreLoadDone iFireStoreLoadDone;

    CollectionReference movies;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init
        iFireStoreLoadDone = this;
        movies = FirebaseFirestore.getInstance().collection("Movies");

        //View
        viewPager = (ViewPager)findViewById(R.id.view_pager);

        getData();


    }

    private void getData() {
        movies.get()
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        iFireStoreLoadDone.onFireStoreLoadFailed(e.getMessage());
                    }
                })
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){

                            List<Movie> movies = new ArrayList<>();
                            for(QueryDocumentSnapshot movieSnapShot: task.getResult()){
                                Movie movie = movieSnapShot.toObject(Movie.class);
                                movies.add(movie);
                            }
                            iFireStoreLoadDone.onFireStoreLoadSuccess(movies);
                        }
                    }
                });
    }

    @Override
    public void onFireStoreLoadSuccess(List<Movie> movies) {
        myAdapter = new MyAdapter(this,movies);
        viewPager.setAdapter(myAdapter);

    }

    @Override
    public void onFireStoreLoadFailed(String message) {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
