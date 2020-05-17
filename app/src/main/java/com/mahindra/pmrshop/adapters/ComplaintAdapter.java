package com.mahindra.pmrshop.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mahindra.pmrshop.R;
import com.mahindra.pmrshop.databinding.ServiceItemRowBinding;
import com.mahindra.pmrshop.listeners.ComplaintClickListener;
import com.mahindra.pmrshop.models.ComplaintModel;
import com.mahindra.pmrshop.models.GrinderModel;
import com.mahindra.pmrshop.listeners.ItemClickedListener;
import com.mahindra.pmrshop.databinding.ItemRowBinding;
import com.mahindra.pmrshop.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;


public class ComplaintAdapter extends RecyclerView.Adapter<ComplaintAdapter.ComplaintViewHolder> {

    private List<ComplaintModel> complaintList;
    private ComplaintClickListener listener;
    private List<String> tempList = new ArrayList<>();

    public ComplaintAdapter(List<ComplaintModel> grinderList, ComplaintClickListener listener) {
        this.complaintList = grinderList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ComplaintViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ServiceItemRowBinding itemBinding = ServiceItemRowBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ComplaintViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ComplaintViewHolder holder, int position) {

        holder.binding.tvComplaint.setText(complaintList.get(position).getServiceType());

        holder.binding.ctvSelection.setOnClickListener(v -> {

            if (holder.binding.ctvSelection.isChecked()) {

                holder.binding.ctvSelection.setText("Select");
                holder.binding.ctvSelection.setCheckMarkDrawable(null);
                holder.binding.ctvSelection.setChecked(false);
                if (position == 0) {
                    if (complaintList.get(position).getServiceType().equals("Motor rewinding")) {
                        tempList.remove("Motor rewinding");
                    } else
                        tempList.remove("Motor complaint");
                }
                if (position == 1) {
                    if (complaintList.get(position).getServiceType().equals("Capacitor problem")) {
                        tempList.remove("Capacitor problem");
                    } else
                        tempList.remove("Bearing issue");
                }
                if (position == 2) {
                    if (complaintList.get(position).getServiceType().equals("General service")) {
                        tempList.remove("General service");
                    } else
                        tempList.remove("Body broken");
                }
                if (position == 3) {
                    if (complaintList.get(position).getServiceType().equals("Not running")) {
                        tempList.remove("Not running");
                    } else
                        tempList.remove("Spares broken");
                }
                if (position == 4) {
                    tempList.remove("Not Running");
                }

                if (position == 5) {
                    tempList.remove("General Service");
                }
            } else {

                tempList.add(complaintList.get(position).getServiceType());
                holder.binding.ctvSelection.setText("");
                holder.binding.ctvSelection.setTextColor(Color.WHITE);
                holder.binding.ctvSelection.setCheckMarkDrawable(R.drawable.ic_check);
                holder.binding.ctvSelection.setChecked(true);
            }
            //Toast.makeText(holder.binding.ctvSelection.getContext(), "Size" + tempList, Toast.LENGTH_SHORT).show();
            CommonUtils.getInstance().setSelectedComplaintList(tempList);

        });


    }

    @Override
    public int getItemCount() {
        return complaintList.size();
    }

    public class ComplaintViewHolder extends RecyclerView.ViewHolder {

        private ServiceItemRowBinding binding;

        public ComplaintViewHolder(@NonNull ServiceItemRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
