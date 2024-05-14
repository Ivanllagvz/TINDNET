package com.example.tindnet.ui.Databases;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.request.target.CustomTarget;
import com.example.tindnet.R;
import java.util.List;

public class EmpresaAdapter extends BaseAdapter {

    private Context context;
    private List<Empresa> empresaList;

    public EmpresaAdapter(Context context, List<Empresa> empresaList) {
        this.context = context;
        this.empresaList = empresaList;
    }

    @Override
    public int getCount() {
        return empresaList.size();
    }

    @Override
    public Object getItem(int position) {
        return empresaList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_empresa, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.image_empresa);
        TextView nombreTextView = convertView.findViewById(R.id.nombre_empresa);
        TextView descripcionTextView = convertView.findViewById(R.id.descripcion_empresa);
        TextView webTextView = convertView.findViewById(R.id.web_empresa);
        TextView sectorTextView = convertView.findViewById(R.id.sector_empresa);
        TextView razonSocialTextView = convertView.findViewById(R.id.razon_social_empresa);
        TextView horariosTextView = convertView.findViewById(R.id.horarios_empresa);

        Empresa empresa = empresaList.get(position);

        nombreTextView.setText(empresa.getNombre());
        descripcionTextView.setText(empresa.getDescripcion());
        webTextView.setText(empresa.getWeb());
        sectorTextView.setText(empresa.getSector());
        razonSocialTextView.setText(empresa.getRazonSocial());
        horariosTextView.setText(empresa.getHorarios());

        Glide.with(context)
                .load(empresa.getImagenUri())
                .placeholder(R.drawable.logo)
                .error(R.drawable.logo_webstyles)
                .into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        imageView.setImageDrawable(resource);
                    }

                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
                        Log.e("Glide", "Failed to load image: " + empresa.getImagenUri());
                        super.onLoadFailed(errorDrawable);
                        imageView.setImageDrawable(errorDrawable);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                        imageView.setImageDrawable(placeholder);
                    }
                });

        return convertView;
    }
}
