package com.servico.roomorm;

import android.arch.persistence.room.Delete;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by João Victor Firmino on 03/12/2017.
 */

class UserAdpt extends RecyclerView.Adapter<UserAdpt.ViewHolder> {
    List<UserOrm> arrayListUser;

    public UserAdpt(List<UserOrm> arrayListUser) {
        this.arrayListUser = arrayListUser;
    }

    @Override
    public UserAdpt.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_users, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserAdpt.ViewHolder holder, int position) {
        holder.textNome.setText(arrayListUser.get(position).getNome());
        holder.textSobre.setText(arrayListUser.get(position).getSobrenome());
        holder.textTelefone.setText(arrayListUser.get(position).getTelefone());
        holder.textEmail.setText(arrayListUser.get(position).getEmail());
        holder.textCPF.setText(arrayListUser.get(position).getCpf());
    }

    @Override
    public int getItemCount() {
        return arrayListUser.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView textNome;
        public TextView textSobre;
        public TextView textTelefone;
        public TextView textEmail;
        public TextView textCPF;
        public ViewHolder(View itemView) {
            super(itemView);
            textNome = itemView.findViewById(R.id.textNome);
            textSobre = itemView.findViewById(R.id.textSobre);
            textTelefone = itemView.findViewById(R.id.textTelefone);
            textEmail = itemView.findViewById(R.id.textEmail);
            textCPF = itemView.findViewById(R.id.textCPF);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            Toast.makeText(v.getContext(), "OK!" , Toast.LENGTH_SHORT).show();
        }

    }
}
