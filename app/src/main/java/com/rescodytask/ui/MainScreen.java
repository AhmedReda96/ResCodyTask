package com.rescodytask.ui;

import androidx.annotation.DrawableRes;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.rescodytask.R;
import com.rescodytask.databinding.ActivityMainScreenBinding;
import com.rescodytask.helper.MessagesRVAdapter;
import com.rescodytask.pojo.Entry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Locale;

public class MainScreen extends FragmentActivity implements OnMapReadyCallback {
    private ActivityMainScreenBinding binding;
    private GoogleMap mMap;
    private MainScreenViewModel viewModel;
    private MessagesRVAdapter messagesRVAdapter;
    private LatLng damascusLatLng, mogadishuLatLng, ibizaLatLng, egyptLatLng, nairobiLatLng, kathmanduLatLng, spainLatLng, athensLatLng, istanbulLatLng, tahrirLatLng;
    private MarkerOptions damascusMarker, mogadishuMarker, ibizaMarker, egyptMarker, nairobiMarker, kathmanduMarker, spainMarker, athensMarker, istanbulMarker, tahrirMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_screen);
        binding.setLifecycleOwner(this);


        init();
    }

    private void getData() {
        viewModel.getCategoriesData();
        viewModel.entryMutableLiveData.observe(this, new Observer<List<Entry>>() {
            @Override
            public void onChanged(List<Entry> entryList) {

                Log.d("TAG", "onChanged: " + String.valueOf(entryList.get(1).getContent().get$t()));


                messagesRVAdapter = new MessagesRVAdapter(entryList, MainScreen.this);
                messagesRVAdapter.notifyDataSetChanged();
                binding.messagesRV.setAdapter(messagesRVAdapter);
                binding.messagesRV.setVisibility(View.VISIBLE);


            }
        });

    }

    private void init() {

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        viewModel = ViewModelProviders.of(this).get(MainScreenViewModel.class);

        binding.messagesRV.setLayoutManager(new GridLayoutManager(MainScreen.this, 1, GridLayoutManager.HORIZONTAL, false));
        binding.messagesRV.setItemAnimator(new DefaultItemAnimator());


        getData();

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        damascusLatLng = new LatLng(33.5074558, 36.212855);
        damascusMarker = new MarkerOptions();
        damascusMarker.position(damascusLatLng);
        damascusMarker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        mMap.addMarker(damascusMarker);

        mogadishuLatLng = new LatLng(2.0591993, 45.236624);
        mogadishuMarker = new MarkerOptions();
        mogadishuMarker.position(mogadishuLatLng);
        mogadishuMarker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mMap.addMarker(mogadishuMarker);


        ibizaLatLng = new LatLng(8.9742592, 1.2773027);
        ibizaMarker = new MarkerOptions();
        ibizaMarker.position(ibizaLatLng);
        ibizaMarker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        mMap.addMarker(ibizaMarker);

        egyptLatLng = new LatLng(26.834923, 26.3813621);
        egyptMarker = new MarkerOptions();
        egyptMarker.position(egyptLatLng);
        egyptMarker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        mMap.addMarker(egyptMarker);


        tahrirLatLng = new LatLng(30.0444145, 31.2335109);
        tahrirMarker = new MarkerOptions();
        tahrirMarker.position(tahrirLatLng);
        tahrirMarker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mMap.addMarker(tahrirMarker);

        nairobiLatLng = new LatLng(-1.3032051, 36.7073088);
        nairobiMarker = new MarkerOptions();
        nairobiMarker.position(nairobiLatLng);
        nairobiMarker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mMap.addMarker(nairobiMarker);


        kathmanduLatLng = new LatLng(27.7089559, 85.2911132);
        kathmanduMarker = new MarkerOptions();
        kathmanduMarker.position(kathmanduLatLng);
        kathmanduMarker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        mMap.addMarker(kathmanduMarker);


        spainLatLng = new LatLng(40.1216604, -8.2039178);
        spainMarker = new MarkerOptions();
        spainMarker.position(spainLatLng);
        spainMarker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        mMap.addMarker(spainMarker);


        athensLatLng = new LatLng(37.990832, 23.7033198);
        athensMarker = new MarkerOptions();
        athensMarker.position(athensLatLng);
        athensMarker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mMap.addMarker(athensMarker);


        istanbulLatLng = new LatLng(41.0049823, 28.7319914);
        istanbulMarker = new MarkerOptions();
        istanbulMarker.position(istanbulLatLng);
        istanbulMarker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        mMap.addMarker(istanbulMarker);





    }



    private BitmapDescriptor bitmapDescriptorFromVector(Context context, @DrawableRes int vectorDrawableResourceId) {
        Drawable background = ContextCompat.getDrawable(context, vectorDrawableResourceId);
        background.setBounds(0, 0, background.getIntrinsicWidth(), background.getIntrinsicHeight());
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorDrawableResourceId);
        vectorDrawable.setBounds(40, 20, vectorDrawable.getIntrinsicWidth() + 40, vectorDrawable.getIntrinsicHeight() + 20);
        Bitmap bitmap = Bitmap.createBitmap(background.getIntrinsicWidth(), background.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        background.draw(canvas);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

}

