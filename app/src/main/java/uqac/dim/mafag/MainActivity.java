package uqac.dim.mafag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageInfo imageInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageInfo = new ImageInfo();

        setEditTextValues(imageInfo);

        Button choiceButton = findViewById(R.id.choiceButton);

        choiceButton.setOnClickListener(v ->{
            Intent intent = new Intent(MainActivity.this, ImageListActivity.class);
            startActivity(intent);

        });

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        // Retrieve the image ID if it exists
        if (intent.hasExtra("chosenImageInfo")) {
            imageInfo = (ImageInfo) getIntent().getSerializableExtra("chosenImageInfo");
            // Now you can use imageId as needed, for example:
            setEditTextValues(imageInfo);
        }
    }

    private void setEditTextValues(ImageInfo imageInfo){
        TextView editText = findViewById(R.id.choiceTextArea);
        TextView editUrl = findViewById(R.id.choiceUrlArea);

        if(imageInfo == null || imageInfo.getChosen_text() == null ){
            editText.setText(R.string.google_name);
            editUrl.setText(R.string.google_url);
        }
        else{
            editText.setText(imageInfo.getChosen_text());
            editUrl.setText(imageInfo.getChosen_url());
        }

    }
}