package com.dvora.myapplicationn.fragments.register_login;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dvora.myapplicationn.R;
import com.dvora.myapplicationn.activities.MainActivity;
import com.dvora.myapplicationn.fragments.BaseFragment;
import com.dvora.myapplicationn.interfaces.CallBackFragment;
import com.dvora.myapplicationn.storage.SharePreferenceHelper;
import com.dvora.myapplicationn.view_modles.RegisterViewModel;

public class RegisterFragment extends BaseFragment {

    private RegisterViewModel mViewModel;
    private Button btnRegister;
    private EditText edtName;
    private EditText edtEmail;
    private EditText edtPass;

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        loadObservers();
    }

    private void loadObservers() {
        mViewModel.getLiveDataResponse().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isRegisterSuccessfully) {
                if (isRegisterSuccessfully) {
                    mViewModel.insertNewUser(getContext());
                    Toast.makeText(getContext(), "Register!", Toast.LENGTH_SHORT).show();
                    mListener.showActivity(MainActivity.class);

                } else
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadViews(view);
        loadAllListeners();
    }

    private void loadAllListeners() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString();
                String pass = edtPass.getText().toString();
                String name = edtName.getText().toString();

                mViewModel.setName(name);
                mViewModel.setEmailAddress(email);
                mViewModel.createNewUser(email, pass);
            }
        });


    }

    private void loadViews(View view) {
        btnRegister = view.findViewById(R.id.btnRegister);
        edtName = view.findViewById(R.id.edtName);
        edtEmail = view.findViewById(R.id.edtEmail);
        edtPass = view.findViewById(R.id.edtPass);
    }
}