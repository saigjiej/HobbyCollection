package com.example.hobbycollection;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class PayAdapter extends RecyclerView.Adapter<PayAdapter.PayViewHolder> {

    private ArrayList<AddInfo> mDataset;
    private Activity activity;
    private FirebaseFirestore firebaseFirestore;

    static class PayViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        PayViewHolder(Activity activity, CardView v, AddInfo post) {
            super(v);
            cardView = v;
        }
    }

    public PayAdapter(Activity activity, ArrayList<AddInfo> myDataset) {
        mDataset = myDataset;
        this.activity = activity;
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    public int getItemViewType(int position){
        return position;
    }

    @NonNull
    @Override
    public PayAdapter.PayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        final PayViewHolder payViewHolder = new PayViewHolder(activity, cardView, mDataset.get(viewType));
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return payViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PayAdapter.PayViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        TextView order = cardView.findViewById(R.id.tv_order);
        order.setText(mDataset.get(position).getOrder());
        TextView name = cardView.findViewById(R.id.tv_name);
        name.setText(mDataset.get(position).getName());
        TextView phoneNumber = cardView.findViewById(R.id.tv_phoneNumber);
        phoneNumber.setText(mDataset.get(position).getPhoneNumber());
        TextView number = cardView.findViewById(R.id.tv_number);
        number.setText(mDataset.get(position).getNumber());


    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
