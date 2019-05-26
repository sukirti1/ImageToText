package com.example.sukarora.imagetotext;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.Text;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private Bitmap bitmap;
    private TextView txtView;
    private ImageView imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button= findViewById(R.id.btnProcess);
        imageview = findViewById(R.id.image_view);
        txtView= findViewById(R.id.txtView);

        bitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.screen);
        imageview.setImageBitmap(bitmap);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // To get bitmap from resource folder of the application.

                //txtView.setText("what!!!");

// Starting Text Recognizer
                TextRecognizer txtRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();
                if (!txtRecognizer.isOperational())
                {
                    // Shows if your Google Play services is not up to date or OCR is not supported for the device
                    txtView.setText("Detector dependencies are not yet available");
                }
                else
                {
                    // Set the bitmap taken to the frame to perform OCR Operations.
                    Frame frame = new Frame.Builder().setBitmap(bitmap).build();
                    SparseArray items = txtRecognizer.detect(frame);
                    StringBuilder strBuilder = new StringBuilder();
                    for (int i = 0; i < items.size(); i++)
                    {
                        TextBlock item = (TextBlock)items.valueAt(i);
                        strBuilder.append(item.getValue());
                        strBuilder.append("/");
                        // The following Process is used to show how to use lines & elements as well
                        /*for (int j= 0; j < items.size(); j++) {
                            TextBlock nitem = (TextBlock) items.valueAt(i);
                            strBuilder.append(nitem.getValue());
                            strBuilder.append("/");
                            for (Text line : nitem.getComponents()) {
                                //extract scanned text lines here
                                Log.v("lines", line.getValue());
                                for (Text element : line.getComponents()) {
                                    //extract scanned text words here
                                    Log.v("element", element.getValue());
                                }
                            }
                        }*/
                    }
                    //txtView.setText(strBuilder.toString());
                    Log.d("here",strBuilder.toString());
                    Intent intent=new Intent(MainActivity.this,viewText.class);
                    intent.putExtra("result", strBuilder.toString());
                    startActivity(intent);
                    //txtView.setText(strBuilder.toString().substring(0, strBuilder.toString().length() - 1));
                }
            }
        });
    }
}
