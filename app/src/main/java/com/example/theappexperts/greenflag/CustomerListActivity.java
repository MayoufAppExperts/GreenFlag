package com.example.theappexperts.greenflag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.theappexperts.greenflag.Controller.RealmController;
import com.example.theappexperts.greenflag.localDB.CustomerModel;

import java.util.ArrayList;

import io.realm.Realm;

/**
 * Created by TheAppExperts on 22/09/2017.
 */

public class CustomerListActivity extends Fragment{

    Realm realm;
    RealmController realmController;
    ArrayList<CustomerModel> customerModels;
    RecyclerView recyclerView;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        realm = Realm.getDefaultInstance();
        realmController = new RealmController(realm);
        customerModels = realmController.getCustomerList();

        initialiseRecyclerView(view);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        setRetainInstance(true);
        return inflater.inflate(R.layout.activity_customer_list, container, false);
    }

    public void initialiseRecyclerView(View view) {

        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerCustomer);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new CustomerAdapter(customerModels, R.layout.row, getActivity()));
    }
}
