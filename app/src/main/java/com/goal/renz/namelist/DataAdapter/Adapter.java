package com.goal.renz.namelist.DataAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.goal.renz.namelist.DataSet.Names;
import com.goal.renz.namelist.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Renz on 5/4/2016.
 */
public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{

    String letter;
    RecyclerView recyclerView;
    ColorGenerator generator = ColorGenerator.MATERIAL;

    static List<Names> namesList;
    static Context context;

    RecyclerView.Adapter adapter;

    //Database Model - where you get and set all the fields

    public Adapter(Context context, List<Names> namesList){
        this.namesList = new ArrayList<>(namesList);
        this.context = context;
        this.namesList = namesList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        letter = String.valueOf(namesList.get(position).getTitle().charAt(0));
        TextDrawable drawable = TextDrawable.builder().buildRound(letter, generator.getRandomColor());

        holder.title.setText(namesList.get(position).getTitle());
        holder.subject.setText(namesList.get(position).getSubject());
        holder.description.setText(namesList.get(position).getDescription());
        holder.date.setText(namesList.get(position).getDate());
        holder.profilepic.setImageDrawable(drawable);


        /*Get From constructor gagamitin kung static naman datas na ilalagay
        holder.course.setText(namesList.get(position).course);
        holder.date.setText(namesList.get(position).date);
        holder.description.setText(namesList.get(position).description);*/


    }

    @Override
    public int getItemCount() {
      //  namesList.notify();
        return namesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView profilepic;
        TextView title;
        TextView subject,date,description;

        public ViewHolder(View itemView) {
            super(itemView);

            profilepic = (ImageView) itemView.findViewById(R.id.profilepic);
            title = (TextView) itemView.findViewById(R.id.title);
            subject = (TextView) itemView.findViewById(R.id.subject);
            date = (TextView) itemView.findViewById(R.id.date);
            description = (TextView) itemView.findViewById(R.id.description);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.recyclerview);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
        //    int i = getAdapterPosition();
        //    Toast.makeText(Adapter.context, i, Toast.LENGTH_LONG).show();

        }
    }

    /*
    public void removeItem(int position){
        namesList.remove(position);
        recyclerView.removeViewAt(position);
        adapter.notifyItemRemoved(position);
        adapter.notifyItemRangeChanged(position, namesList.size());
    }
    */

}
