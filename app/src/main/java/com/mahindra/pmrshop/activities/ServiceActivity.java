package com.mahindra.pmrshop.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.mahindra.pmrshop.R;
import com.mahindra.pmrshop.adapters.ComplaintAdapter;
import com.mahindra.pmrshop.databinding.ActivityServiceTypeBinding;
import com.mahindra.pmrshop.listeners.ComplaintClickListener;
import com.mahindra.pmrshop.models.ComplaintModel;

import java.util.ArrayList;
import java.util.List;

public class ServiceActivity extends AppCompatActivity implements ComplaintClickListener {

    private ActivityServiceTypeBinding binding;
    private ComplaintAdapter adapter;
    private List<ComplaintModel> complaintList;
    private List<ComplaintModel> motorList;
    private int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_service_type);

        Intent intent = getIntent();

        if (intent != null) {
            String name = intent.getStringExtra("Name");
            this.position = intent.getIntExtra("Position", 0);
            setSelectedService(name);

        }

        setUpRecyclerViewWithData();
        setAdapter();

        binding.btnNext.setOnClickListener(v -> startActivity(new Intent(this, CustomerDetailsActivity.class)));
    }

    private void setSelectedService(String name) {
        SpannableStringBuilder builder = new SpannableStringBuilder();

        SpannableString redSpannable = new SpannableString("You have selected ");
        redSpannable.setSpan(new ForegroundColorSpan(Color.parseColor("#ff000000")), 0, "You have selected".length(), 0);
        builder.append(redSpannable);

        SpannableString whiteSpannable = new SpannableString(name + " ");
        whiteSpannable.setSpan(new ForegroundColorSpan(Color.BLUE), 0, name.length(), 0);
        builder.append(whiteSpannable);

        SpannableString blueSpannable = new SpannableString("for the Service.");
        blueSpannable.setSpan(new ForegroundColorSpan(Color.parseColor("#ff000000")), 0, "for the Service.".length(), 0);
        builder.append(blueSpannable);

        binding.tvSelectedService.setText(builder, TextView.BufferType.SPANNABLE);
    }

    private void setAdapter() {
        if (position == 3) {
            adapter = new ComplaintAdapter(motorList, this);
            binding.rvServiceList.setAdapter(adapter);
        } else {
            adapter = new ComplaintAdapter(complaintList, this);
            binding.rvServiceList.setAdapter(adapter);
        }
    }

    private void setUpRecyclerViewWithData() {
        getMotorList();
        complaintList();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        binding.rvServiceList.setLayoutManager(mLayoutManager);
        binding.rvServiceList.setItemAnimator(new DefaultItemAnimator());
        binding.rvServiceList.setHasFixedSize(true);
    }

    private void complaintList() {
        complaintList = new ArrayList<>();
        complaintList.add(new ComplaintModel("Motor complaint"));
        complaintList.add(new ComplaintModel("Bearing issue"));
        complaintList.add(new ComplaintModel("Body broken"));
        complaintList.add(new ComplaintModel("Spares broken"));
        complaintList.add(new ComplaintModel("Not Running"));
        complaintList.add(new ComplaintModel("General Service"));
    }

    private void getMotorList() {
        motorList = new ArrayList<>();
        motorList.add(new ComplaintModel("Motor rewinding"));
        motorList.add(new ComplaintModel("Capacitor problem"));
        motorList.add(new ComplaintModel("General service"));
        motorList.add(new ComplaintModel("Not running"));
    }

    @Override
    public void onComplaintClick(int position) {

    }
}
