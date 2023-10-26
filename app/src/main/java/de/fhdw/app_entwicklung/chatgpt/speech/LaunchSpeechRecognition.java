package de.fhdw.app_entwicklung.chatgpt.speech;

import android.content.Context;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.util.Log;

import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class LaunchSpeechRecognition extends ActivityResultContract<LaunchSpeechRecognition.SpeechRecognitionArgs, String> {

    @NonNull
    @Override
    public Intent createIntent(@NonNull Context context, SpeechRecognitionArgs speechRecognitionArgs) {
        return new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
    }

    @Override
    public String parseResult(int i, @Nullable Intent intent) {
        if (intent != null) {
            ArrayList<String> result = intent.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            Log.d("a", "received intent result");
            return result.get(0);
        }
        return null;
    }

    public static class SpeechRecognitionArgs
    {
        public SpeechRecognitionArgs()
        {
        }
    }
}