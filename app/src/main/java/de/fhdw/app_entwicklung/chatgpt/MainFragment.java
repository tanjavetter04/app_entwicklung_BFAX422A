package de.fhdw.app_entwicklung.chatgpt;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import de.fhdw.app_entwicklung.chatgpt.openai.ChatGpt;
import de.fhdw.app_entwicklung.chatgpt.speech.LaunchSpeechRecognition;

public class MainFragment extends Fragment {

    private final ActivityResultLauncher<LaunchSpeechRecognition.SpeechRecognitionArgs> getTextFromSpeech = registerForActivityResult(
            new LaunchSpeechRecognition(),
            query -> {
                MainActivity.backgroundExecutorService.execute(() -> {
                    ChatGpt chatGpt = new ChatGpt("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");
                    String answer = chatGpt.getChatCompletion(query);
                    getTextView().setText(answer);
                    Log.d("c", "setText");
                });
            });

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getAskButton().setOnClickListener(v ->
            getTextFromSpeech.launch(new LaunchSpeechRecognition.SpeechRecognitionArgs())
        );
    }

    private TextView getTextView() {
        //noinspection ConstantConditions
        return getView().findViewById(R.id.textView);
    }

    private Button getAskButton() {
        //noinspection ConstantConditions
        return getView().findViewById(R.id.button_ask);
    }

}