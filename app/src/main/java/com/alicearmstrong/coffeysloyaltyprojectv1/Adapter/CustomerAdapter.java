package com.alicearmstrong.coffeysloyaltyprojectv1.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alicearmstrong.coffeysloyaltyprojectv1.MessageActivityOwner;
import com.alicearmstrong.coffeysloyaltyprojectv1.R;
import com.alicearmstrong.coffeysloyaltyprojectv1.database.Customers;

import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.ViewHolder>
{

    private Context mContext;
    private List<Customers> mCustomers;

    public CustomerAdapter (Context mContext, List<Customers> mCustomers)
    {
        this.mCustomers = mCustomers;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate( R.layout.user_item , viewGroup, false);
        return new CustomerAdapter.ViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i)
    {
        final Customers customer = mCustomers.get( i );
        viewHolder.customerName.setText( customer.getFirstName() + " " + customer.getSurname() );

        viewHolder.itemView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MessageActivityOwner.class );
                intent.putExtra( "userid", customer.getId() );
                mContext.startActivity( intent );

            }
        } );
    }

    @Override
    public int getItemCount() {
        return mCustomers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {

        public TextView customerName;

        public ViewHolder(@NonNull View itemView)
        {
            super( itemView );

            customerName = itemView.findViewById( R.id.customerName );
        }
    }

}
