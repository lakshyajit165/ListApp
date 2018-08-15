package com.example.elkay.listapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.elkay.listapp.MainActivity;
import com.example.elkay.listapp.Model.ToDo;
import com.example.elkay.listapp.R;

import java.util.List;

class ListItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnCreateContextMenuListener
{
    ItemClickListener itemClickListener;
    TextView item_title, item_description;

    public ListItemViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        itemView.setOnCreateContextMenuListener(this);

        item_title = (TextView)itemView.findViewById(R.id.item_title);
        item_description = (TextView)itemView.findViewById(R.id.item_description);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view){

        itemClickListener.onClick(view,getAdapterPosition(),false);

    }
    @Override
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo){
        contextMenu.setHeaderTitle("Select the action");
        contextMenu.add(0,0,getAdapterPosition(),"DELETE");
    }
}


public class ListItemAdapter extends RecyclerView.Adapter<ListItemViewHolder> {
    @NonNull
    MainActivity mainActivity;
    List<ToDo> todoList;

    public ListItemAdapter(MainActivity mainActivity, List<ToDo> todoList){
        this.mainActivity = mainActivity;
        this.todoList = todoList;
    }

    @Override
    public ListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mainActivity.getBaseContext());
        View view = inflater.inflate(R.layout.list_item,parent,false);

        return new ListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemViewHolder holder, int position) {

        //Set data for item
        holder.item_title.setText(todoList.get(position).getTitle());
        holder.item_description.setText(todoList.get(position).getDescription());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                //When user selects item data will auto set for Edit text view
                mainActivity.title.setText(todoList.get(position).getTitle());
                mainActivity.description.setText(todoList.get(position).getDescription());

                mainActivity.isUpdate = true;
                mainActivity.idUpdate = todoList.get(position).getId();

            }
        });
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }
}
