package fjc.com.everpobre.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import fjc.com.everpobre.R;

/**
 * Created by javier on 8/5/16.
 */
public class NotebookRowViewHolder extends RecyclerView.ViewHolder {

    //El ViewHolder es un elemento de interfaz puro
    //Este tiene que pintar es el texto (name)
    private String notebookName;
    @Bind(R.id.row_notebook_notebook_name) TextView notebookNameTextView;

    //rowNotebook es una celda cargada
    //Una de sus vistas es un textView
    public NotebookRowViewHolder(View rowNotebook) {
        super(rowNotebook);

        //notebookNameTextView = (TextView) rowNotebook.findViewById(R.id.row_notebook_notebook_name);
        //La clase en la que estamos this
        ButterKnife.bind(this, rowNotebook); //Esta linea es igual que la anterior comentada

    }
    public void setNotebookName (String name){
        notebookNameTextView.setText(name);
    }

}
