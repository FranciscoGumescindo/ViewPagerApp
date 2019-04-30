package com.android.gumeoficial.viewpagerapp.Interface;

import com.android.gumeoficial.viewpagerapp.Model.Movie;

import java.util.List;

public interface IFireStoreLoadDone {

    void onFireStoreLoadSuccess(List<Movie> movies);
    void onFireStoreLoadFailed(String message);

}
