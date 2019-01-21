package com.example.nathany.ioasysenterprises.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.nathany.ioasysenterprises.R;
import com.example.nathany.ioasysenterprises.activity.EnterpriseDetails;
import com.example.nathany.ioasysenterprises.model.EnterpriseCatalog;

public class EnterpriseAdapter extends RecyclerView.Adapter<EnterpriseAdapter.ViewHolder> {

    private EnterpriseCatalog enterprises;
    private Context context;


    public EnterpriseAdapter(EnterpriseCatalog enterprise, Context context) {
        this.enterprises = enterprise;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.enterprise_item, viewGroup, false);


        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.name.setText(enterprises.getEnterprises().get(i).getEnterprise_name());
        viewHolder.location.setText(enterprises.getEnterprises().get(i).getCity() + ", " + enterprises.getEnterprises().get(i).getCountry());
        viewHolder.type.setText(enterprises.getEnterprises().get(i).getEnterprise_type().toString());
        viewHolder.description = enterprises.getEnterprises().get(i).getDescription();

        viewHolder.BASE_URL = "http://empresas.ioasys.com.br" + enterprises.getEnterprises().get(i).getPhoto();

        RequestOptions options = new RequestOptions();
        options.error(R.drawable.imagenotfound);

        Glide.with(context).setDefaultRequestOptions(options).load(viewHolder.BASE_URL).apply(options).into(viewHolder.photo);


    }

    @Override
    public int getItemCount() {
        return enterprises.getEnterprises().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView name, type, location;
        ImageView photo;
        String description = "", BASE_URL = "";

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            photo = itemView.findViewById(R.id.enterprise_photo);
            name = itemView.findViewById(R.id.txt_enterprise_name);
            type = itemView.findViewById(R.id.txt_enterprise_type);
            location = itemView.findViewById(R.id.txt_enterprise_location);

            fonts(itemView);
        }

        @Override
        public void onClick(View view) {

            Intent intent =  new Intent(view.getContext(), EnterpriseDetails.class);
            intent.putExtra("name",name.getText());
            intent.putExtra("description", description);
            intent.putExtra("location", location.getText());
            intent.putExtra("type",type.getText());
            intent.putExtra("photo", BASE_URL);
            view.getContext().startActivity(intent);
        }
        public void fonts(View itemView){
            Typeface fontTitle = Typeface.createFromAsset(itemView.getContext().getAssets(),"Roboto-Bold.ttf");
            name.setTypeface(fontTitle);
            Typeface font = Typeface.createFromAsset(itemView.getContext().getAssets(),"Roboto-Regular.ttf");
            type.setTypeface(font);
            location.setTypeface(font);
        }
    }

}
