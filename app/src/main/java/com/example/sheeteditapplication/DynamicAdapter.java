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

public class DynamicAdapter extends RecyclerView.Adapter<DynamicAdapter.ViewHolder> {
    private final List<List<String>> data;
    private final Context context;

    public DynamicAdapter(Context context, List<List<String>> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        List<String> rowData = data.get(position);
        holder.bind(rowData);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final LinearLayout rowLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rowLayout = itemView.findViewById(R.id.rowLayout);
        }

        public void bind(List<String> rowData) {
            rowLayout.removeAllViews(); // Clear previous views

            for (String cellValue : rowData) {
                EditText editText = new EditText(rowLayout.getContext());
                editText.setText(cellValue);
                rowLayout.addView(editText);
            }
        }
    }
}

