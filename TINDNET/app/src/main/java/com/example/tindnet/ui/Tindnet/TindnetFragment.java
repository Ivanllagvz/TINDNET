package com.example.tindnet.ui.Tindnet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tindnet.R;
import com.example.tindnet.ui.Databases.Empresa;
import com.example.tindnet.ui.Databases.EmpresaAdapter;
import com.example.tindnet.ui.Databases.EmpresaDbHelper;
import java.util.ArrayList;
import java.util.List;

public class TindnetFragment extends Fragment {

    private RecyclerView recyclerView;
    private EmpresaAdapter empresaAdapter;
    private List<Empresa> empresaList;
    private EmpresaDbHelper dbHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tindnet, container, false);

        recyclerView = view.findViewById(R.id.recycler_empresas);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        empresaList = new ArrayList<>();
        dbHelper = new EmpresaDbHelper(getActivity());

        cargarEmpresas();

        return view;
    }

    private void cargarEmpresas() {
        empresaList = dbHelper.getAllEmpresas();
        empresaAdapter = new EmpresaAdapter(getActivity(), empresaList);
        recyclerView.setAdapter(empresaAdapter);
    }
}