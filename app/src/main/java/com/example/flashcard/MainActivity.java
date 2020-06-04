package com.example.flashcard;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity {

    Map<String,String> wordList  = new TreeMap<String,String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_flashcard/*, R.id.navigation_quiz*/)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    public void wordInputOnClick(View view) {
        TextInputEditText wordTextInput = (TextInputEditText) findViewById(R.id.word_input);
        TextInputEditText definitionTextInput = (TextInputEditText) findViewById(R.id.definition_input);
        TextView text = (TextView) findViewById(R.id.text);

        String wordText = wordTextInput.getText().toString();
        String definitionText = definitionTextInput.getText().toString();

        wordList.put(wordText, definitionText);

        String temp = " ";

        for (String key : wordList.keySet()) {
            temp += (key + ":  " + wordList.get(key) + "\r\n");
//            TextView item = new TextView(this);
//            item.setText(key + ":  " + wordList.get(key));
//            text.addView(item);
        }

        text.setText(temp);
    }


    public void getNextCardOnClick(View view) {
        TextView frontTextView = findViewById(R.id.front);
        TextView backTextView = findViewById(R.id.back);

        String word = "word";
        String definition = "definition";
        if (wordList.size() != 0){
            word = getRandomKeyFromWordList();
            definition = wordList.get(word);
        }


        frontTextView.setText(word);
        backTextView.setText(definition);
    }


    private String getRandomKeyFromWordList(){
        int size = wordList.size();

        int item = new Random().nextInt(size);
        int i = 0;
        for(String s : wordList.keySet())
        {
            if (i == item)
                return s;
            i++;
        }
        return null;
    }
}
