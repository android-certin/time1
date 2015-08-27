package com.ciandt.worldwonders.activity;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.ciandt.worldwonders.R;
import com.ciandt.worldwonders.fragments.DetailFragment;
import com.ciandt.worldwonders.model.Bookmark;
import com.ciandt.worldwonders.model.Wonder;
import com.ciandt.worldwonders.repository.BookmarkRepository;

public class WonderDetailActivity extends AppCompatActivity {

    private Wonder wonder;
    private MenuItem bookmarkItem;

    private static final String WONDER_EXTRA = "wonder";

    private void changeBookmarkIcon(Boolean isChecked) {

        if (isChecked) {

            bookmarkItem.setChecked(true);
            bookmarkItem.setIcon(R.drawable.ic_bookmark_white_24dp);

        } else {

            bookmarkItem.setChecked(false);
            bookmarkItem.setIcon(R.drawable.ic_bookmark_border_white_24dp);
        }
    }

    private void checkBookmarkItem() {

        BookmarkRepository bookmarkRepository = new BookmarkRepository(this);
        bookmarkRepository.isBookmarked(wonder.getId(), new BookmarkRepository.CheckedbookmarkListener() {

            @Override
            public void onCheckedbookmark(Exception e, Boolean isChecked) {
                changeBookmarkIcon(isChecked);
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wonder_detail);
        
        Bundle bundle = new Bundle(1);
        bundle.putSerializable("wonder", (Wonder) getIntent().getSerializableExtra("wonder"));

        DetailFragment detailFragment = new DetailFragment();
        detailFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_detail, detailFragment, "itemDetail")
                .commit();


    }

    private void setShareItemVisibility (MenuItem directions) {

        try{

            ApplicationInfo info = getPackageManager().getApplicationInfo("com.google.android.apps.maps", 0);
            directions.setVisible(true);

        } catch(PackageManager.NameNotFoundException e) {
            directions.setVisible(false);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_wonder_detail, menu);

        bookmarkItem = menu.findItem(R.id.bookmark);
        setShareItemVisibility(menu.findItem(R.id.directions));
        checkBookmarkItem();

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

                changeBookmarkIcon(!item.isChecked());
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
