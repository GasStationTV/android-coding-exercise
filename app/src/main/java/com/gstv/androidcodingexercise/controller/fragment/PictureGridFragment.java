package com.gstv.androidcodingexercise.controller.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.Toast;
import com.googlecode.flickrjandroid.Flickr;
import com.googlecode.flickrjandroid.FlickrException;
import com.googlecode.flickrjandroid.photos.Photo;
import com.googlecode.flickrjandroid.photos.PhotoList;
import com.googlecode.flickrjandroid.photos.PhotosInterface;
import com.googlecode.flickrjandroid.photos.SearchParameters;
import com.gstv.androidcodingexercise.App;
import com.gstv.androidcodingexercise.Constants;
import com.gstv.androidcodingexercise.R;
import com.gstv.androidcodingexercise.controller.adapter.FlickrPictureAdapter;
import com.gstv.androidcodingexercise.util.AsyncTaskListener;
import com.gstv.androidcodingexercise.util.FlickrPhotoUtil;
import com.gstv.androidcodingexercise.util.FlickrPhotoUtil.FlickrPhotoSize;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * AndroidCodingExercise
 * Created by Michael Girard on 3/25/15.
 * Copyright 2015 GasStationTV inc. All rights reserved.
 */
public class PictureGridFragment extends Fragment {
    private static final String TAG = Constants.BASE_TAG + PictureGridFragment.class.getSimpleName();

    private GridView gridView;

    @Override public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setHasOptionsMenu(true);
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstance) {
        //TODO: implement method
        View view = inflater.inflate(R.layout.fragment_picture_grid, parent, false);
        gridView = (GridView)view.findViewById(R.id.grdFlickrPictures);
        return view;
    }
    @Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.menu_main, menu);
    }
    @Override public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
