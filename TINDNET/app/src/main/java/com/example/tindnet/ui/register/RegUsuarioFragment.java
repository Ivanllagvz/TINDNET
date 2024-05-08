package com.example.tindnet.ui.register;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.tindnet.R;
import com.example.tindnet.ui.Databases.UserContract;
import com.example.tindnet.ui.Databases.UserDbHelper;
import com.google.android.material.textfield.TextInputLayout;

public class RegUsuarioFragment extends Fragment {

    private UserDbHelper mDbHelper;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_reg_usuario, container, false);
        mDbHelper = new UserDbHelper(getContext());

        Button btnRegister = root.findViewById(R.id.BTN_Regusuario);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInputs(root)) {
                    long newRowId = insertUser(root);
                    if (newRowId != -1) {
                        Toast.makeText(getContext(), "Datos guardados correctamente", Toast.LENGTH_SHORT).show();
                        navigateToLoginFragment();
                    } else {
                        Toast.makeText(getContext(), "Error al guardar los datos", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return root;
    }

    private boolean validateInputs(View root) {
        boolean isValid = true;

        TextInputLayout nomRegUsuarioLayout = root.findViewById(R.id.NomRegUsuarioText);
        String nomRegUsuario = nomRegUsuarioLayout.getEditText().getText().toString().trim();
        if (nomRegUsuario.isEmpty()) {
            isValid = false;
        } else {
            nomRegUsuarioLayout.setError(null);
        }

        TextInputLayout dniRegLayout = root.findViewById(R.id.DNIRegUsuarioText);
        String dniReg = dniRegLayout.getEditText().getText().toString().trim();
        if (dniReg.isEmpty()) {
            isValid = false;
        } else {
            dniRegLayout.setError(null);
        }

        TextInputLayout telRegLayout = root.findViewById(R.id.TelRegUsuarioText);
        String telReg = telRegLayout.getEditText().getText().toString().trim();
        if (telReg.isEmpty()) {
            isValid = false;
        } else {
            telRegLayout.setError(null);
        }

        TextInputLayout emailRegLayout = root.findViewById(R.id.EmailRegUsuarioText);
        String emailReg = emailRegLayout.getEditText().getText().toString().trim();
        if (emailReg.isEmpty()) {
            isValid = false;
        } else {
            emailRegLayout.setError(null);
        }

        TextInputLayout passwordRegLayout = root.findViewById(R.id.PasswordRegUsuarioText);
        String passwordReg = passwordRegLayout.getEditText().getText().toString().trim();
        if (passwordReg.isEmpty()) {
            isValid = false;
        } else {
            passwordRegLayout.setError(null);
        }

        return isValid;
    }

    private long insertUser(View root) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        TextInputLayout nomRegUsuarioLayout = root.findViewById(R.id.NomRegUsuarioText);
        String nomRegUsuario = nomRegUsuarioLayout.getEditText().getText().toString().trim();

        TextInputLayout dniRegLayout = root.findViewById(R.id.DNIRegUsuarioText);
        String dniReg = dniRegLayout.getEditText().getText().toString().trim();

        TextInputLayout telRegLayout = root.findViewById(R.id.TelRegUsuarioText);
        String telReg = telRegLayout.getEditText().getText().toString().trim();

        TextInputLayout emailRegLayout = root.findViewById(R.id.EmailRegUsuarioText);
        String emailReg = emailRegLayout.getEditText().getText().toString().trim();

        TextInputLayout passwordRegLayout = root.findViewById(R.id.PasswordRegUsuarioText);
        String passwordReg = passwordRegLayout.getEditText().getText().toString().trim();

        ContentValues values = new ContentValues();
        values.put(UserContract.UserEntry.COLUMN_NAME, nomRegUsuario);
        values.put(UserContract.UserEntry.COLUMN_DNI, dniReg);
        values.put(UserContract.UserEntry.COLUMN_PHONE, telReg);
        values.put(UserContract.UserEntry.COLUMN_EMAIL, emailReg);
        values.put(UserContract.UserEntry.COLUMN_PASSWORD, passwordReg);

        long newRowId = db.insert(UserContract.UserEntry.TABLE_NAME, null, values);
        return newRowId;
    }

    private void navigateToLoginFragment() {
        Navigation.findNavController(requireView()).navigate(R.id.nav_login);
    }

}