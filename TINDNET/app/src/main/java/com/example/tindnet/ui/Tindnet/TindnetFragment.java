package com.example.tindnet.ui.Tindnet;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tindnet.R;
import com.example.tindnet.ui.Databases.DescartadosContract;
import com.example.tindnet.ui.Databases.DescartadosDbHelper;
import com.example.tindnet.ui.Databases.Empresa;
import com.example.tindnet.ui.Databases.EmpresaAdapter;
import com.example.tindnet.ui.Databases.EmpresaDbHelper;
import com.example.tindnet.ui.Databases.FavoritosContract;
import com.example.tindnet.ui.Databases.FavoritosDbHelper;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.List;

public class TindnetFragment extends Fragment {

    private SwipeFlingAdapterView swipeFlingAdapterView;
    private EmpresaDbHelper dbHelper;
    private FavoritosDbHelper favoritosDbHelper;
    private SQLiteDatabase favoritosDb;
    private DescartadosDbHelper descartadosDbHelper;
    private SQLiteDatabase descartadosDb;
    private EmpresaAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tindnet, container, false);

        swipeFlingAdapterView = view.findViewById(R.id.swipe_view);
        dbHelper = new EmpresaDbHelper(getContext());
        favoritosDbHelper = new FavoritosDbHelper(getContext());
        favoritosDb = favoritosDbHelper.getWritableDatabase();
        descartadosDbHelper = new DescartadosDbHelper(getContext());
        descartadosDb = descartadosDbHelper.getWritableDatabase();

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
                Empresa empresa = (Empresa) dataObject;
                insertEmpresaInDescartados(empresa);
                Toast.makeText(getContext(), "Descartado: " + empresa.getNombre(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                Empresa empresa = (Empresa) dataObject;
                insertEmpresaInFavoritos(empresa);
                Toast.makeText(getContext(), "Favorito: " + empresa.getNombre(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdapterAboutToEmpty(int i) {

            }

            @Override
            public void onScroll(float v) {

            }

        });

        return view;
    }

    private void insertEmpresaInFavoritos(Empresa empresa) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FavoritosContract.FavoritosEntry.COLUMN_NOMBRE, empresa.getNombre());
        contentValues.put(FavoritosContract.FavoritosEntry.COLUMN_NUMERO, empresa.getNumero());
        contentValues.put(FavoritosContract.FavoritosEntry.COLUMN_EMAIL, empresa.getEmail());
        contentValues.put(FavoritosContract.FavoritosEntry.COLUMN_PASSWORD, empresa.getPassword());
        contentValues.put(FavoritosContract.FavoritosEntry.COLUMN_DESCRIPCION, empresa.getDescripcion());
        contentValues.put(FavoritosContract.FavoritosEntry.COLUMN_TELEFONO, empresa.getTelefono());
        contentValues.put(FavoritosContract.FavoritosEntry.COLUMN_WEB, empresa.getWeb());
        contentValues.put(FavoritosContract.FavoritosEntry.COLUMN_SECTOR, empresa.getSector());
        contentValues.put(FavoritosContract.FavoritosEntry.COLUMN_RAZON_SOCIAL, empresa.getRazonSocial());
        contentValues.put(FavoritosContract.FavoritosEntry.COLUMN_HORARIOS, empresa.getHorarios());
        contentValues.put(FavoritosContract.FavoritosEntry.COLUMN_DOCUMENTO_URI, empresa.getDocumentoUri());

        List<String> imagenUris = empresa.getImagenUris();
        if (imagenUris.size() > 0) contentValues.put(FavoritosContract.FavoritosEntry.COLUMN_IMAGEN_URI, imagenUris.get(0));
        if (imagenUris.size() > 1) contentValues.put(FavoritosContract.FavoritosEntry.COLUMN_IMAGEN_URI_2, imagenUris.get(1));
        if (imagenUris.size() > 2) contentValues.put(FavoritosContract.FavoritosEntry.COLUMN_IMAGEN_URI_3, imagenUris.get(2));
        if (imagenUris.size() > 3) contentValues.put(FavoritosContract.FavoritosEntry.COLUMN_IMAGEN_URI_4, imagenUris.get(3));
        if (imagenUris.size() > 4) contentValues.put(FavoritosContract.FavoritosEntry.COLUMN_IMAGEN_URI_5, imagenUris.get(4));

        contentValues.put(FavoritosContract.FavoritosEntry.COLUMN_LOGO_URI, empresa.getLogoUri());

        favoritosDb.insert(FavoritosContract.FavoritosEntry.TABLE_NAME, null, contentValues);
    }

    private void insertEmpresaInDescartados(Empresa empresa) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DescartadosContract.DescartadosEntry.COLUMN_NOMBRE, empresa.getNombre());
        contentValues.put(DescartadosContract.DescartadosEntry.COLUMN_NUMERO, empresa.getNumero());
        contentValues.put(DescartadosContract.DescartadosEntry.COLUMN_EMAIL, empresa.getEmail());
        contentValues.put(DescartadosContract.DescartadosEntry.COLUMN_PASSWORD, empresa.getPassword());
        contentValues.put(DescartadosContract.DescartadosEntry.COLUMN_DESCRIPCION, empresa.getDescripcion());
        contentValues.put(DescartadosContract.DescartadosEntry.COLUMN_TELEFONO, empresa.getTelefono());
        contentValues.put(DescartadosContract.DescartadosEntry.COLUMN_WEB, empresa.getWeb());
        contentValues.put(DescartadosContract.DescartadosEntry.COLUMN_SECTOR, empresa.getSector());
        contentValues.put(DescartadosContract.DescartadosEntry.COLUMN_RAZON_SOCIAL, empresa.getRazonSocial());
        contentValues.put(DescartadosContract.DescartadosEntry.COLUMN_HORARIOS, empresa.getHorarios());
        contentValues.put(DescartadosContract.DescartadosEntry.COLUMN_DOCUMENTO_URI, empresa.getDocumentoUri());

        List<String> imagenUris = empresa.getImagenUris();
        if (imagenUris.size() > 0) contentValues.put(DescartadosContract.DescartadosEntry.COLUMN_IMAGEN_URI, imagenUris.get(0));
        if (imagenUris.size() > 1) contentValues.put(DescartadosContract.DescartadosEntry.COLUMN_IMAGEN_URI_2, imagenUris.get(1));
        if (imagenUris.size() > 2) contentValues.put(DescartadosContract.DescartadosEntry.COLUMN_IMAGEN_URI_3, imagenUris.get(2));
        if (imagenUris.size() > 3) contentValues.put(DescartadosContract.DescartadosEntry.COLUMN_IMAGEN_URI_4, imagenUris.get(3));
        if (imagenUris.size() > 4) contentValues.put(DescartadosContract.DescartadosEntry.COLUMN_IMAGEN_URI_5, imagenUris.get(4));

        contentValues.put(DescartadosContract.DescartadosEntry.COLUMN_LOGO_URI, empresa.getLogoUri());

        descartadosDb.insert(DescartadosContract.DescartadosEntry.TABLE_NAME, null, contentValues);
    }
}