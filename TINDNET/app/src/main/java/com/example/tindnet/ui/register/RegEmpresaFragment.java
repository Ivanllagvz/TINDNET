package com.example.tindnet.ui.register;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.tindnet.R;
import com.example.tindnet.ui.Databases.EmpresaContract;
import com.example.tindnet.ui.Databases.EmpresaDbHelper;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class RegEmpresaFragment extends Fragment {

    private TextInputEditText nomEmpresaEditText;
    private TextInputEditText numEmpresaEditText;
    private TextInputEditText passwordEmpresaEditText;
    private TextInputEditText emailEmpresaEditText;
    private TextInputEditText descEmpresaEditText;
    private TextInputEditText telEmpresaEditText;
    private TextInputEditText webEmpresaEditText;
    private TextInputEditText sectorEmpresaEditText;
    private TextInputEditText razonSocialEditText;
    private TextInputEditText horariosEditText;

    private Uri documentoUri;
    private Uri imagenUri;
    private Uri logoUri;
    private ImageView imageView;

    private static final int REQUEST_PDF = 1;
    private static final int REQUEST_IMAGEN = 2;
    private static final int REQUEST_LOGO = 3;

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
        passwordEmpresaEditText = view.findViewById(R.id.PasswordRegEmpresa);
        descEmpresaEditText = view.findViewById(R.id.DescRegEmpresa);
        telEmpresaEditText = view.findViewById(R.id.TelRegEmpresa);
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
                String nombre = Objects.requireNonNull(nomEmpresaEditText.getText()).toString();
                String numero = Objects.requireNonNull(numEmpresaEditText.getText()).toString();
                String email = Objects.requireNonNull(emailEmpresaEditText.getText()).toString();
                String password = Objects.requireNonNull(passwordEmpresaEditText.getText()).toString();
                String descripcion = Objects.requireNonNull(descEmpresaEditText.getText()).toString();
                String telefono = Objects.requireNonNull(telEmpresaEditText.getText()).toString();
                String web = Objects.requireNonNull(webEmpresaEditText.getText()).toString();
                String sector = Objects.requireNonNull(sectorEmpresaEditText.getText()).toString();
                String razonSocial = Objects.requireNonNull(razonSocialEditText.getText()).toString();
                String horarios = Objects.requireNonNull(horariosEditText.getText()).toString();
                insertEmpresa(nombre, numero, email, password, descripcion, telefono, web, sector, razonSocial, horarios);
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
                if (data != null && data.getData() != null) {
                    documentoUri = data.getData();
                    Log.d("RegEmpresaFragment", "Documento URI: " + documentoUri.toString());
                }
            } else if (requestCode == REQUEST_IMAGEN) {
                if (data != null && data.getData() != null) {
                    imagenUri = data.getData();
                    Log.d("RegEmpresaFragment", "Imagen URI: " + imagenUri.toString());
                }
            } else if (requestCode == REQUEST_LOGO) {
                if (data != null && data.getData() != null) {
                    logoUri = data.getData();
                    Log.d("RegEmpresaFragment", "Logo URI: " + logoUri.toString());
                }
            }
        }
    }

    private void insertEmpresa(String nombre, String numero, String email, String password, String descripcion, String telefono, String web, String sector, String razonSocial, String horarios) {
        if (nombre.isEmpty() || numero.isEmpty() || email.isEmpty() || descripcion.isEmpty() || web.isEmpty() || sector.isEmpty() || razonSocial.isEmpty() || horarios.isEmpty()) {
            Toast.makeText(getActivity(), "Debe completar todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        ContentValues contentValues = new ContentValues();
        contentValues.put(EmpresaContract.EmpresaEntry.COLUMN_NOMBRE, nombre);
        contentValues.put(EmpresaContract.EmpresaEntry.COLUMN_NUMERO, numero);
        contentValues.put(EmpresaContract.EmpresaEntry.COLUMN_EMAIL, email);
        contentValues.put(EmpresaContract.EmpresaEntry.COLUMN_PASSWORD, password);
        contentValues.put(EmpresaContract.EmpresaEntry.COLUMN_DESCRIPCION, descripcion);
        contentValues.put(EmpresaContract.EmpresaEntry.COLUMN_TELEFONO, telefono);
        contentValues.put(EmpresaContract.EmpresaEntry.COLUMN_WEB, web);
        contentValues.put(EmpresaContract.EmpresaEntry.COLUMN_SECTOR, sector);
        contentValues.put(EmpresaContract.EmpresaEntry.COLUMN_RAZON_SOCIAL, razonSocial);
        contentValues.put(EmpresaContract.EmpresaEntry.COLUMN_HORARIOS, horarios);

        if (documentoUri != null) {
            contentValues.put(EmpresaContract.EmpresaEntry.COLUMN_DOCUMENTO_URI, documentoUri.toString());
        }
        if (imagenUri != null) {
            contentValues.put(EmpresaContract.EmpresaEntry.COLUMN_IMAGEN_URI, imagenUri.toString());
        }
        if (logoUri != null) {
            contentValues.put(EmpresaContract.EmpresaEntry.COLUMN_LOGO_URI, logoUri.toString());
        }

        long newRowId = mDatabase.insert(EmpresaContract.EmpresaEntry.TABLE_NAME, null, contentValues);

        if (newRowId == -1) {
            Toast.makeText(getActivity(), "Error al registrar la empresa", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "Empresa registrada con éxito", Toast.LENGTH_SHORT).show();
            Navigation.findNavController(requireView()).navigate(R.id.nav_logempresa);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mDatabase.close();
    }
}