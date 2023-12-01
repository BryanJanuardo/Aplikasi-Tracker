package com.example.tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateItemActivity extends AppCompatActivity {

    TextView errorMessages;
    Button submit;
    ImageButton backPage;
    EditText InputNama, InputKalori, InputSpesific;
    CheckBox InputJenis1, InputJenis2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_item);

        backPage = findViewById(R.id.BackPage);

        InputNama = findViewById(R.id.InputNameItem);
        InputKalori = findViewById(R.id.InputTotalCalorie);
        InputJenis1 = findViewById(R.id.InputTypeItem1);
        InputJenis2 = findViewById(R.id.InputTypeItem2);
        InputSpesific = findViewById(R.id.InputSpecificContentItem);

        errorMessages = findViewById(R.id.errorMessage);
        submit = findViewById(R.id.buttonSubmit);

        backPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateItemActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String namaItem = InputNama.getText().toString();
                String kaloriItem = InputKalori.getText().toString();
                String jenis1Item = InputJenis1.getText().toString();
                String jenis2Item = InputJenis2.getText().toString();
                String inputSpesific = InputSpesific.getText().toString();

                if(!validation(namaItem, kaloriItem, jenis1Item, jenis2Item, inputSpesific))
                    return;

                insertItem(namaItem, kaloriItem, jenis1Item, jenis2Item, inputSpesific);
                Intent intent = new Intent(CreateItemActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void insertItem(String namaItem, String kaloriItem, String jenis1Item,
                            String jenis2Item, String inputSpecific) {
        DatabaseItemList DB = new DatabaseItemList(CreateItemActivity.this);
        if(InputJenis1.isChecked()){
            DB.addItem(namaItem.trim(), jenis1Item.trim(), Double.parseDouble(kaloriItem.trim()), Double.parseDouble(inputSpecific.trim()));
        }else {
            DB.addItem(namaItem.trim(), jenis2Item.trim(), Double.parseDouble(kaloriItem.trim()), Double.parseDouble(inputSpecific.trim()));
        }

    }
    private boolean regexAllNumber(String input)
    {
        String regex = "^[+-]?\\d*\\.?\\d+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        return matcher.matches();
    }

    private boolean validation(String namaItem, String kaloriItem,
                              String jenis1Item, String jenis2Item, String inputSpesific) {
        if(namaItem.isEmpty() ||
           kaloriItem.isEmpty() ||
            inputSpesific.isEmpty() ||
           (!InputJenis1.isChecked() && !InputJenis2.isChecked())){
            errorMessages.setText("Semua Input harus terisi!");
            return false;
        }
        else if (!regexAllNumber(kaloriItem)){
            errorMessages.setText("Input Total Kalori harus berisi angka semua!");
            return false;
        }
        else if(!regexAllNumber(inputSpesific)){
            errorMessages.setText("Input Berat/Volume harus berisi angka semua!");
            return false;
        }
        else if(InputJenis1.isChecked() && InputJenis2.isChecked()){
            errorMessages.setText("Input Jenis Item hanya boleh 1 kali dicentang!");
            return false;
        }
        else if(Double.parseDouble(kaloriItem) < 0){
            errorMessages.setText("Input Total Kalori harus lebih dari 0!");
            return false;
        }
        return true;
    }

}