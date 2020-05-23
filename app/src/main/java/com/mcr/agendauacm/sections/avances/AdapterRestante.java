package com.mcr.agendauacm.sections.avances;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mcr.agendauacm.R;
import com.mcr.agendauacm.model.Materia;

import java.util.List;

public class AdapterRestante extends RecyclerView.Adapter<AdapterRestante.ViewHolder> {

    private List<Materia> materias;
    private int layout;

    public AdapterRestante(List<Materia> materias, int layout)
    {
        this.materias=materias;
        this.layout=layout;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(materias.get(position));
    }

    @Override
    public int getItemCount() {
        return materias.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView tvMateria;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            tvMateria=(TextView) itemView.findViewById(R.id.tvMateriaRestante);
        }

        public void bind(final Materia materia)
        {
            this.tvMateria.setText(materia.getNombre());
        }
    }

}
