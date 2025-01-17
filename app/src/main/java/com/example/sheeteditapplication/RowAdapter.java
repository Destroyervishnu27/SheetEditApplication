package com.example.sheeteditapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RowAdapter extends RecyclerView.Adapter<RowAdapter.ViewHolder> {
    private final Context context;
    private final List<String> rowData;

    public RowAdapter(Context context, List<String> rowData) {
        this.context = context;
        this.rowData = rowData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cell_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.editText.setText(rowData.get(position)); // Set cell value
    }

    @Override
    public int getItemCount() {
        return rowData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        EditText editText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            editText = itemView.findViewById(R.id.editTextCell);
        }
    }
}

