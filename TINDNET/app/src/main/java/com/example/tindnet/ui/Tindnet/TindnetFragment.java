package com.example.tindnet.ui.Tindnet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.tindnet.R;
import com.example.tindnet.ui.Databases.Empresa;
import com.example.tindnet.ui.Databases.EmpresaAdapter;
import com.example.tindnet.ui.Databases.EmpresaDbHelper;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;
import java.util.List;

public class TindnetFragment extends Fragment {

    private SwipeFlingAdapterView swipeFlingAdapterView;
    private EmpresaAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tindnet, container, false);

        swipeFlingAdapterView = view.findViewById(R.id.swipe_view);

        EmpresaDbHelper dbHelper = new EmpresaDbHelper(getContext());
        List<Empresa> empresaList = dbHelper.getAllEmpresas();
        adapter = new EmpresaAdapter(getContext(), empresaList);
        swipeFlingAdapterView.setAdapter(adapter);

        swipeFlingAdapterView.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                empresaList.remove(0);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                // Acción cuando se desliza a la izquierda
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                // Acción cuando se desliza a la derecha
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                // Acción cuando el adaptador está a punto de quedarse vacío
            }

            @Override
            public void onScroll(float scrollProgressPercent) {
                // Acción durante el desplazamiento
            }
        });

        swipeFlingAdapterView.setOnItemClickListener((itemPosition, dataObject) -> {
            // Acción cuando se hace clic en una tarjeta
        });

        return view;
    }
}
