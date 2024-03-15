package uqac.dim.mafag;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.util.Log;
import android.os.Build;


public class MainActivity extends AppCompatActivity {


    private ImageInfo imageInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CharSequence name = getString(R.string.channel_name);
        String description = getString(R.string.channel_description);
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel("CHANNEL_ID", name, importance);
        channel.setDescription(description);
        NotificationManager simpleNotification = getSystemService(NotificationManager.class);
        simpleNotification.createNotificationChannel(channel);

        imageInfo = new ImageInfo();

        setEditTextValues(imageInfo);

        Button choiceButton = findViewById(R.id.choiceButton);

        choiceButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ImageListActivity.class);
            startActivity(intent);

        });
        handleIntent(getIntent());

        Button openButton = findViewById(R.id.openButton);

        if (openButton != null) {
            openButton.setOnClickListener((v -> {
                String url = "https://" + imageInfo.getChosen_url();

                Intent urlIntent = new Intent(Intent.ACTION_VIEW);
                urlIntent.setData(Uri.parse(url));

                if (urlIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(urlIntent);
                }
                startActivity(urlIntent);
            }));
        }

        Button notificationButton = findViewById(R.id.notificationButton);

        if (notificationButton != null) {
            notificationButton.setOnClickListener(v -> {
                String url = "https://" + imageInfo.getChosen_url();

                Intent urlIntent = new Intent(Intent.ACTION_VIEW);
                urlIntent.setData(Uri.parse(url));

                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, urlIntent, PendingIntent.FLAG_IMMUTABLE);

                NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "CHANNEL_ID")
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentTitle("Open URL")
                        .setContentText("Tap to open the URL.")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true);


                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
                if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {

                    return;
                }
                notificationManager.notify(1, builder.build());
            });
        }

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        // Retrieve the image ID if it exists
        if (intent.hasExtra("chosenImageInfo")) {
            imageInfo = (ImageInfo) intent.getSerializableExtra("chosenImageInfo");
            // Now you can use imageId as needed, for example:
            Log.i("chosen name",imageInfo.getChosen_text());
            Log.i("chosen url",imageInfo.getChosen_url());

            setEditTextValues(imageInfo);
        }
    }
    private void handleIntent(Intent intent) {
        if (intent != null && intent.hasExtra("chosenImageInfo")) {
            imageInfo = (ImageInfo) intent.getSerializableExtra("chosenImageInfo");
            Log.i("chosen name", imageInfo.getChosen_text());
            Log.i("chosen url", imageInfo.getChosen_url());
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