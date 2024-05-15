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
            }

            @Override
            public void onRightCardExit(Object dataObject) {
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
            }

            @Override
            public void onScroll(float scrollProgressPercent) {
            }
        });

        swipeFlingAdapterView.setOnItemClickListener((itemPosition, dataObject) -> {
        });

        return view;
    }
}