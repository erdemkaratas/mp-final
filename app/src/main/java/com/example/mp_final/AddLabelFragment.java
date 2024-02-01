package com.example.mp_final;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.mp_final.databinding.FragmentAddLabelBinding;
import com.example.mp_final.ui.Label;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class AddLabelFragment extends Fragment {

    private FragmentAddLabelBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentAddLabelBinding.inflate(inflater,container,false);
        View root=binding.getRoot();

        LinearLayout linearLayout=binding.linearLayout;
        FirebaseFirestore db=FirebaseFirestore.getInstance();
        CollectionReference labelref=db.collection("label");

        labelref.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot document:task.getResult()){
                        Label label=document.toObject(Label.class);
                        CheckBox checkbox=new CheckBox(getActivity());
                        checkbox.setText(label.getLabelText());
                        linearLayout.addView(checkbox);
                    }
                }
                else{
                    Log.d("TAG", "Hata",task.getException());
                }
            }
        });
        Button added=binding.added;
        added.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
             EditText labelText=binding.label;
             String labelEditText=labelText.getText().toString();

             labelref.whereEqualTo("labelEditText",labelEditText).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                 @Override
                 public void onComplete(@NonNull Task<QuerySnapshot> task) {
                     if(task.isSuccessful() && task.getResult().size()>0) {
                         Toast.makeText(getActivity(), "Mevcut etiket girildi", Toast.LENGTH_SHORT).show();
                     }else{
                         labelref.add(new Label(labelEditText))
                                 .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                     public void onSuccess(DocumentReference documentReference){
                                         Toast.makeText(getActivity(),"Label Başarıyla Eklendi",Toast.LENGTH_SHORT).show();
                                         labelText.setText("");
                                     }

                                 }).addOnFailureListener(new OnFailureListener() {
                                     @Override
                                     public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(getActivity(), "Label ekleme işlemi yapılamadı",Toast.LENGTH_SHORT).show();
                                     }
                                 });
                     }
                 }
             });
            }
        });



        return root;
    }
}