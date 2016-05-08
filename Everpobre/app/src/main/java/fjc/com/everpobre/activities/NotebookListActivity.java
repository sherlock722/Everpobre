package fjc.com.everpobre.activities;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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

            //Inyecto un fragmento
            NotebooksFragment notebooksFragment = new NotebooksFragment();
            fm.beginTransaction()
                    .add(R.id.activity_notebook_fragment_container,notebooksFragment)
                    .commit();
        }

    }
}
