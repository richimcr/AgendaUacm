package com.mcr.agendauacm.sections.avances;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mcr.agendauacm.R;
import com.mcr.agendauacm.model.Materia;

import java.util.List;

public class AdapterMaterias extends RecyclerView.Adapter<AdapterMaterias.ViewHolder> {

    private List<Materia> materias;
    private int layout;
    private IonItemClickListener ionItemClickListener;

    public AdapterMaterias(List<Materia> materias, int layout, IonItemClickListener listener)
    {
        this.materias=materias;
        this.layout=layout;
        this.ionItemClickListener=listener;
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
        holder.bind(materias.get(position),ionItemClickListener);
    }

    @Override
    public int getItemCount() {
        return materias.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        private Switch aSwitch;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            aSwitch = (Switch) itemView.findViewById(R.id.switchMateria);
        }

        public void bind(final Materia materia, final IonItemClickListener listener)
        {
            this.aSwitch.setText(materia.getNombre());

            if(materia.getEstado().equals("S")) {
                aSwitch.setChecked(true);
            }
            else {
                aSwitch.setChecked(false);
            }

            aSwitch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean cambio = true;
                    listener.onItemChangeState(materia,getAdapterPosition(),aSwitch.isChecked());
                }
            });

        }
    }


    public interface IonItemClickListener {
        void onItemChangeState(Materia materia, int position, boolean estado);
    }
}
