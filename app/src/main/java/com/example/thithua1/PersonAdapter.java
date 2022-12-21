package com.example.thithua1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {
    private ArrayList<person_class> listPerson;
    private Context mContext;

    public PersonAdapter(Context context, ArrayList<person_class> listPerson){
        this.mContext = context;
        this.listPerson = listPerson;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item,parent,false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        person_class p = listPerson.get(position);
        if (p==null){
            return;
        }
        holder.rvPic.setImageResource(p.getPic());
        holder.rvFullName.setText(p.getFullname());

        holder.itemView.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                int values = 0;
                if (p.getFullname()=="Lí thuyết") {
                    values = 1;
                }else if (p.getFullname()=="Biển báo") {
                    values = 2;
                }else if (p.getFullname()=="Câu hỏi liệt") {
                    values = 3;
                }

                Intent intent = new Intent(view.getContext(),ThiThu.class);
                intent.putExtra("idcategories",values);

                mContext.startActivity(intent);
            }
        });
        //holder.rvPhone.setText(p.getPhone());
    }

    @Override
    public int getItemCount(){
        if(listPerson!=null){
            return listPerson.size();
        }
        return 0;
    }



    public class ViewHolder extends RecyclerView.ViewHolder{
        CardView layout_item;
        ImageView rvPic, rvCall, rvChat;
        TextView rvFullName, rvPhone;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            rvPic = itemView.findViewById(R.id.product_image);
            rvFullName = itemView.findViewById(R.id.product_title);
            //rvPhone = itemView.findViewById(R.id.product_price);

        };
    }

    private static final int REQUEST_CODE_QUESTION = 1;

//    private void localCategories(){
//        Database database = new Database(this);
//
//    }
}


