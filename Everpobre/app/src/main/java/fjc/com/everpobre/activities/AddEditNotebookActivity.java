package fjc.com.everpobre.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import fjc.com.everpobre.Constants;
import fjc.com.everpobre.R;
import fjc.com.everpobre.model.Notebook;


public class AddEditNotebookActivity extends AppCompatActivity {

    @Bind(R.id.activity_add_edit_notebook_save_notebook)
    Button saveNotebook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_notebook);

        //En el caso de una actividad se pasa this
        ButterKnife.bind(this);

        //Proceso el Intent que me viene del Fragment con un notebook como parametro
        processIntent (getIntent());

        //setOnClickListener
        saveNotebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAndFinish();
            }
        });
    }

    private void processIntent(Intent intent) {
         Notebook notebook = (Notebook)intent.getSerializableExtra(Constants.FJC_COM_EVERPOBRE_NOTEBOOKTOEDIT);
    }

    private void saveAndFinish() {
        //Se hace el save

        //Se hace el finish() y cierro la actividad
        finish();

    }


}
