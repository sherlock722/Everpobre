package fjc.com.everpobre.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fjc.com.everpobre.R;
import fjc.com.everpobre.model.Notebook;
import fjc.com.everpobre.model.Notebooks;
import fjc.com.everpobre.views.NotebookRowViewHolder;

/**
 * Created by javier on 8/5/16.
 */
//Extiendo se RecyclerViewAdapter y es una clase plantilla
public class NotebookAdapter extends RecyclerView.Adapter<NotebookRowViewHolder> {

    //Este adapter crea las filas y las pinta.

    private LayoutInflater layoutInflater;
    private Notebooks notebooks;

    //Cuando necesitamos un contexto lo inyectamos en el constructor, al igual que los Notebooks
    public NotebookAdapter (Notebooks notebooks, Context context){
        layoutInflater = LayoutInflater.from(context);
        this.notebooks=notebooks;

    }

    //Crea las celdas. Se llama varias veces para crear las celdas
    @Override
    public NotebookRowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //Parametros: Fichero que cargo, donde meto esto (parent),
        View view = layoutInflater.inflate(R.layout.row_notebook,parent,false);

        //Devuelvo un ViewHolder utilizando el método NotebookRowViewHolder de NotebookRowViewHolder
        return new NotebookRowViewHolder(view);
    }

    //Recicla las celdas. Cada vez que repinte se llamará a este método
    @Override
    public void onBindViewHolder(NotebookRowViewHolder holder, int position) {

        Notebook notebook = notebooks.get(position);
        //Pinto en el textView el nombre del notebook
        holder.setNotebookName(notebook.getName());
    }

    @Override
    public int getItemCount() {
        return notebooks.size();
    }
}
