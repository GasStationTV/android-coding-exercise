package com.gstv.androidcodingexercise.joel.activities;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;
import android.app.ProgressDialog;

import com.gstv.androidcodingexercise.R;
import com.gstv.androidcodingexercise.joel.activities.data_types.ImageDisplayArrayAdapterData;
import com.gstv.androidcodingexercise.joel.web_api.Defines;
import com.gstv.androidcodingexercise.joel.web_api.find_by_username.*;
import com.gstv.androidcodingexercise.joel.web_api.DataManagerSingleton;

/**
 * Created by user on 8/12/15.
 */
public class Main extends Activity {
    private MainAsyncTask mainAsyncTask;
    private Button loginButton;
    private EditText username;
    private Context thisContextForAsyncTask;

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.joel_main_activity);
        setGlobalsForAsyncTask();
        setUpLoginButton();
    }

    @Override
    public void onResume() {
        super.onResume();
        mainAsyncTask = null;
    }

    @Override
    public void onPause() {
        super.onPause();
        if(mainAsyncTask != null) {
            mainAsyncTask.cancel(true);
        }
    }

    private void setGlobalsForAsyncTask() {
        username = (EditText)findViewById(R.id.username);
        thisContextForAsyncTask = this;
    }

    private void setUpLoginButton() {
        loginButton = (Button) findViewById(R.id.username_get_photos_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                if(mainAsyncTask == null) {
                    mainAsyncTask = new MainAsyncTask();
                    mainAsyncTask.execute();
                }
            }
        });
    }

    // generic types are Params, Progress, and Result
    private class MainAsyncTask extends AsyncTask<String, String, FindPersonResponseData> {
        private FindPersonByUsername findPersonByUsername;
        private String asyncUsername;
        private Context context;
        private ProgressDialog progressDialog;

        public MainAsyncTask() {
            progressDialog = new ProgressDialog(Main.this);
        }

        @Override
        public void onCancelled() {
            findPersonByUsername.stop();
        }

        @Override
        public void onPreExecute() {
            findPersonByUsername = null;
            asyncUsername = username.getText().toString();
            context = thisContextForAsyncTask;
            progressDialog.setMessage("Please Wait...");
            progressDialog.show();
        }

        @Override
        public FindPersonResponseData doInBackground(String[] params) {
            findPersonByUsername = new FindPersonByUsername();
            FindPersonResponseData responseData = findPersonByUsername.findPersonByUsername(asyncUsername);
            return responseData;
        }

        @Override
        public void onProgressUpdate(String[] progress) {
        }

        @Override
        public void onPostExecute(FindPersonResponseData _result) {
            if(progressDialog.isShowing()) {
                progressDialog.dismiss();
            }

            if(_result.errorData != null) {
                Toast.makeText(getApplicationContext(), _result.errorData.message, Toast.LENGTH_LONG).show();
            } else if(_result.jsonData != null) {
                processAsyncSuccess(_result);
            } else {
                Toast.makeText(getApplicationContext(), "Unspecified error", Toast.LENGTH_LONG).show();
            }
        }

        private void processAsyncSuccess(FindPersonResponseData _result) {
            DataManagerSingleton.setUsername(_result.jsonData.user.username._content);
            DataManagerSingleton.setNsid(_result.jsonData.user.nsid);
            //Toast.makeText(getApplicationContext(), _result.jsonData.stat, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(context, ImageListDisplay.class);
            startActivity(intent);
        }
    }
}
