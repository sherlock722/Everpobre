package fjc.com.everpobre.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import fjc.com.everpobre.Constants;
import fjc.com.everpobre.R;
import fjc.com.everpobre.activities.AddEditNotebookActivity;
import fjc.com.everpobre.adapters.NotebookAdapter;
import fjc.com.everpobre.model.Notebook;
import fjc.com.everpobre.model.Notebooks;
import fjc.com.everpobre.model.db.dao.NotebookDAO;

public class NotebooksFragment extends Fragment {

    //Inyecta la vista el Bind
    @Bind(R.id.activity_notebook_recycler_view)
    RecyclerView notebookRecyclerView;


    public NotebooksFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notebooks, container, false);

        //Referencia al recyclerView
        ButterKnife.bind(this, view);

        //Los RecyclerView necesitan tener un LayoutManager (utilizamos el LinearLayout)
        //Con esta linea sabe que tiene que mostrar las celdas de manera lineal
        notebookRecyclerView.setLayoutManager(new LinearLayoutManager((getActivity())));

        //Necesito un adaptador (el contexto se obtiene de getActivity)
        //Creo un DAO
        NotebookDAO notebookDAO = new NotebookDAO();
        //Creo un Notebooks a partir del DAO
        Notebooks notebooks = notebookDAO.query();

        //Adaptador
        NotebookAdapter adapter = new NotebookAdapter(notebooks, getActivity());

        //Recojo el click ejecutado sobre las filas del ReciclerView
        adapter.setOnElementClickListener(new NotebookAdapter.OnElementClick<Notebook>() {
            @Override
            public void clickedOn(Notebook notebook, int position) {

                Log.d("Clicker", "Clicker");

                //Se llama a una nueva actividad pasandole el contexto
                Intent i = new Intent(getActivity(), AddEditNotebookActivity.class);
                i.putExtra(Constants.FJC_COM_EVERPOBRE_NOTEBOOKTOEDIT, notebook);
                startActivity(i);

            }
        });

        notebookRecyclerView.setAdapter(adapter);


        return view;
    }

}
