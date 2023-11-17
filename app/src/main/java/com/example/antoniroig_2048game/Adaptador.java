package com.example.antoniroig_2048game;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Adaptador extends ArrayAdapter<Integer> {
    private Activity context;
    private Integer[] numeros;
    public Adaptador (Activity context, Integer[] numeros){
        super(context, R.layout.squarenumber, numeros);
        this.numeros = numeros;
        this.context = context;
    }

    static class ViewHolder {
        TextView numero;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View item = convertView;
        ViewHolder holder = new ViewHolder();
        if(item == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            item = inflater.inflate(R.layout.squarenumber, null);
            holder.numero = item.findViewById(R.id.tile);
            item.setTag(holder);
        }else{
            holder = (ViewHolder) item.getTag();
        }
        holder.numero.setText((numeros[position] + 1)+"");
        return (item);
    }
}
