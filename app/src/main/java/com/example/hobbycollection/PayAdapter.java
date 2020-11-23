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

    private ArrayList<PostInfo> mDataset;
    private Activity activity;
    private FirebaseFirestore firebaseFirestore;

    public static class PayViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;
        public PayViewHolder(Activity activity, CardView v, PostInfo post) {
            super(v);
            cardView = v;
        }
    }

    public PayAdapter(Activity activity, ArrayList<PostInfo> myDataset) {
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
        TextView textView = cardView.findViewById(R.id.textView);
        textView.setText(mDataset.get(position).getOrder());
        textView.setText(mDataset.get(position).getName());
        textView.setText(mDataset.get(position).getPhoneNumber());
        textView.setText(mDataset.get(position).getNumber());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
