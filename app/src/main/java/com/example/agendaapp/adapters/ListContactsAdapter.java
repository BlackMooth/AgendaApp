package com.example.agendaapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.XmlRes;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agendaapp.R;
import com.example.agendaapp.ViewActivity;
import com.example.agendaapp.entities.Contact;

import java.util.ArrayList;

public class ListContactsAdapter extends RecyclerView.Adapter<ListContactsAdapter.ContactViewHolder> {

    ArrayList<Contact> listContacts;

    public ListContactsAdapter(ArrayList<Contact> listContacts) {
        this.listContacts = listContacts;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_contact, null, false);

        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        holder.textViewName.setText(listContacts.get(position).getName());
        holder.textViewTelephone.setText(listContacts.get(position).getTelephone());
        holder.textViewEmail.setText(listContacts.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return listContacts.size();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        TextView textViewTelephone;
        TextView textViewEmail;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.textViewName);
            textViewTelephone = itemView.findViewById(R.id.textViewTelephone);
            textViewEmail = itemView.findViewById(R.id.textViewEmail);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, ViewActivity.class);
                    intent.putExtra("Name", listContacts.get(getAdapterPosition()).getName());
                    context.startActivity(intent);
                }
            });
        }
    }
}
