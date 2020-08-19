package com.rescodytask.ui;


import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rescodytask.api.Client;
import com.rescodytask.api.Services;
import com.rescodytask.pojo.Entry;
import com.rescodytask.pojo.Response;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class MainScreenViewModel extends ViewModel {
    private static final String TAG = "mainScreenViewModel";
    private Services services;
    private Observable<Response> responseObservable;
    private Observer<Response> responseObserver;
    public MutableLiveData<List<Entry>> entryMutableLiveData = new MutableLiveData<>();


    public void getCategoriesData() {
        services = Client.getClient().create(Services.class);


        responseObservable = services.getResponse()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        responseObserver = new Observer<Response>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Response response) {
                entryMutableLiveData.setValue(response.getFeed().getEntry());

            }


            @Override
            public void onError(Throwable e) {
                Log.d("TAG", "viewModel: " + e.getMessage());

            }

            @Override
            public void onComplete() {

            }
        };
        responseObservable.subscribe(responseObserver);


    }


}







