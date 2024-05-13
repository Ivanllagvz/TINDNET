package com.example.tindnet.ui.register;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.tindnet.R;

public class RegisterFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_register, container, false);

        Button btnRegisterUsuario = root.findViewById(R.id.BTNRegisterUsuario);
        btnRegisterUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(RegisterFragment.this)
                        .navigate(R.id.nav_regusuario);
            }
        });

        Button btnRegisterEmpresa = root.findViewById(R.id.BTNRegisterEmpresa);
        btnRegisterEmpresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.nav_RegEmpresa);
            }
        });

        return root;
    }
}
