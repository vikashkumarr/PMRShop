package com.mahindra.pmrshop.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.mahindra.pmrshop.R;
import com.mahindra.pmrshop.adapters.GrinderAdapter;
import com.mahindra.pmrshop.databinding.ActivityMainBinding;
import com.mahindra.pmrshop.listeners.ItemClickedListener;
import com.mahindra.pmrshop.models.GrinderModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemClickedListener {

    private ActivityMainBinding binding;
    private List<GrinderModel> grinderList;
    private GrinderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setUpRecyclerViewWithData();
        setAdapter();
    }

    private void setAdapter() {
        adapter = new GrinderAdapter(grinderList,this);
        binding.recyclerView.setAdapter(adapter);
    }

    private void setUpRecyclerViewWithData() {
        grinderList();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(mLayoutManager);
        binding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        binding.recyclerView.setHasFixedSize(true);
    }

    private void grinderList() {
        grinderList = new ArrayList<>();
        grinderList.add(new GrinderModel("Table Top Grinder", R.drawable.table_top_grinder));
        grinderList.add(new GrinderModel("Table Top Tilting Grinder", R.drawable.table_top_tilting));
        grinderList.add(new GrinderModel("Tilting Grinder", R.drawable.tilting_grinder));
        grinderList.add(new GrinderModel("Grinder Motor", R.drawable.motor));
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, ServiceActivity.class);
        intent.putExtra("Name",grinderList.get(position).getGrinderName());
        intent.putExtra("Position",position);
        startActivity(intent);
    }
}
