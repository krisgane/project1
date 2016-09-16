package rentbazaar.wyby;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBar; //or the v4, haven't tried with that though
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setBackgroundDrawable(new ColorDrawable(
                    getResources().getColor(R.color.borderColor)));
            actionBar.setIcon(R.drawable.ic_launcher);
            actionBar.setHomeButtonEnabled(true);
        }

        Context context = getBaseContext();

        Spinner spinner = (Spinner) findViewById(R.id.spinnerDistance);
        spinner.setOnItemSelectedListener(this);
        List<String> distances = Arrays.asList(
                   getResources().getString(R.string.less_than_quarter),
                   getResources().getString(R.string.less_than_half),
                   getResources().getString(R.string.less_than_one),
                   getResources().getString(R.string.less_than_two),
                   getResources().getString(R.string.less_than_five));
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, distances);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        RelativeLayout tableLayout = (RelativeLayout) findViewById(R.id.table_layout);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        MiniListingDataProvider provider = new MiniListingDataProvider();
        ArrayList<MiniListingData> miniListings = provider.getData();

        // (TODO): Remove some buffer for safety.
        int screenWidth = metrics.widthPixels - 30;
        int scaledWidth = screenWidth / 2;
        BitmapFactory.Options bmpOptions = new BitmapFactory.Options();
        bmpOptions.inTargetDensity = DisplayMetrics.DENSITY_DEFAULT;
        int i = 1;
        for (MiniListingData miniListingData : miniListings) {
            Bitmap bmp = BitmapFactory.decodeResource(
                    getResources(), miniListingData.getDrawableId(), bmpOptions);
            // (TODO): Hack to calculate height scaled according to width.
            int scaledHeight = bmp.getHeight() * 50 * scaledWidth / (bmp.getWidth() * 100);

            MiniListingViewBuilder builder = new MiniListingViewBuilder()
                    .withMiniListing(miniListingData)
                    .withWidth(scaledWidth)
                    .withHeight(scaledHeight);
            if (i > 1) {
                // (TODO) : HACK - All non first/second images are below.
                if (i > 2) {
                    builder = builder.setBelow();
                }
                // (TODO) : HACK - All even images are on the right.
                if (i % 2 == 0) {
                    builder = builder.setRight();
                }
            }
            tableLayout.addView(builder.build(context, i));
            i++;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_actions, menu);
        return true;
    }
}
