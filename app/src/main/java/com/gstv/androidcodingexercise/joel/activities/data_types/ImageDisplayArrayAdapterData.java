package com.gstv.androidcodingexercise.joel.activities.data_types;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import com.gstv.androidcodingexercise.joel.web_api.data_types.Photo;
import java.io.Serializable;
/**
 * Created by user on 8/16/15.
 */
public class ImageDisplayArrayAdapterData implements Parcelable {
    public Bitmap bitmap;
    public String imageName;
    public String fullSizeImageUrl;
    public String fullSizeImageHeight;
    public String fullSizeImageWidth;

    public ImageDisplayArrayAdapterData() {
        bitmap = null;
        imageName = "";
        fullSizeImageUrl = "";
        fullSizeImageHeight = "";
        fullSizeImageWidth = "";
    }

    public static final Parcelable.Creator<ImageDisplayArrayAdapterData> CREATOR =
            new Creator<ImageDisplayArrayAdapterData>() {
        public ImageDisplayArrayAdapterData createFromParcel(Parcel _source) {
            ImageDisplayArrayAdapterData data = new ImageDisplayArrayAdapterData();
            data.bitmap = (Bitmap)_source.readValue(Bitmap.class.getClassLoader());
            data.imageName = (String)_source.readString();
            data.fullSizeImageUrl = (String)_source.readString();
            data.fullSizeImageHeight = (String)_source.readString();
            data.fullSizeImageWidth = (String)_source.readString();
            return data;
        }

        public ImageDisplayArrayAdapterData[] newArray(int _size) {
            return new ImageDisplayArrayAdapterData[_size];
        }
    };

    @Override
    public void writeToParcel(Parcel _parcel, int _int) {
        _parcel.writeValue(bitmap);
        _parcel.writeString(imageName);
        _parcel.writeString(fullSizeImageUrl);
        _parcel.writeString(fullSizeImageHeight);
        _parcel.writeString(fullSizeImageWidth);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
