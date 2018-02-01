package com.example.ganesh.retrofitexample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ganesh.retrofitexample.models.Item;

import java.util.List;

/**
 * Created by ganesh on 2/1/18.
 */

public class AnswerAdapter  extends RecyclerView.Adapter<AnswerAdapter.ViewHolder>{
    private List<Item> items;
    private final Context context;
    private final PostItemListener itemListener;
    public AnswerAdapter(List<Item> items, Context context, PostItemListener itemListener) {
        this.items = items;
        this.context = context;
        this.itemListener = itemListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater= LayoutInflater.from(context);

        View postView = layoutInflater.inflate(android.R.layout.simple_list_item_1, parent,false);
        ViewHolder viewHolder = new ViewHolder(postView, this.itemListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Item item = items.get(position);
        TextView textView = holder.titleTv;
        textView.setText(item.getOwner().getDisplayName());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView titleTv;
        PostItemListener itemListener;
        public ViewHolder(View itemView, PostItemListener postItemListener) {
            super(itemView);
            titleTv = (TextView)itemView.findViewById(android.R.id.text1);

            this.itemListener = postItemListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Item item = getItem(getAdapterPosition());
            this.itemListener.onPostClick(item.getAnswerId());
            notifyDataSetChanged();

        }
    }
    public void updateAnswers(List<Item> items){
        this.items = items;
        notifyDataSetChanged();
    }
    private Item getItem(int adapterPosition) {
        return items.get(adapterPosition);
    }

    public interface PostItemListener{
        void onPostClick(long id);
    }
}
