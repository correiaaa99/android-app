package com.example.artdecora;


import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter_livroIdeias extends RecyclerView.Adapter<Adapter_livroIdeias.MyViewHolder> {

    private Context context;
    private List<Item_LivroIdeias> item_livroIdeiasList;
    private Dialog dialog;

    Adapter_livroIdeias(Context context, List<Item_LivroIdeias> item_livroIdeiasList, Dialog dialog) {
        this.context = context;
        this.item_livroIdeiasList = item_livroIdeiasList;
        this.dialog = dialog;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.simglerow_livro_ideias,parent,false);
        final MyViewHolder myViewHolder = new MyViewHolder(view);

        dialog = new Dialog(context);
        dialog.setContentView(R.layout.popup_editar_livro_ideias);
        TextView tvClose = dialog.findViewById(R.id.tvClosePopup);
        EditText txtTitulo = dialog.findViewById(R.id.txtTitulo);
        //txtTitulo.setText(item_livroIdeiasList.get(myViewHolder.getAdapterPosition()).getNomeLivroIdeias());
        tvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.livro_nome.setText(item_livroIdeiasList.get(position).getNomeLivroIdeias());
        holder.imageViewLivro1.setImageResource(item_livroIdeiasList.get(position).getImageViewLivro1());
        holder.imageViewLivro2.setImageResource(item_livroIdeiasList.get(position).getImageviewLivro2());
        holder.txtOptionsMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context,holder.txtOptionsMenu);
                popupMenu.inflate(R.menu.recycler_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.nav_editar:
                                dialog.show();
                                break;
                            case R.id.nav_remover:
                                item_livroIdeiasList.remove(position);
                                notifyDataSetChanged();
                                Toast.makeText(context, "eliminado com sucesso", Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return item_livroIdeiasList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView livro_nome;
        AppCompatImageView imageViewLivro1;
        AppCompatImageView imageViewLivro2;
        TextView txtOptionsMenu;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            livro_nome = itemView.findViewById(R.id.txtNomeLivroIdeias);
            imageViewLivro1 = itemView.findViewById(R.id.imageViewLivro1);
            imageViewLivro2 = itemView.findViewById(R.id.imageViewLivro2);
            txtOptionsMenu = itemView.findViewById(R.id.txtViewOptions);
        }
    }
}