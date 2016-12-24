package com.example.rojesharunkumar.sampleasynctasks;

public interface TaskListener {
    void onTaskStarted();
 
    void onTaskFinished(String result);
}