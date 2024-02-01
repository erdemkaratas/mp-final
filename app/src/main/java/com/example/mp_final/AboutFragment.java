package com.example.mp_final;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mp_final.databinding.FragmentAboutBinding;

public class AboutFragment extends Fragment {
    FragmentAboutBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentAboutBinding.inflate(inflater, container,false);
        View root=binding.getRoot();

        Button btngithub=binding.btnGithub;
        Button btnlinkedin=binding.btnLinkedin;

        btngithub.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String url="https://github.com/erdemkaratas";
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        btnlinkedin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String url2="https://www.linkedin.com/in/erdem-karata%C5%9F-3505/nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn  ";
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url2));
                startActivity(intent);
            }
        });


        return root;
    }
}