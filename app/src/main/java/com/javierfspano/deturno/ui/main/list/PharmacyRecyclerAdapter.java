package com.javierfspano.deturno.ui.main.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.javierfspano.deturno.R;
import com.javierfspano.deturno.data.Pharmacy;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class PharmacyRecyclerAdapter extends RecyclerView.Adapter<PharmacyRecyclerAdapter.ViewHolder> {

    private final List<Pharmacy> pharmacies;

    public PharmacyRecyclerAdapter() {
        this.pharmacies = new ArrayList<>();
    }

    public PharmacyRecyclerAdapter(List<Pharmacy> pharmacies) {
        this.pharmacies = pharmacies;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_pharmacy_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.data = pharmacies.get(position);
        holder.pharmacyNameLabel.setText(holder.data.getName());
        holder.pharmacyAddressLabel.setText(holder.data.getStreetName() + " " + holder.data.getStreetNumber());
    }

    @Override
    public int getItemCount() {
        return pharmacies.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final TextView pharmacyNameLabel;
        public final TextView pharmacyAddressLabel;
        public Pharmacy data;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            pharmacyNameLabel = view.findViewById(R.id.pharmacyNameLabel);
            pharmacyAddressLabel = view.findViewById(R.id.pharmacyAddressLabel);
        }
    }
}