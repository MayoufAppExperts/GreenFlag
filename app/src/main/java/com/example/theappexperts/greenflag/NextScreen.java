package com.example.theappexperts.greenflag;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.codetroopers.betterpickers.datepicker.DatePickerBuilder;
import com.example.theappexperts.greenflag.Controller.RealmController;
import com.example.theappexperts.greenflag.localDB.CustomerModel;


import java.io.ByteArrayOutputStream;
import io.realm.Realm;



public class NextScreen extends Fragment {

    ImageView imageView;
    Button dateButton, photoBtn, saveBtn;
    Realm realm;
    RealmController realmController;
    CustomerModel customerModel;
    EditText name, password, userName,age;
    static final int REQUEST_IMAGE_CAPTURE =1;
    byte[] imageInByte;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setRetainInstance(true);

        return inflater.inflate(R.layout.activity_next_screen, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        name = (EditText) view.findViewById(R.id.name);
        password = (EditText) view.findViewById(R.id.password);
        userName = (EditText) view.findViewById(R.id.userName);
        age = (EditText) view.findViewById(R.id.age);


        dateButton = (Button) view.findViewById(R.id.dateBtn);
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerBuilder dpb = new DatePickerBuilder()
                        .setFragmentManager(getFragmentManager())
                        .setStyleResId(R.style.BetterPickersDialogFragment)
                        .setYearOptional(true);
                dpb.show();
            }
        });

        Spinner sp= (Spinner) view.findViewById(R.id.natSpinner);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> av, View v,
                                       int position, long itemId) {

                String item=av.getItemAtPosition(position).toString();
            }
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });


        realm = Realm.getDefaultInstance();
        realmController = new RealmController(realm);
        saveBtn = (Button)view.findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customerModel = new CustomerModel(name.getText().toString(), password.getText().toString(), userName.getText().toString(), age.getText().toString(), imageInByte );
                realmController.saveCustomer(customerModel);
                getFragmentManager().beginTransaction()
                        .replace(R.id.homeFragment, new CustomerListActivity())
                        .commit();
            }
        });


        this.imageView = (ImageView)view.findViewById(R.id.photoIv);
        Button photoButton = (Button) view.findViewById(R.id.photoBtn);
        photoButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE);
                }
            });
        }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == getActivity().RESULT_OK ){
            Bundle extras = data.getExtras();
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
            //Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.PNG, 100, baos);
            imageInByte = baos.toByteArray();
        }
    }
}
