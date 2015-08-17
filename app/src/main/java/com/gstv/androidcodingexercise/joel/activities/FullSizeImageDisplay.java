package com.gstv.androidcodingexercise.joel.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.gstv.androidcodingexercise.R;
import com.gstv.androidcodingexercise.joel.activities.data_types.ImageDisplayArrayAdapterData;

/**
 * Created by user on 8/16/15.
 */
public class FullSizeImageDisplay extends Activity {
    private ImageView imageView;
    private ImageDisplayArrayAdapterData imageData;
    private GetFullSizeUserPhotoAsyncTask getFullSizeUserPhotoAsyncTask;

    @Override
    protected void onCreate(Bundle _bundle) {
        super.onCreate(_bundle);
        setContentView(R.layout.activity_full_size_image_display);
        imageView = (ImageView) findViewById(R.id.full_size_image_view);
        imageData = (ImageDisplayArrayAdapterData)getIntent()
                .getParcelableExtra("ImageDisplayArrayAdapterData");
    }

    @Override
    public void onResume() {
        super.onResume();
        getFullSizeUserPhotoAsyncTask = new GetFullSizeUserPhotoAsyncTask();
        getFullSizeUserPhotoAsyncTask.execute();
    }

    @Override
    public void onPause() {
        super.onPause();
        getFullSizeUserPhotoAsyncTask.cancel(true);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    private Handler loadFullSizeBitmapIntoImageView = new Handler() {
        @Override
        public void handleMessage(Message _message) {
            if(_message == null) {
            } else if(_message.obj == null) {
            } else if(_message.arg1 == 100) {
                imageView.setImageBitmap((Bitmap) _message.obj);
            }
        }
    };


    // generic types are Params, Progress, and Result
    private class GetFullSizeUserPhotoAsyncTask extends AsyncTask<String, String, Void> {
        private Context asyncContext;
        private Handler asyncHandler;
        private ImageDisplayArrayAdapterData asyncImageData;
        private ProgressDialog progressDialog;

        public GetFullSizeUserPhotoAsyncTask() {
            progressDialog = new ProgressDialog(FullSizeImageDisplay.this);
        }

        @Override
        public void onCancelled() {

        }

        @Override
        public void onPreExecute() {
            asyncContext = getApplicationContext();
            asyncHandler = loadFullSizeBitmapIntoImageView;
            asyncImageData = imageData;
            progressDialog.setMessage("Please Wait...");
            progressDialog.show();
        }

        @Override
        public Void doInBackground(String[] params) {
            int photoWidth = Integer.valueOf(asyncImageData.fullSizeImageWidth);
            int photoHeight = Integer.valueOf(asyncImageData.fullSizeImageHeight);
            try {
                Bitmap bitmap = Glide
                        .with(asyncContext)
                        .load(asyncImageData.fullSizeImageUrl)
                        .asBitmap()
                        .into(photoWidth, photoHeight)
                        .get();
                sendBitmapToHandler(bitmap);
            } catch (java.lang.InterruptedException _e) {
            } catch (java.util.concurrent.ExecutionException _e) {
            }

            return null;
        }

        private void sendBitmapToHandler(Bitmap _bitmap) {
            Message message = asyncHandler.obtainMessage();
            message.arg1 = 100;
            message.obj = _bitmap;
            asyncHandler.sendMessage(message);
        }

        @Override
        public void onProgressUpdate(String[] progress) {
        }

        @Override
        public void onPostExecute(Void _v) {
            if(progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
    }
}
