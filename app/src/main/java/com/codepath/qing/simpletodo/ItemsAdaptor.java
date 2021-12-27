package com.codepath.qing.simpletodo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ItemsAdaptor extends RecyclerView.Adapter<ItemsAdaptor.ViewHolder> {
    public interface OnLongClickListener{
        void onItemLongClicked(int position);
    }

    public void removeView(int position){
        items.remove(position);
        notifyItemRemoved(position);
        //Toast.makeText(getApplicationContext(),"Item was successfully removed!", Toast.LENGTH_SHORT).show();
        main.saveItems();
    }
    List<String> items;
    View.OnClickListener clickListener;
    OnLongClickListener longClickListener;
    MainActivity main;
    public ItemsAdaptor(List<String> items,MainActivity main) {
        this.items = items;
        this.main=main;
        this.longClickListener = longClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1,parent, false);
        View todoView=LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview,parent,false);

        return new ViewHolder(todoView, main.itemsAdaptor);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String item = items.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvItem;
        Button btn;
        ItemsAdaptor adaptor;
        public ViewHolder(@NonNull View itemView, ItemsAdaptor adaptor) {
            super(itemView);
            this.adaptor=adaptor;
            tvItem = itemView.findViewById(R.id.inputtext);
            btn = itemView.findViewById(R.id.btnDLT);
            btn.setVisibility(View.INVISIBLE);
        }

        public void bind(String item) {

            tvItem.setText(item);
            tvItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                  //  longClickListener.onItemLongClicked(getAdapterPosition());
                    Log.e("czq","set visible");
                    btn.setVisibility(View.VISIBLE);
                    return true;
                }
            });
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    adaptor.removeView(getAdapterPosition());
                }
            });
            btn.setVisibility(View.INVISIBLE);
        }
    }
}
