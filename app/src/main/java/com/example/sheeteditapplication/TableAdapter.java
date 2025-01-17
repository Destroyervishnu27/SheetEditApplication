package com.example.sheeteditapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.ViewHolder> {
    private final Context context;
    private final List<String> tableData;
    private final int columnCount;

    public TableAdapter(Context context, List<String> tableData, int columnCount) {
        this.context = context;
        this.tableData = tableData;
        this.columnCount = columnCount;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cell_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String cellValue = tableData.get(position);
        holder.editTextCell.setText(cellValue);
    }

    @Override
    public int getItemCount() {
        return tableData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        EditText editTextCell;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            editTextCell = itemView.findViewById(R.id.editTextCell);
        }
    }
}

