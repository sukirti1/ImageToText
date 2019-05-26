package com.example.sukarora.imagetotext;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class viewText extends AppCompatActivity {

    private TextView textview1;
    private TextView textview2;
    private String newString;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_text);

        textview1= findViewById(R.id.textView);
        textview2= findViewById(R.id.textView2);

        Bundle extras= getIntent().getExtras();
        newString= extras.getString("result");

        //newString= newString.replace("/", " \n ");

        String substring1 = newString.substring(newString.indexOf("Reporting:")+11, newString.indexOf("Reporting:")+22);
        String substring2 = newString.substring(newString.indexOf("AFSB")+27,newString.indexOf("AFSB")+44);
        String newSubstring = substring1.concat(" :: "+substring2);



        textview1.setText(newSubstring);
        //textview2.setText(substring2);


    }
}
