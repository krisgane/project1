package rentbazaar.wyby;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Currency;
import java.util.Locale;

/**
 * Using RelativeLayout as it seems to work well with Image/Text overlay.
 * TODO : Explore other options.
 * Created by krisgane on 9/11/16.
 */
public class MiniListingViewBuilder {
    private static final Integer LAYOUT_ID_OFFSET = 1000;
    private static final Integer IMG_ID_OFFSET = 10000;
    private static final Integer RENT_ID_OFFSET = 100000;
    private static final Integer DISTANCE_ID_OFFSET = 200000;
    private static final Integer SD_ID_OFFSET = 300000;

    private Double rent;
    private Double distance;
    private String shortDescription;
    private String longDescription;
    private String currencySymbol;
    private Integer height;
    private Integer width;
    private Boolean isRight;
    private Boolean isBelow;
    private Integer drawableId;

    MiniListingViewBuilder() {
        this.rent = 0.0;
        this.distance = 0.0;
        this.shortDescription = "";
        this.longDescription = "";
        this.isRight = false;
        this.isBelow = false;
        this.drawableId = -1;
        // TODO : Extend this for other symbols if needed.  Defaulting to UK Sterling pound for now.
        this.currencySymbol = Currency.getInstance(Locale.UK).getSymbol();
    }

    public MiniListingViewBuilder withMiniListing(MiniListingData miniListingData) {
        this.rent = miniListingData.getRent();
        this.distance = miniListingData.getDistance();
        this.drawableId = miniListingData.getDrawableId();
        this.shortDescription = miniListingData.getShortDescription();
        return this;
    }

    public MiniListingViewBuilder withLongDescription(String longDescription) {
        this.longDescription = longDescription;
        return this;
    }

    public MiniListingViewBuilder withHeight(Integer height) {
        this.height = height;
        return this;
    }

    public MiniListingViewBuilder withWidth(Integer width) {
        this.width = width;
        return this;
    }

    public MiniListingViewBuilder setRight() {
        this.isRight = true;
        return this;
    }

    public MiniListingViewBuilder setBelow() {
        this.isBelow = true;
        return this;
    }

    public RelativeLayout build(Context parentContext, int resourceId) {
        RelativeLayout relativeLayout = new RelativeLayout(parentContext);
        relativeLayout.setId(LAYOUT_ID_OFFSET + resourceId);
        Resources res = parentContext.getResources();

        RelativeLayout.LayoutParams layoutParams =
                new RelativeLayout.LayoutParams(width, height);
        if (isRight) {
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
        } else {
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
        }
        if (isBelow) {
            layoutParams.addRule(RelativeLayout.BELOW, LAYOUT_ID_OFFSET + resourceId - 2);
        }
        relativeLayout.setLayoutParams(layoutParams);

        int imageViewId = IMG_ID_OFFSET + resourceId;
        ImageView imageView = new ImageView(parentContext);
        imageView.setLayoutParams(new RelativeLayout.LayoutParams(width, height - 50));
        imageView.setId(imageViewId);
        imageView.setImageResource(drawableId);
        relativeLayout.addView(imageView);

        // TODO : Fixed all textbox heights to 50px for now.
        TextView rentView = new TextView(parentContext);
        RelativeLayout.LayoutParams rentLayoutParams =
                new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, 50);
        rentLayoutParams.addRule(RelativeLayout.ALIGN_RIGHT, imageViewId);
        rentLayoutParams.addRule(RelativeLayout.ALIGN_TOP, imageViewId);
        rentView.setLayoutParams(rentLayoutParams);
        // TODO : Extend this for other countries.  Defaulting to UK for now.
        rentView.setText(
                String.format(res.getString(R.string.rent_message), rent, currencySymbol));
        rentView.setId(RENT_ID_OFFSET + resourceId);
        // R.color.holo_green_light
        rentView.setBackgroundResource(ColorSet.MINI_LISTING_RENT_BG_COLOR);
        relativeLayout.addView(rentView);

        TextView distanceView = new TextView(parentContext);
        RelativeLayout.LayoutParams distanceLayoutParams =
                new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, 50);
        distanceLayoutParams.addRule(RelativeLayout.ALIGN_LEFT, imageViewId);
        distanceLayoutParams.addRule(RelativeLayout.ALIGN_TOP, imageViewId);
        distanceView.setLayoutParams(distanceLayoutParams);
        distanceView.setText(
                String.format(res.getString(R.string.distance_message), distance));
        distanceView.setId(DISTANCE_ID_OFFSET + resourceId);
        // R.color.holo_red_light
        distanceView.setBackgroundResource(ColorSet.MINI_LISTING_DISTANCE_BG_COLOR);
        relativeLayout.addView(distanceView);

        // TODO : Add long description as well if needed.
        TextView shortDescriptionView = new TextView(parentContext);
        RelativeLayout.LayoutParams descriptionLayoutParams =
                new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, 50);
        descriptionLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        descriptionLayoutParams.addRule(RelativeLayout.BELOW, imageViewId);
        shortDescriptionView.setLayoutParams(descriptionLayoutParams);
        shortDescriptionView.setId(SD_ID_OFFSET + resourceId);
        shortDescriptionView.setText(shortDescription);
        shortDescriptionView.setTextColor(res.getColor(R.color.colorBlack));
        shortDescriptionView.setGravity(Gravity.CENTER);
        relativeLayout.addView(shortDescriptionView);
        return relativeLayout;
    }
}
