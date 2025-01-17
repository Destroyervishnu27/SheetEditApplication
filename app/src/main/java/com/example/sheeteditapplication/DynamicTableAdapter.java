package com.example.sheeteditapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DynamicTableAdapter extends RecyclerView.Adapter<DynamicTableAdapter.ViewHolder> {
    private final List<List<String>> data;
    private final Context context;

    public DynamicTableAdapter(Context context, List<List<String>> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        List<String> rowData = data.get(position);

        // Dynamically create cells for each column
        holder.rowLayout.removeAllViews();
        for (String cellValue : rowData) {
            EditText editText = new EditText(context);
            editText.setText(cellValue);
            editText.setBackgroundResource(R.drawable.cell_background);
            editText.setGravity(android.view.Gravity.CENTER);
            editText.setPadding(8, 8, 8, 8);
            editText.setLayoutParams(new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f)); // Weight for equal spacing
            holder.rowLayout.addView(editText);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout rowLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rowLayout = itemView.findViewById(R.id.rowLayout);
        }
    }
}

