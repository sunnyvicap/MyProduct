package com.example.testapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ModelAdpater extends RecyclerView.Adapter<ModelAdpater.ModelViewHoler> {

    private Context context;
    private List<Model> modelList;
    private CheckedChangeListner checkedChangeListner;

    public ModelAdpater(Context context, List<Model> modelList, CheckedChangeListner checkedChangeListner) {
        this.context = context;
        this.modelList = modelList;
        this.checkedChangeListner = checkedChangeListner;
    }

    @NonNull
    @Override
    public ModelAdpater.ModelViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.model_child_list, parent, false);
        return new ModelViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ModelAdpater.ModelViewHoler holder, final int position) {

        if (!modelList.isEmpty()) {

            final Model model = modelList.get(position);

            holder.mTXTMobile.setText(model.mobile);
            holder.mTXTName.setText(model.name);
            holder.mTXTCompany.setText(model.companyName);
            holder.deleteRow.setChecked(false);

            holder.deleteRow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(holder.deleteRow.isChecked()){

                        checkedChangeListner.getCheckedPosition(position,true);
                    }else {

                        checkedChangeListner.getCheckedPosition(position,false);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ModelViewHoler extends RecyclerView.ViewHolder {

        CheckBox deleteRow;
        TextView mTXTName;
        TextView mTXTMobile;
        TextView mTXTCompany;

        public ModelViewHoler(@NonNull View itemView) {
            super(itemView);

            deleteRow = itemView.findViewById(R.id.deleteRow);
            mTXTName = itemView.findViewById(R.id.mTXTName);
            ;
            mTXTMobile = itemView.findViewById(R.id.mTXTMobile);
            ;
            mTXTCompany = itemView.findViewById(R.id.mTXTCompany);
            ;
        }
    }
}
