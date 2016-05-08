package fjc.com.everpobre;

import android.app.Application;
import android.util.Log;

import fjc.com.everpobre.model.db.DBConstants;
import fjc.com.everpobre.model.db.DBHelper;

/**
 * Created by javier on 4/5/16.
 */
public class EverpobreApp extends Application{

    public static final String TAG = EverpobreApp.class.getName();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"Hello Word!");

        //Inicializo la bbdd
        DBHelper.configure(DBConstants.DBNAME, getApplicationContext());
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
