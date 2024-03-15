package uqac.dim.mafag;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ImageListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.images_layout);

        setupImageClickListener(R.id.microsoft_image);
        setupImageClickListener(R.id.apple_image);
        setupImageClickListener(R.id.amazon_image);
        setupImageClickListener(R.id.facebook_image);
        setupImageClickListener(R.id.google_image);
    }

    private void setupImageClickListener(int imageViewId) {
        ImageView imageView = findViewById(imageViewId);

        imageView.setOnClickListener(v -> {
            ImageInfo chosenImage = new ImageInfo();

            if (imageViewId == R.id.microsoft_image) {
                chosenImage.setChosen_text(getString(R.string.microsoft_name));
                chosenImage.setChosen_url(getString(R.string.microsoft_url));
            }
            else if (imageViewId == R.id.apple_image) {
                chosenImage.setChosen_text(getString(R.string.apple_name));
                chosenImage.setChosen_url(getString(R.string.apple_url));
            }
            else if (imageViewId == R.id.amazon_image) {
                chosenImage.setChosen_text(getString(R.string.amazon_name));
                chosenImage.setChosen_url(getString(R.string.amazon_url));
            }
            else if (imageViewId == R.id.facebook_image) {
                chosenImage.setChosen_text(getString(R.string.facebook_name));
                chosenImage.setChosen_url(getString(R.string.facebook_url));
            }
            else{
                chosenImage.setChosen_text(getString(R.string.google_name));
                chosenImage.setChosen_url(getString(R.string.google_url));
            }
            Log.i("chosen name in ImageList", chosenImage.getChosen_text());
            Log.i("chosen url in ImageList", chosenImage.getChosen_url());

            Intent intent = new Intent(ImageListActivity.this, MainActivity.class);
            intent.putExtra("chosenImageInfo", chosenImage);
            startActivity(intent);
        });

    }
}