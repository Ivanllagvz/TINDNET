package com.example.tindnet.ui.login;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import com.example.tindnet.R;
import com.example.tindnet.ui.Databases.EmpresaDbHelper;
import com.google.android.material.textfield.TextInputEditText;
import java.util.Objects;

public class LogEmpresaFragment extends Fragment {

    private EmpresaDbHelper mDbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_log_empresa, container, false);
        mDbHelper = new EmpresaDbHelper(getContext());

        Button btnLogin = root.findViewById(R.id.BTN_LogEmpresa);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextInputEditText emailEditText = root.findViewById(R.id.EmailLog);
                TextInputEditText passwordEditText = root.findViewById(R.id.PasswordLog);
                TextInputEditText fiscalEditText = root.findViewById(R.id.FiscalLog);

                String email = Objects.requireNonNull(emailEditText.getText()).toString().trim();
                String password = Objects.requireNonNull(passwordEditText.getText()).toString().trim();
                String fiscalId = Objects.requireNonNull(fiscalEditText.getText()).toString().trim();

                if (mDbHelper.checkEmpresaCredentials(email, password, fiscalId)) {
                    Navigation.findNavController(v).navigate(R.id.nav_TindnetEmpresa);
                } else {
                    Toast.makeText(getContext(), "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return root;
    }
}