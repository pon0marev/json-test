package com.example.chrom.jsontest;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ItemViewHolder>{

    private static RVClickListener clickListener;
    private  List<Message> messageList;
    Context context;
    int layout=R.layout.layout_item;



    public RVAdapter(Context context, List<Message> messageList){
        this.context=context;
        this.messageList = messageList;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        ItemViewHolder pvh = new ItemViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

        holder.textId.setText(String.valueOf(messageList.get(position).getId()));
        holder.textTime.setText(String.valueOf(messageList.get(position).getTime()));
        holder.textText.setText(messageList.get(position).getText());
        Picasso.get().load(messageList.get(position).getImage()).into(holder.itemImage);

        /*InputStream inputStream = null;
        try{
            inputStream = context.getAssets().open(this.image[position]);
            Drawable d = Drawable.createFromStream(inputStream, null);
            holder.itemImage.setImageDrawable(d);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally {
            try{
                if(inputStream!=null)
                    inputStream.close();
            }
            catch (IOException ex){
                ex.printStackTrace();
            }
        }*/
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        private final TextView textTime;
        private final TextView textText;
        CardView cv;
        TextView textId;
        ImageView itemImage;
        ItemViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            cv.setOnClickListener(this);
            textId = (TextView)itemView.findViewById(R.id.textId);
            textTime = (TextView)itemView.findViewById(R.id.textTime);
            textText = (TextView)itemView.findViewById(R.id.textText);
            itemImage = (ImageView)itemView.findViewById(R.id.imageView);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
        }

        @Override
        public boolean onLongClick(View v) {
            clickListener.onItemLongClick(getAdapterPosition(), v);
            return false;
        }
    }
    public void setOnItemClickListener(RVClickListener clickListener) {
        RVAdapter.clickListener =  clickListener;
    }

    public interface RVClickListener {
        void onItemClick(int position, View v);
        void onItemLongClick(int position, View v);
    }

}