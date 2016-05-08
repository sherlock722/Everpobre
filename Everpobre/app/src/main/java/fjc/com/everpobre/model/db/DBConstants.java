package fjc.com.everpobre.model.db;

/**
 * Created by javier on 8/5/16.
 */
public class DBConstants {

    //Nombre de la tabla
    public static final String TABLE_NOTEBOOK = "NOTEBOOK";

    // Table field constants
    //Campos de la tabla
    public static final String KEY_NOTEBOOK_ID = "_id";
    public static final String KEY_NOTEBOOK_NAME = "name";
    //public static final String KEY_NOTEBOOK_DESCRIPTION = "description";

    //Array con los nombres de las columnas
    public static final String[] allColumns = {
            KEY_NOTEBOOK_ID,
            KEY_NOTEBOOK_NAME,
            //KEY_RADAR_DESCRIPTION
    };

    //Script de creación de la tabla de bbdd
    public static final String SQL_CREATE_RADAR_TABLE =
            "create table " + TABLE_NOTEBOOK
                    + "( "
                    + KEY_NOTEBOOK_ID + " integer primary key autoincrement, "
                    + KEY_NOTEBOOK_NAME + " text not null"
                    //+ KEY_NOTEBOOK_DESCRIPTION + " text not null"
                    + ");";

    public static final String DBNAME = "Everpobre.sqlite";
}
