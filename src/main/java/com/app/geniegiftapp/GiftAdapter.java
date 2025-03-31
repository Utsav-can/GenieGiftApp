package com.app.geniegiftapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.List;

public class GiftAdapter extends RecyclerView.Adapter<GiftAdapter.GiftViewHolder> {
    private List<Gift> gifts;
    private boolean isCartMode;

    public GiftAdapter(List<Gift> gifts, boolean isCartMode) {
        this.gifts = gifts;
        this.isCartMode = isCartMode;
    }

    @Override
    public GiftViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gift, parent, false);
        return new GiftViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GiftViewHolder holder, int position) {
        Gift gift = gifts.get(position);
        holder.name.setText(gift.getName());
        holder.price.setText(String.format("$%.2f", gift.getPrice()));
        Picasso.get().load(gift.getImageUrl()).into(holder.image);
        holder.addButton.setText(isCartMode ? "Remove" : "Add");
        holder.addButton.setOnClickListener(v -> {
            if (isCartMode) {
                GiftData.getInstance().removeFromCart(gift);
                gifts.remove(position);
                notifyDataSetChanged();
                Toast.makeText(holder.itemView.getContext(), gift.getName() + " removed from cart", Toast.LENGTH_SHORT).show();
            } else {
                GiftData.getInstance().addToCart(gift);
                Toast.makeText(holder.itemView.getContext(), gift.getName() + " added to cart", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return gifts.size();
    }

    static class GiftViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name, price;
        Button addButton;

        GiftViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.gift_image);
            name = itemView.findViewById(R.id.gift_name);
            price = itemView.findViewById(R.id.gift_price);
            addButton = itemView.findViewById(R.id.add_to_cart_button);
        }
    }
}