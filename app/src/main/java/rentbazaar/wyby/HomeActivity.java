package rentbazaar.wyby;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Context context = getBaseContext();
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
            int scaledHeight = bmp.getHeight() * scaledWidth / bmp.getWidth();

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
}
