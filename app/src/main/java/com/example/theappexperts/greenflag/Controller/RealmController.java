package com.example.theappexperts.greenflag.Controller;

import com.example.theappexperts.greenflag.localDB.CustomerModel;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by TheAppExperts on 22/09/2017.
 */

public class RealmController {
    Realm realm;

    public RealmController(Realm realm) {
        this.realm = realm;
    }
    public void saveCustomer(final CustomerModel customerModel){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(customerModel);
            }
        });
    }
    public ArrayList<CustomerModel> getCustomerList(){

        java.util.ArrayList<CustomerModel> customerModelsArrayList = new ArrayList<>();
        RealmResults<CustomerModel> realmResults = realm.where(CustomerModel.class).findAll();

        for (CustomerModel customerModel:realmResults){
            customerModelsArrayList.add(customerModel);
        }

        return customerModelsArrayList;
    }
}
