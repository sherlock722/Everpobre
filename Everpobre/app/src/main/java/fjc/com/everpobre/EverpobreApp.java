package fjc.com.everpobre;

import android.app.Application;
import android.util.Log;

import fjc.com.everpobre.model.Notebook;
import fjc.com.everpobre.model.db.DBConstants;
import fjc.com.everpobre.model.db.DBHelper;
import fjc.com.everpobre.model.db.dao.NotebookDAO;

/**
 * Created by javier on 4/5/16.
 */
public class EverpobreApp extends Application{

    public static final String TAG = EverpobreApp.class.getName();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "Hello Word!");

        //Inicializo la bbdd
        DBHelper.configure(DBConstants.DBNAME, getApplicationContext());

        //Cargamos la bbdd
        NotebookDAO notebookDAO = new NotebookDAO();

        for (int i=0; i<20; i++){
            Notebook notebook = new Notebook(i,"Notebook "+ i);
            notebookDAO.insert(notebook);
        }
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
