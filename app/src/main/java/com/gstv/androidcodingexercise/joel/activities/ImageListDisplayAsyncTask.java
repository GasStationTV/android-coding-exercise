package com.gstv.androidcodingexercise.joel.activities;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import com.bumptech.glide.Glide;
import com.gstv.androidcodingexercise.joel.activities.data_types.ImageDisplayArrayAdapterData;
import com.gstv.androidcodingexercise.joel.web_api.DataManagerSingleton;
import com.gstv.androidcodingexercise.joel.web_api.Defines;
import com.gstv.androidcodingexercise.joel.web_api.get_user_photo_list.GetUserPhotoList;
import com.gstv.androidcodingexercise.joel.web_api.get_user_photo_list.GetUserPhotoListResponseData;
import com.gstv.androidcodingexercise.joel.web_api.data_types.Photo;
import com.gstv.androidcodingexercise.joel.web_api.search_photos_by_keywords.SearchPhotosByKeywords;
import com.gstv.androidcodingexercise.joel.web_api.search_photos_by_keywords.SearchPhotosByKeywordsResponseData;

/**
 * Created by user on 8/16/15.
 */

// generic types are Params, Progress, and Result
public class ImageListDisplayAsyncTask extends AsyncTask<String, String, Void> {
    private Context asyncContext;
    private Activity activity;
    private Handler asyncHandler;
    private GetUserPhotoList getUserPhotoList;
    private SearchPhotosByKeywords searchPhotosByKeywords;

    public ImageListDisplayAsyncTask(Context _context, Handler _handler) {
        asyncContext = _context;
        asyncHandler = _handler;
        getUserPhotoList = null;
        searchPhotosByKeywords = null;
    }

    @Override
    public void onCancelled() {
        if(getUserPhotoList != null) {
            getUserPhotoList.stop();
        }
        if(searchPhotosByKeywords != null) {
            searchPhotosByKeywords.stop();
        }
    }

    @Override
    public void onPreExecute() {
        super.onPreExecute();
        tellHandlerToStartProgressDialog();
    }

    @Override
    public Void doInBackground(String[] params) {
        Photo[] photos;
        if(params == null || params.length < 1 || params[0].compareTo("") == 0) {
            photos = getListOfUserPhotos();
        } else {
            photos = getListOfUserPhotosFromKeywords(Defines.apiKey, Defines.nsid, params[0]);
        }

        if(photos == null) {
            System.out.println("error loading photos in AsyncTask");
            tellHandlerToStopProgressDialog();
        } else if(photos.length < 1) {
            tellHandlerToSetResultsTextView("No results available");
            tellHandlerToStopProgressDialog();
        } else  {
            doInBackgroundMainLoop(photos);
        }

        return null;
    }

    private Photo[] getListOfUserPhotos() {
        Photo[] ret = null;

        getUserPhotoList = new GetUserPhotoList();
        GetUserPhotoListResponseData getUserPhotoListResponseData =
                getUserPhotoList.getUserPhotoList(DataManagerSingleton.getNsid(), 1);
        if(getUserPhotoListResponseData.dataError != null) {
        } else if(getUserPhotoListResponseData.dataSuccess != null) {
            ret = getUserPhotoListResponseData.dataSuccess.photos.photo;
        }

        return ret;
    }

    private Photo[] getListOfUserPhotosFromKeywords(String _apiKey, String _nsid, String _keywords) {
        Photo[] ret = null;

        searchPhotosByKeywords = new SearchPhotosByKeywords();
        SearchPhotosByKeywordsResponseData searchPhotosByKeywordsResponseData =
                searchPhotosByKeywords.getUserPhotoList(_apiKey, _nsid, _keywords);
        if(searchPhotosByKeywordsResponseData.dataError != null) {
        } else if(searchPhotosByKeywordsResponseData.dataSuccess != null) {
            ret = searchPhotosByKeywordsResponseData.dataSuccess.photos.photo;
        }

        return ret;
    }

    private void doInBackgroundMainLoop(Photo[] _photos) {
        for(int i = 0; i < _photos.length; i++) {
            if(isCancelled()) {
                break;
            } else {
                Bitmap bitmapToLoad = loadBitmapFromUrl(_photos[i]);
                if(bitmapToLoad == null) {
                    System.out.println("error with bitmapToLoad");
                } else {
                    sendBitmapToHandler(prepareDataForHandler(bitmapToLoad, _photos[i]));
                    tellHandlerToStopProgressDialog();
                }
            }
        }
    }

    private Bitmap loadBitmapFromUrl(Photo _photo) {
        Bitmap ret = null;

        try {
            ret = Glide
                    .with(asyncContext)
                    .load(_photo.url_t)
                    .asBitmap()
                    .into(Integer.valueOf(_photo.width_t), Integer.valueOf(_photo.height_t))
                    .get();
        } catch (java.lang.InterruptedException _e) {
        } catch (java.util.concurrent.ExecutionException _e) {
        }

        return ret;
    }

    private ImageDisplayArrayAdapterData prepareDataForHandler(Bitmap _bitmap, Photo _photo) {
        ImageDisplayArrayAdapterData ret = new ImageDisplayArrayAdapterData();
        ret.bitmap = _bitmap;
        ret.imageName = _photo.title;
        ret.fullSizeImageUrl = _photo.url_o;
        ret.fullSizeImageHeight = _photo.height_o;
        ret.fullSizeImageWidth = _photo.width_o;
        return ret;
    }

    private void sendBitmapToHandler(ImageDisplayArrayAdapterData _data) {
        Message message = asyncHandler.obtainMessage();
        message.arg1 = Defines.imageListDisplayAddDataToAdapter;
        message.obj = _data;
        asyncHandler.sendMessage(message);
    }

    private void tellHandlerToSetResultsTextView(String _results) {
        Message message = asyncHandler.obtainMessage();
        message.arg1 = Defines.imageListDisplaySetResults;
        message.obj = (Object)_results;
        asyncHandler.sendMessage(message);
    }

    private void tellHandlerToStartProgressDialog() {
        Message message = asyncHandler.obtainMessage();
        message.arg1 = Defines.activityStartProgressDialog;
        asyncHandler.sendMessage(message);
    }

    private void tellHandlerToStopProgressDialog() {
        Message message = asyncHandler.obtainMessage();
        message.arg1 = Defines.activityStopProgressDialog;
        asyncHandler.sendMessage(message);
    }

    @Override
    public void onProgressUpdate(String[] progress) {
    }

    @Override
    public void onPostExecute(Void _v) {
    }
}
