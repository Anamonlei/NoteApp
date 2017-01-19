package com.noteapp.noteapp;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class NoteDetailFragment extends Fragment {

    EditText titleText;
    EditText descriptiomText;

    public NoteDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note_detail, container, false);

        titleText = (EditText) view.findViewById(R.id.fragment_note_detail_title_text);
        descriptiomText = (EditText) view.findViewById(R.id.fragment_note_detail_description_text);

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        //save all from screen to disk;fragment to going to be destroyed
        saveAllDataToDisk();
    }

    @Override
    public void onResume() {
        super.onResume();
        //load data to show on screen (if any)
        loadAllDataFromDisk();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("NOTE_TITLE");
        editor.remove("NOTE_DESCRIPTION");
        editor.apply();
    }

    private void loadAllDataFromDisk() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String noteTitle = preferences.getString("NOTE_TITLE", "");
        String noteDescription = preferences.getString("NOTE_DESCRIPTION", "");

        titleText.setText(noteTitle);
        descriptiomText.setText(noteDescription);


    }

    private void saveAllDataToDisk() {
        //abro el fichero
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor editor = preferences.edit();

        //leo lo que has escrito en pantalla
        String title = titleText.getText().toString();
        String description = descriptiomText.getText().toString();

        //grabo eso en el fichero
        editor.putString("NOTE_TITLE", title);
        editor.putString("NOTE_DESCRIPTION", description);

        editor.apply();


    }

}
