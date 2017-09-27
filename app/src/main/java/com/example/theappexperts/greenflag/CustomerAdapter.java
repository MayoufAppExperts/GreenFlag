package com.example.theappexperts.greenflag;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.theappexperts.greenflag.localDB.CustomerModel;

import java.util.ArrayList;


class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.MyViewHolder> {

    ArrayList<CustomerModel> customerModels;
    int row;

    public ArrayList<CustomerModel> getCustomerModels() {
        return customerModels;
    }

    public void setCustomerModels(ArrayList<CustomerModel> customerModels) {
        this.customerModels = customerModels;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public Context getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    Context applicationContext;

    public CustomerAdapter(ArrayList<CustomerModel> customerModels, int row, Context applicationContext) {
        this.customerModels=customerModels;
        this.row=row;
        this.applicationContext=applicationContext;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(row, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (customerModels.get(position).getImageInByte() != null){
            Bitmap bitmap = BitmapFactory.decodeByteArray(customerModels.get(position)
                    .getImageInByte(), 0, customerModels.get(position)
                    .getImageInByte().length);
            holder.imageV.setImageBitmap(bitmap);
        }
        holder.tvName.setText(customerModels.get(position).getName());
        holder.tvPass.setText(customerModels.get(position).getPassword());
        holder.tvUserName.setText(customerModels.get(position).getUserName());
        holder.tvAge.setText(customerModels.get(position).getAge());


    }

    @Override
    public int getItemCount() {
        return customerModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvPass, tvUserName, tvAge;
        ImageView imageV;


        public MyViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView)itemView.findViewById(R.id.textView);
            tvPass= (TextView)itemView.findViewById(R.id.textView2);
            tvUserName = (TextView)itemView.findViewById(R.id.textUserName);
            tvAge= (TextView)itemView.findViewById(R.id.textAge);
            imageV = (ImageView)itemView.findViewById (R.id.ivNew);



        }
    }
}
