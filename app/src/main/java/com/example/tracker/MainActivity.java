package com.example.tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DatabaseItemList DB;

    LinearLayout LLayout;
    Button createButton, deleteButton;
    TextView totalCalorieText;
    ArrayList<ItemContent> ListItem = new ArrayList<ItemContent>();
    Double totalCalories = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LLayout = findViewById(R.id.listItem);
        createButton = findViewById(R.id.createButton);
        deleteButton = findViewById(R.id.deleteButton);
        totalCalorieText = findViewById(R.id.totalCalorie);

        storeData();
        ListItem.add(new FoodContent("Ayam Geprek", 250.32, 25.6, "Makanan"));
        ListItem.add(new FoodContent("Ayam Geprek", 250.32, 25.6, "Makanan"));
        ListItem.add(new FoodContent("Ayam Geprek", 250.32, 25.6, "Makanan"));
        ListItem.add(new FoodContent("Ayam Geprek", 250.32, 25.6, "Makanan"));
        for(ItemContent i : ListItem)
        {
            View newItem = getLayoutInflater().inflate(R.layout.itemlayout, null, false);

            TextView newItemName = (TextView)newItem.findViewById(R.id.ItemName);
            TextView newItemCalorie = (TextView)newItem.findViewById(R.id.ItemCalorie);

            LLayout.addView(newItem);
            if(i instanceof FoodContent)
            {
                newItemName.setText(i.getNamaItem());
                newItemCalorie.setText(i.getKaloriItem().toString());
            }else
            {
                newItemName.setText(i.getNamaItem());
                newItemCalorie.setText(i.getKaloriItem().toString());
            }

            totalCalories += i.getKaloriItem();
        }

        totalCalorieText.setText(totalCalories + " Kcal");
    }

    public void storeData(){
        Cursor cursor = DB.readData();

        if(cursor.getCount() == 0){
            Toast.makeText(this, "No Data.", Toast.LENGTH_SHORT).show();
        }else {
            while(cursor.moveToNext()){
                String NamaItem = cursor.getString(1);
                String TypeItem = cursor.getString(2);
                Double TotalItem = cursor.getDouble(3);
                Double WeightTotalItem = cursor.getDouble(4);

                ItemContent newItem;
                if(TypeItem.equals("Makanan")){
                    newItem = new FoodContent(NamaItem, TotalItem, WeightTotalItem, TypeItem);
                }else{
                    newItem = new DrinkContent(NamaItem, TotalItem, WeightTotalItem, TypeItem);
                }

                ListItem.add(newItem);
            }
        }
    }

    public void createDish(View v)
    {
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateItemActivity.class);
                startActivity(intent);
            }
        });

    }
}