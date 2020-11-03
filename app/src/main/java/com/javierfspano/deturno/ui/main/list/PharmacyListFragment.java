package com.javierfspano.deturno.ui.main.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.javierfspano.deturno.R;
import com.javierfspano.deturno.data.Pharmacy;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class PharmacyListFragment extends Fragment implements PharmacyListContract.View {

    private RecyclerView recyclerView;

    private PharmacyRecyclerAdapter adapter;

    public PharmacyListFragment() {
    }

    public static PharmacyListFragment newInstance() {
        return new PharmacyListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pharmacy_list, container, false);

        recyclerView = view.findViewById(R.id.pharmacy_recycler_view);

        return view;
    }

    @Override
    public void setList(List<Pharmacy> list) {
        adapter = new PharmacyRecyclerAdapter(list);
        recyclerView.setAdapter(adapter);

    }
}