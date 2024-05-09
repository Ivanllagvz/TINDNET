package com.example.tindnet.ui.register;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tindnet.R;
import com.example.tindnet.ui.Databases.EmpresaContract;
import com.example.tindnet.ui.Databases.EmpresaDbHelper;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class RegEmpresaFragment extends Fragment {

    // Variables para los campos de entrada de texto
    private TextInputEditText nomEmpresaEditText;
    private TextInputEditText numEmpresaEditText;
    private TextInputEditText emailEmpresaEditText;
    private TextInputEditText descEmpresaEditText;
    private TextInputEditText webEmpresaEditText;
    private TextInputEditText sectorEmpresaEditText;
    private TextInputEditText razonSocialEditText;
    private TextInputEditText horariosEditText;

    // Variables para las URIs de los archivos seleccionados
    private Uri documentoUri;
    private Uri imagenUri;
    private Uri logoUri;

    // Constantes para identificar los resultados de las actividades
    private static final int REQUEST_PDF = 1;
    private static final int REQUEST_IMAGEN = 2;
    private static final int REQUEST_LOGO = 3;

    // Variable para la base de datos SQLite
    private SQLiteDatabase mDatabase;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reg_empresa, container, false);

        EmpresaDbHelper dbHelper = new EmpresaDbHelper(getActivity());
        mDatabase = dbHelper.getWritableDatabase();

        nomEmpresaEditText = view.findViewById(R.id.NomRegEmpresa);
        numEmpresaEditText = view.findViewById(R.id.NumRegEmpresa);
        emailEmpresaEditText = view.findViewById(R.id.EmailRegEmpresa);
        descEmpresaEditText = view.findViewById(R.id.DescRegEmpresa);
        webEmpresaEditText = view.findViewById(R.id.WebRegEmpresa);
        sectorEmpresaEditText = view.findViewById(R.id.SectorRegEmpresa);
        razonSocialEditText = view.findViewById(R.id.RazónRegEmpresa);
        horariosEditText = view.findViewById(R.id.HorariosRegEmpresa);

        Button btnDocumentos = view.findViewById(R.id.BTNDocumentos);
        btnDocumentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccionarDocumentoPDF();
            }
        });

        Button btnImagenes = view.findViewById(R.id.BTNImágenes);
        btnImagenes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccionarImagen();
            }
        });

        Button btnLogo = view.findViewById(R.id.BTNLogo);
        btnLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccionarLogotipo();
            }
        });

        Button regEmpresaButton = view.findViewById(R.id.BTNRegEmpresa);
        regEmpresaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = nomEmpresaEditText.getText().toString();
                String numero = numEmpresaEditText.getText().toString();
                String email = emailEmpresaEditText.getText().toString();
                String descripcion = descEmpresaEditText.getText().toString();
                String web = webEmpresaEditText.getText().toString();
                String sector = sectorEmpresaEditText.getText().toString();
                String razonSocial = razonSocialEditText.getText().toString();
                String horarios = horariosEditText.getText().toString();
                insertEmpresa(nombre, numero, email, descripcion, web, sector, razonSocial, horarios);
            }
        });

        return view;
    }

    private void seleccionarDocumentoPDF() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/pdf");
        startActivityForResult(intent, REQUEST_PDF);
    }

    private void seleccionarImagen() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_IMAGEN);
    }

    private void seleccionarLogotipo() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_LOGO);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK) {
            if (requestCode == REQUEST_PDF) {
                if (data != null) {
                    documentoUri = data.getData();
                }
            } else if (requestCode == REQUEST_IMAGEN) {
                if (data != null) {
                    imagenUri = data.getData();
                }
            } else if (requestCode == REQUEST_LOGO) {
                if (data != null) {
                    logoUri = data.getData();
                }
            }
        }
    }

    private void insertEmpresa(String nombre, String numero, String email, String descripcion, String web, String sector, String razonSocial, String horarios) {
        if (nombre.isEmpty() || numero.isEmpty() || email.isEmpty() || descripcion.isEmpty() || web.isEmpty() || sector.isEmpty() || razonSocial.isEmpty() || horarios.isEmpty()) {
            Toast.makeText(getActivity(), "Debe completar todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        ContentValues contentValues = new ContentValues();
        contentValues.put(EmpresaContract.EmpresaEntry.COLUMN_NOMBRE, nombre);
        contentValues.put(EmpresaContract.EmpresaEntry.COLUMN_NUMERO, numero);
        contentValues.put(EmpresaContract.EmpresaEntry.COLUMN_EMAIL, email);
        contentValues.put(EmpresaContract.EmpresaEntry.COLUMN_DESCRIPCION, descripcion);
        contentValues.put(EmpresaContract.EmpresaEntry.COLUMN_WEB, web);
        contentValues.put(EmpresaContract.EmpresaEntry.COLUMN_SECTOR, sector);
        contentValues.put(EmpresaContract.EmpresaEntry.COLUMN_RAZON_SOCIAL, razonSocial);
        contentValues.put(EmpresaContract.EmpresaEntry.COLUMN_HORARIOS, horarios);

        long newRowId = mDatabase.insert(EmpresaContract.EmpresaEntry.TABLE_NAME, null, contentValues);

        if (newRowId == -1) {
            Toast.makeText(getActivity(), "Error al registrar la empresa", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "Empresa registrada con éxito", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mDatabase.close();
    }
}
