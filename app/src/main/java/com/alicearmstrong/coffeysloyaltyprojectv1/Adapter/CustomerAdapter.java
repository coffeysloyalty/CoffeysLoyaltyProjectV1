package com.alicearmstrong.coffeysloyaltyprojectv1.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.alicearmstrong.coffeysloyaltyprojectv1.R;
import com.alicearmstrong.coffeysloyaltyprojectv1.database.Customers;
import com.alicearmstrong.coffeysloyaltyprojectv1.uiOwner.chatOwner.MessageActivityOwner;

import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.ViewHolder>
{

    private Context context;
    private List<Customers> customersList;

    public CustomerAdapter (Context context, List<Customers> customersList)
    {
        this.customersList = customersList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        // Set layout to user_item for displaying each user
        View view = LayoutInflater.from(context).inflate( R.layout.user_item , viewGroup, false);
        return new CustomerAdapter.ViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i)
    {
        final Customers customer = customersList.get( i );
        // Set title to user's name
        viewHolder.customerName.setText( customer.getFirstName() + " " + customer.getSurname() );

        // Open Message Activity when user is selected
        viewHolder.itemView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(context, MessageActivityOwner.class );
                intent.putExtra( "userid", customer.getId() );
                context.startActivity( intent );
            }
        } );
    }

    @Override
    public int getItemCount()
    {
        return customersList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder
    {

        public TextView customerName;

        public ViewHolder(@NonNull View view)
        {
            super( view );

            customerName = view.findViewById( R.id.customerName );
        }
    }
}
