package com.example.hseandroidlab2;

import android.annotation.SuppressLint;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ShoppingAdapter extends RecyclerView.Adapter<ShoppingAdapter.ViewHolder> {
    private final List<ShoppingItem> shoppingItems;

    public ShoppingAdapter(List<ShoppingItem> shoppingItems) {
        this.shoppingItems = shoppingItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shopping, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ShoppingItem item = shoppingItems.get(position);
        holder.itemNameEditText.setText(item.getItemName());
        holder.quantityEditText.setText(String.valueOf(item.getQuantity()));

        // Обработчик изменения текста для редактирования названия
        holder.itemNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                String nameStr = editable.toString();
                item.setItemName(nameStr);
            }
        });

        // Обработчик изменения текста для редактирования количества
        holder.quantityEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                String quantityStr = editable.toString();
                int quantity = quantityStr.isEmpty() ? 0 : Integer.parseInt(quantityStr);
                item.setQuantity(quantity);
            }
        });

        // Обработчик нажатия на кнопку "Удалить"
        holder.btnDelete.setOnClickListener(view -> {
            // Удалить элемент из списка и обновить адаптер
            shoppingItems.remove(position);
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return shoppingItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemNameEditText;
        EditText quantityEditText;
        Button btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemNameEditText = itemView.findViewById(R.id.edit_item_name);
            quantityEditText = itemView.findViewById(R.id.edit_quantity);
            btnDelete = itemView.findViewById(R.id.btn_delete);
        }
    }
}
