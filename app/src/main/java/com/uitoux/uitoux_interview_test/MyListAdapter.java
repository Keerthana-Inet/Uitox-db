package com.uitoux.uitoux_interview_test;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder> {
    private final List<DbModel> listdata;
    private final CallBack callBack;

    // RecyclerView recyclerView;
    public MyListAdapter(List<DbModel> listdata, CallBack callBack) {
        this.listdata = listdata;
        this.callBack = callBack;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final DbModel myListData = listdata.get(position);
        holder.textView.setText(myListData.getTitle());
        Log.d("Nametitle", "Nametitle: "+myListData.getTitle());
        holder.sno.setText(myListData.getSno());
        holder.mark1.setText(myListData.getMark1());
        holder.mark2.setText(myListData.getMark2());
        holder.mark3.setText(myListData.getMark3());
        int m1=Integer.parseInt(myListData.getMark1().toString());
        int m2=Integer.parseInt(myListData.getMark2().toString());
        int m3=Integer.parseInt(myListData.getMark3().toString());
        int tot=m1+m2+m3;
        Log.d("Total", "Total: "+tot);

        holder.total.setText(String.valueOf(tot));
        holder.textUpdate.setOnClickListener(view -> {
            callBack.onClick(listdata.get(position), "update");
            Toast.makeText(view.getContext(), "clicked on item to update: " + myListData, Toast.LENGTH_LONG).show();
        });
        holder.textDel.setOnClickListener(view -> {
            callBack.onClick(listdata.get(position), "delete");
            Toast.makeText(view.getContext(), "clicked on item to delete: " + myListData, Toast.LENGTH_LONG).show();
        });
    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView, textDel, textUpdate,mark1,mark2,sno,mark3,total;

        public ViewHolder(View itemView) {
            super(itemView);
            this.textView = itemView.findViewById(R.id.textView);
            this.mark1 = itemView.findViewById(R.id.mark1);
            this.mark2 = itemView.findViewById(R.id.mark2);
            this.textDel = itemView.findViewById(R.id.textDel);
            this.textUpdate = itemView.findViewById(R.id.textUpdate);
            this.sno = itemView.findViewById(R.id.sno);
            this.mark3 = itemView.findViewById(R.id.mark3);
            this.total = itemView.findViewById(R.id.total);
        }
    }

    interface CallBack {
        void onClick(DbModel dbModel, String type);
    }
}
