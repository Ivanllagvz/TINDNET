package com.example.tindnet.ui.Databases;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.tindnet.R;
import java.util.List;

public class EmpresaAdapter extends RecyclerView.Adapter<EmpresaAdapter.EmpresaViewHolder> {

    private Context context;
    private List<Empresa> empresaList;

    public EmpresaAdapter(Context context, List<Empresa> empresaList) {
        this.context = context;
        this.empresaList = empresaList;
    }

    @NonNull
    @Override
    public EmpresaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_empresa, parent, false);
        return new EmpresaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmpresaViewHolder holder, int position) {
        Empresa empresa = empresaList.get(position);
        holder.bind(empresa);
    }

    @Override
    public int getItemCount() {
        return empresaList.size();
    }

    public class EmpresaViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView nombreTextView;
        private TextView descripcionTextView;
        private TextView webTextView;
        private TextView sectorTextView;
        private TextView razonSocialTextView;
        private TextView horariosTextView;

        public EmpresaViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_empresa);
            nombreTextView = itemView.findViewById(R.id.nombre_empresa);
            descripcionTextView = itemView.findViewById(R.id.descripcion_empresa);
            webTextView = itemView.findViewById(R.id.web_empresa);
            sectorTextView = itemView.findViewById(R.id.sector_empresa);
            razonSocialTextView = itemView.findViewById(R.id.razon_social_empresa);
            horariosTextView = itemView.findViewById(R.id.horarios_empresa);
        }

        public void bind(Empresa empresa) {
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
                    .into(imageView);
        }
    }
}