package com.ciandt.worldwonders.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ciandt.worldwonders.R;
import com.ciandt.worldwonders.helpers.ImageHelper;
import com.ciandt.worldwonders.model.Bookmark;
import com.ciandt.worldwonders.model.Wonder;
import com.ciandt.worldwonders.repository.BookmarkRepository;

public class WonderDetailActivity extends AppCompatActivity {

    private Wonder wonder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wonder_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        wonder = (Wonder)getIntent().getSerializableExtra("wonder");
        if (wonder != null) {

            TextView textView = (TextView)findViewById(R.id.text_view);
            textView.setText(wonder.getDescription());

            CollapsingToolbarLayout ctb = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
            ctb.setTitle(wonder.getName());

            ImageView imageView = (ImageView) findViewById(R.id.image);
            ImageHelper.setImage(imageView, wonder.getPhoto().split("\\.")[0], this);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_wonder_detail, menu);
        return true;
    }

    private void shareAction () {

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, wonder.getName());
        sendIntent.setType("text/plain");
        startActivity(sendIntent);

    }

    private void directionsAction() {

        Uri gmmIntentUri = Uri.parse("google.streetview:cbll=" + wonder.getLatitude() +
                "," + wonder.getLongitude());
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    private void addBookmark(Bookmark bookmark) {

        BookmarkRepository repository = new BookmarkRepository(this);
        int id = repository.addBookmark(bookmark, new BookmarkRepository.BookmarkListener() {

            @Override
            public void onAddBookmark(Exception e, Long isPersist) {

                Toast.makeText(getApplicationContext(), "Gravado com sucesso.", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case  R.id.action_settings:
                return true;
            case R.id.bookmark:

                Bookmark bookmark = new Bookmark();
                bookmark.setIdWonder(wonder.getId());

                addBookmark(bookmark);
                break;

            case R.id.share:

                shareAction();
                break;

            case R.id.directions:
                directionsAction();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
