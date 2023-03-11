package com.example.api.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.api.DetailActivity;
import com.example.api.EditActivity;
import com.example.api.MainActivity;
import com.example.api.R;
import com.example.api.entity.Trainee;

import java.util.List;

public class TraineeAdapter extends BaseAdapter{

    private MainActivity context;
    private int layout;
    private List<Trainee> traineeList;

    public TraineeAdapter(MainActivity context, int layout, List<Trainee> traineeList) {
        this.context = context;
        this.layout = layout;
        this.traineeList = traineeList;
    }

    @Override
    public int getCount() {
        return traineeList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(layout, null);
            holder.trainee_name = convertView.findViewById(R.id.trainee_name);
            holder.image_delete = convertView.findViewById(R.id.image_delete);
            holder.image_edit = convertView.findViewById(R.id.image_edit);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Trainee trainee = traineeList.get(position);
        holder.trainee_name.setText(trainee.getName());
        holder.image_delete.setImageResource(R.drawable.delete);
        holder.image_edit.setImageResource(R.drawable.edit);

        holder.trainee_name.setOnClickListener(v -> {

            Intent intent = new Intent(context, DetailActivity.class);

            Bundle bundle = new Bundle();
            bundle.putParcelable("traineeDetail", trainee);

            intent.putExtras(bundle);

            context.startActivity(intent);

            context.finish();

        });

        holder.image_edit.setOnClickListener(v -> {

            Intent intent = new Intent(context, EditActivity.class);

            Bundle bundle = new Bundle();
            bundle.putParcelable("traineeEdit", trainee);

            intent.putExtras(bundle);

            context.startActivity(intent);

            context.finish();

        });

        holder.image_delete.setOnClickListener(v -> {
            context.dialogDeleteTask(trainee.getName(), trainee.getId());
//            Toast.makeText(context, "delete " + task.getName(), Toast.LENGTH_SHORT).show();
        });

        return convertView;
    }

    private class ViewHolder{
        TextView trainee_name;
        ImageView image_delete, image_edit;
    }
}
