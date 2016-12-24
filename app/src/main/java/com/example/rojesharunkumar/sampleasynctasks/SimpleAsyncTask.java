package com.example.rojesharunkumar.sampleasynctasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class SimpleAsyncTask extends Fragment implements View.OnClickListener{

    private ProgressDialog mProgressbar;
    private boolean isTaskRunning = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Fragments have the ability to retain their instances by setting true
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_simple_async_task, container, false);
        Button button = (Button) view.findViewById(R.id.start);
        button.setOnClickListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // If we are returning here from a screen orientation
        // and the AsyncTask is still working, re-create and display the
        // progress dialog.
        if (isTaskRunning) {
            mProgressbar = ProgressDialog.show(getActivity(), "Loading", "Please wait a moment!");
        }
    }

    @Override
    public void onClick(View v) {
       AsyncDemo asyncDemo = new AsyncDemo(this);
        asyncDemo.execute();
    }

    @Override
    public void onDetach() {
        // All dialogs should be closed before leaving the activity in order to avoid
        // the: Activity has leaked window com.android.internal.policy... exception
        if (mProgressbar != null && mProgressbar.isShowing()) {
            mProgressbar.dismiss();
        }
        super.onDetach();
    }


    class AsyncDemo extends AsyncTask<String,Integer,Long>{ // Params, Progress, Result

        private Context context;

        public AsyncDemo(SimpleAsyncTask simpleAsyncTask) {
            this.context = simpleAsyncTask.getActivity();

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            isTaskRunning = true;
            mProgressbar = new ProgressDialog(context);
            mProgressbar.setMessage("Downloading....");
            mProgressbar.show();

        }

        @Override
        protected Long doInBackground(String... voids) {

            for(int i=0;i<20;i++){
                //
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);


        }

        @Override
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);
            mProgressbar.hide();
            isTaskRunning = false;
        }
    }
}
