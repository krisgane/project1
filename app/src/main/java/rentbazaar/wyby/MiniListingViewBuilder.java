package rentbazaar.wyby;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup;
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
    private static final Integer RENT_IMG_ID_OFFSET = 20000;
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
        RelativeLayout.LayoutParams imageLayoutParams =
                new RelativeLayout.LayoutParams(500, 400);
        imageLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        imageView.setLayoutParams(imageLayoutParams);
        imageView.setId(imageViewId);
        imageView.setImageResource(drawableId);
        imageView.setPadding(0,0,0,50);
        relativeLayout.addView(imageView);

        int rentImageViewId = RENT_IMG_ID_OFFSET + resourceId;
        ImageView rentImageView = new ImageView(parentContext);
        rentImageView.setImageResource(R.drawable.r023);
        RelativeLayout.LayoutParams rentLayoutParams =
                new RelativeLayout.LayoutParams(100, 100);
        rentLayoutParams.addRule(RelativeLayout.ALIGN_RIGHT, imageViewId);
        rentLayoutParams.addRule(RelativeLayout.ALIGN_TOP, imageViewId);
        rentImageView.setLayoutParams(rentLayoutParams);
        rentImageView.setId(rentImageViewId);
        relativeLayout.addView(rentImageView);

        // TODO : Fixed all textbox heights to 50px for now.
        TextView rentView = new TextView(parentContext);
        RelativeLayout.LayoutParams rentTextLayoutParams =
                new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
        rentTextLayoutParams.addRule(RelativeLayout.ALIGN_RIGHT, imageViewId);
        rentTextLayoutParams.addRule(RelativeLayout.ALIGN_BOTTOM, rentImageViewId);
        rentView.setLayoutParams(rentTextLayoutParams);
        // TODO : Extend this for other countries.  Defaulting to UK for now.
        rentView.setText(
                String.format(res.getString(R.string.rent_message), rent, currencySymbol));
        rentView.setTextColor(res.getColor(R.color.colorWhite));
        rentView.setId(RENT_ID_OFFSET + resourceId);
        // R.color.holo_green_light
        relativeLayout.addView(rentView);

        // TODO : Add long description as well if needed.
        TextView shortDescriptionView = new TextView(parentContext);
        RelativeLayout.LayoutParams descriptionLayoutParams =
                new RelativeLayout.LayoutParams(405, 70);
        descriptionLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        descriptionLayoutParams.addRule(RelativeLayout.ALIGN_BOTTOM, imageViewId);
        descriptionLayoutParams.addRule(RelativeLayout.ALIGN_RIGHT, imageViewId);
        shortDescriptionView.setLayoutParams(descriptionLayoutParams);
        shortDescriptionView.setId(SD_ID_OFFSET + resourceId);
        shortDescriptionView.setText(shortDescription);
        shortDescriptionView.setTextColor(res.getColor(R.color.colorBlack));
        shortDescriptionView.setBackgroundResource(R.color.colorWhite);
        shortDescriptionView.setGravity(Gravity.CENTER);
        relativeLayout.addView(shortDescriptionView);
        relativeLayout.setPadding(0,0,0,30);
        return relativeLayout;
    }
}
