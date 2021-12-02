package com.burhanrashid52.photoeditor.borders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.burhanrashid52.photoeditor.R;
import com.burhanrashid52.photoeditor.tools.EditingToolsAdapter;
import com.burhanrashid52.photoeditor.tools.ToolType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class bordersAdapter extends RecyclerView.Adapter<bordersAdapter.ViewHolder> {

    private List<BorderModel> mborderlist = new ArrayList<>();
    private bordersAdapter.OnItemSelected mOnItemSelected;

    public bordersAdapter(bordersAdapter.OnItemSelected onItemSelected) {
        mOnItemSelected = onItemSelected;
        mborderlist.add(new bordersAdapter.BorderModel("Brush", R.drawable.ic_brush, borderType.one));
        mborderlist.add(new bordersAdapter.BorderModel("Text", R.drawable.ic_text, borderType.two));

    }
    public interface OnItemSelected {
        void onBorderSelected (borderType bordertype) throws IOException;
    }
    class BorderModel {
        private String mBorderName;
        private int mBorderIcon;
        private borderType mBorderType;

        BorderModel(String bordername, int bordericon, borderType bordertype) {
            mBorderName = bordername;
            mBorderType = bordertype;
            mBorderIcon = bordericon;
        }


    }
    @NonNull
    @Override
    public bordersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_editing_tools, parent, false);
        return new bordersAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull bordersAdapter.ViewHolder holder, int position) {
        BorderModel item = mborderlist.get(position);
        holder.txtTool.setText(item.mBorderName);
        holder.imgBorderIcon.setImageResource(item.mBorderIcon);
    }

    @Override
    public int getItemCount() {

        return mborderlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgBorderIcon;
        TextView txtTool;

        ViewHolder(View itemView) {
            super(itemView);
            imgBorderIcon = itemView.findViewById(R.id.imgToolIcon);
            txtTool = itemView.findViewById(R.id.txtTool);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        mOnItemSelected.onBorderSelected(mborderlist.get(getLayoutPosition()).mBorderType);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
