package com.example.nathany.ioasysenterprises.activity;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.nathany.ioasysenterprises.R;

public class EnterpriseDetails extends AppCompatActivity {

    String name = "", description = "", location = "", photo = "", type = "";
    TextView txtName,txtType,txtCity,txtDescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        setContentView(R.layout.activity_enterprise_details);
        
        checkIntent();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
            default: return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void checkIntent() {

        //recupera informações
        name = getIntent().getStringExtra("name");
        description = getIntent().getStringExtra("description");
        location = getIntent().getStringExtra("location");
        type = getIntent().getStringExtra("type");
        photo = getIntent().getStringExtra("photo");

        //seta informações
        txtName = findViewById(R.id.details_enterprise_name);
        txtName.setText(name);

        txtType = findViewById(R.id.details_enterprise_type);
        txtType.setText(type);

        txtCity = findViewById(R.id.details_enterprise_location);
        txtCity.setText(location);

        txtDescription = findViewById(R.id.details_enterprise_description);
        txtDescription.setText(description);

        //recupera imagem
        ImageView imageView = findViewById(R.id.detalis_enterprise_photo);

        RequestOptions options = new RequestOptions();
        options.error(R.drawable.imagenotfound);

        Glide.with(this).setDefaultRequestOptions(options).load(photo)
                .apply(options).into(imageView);

        //altera fonte
        fonts();
    }
    public void fonts(){
        Typeface fontTitle = Typeface.createFromAsset(getAssets(),"Roboto-Bold.ttf");
        txtName.setTypeface(fontTitle);
        Typeface font = Typeface.createFromAsset(getAssets(),"Roboto-Regular.ttf");
        txtType.setTypeface(font);
        txtDescription.setTypeface(font);
        txtCity.setTypeface(font);
    }
}
