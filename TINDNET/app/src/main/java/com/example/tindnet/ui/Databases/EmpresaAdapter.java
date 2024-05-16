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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.request.target.CustomTarget;
import com.example.tindnet.R;

import java.util.ArrayList;
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

        RecyclerView recyclerView = convertView.findViewById(R.id.recycler_view_images);
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

        Log.d("EmpresaAdapter", "Empresa: " + empresa.getNombre());
        for (String uri : empresa.getImagenUris()) {
            Log.d("EmpresaAdapter", "Imagen URI: " + uri);
        }

        ImageRecyclerAdapter imageRecyclerAdapter = new ImageRecyclerAdapter(context, empresa.getImagenUris());
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(imageRecyclerAdapter);

        return convertView;
    }
}