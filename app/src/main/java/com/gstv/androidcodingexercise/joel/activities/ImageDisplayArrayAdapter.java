package com.gstv.androidcodingexercise.joel.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.app.Activity;
import android.widget.ImageView;
import android.widget.TextView;

import com.gstv.androidcodingexercise.joel.activities.data_types.ImageDisplayArrayAdapterData;

import com.gstv.androidcodingexercise.R;

import java.util.ArrayList;

/**
 * Created by user on 8/16/15.
 */
public class ImageDisplayArrayAdapter extends ArrayAdapter<ImageDisplayArrayAdapterData> {
    private final Activity context;
    private final ArrayList<ImageDisplayArrayAdapterData> bitmapData;

    public ImageDisplayArrayAdapter(Activity _context, ArrayList<ImageDisplayArrayAdapterData> _bitmapData) {
        super(_context, R.layout.joel_thumbnail_list_item, _bitmapData);
        this.context = _context;
        this.bitmapData = _bitmapData;
    }

    @Override
    public View getView(int _position, View _view, ViewGroup _parent) {
        View rowView = _view;

        if(rowView != null) {
        } else {
            LayoutInflater layoutInflater = context.getLayoutInflater();
            rowView = layoutInflater.inflate(R.layout.joel_thumbnail_list_item, null);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.image_list_main_image);
            TextView textView = (TextView) rowView.findViewById(R.id.image_list_image_name);
            imageView.setImageBitmap(bitmapData.get(_position).bitmap);
            textView.setText(bitmapData.get(_position).imageName);
            rowView.setTag(_position);
            setUpRowViewOnClickListener(rowView, _position);
        }

        return rowView;
    }

    private void setUpRowViewOnClickListener(View _rowView, int _position) {
        _rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FullSizeImageDisplay.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("ImageDisplayArrayAdapterData", bitmapData.get((int) v.getTag()));
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public void add(ImageDisplayArrayAdapterData _data) {
        bitmapData.add(_data);
        notifyDataSetChanged();
    }

    @Override
    public void clear() {
        bitmapData.clear();
        notifyDataSetChanged();
    }
}
