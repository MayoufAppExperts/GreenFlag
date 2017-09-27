package com.example.theappexperts.greenflag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

//import com.example.theappexperts.greenflag.Controller.RealmController;
//import com.example.theappexperts.greenflag.localDB.CustomerModel;

import io.realm.Realm;

/**
 * Created by TheAppExperts on 20/09/2017.
 */

public class RegForm extends Fragment {

    EditText email, pass1, pass2;
    Button button;
    Realm realm;
    //RealmController realmController;
    //CustomerModel customerModel;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        email = (EditText) view.findViewById(R.id.email);
        pass1 = (EditText) view.findViewById(R.id.pass1);
        pass2 = (EditText) view.findViewById(R.id.pass2);
        button = (Button) view.findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.homeFragment, new NextScreen())
                        .commit();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        setRetainInstance(true);

        return inflater.inflate(R.layout.reg_form, container, false);
    }


}