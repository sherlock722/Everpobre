package fjc.com.everpobre.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import fjc.com.everpobre.R;
import fjc.com.everpobre.fragments.NotebooksFragment;

public class NotebookListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notebook_list);

        //Siempre usar getSupportFragmentManager (de la libreria de soporte)
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        if (fm != null){

            //Inyecto el fragmento NotebooksFragment
            NotebooksFragment notebooksFragment = new NotebooksFragment();
            fm.beginTransaction()
                    .add(R.id.activity_notebook_fragment_container,notebooksFragment)
                    .commit();
        }

    }
}
