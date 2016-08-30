package com.tecif.myfav;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class CardViewActivity extends Activity {

    TextView itemTittle;
    TextView itemDescription;
    RatingBar itemRank;
    ImageView itemPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_desc);
        itemTittle = (TextView) findViewById(R.id.item_tittle);
        itemDescription = (TextView) findViewById(R.id.item_description);
        itemRank = (RatingBar) findViewById(R.id.item_rating);
        itemPhoto = (ImageView) findViewById(R.id.item_photo);

    }
}
