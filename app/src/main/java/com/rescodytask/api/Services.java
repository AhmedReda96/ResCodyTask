package com.rescodytask.api;



import com.rescodytask.pojo.Response;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Services {


    @GET("public/basic?alt=json")
    Observable<Response> getResponse();



}
