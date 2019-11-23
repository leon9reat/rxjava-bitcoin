package com.medialink.rxjavabitcoin;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.medialink.rxjavabitcoin.models.Market;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> {
    private ArrayList<Market> listMarket;

    public MainAdapter() {
        this.listMarket = new ArrayList<>();
    }

    public void setData(ArrayList<Market> inputData) {
        this.listMarket.addAll(inputData);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Market item = listMarket.get(position);
        holder.tvCoin.setText(item.getCoinName());
        holder.tvMarket.setText(item.getMarket());
        holder.tvPrice.setText("$" + String.format("%.2f", Double.parseDouble(item.getPrice())));

        if (item.getCoinName().equalsIgnoreCase("eth")) {
            holder.cardItemMain.setCardBackgroundColor(Color.GRAY);
        } else {
            holder.cardItemMain.setCardBackgroundColor(Color.GREEN);
        }
    }

    @Override
    public int getItemCount() {
        return listMarket.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_coin)
        TextView tvCoin;
        @BindView(R.id.tv_market)
        TextView tvMarket;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.card_item_main)
        CardView cardItemMain;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
