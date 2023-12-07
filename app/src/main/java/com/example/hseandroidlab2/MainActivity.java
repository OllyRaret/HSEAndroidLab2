package com.example.hseandroidlab2;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ShoppingAdapter adapter;
    private List<ShoppingItem> shoppingItems;
    private EditText newItemEditText;
    private EditText quantityEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        shoppingItems = new ArrayList<>();
        adapter = new ShoppingAdapter(shoppingItems);
        recyclerView.setAdapter(adapter);

        newItemEditText = findViewById(R.id.edit_new_item);
        quantityEditText = findViewById(R.id.edit_quantity);

        FloatingActionButton fabAdd = findViewById(R.id.fab_add);
        fabAdd.setOnClickListener(v -> addItem());
    }

    private void addItem() {
        String newItemName = newItemEditText.getText().toString().trim();
        String quantityStr = quantityEditText.getText().toString().trim();

        if (!newItemName.isEmpty() && !quantityStr.isEmpty()) {
            int quantity = Integer.parseInt(quantityStr);

            // Создаем новый объект ShoppingItem с указанным именем и количеством
            ShoppingItem newItem = new ShoppingItem(newItemName, quantity);

            // Добавляем новый товар в список
            shoppingItems.add(newItem);

            // Уведомляем адаптер о изменениях в списке
            adapter.notifyDataSetChanged();

            // Очищаем поля ввода
            newItemEditText.getText().clear();
            quantityEditText.getText().clear();
        } else {
            Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show();
        }
    }
}
