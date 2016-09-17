package rentbazaar.wyby;

import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setBackgroundDrawable(new ColorDrawable(
                    getResources().getColor(R.color.borderColor)));
            actionBar.setIcon(R.drawable.ic_launcher);
            actionBar.setHomeButtonEnabled(true);
        }
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.table_layout);
        RelativeLayout layout1 = buildImage(1, R.drawable.camera_icon, "Electronics");
        layout1.setLayoutParams(getParams(null, null));
        layout.addView(layout1);
        RelativeLayout layout2 = buildImage(2, R.drawable.gown, "Clothes & Accessories");
        layout2.setLayoutParams(getParams(layout1, null));
        layout.addView(layout2);
        RelativeLayout layout3 = buildImage(3, R.drawable.babies, "Baby Stuff");
        layout3.setLayoutParams(getParams(null, layout1));
        layout.addView(layout3);
        RelativeLayout layout4 = buildImage(4, R.drawable.camping, "Outdoor");
        layout4.setLayoutParams(getParams(layout3, layout2));
        layout.addView(layout4);
        RelativeLayout layout5 = buildImage(5, R.drawable.sports, "Sports");
        layout5.setLayoutParams(getParams(null, layout3));
        layout.addView(layout5);
        RelativeLayout layout6 = buildImage(6, R.drawable.cds, "Media");
        layout6.setLayoutParams(getParams(layout5, layout4));
        layout.addView(layout6);
        RelativeLayout layout7 = buildImage(7, R.drawable.chair, "Household");
        layout7.setLayoutParams(getParams(null, layout5));
        layout.addView(layout7);
        RelativeLayout layout8 = buildImage(8, R.drawable.household, "Miscellaneous");
        layout8.setLayoutParams(getParams(layout7, layout6));
        layout.addView(layout8);
    }

    private RelativeLayout buildImage(int index, int drawableId, String text) {
        int id = index + 100000;
        int did = index + 200000;
        int rid = index + 300000;
        RelativeLayout relativeLayout = new RelativeLayout(getBaseContext());

        ImageView imageView = new ImageView(getBaseContext());
        RelativeLayout.LayoutParams imageLayoutParams = new RelativeLayout.LayoutParams(500, 300);
        imageView.setLayoutParams(imageLayoutParams);
        imageView.setId(id);
        imageView.setImageResource(drawableId);
        imageView.setPadding(0, 50, 0, 50);
        relativeLayout.addView(imageView);

        TextView shortDescriptionView = new TextView(getBaseContext());
        RelativeLayout.LayoutParams descriptionLayoutParams =
                new RelativeLayout.LayoutParams(600, 50);
        descriptionLayoutParams.addRule(RelativeLayout.ALIGN_BOTTOM, id);
        shortDescriptionView.setLayoutParams(descriptionLayoutParams);
        shortDescriptionView.setId(did);
        shortDescriptionView.setText(text);
        shortDescriptionView.setTextColor(getResources().getColor(R.color.colorBlack));
        shortDescriptionView.setGravity(Gravity.CENTER);
        relativeLayout.addView(shortDescriptionView);
        relativeLayout.setId(rid);
        relativeLayout.setBackgroundResource(R.drawable.border);
        return relativeLayout;
    }

    private RelativeLayout.LayoutParams getParams(RelativeLayout left, RelativeLayout top) {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(500, 350);
        if (left != null) {
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
            params.addRule(RelativeLayout.RIGHT_OF, left.getId());
        } else {
            params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
        }
        if (top != null) {
            params.addRule(RelativeLayout.BELOW, top.getId());
        } else {
            params.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
        }
        return params;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_actions, menu);
        return true;
    }
}
