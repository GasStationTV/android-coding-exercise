package com.gstv.androidcodingexercise.joel.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.gstv.androidcodingexercise.R;
import com.gstv.androidcodingexercise.joel.web_api.Defines;
import android.os.Handler;
import android.os.Message;
import android.widget.ListView;
import android.widget.TextView;
import com.gstv.androidcodingexercise.joel.activities.data_types.ImageDisplayArrayAdapterData;
import java.util.ArrayList;

public class ImageListDisplay extends Activity {
    private ListView listView;
    private ImageDisplayArrayAdapter arrayAdapter;
    private ArrayList<ImageDisplayArrayAdapterData> bitmapData;
    private ImageListDisplayAsyncTask imageListDisplayAsyncTask;
    private EditText searchText;
    private Button searchButton;
    private TextView notificationTextView;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.joel_activity_image_display);
        notificationTextView = (TextView)findViewById(R.id.text_view_for_thumbnails_results);
        progressDialog = new ProgressDialog(ImageListDisplay.this);
        setUpListViewAndAdapter();
        setUpSearchValues();
    }

    @Override
    public void onResume() {
        super.onResume();
        setUpNewAsyncTask();
    }

    @Override
    public void onPause() {
        super.onPause();
        imageListDisplayAsyncTask.cancel(true);
        arrayAdapter.clear();
        notificationTextView.setText("");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    private void setUpListViewAndAdapter() {
        listView = (ListView) findViewById(R.id.list_view_for_thumbnails);
        bitmapData = new ArrayList();
        arrayAdapter = new ImageDisplayArrayAdapter(this, bitmapData);
        listView.setAdapter(arrayAdapter);
    }

    private void setUpSearchValues() {
        searchText = (EditText)findViewById(R.id.image_list_search_edittext);
        searchButton = (Button)findViewById(R.id.image_list_search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayAdapter.clear();
                notificationTextView.setText("");
                setUpNewAsyncTask();
            }
        });
    }

    private void setUpNewAsyncTask() {
        if(imageListDisplayAsyncTask != null) {
            imageListDisplayAsyncTask.cancel(true);
        }
        imageListDisplayAsyncTask = new ImageListDisplayAsyncTask(
                getApplicationContext(), listViewHandler);
        imageListDisplayAsyncTask.execute(searchText.getText().toString());
    }

    private Handler listViewHandler = new Handler() {
        @Override
        public void handleMessage(Message _message) {
            if(_message == null) {
            } else if(_message.arg1 == Defines.activityStartProgressDialog) {
                progressDialog.setMessage("Please Wait...");
                progressDialog.show();
            } else if(_message.arg1 == Defines.activityStopProgressDialog) {
                if(progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            } else if(_message.obj == null) {
            } else if(_message.arg1 == Defines.imageListDisplayAddDataToAdapter) {
                arrayAdapter.add((ImageDisplayArrayAdapterData)_message.obj);
            } else if(_message.arg1 == Defines.imageListDisplaySetResults) {
                notificationTextView.setText((String)_message.obj);
            }
        }
    };
}
