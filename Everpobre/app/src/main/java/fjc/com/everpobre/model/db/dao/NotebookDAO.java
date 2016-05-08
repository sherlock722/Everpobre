package fjc.com.everpobre.model.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import fjc.com.everpobre.model.Notebook;
import fjc.com.everpobre.model.Notebooks;
import fjc.com.everpobre.model.db.DBConstants;
import fjc.com.everpobre.model.db.DBHelper;

public class NotebookDAO {


    //id invalido Anulo el id con valor 0
    private static final long INVALID_ID_DELETE_ALL_RECORDS = 0;

    //Nombre de la bbdd
    //private final String databaseName;

	//Contexto con un WeakReference para que sea una referencia débil del contexto
    //private WeakReference<Context> context;
    // private Context context;

    private DBHelper db;

	public NotebookDAO() {

        //this("Notebooks.sqlite", context);
        db = DBHelper.getInstance();
	}

	/*public NotebookDAO(String databaseName, Context context) {
		this.context = new WeakReference<Context>(context);
		this.databaseName = databaseName;
	}*/

	public long insert(Notebook notebook) {

        if (notebook == null) {

            //Excepcion de tipo RUNTIME (Tiempo de ejecución)
            //Al ser de tipo RUNTIME no la tengo que declararla ni hacer un try catch
			throw new IllegalArgumentException("Passing NULL notebook, imbecile");
		}

        //El contexto no es nulo
		/*if (context.get() == null) {
			throw new IllegalStateException("Context NULL");
		}*/

		// insert
        //Creo una instancia del DBHelper (Singleton)
		//DBHelper db = DBHelper.getInstance();

		//Hacemos conexion de sólo escritura
        //Utilizar la conexión que menos sobrecarga suponga
        //El null que aparece en el insert es el nullColumnHack - Sirve para abrir huecos en la tabla
        long id = db.getWritableDatabase().insert(DBConstants.TABLE_NOTEBOOK, null, this.getContentValues(notebook));
		notebook.setId(id);
		db.close();
		db=null;

		return id;
	}

	public int update(long id, Notebook notebook) {
		if (notebook == null) {
			throw new IllegalArgumentException("Passing NULL radar, imbecile");
		}

        //Compruebo que el id es 0 o negativo
        if (id < 1){
            throw new IllegalArgumentException("Passing id invalid, imbecile");
        }

		/*if (context.get() == null) {
			throw new IllegalStateException("Context NULL");
		}*/

		//DBHelper db = DBHelper.getInstance();

        //Como escribir los where (2 formas)
		//int numberOfRowsUpdated = db.getWritableDatabase().update(TABLE_NOTEBOOK, this.getContentValues(notebook), KEY_NOTEBOOK_ID + "=" + id, null);
        //int numberOfRowsUpdated = db.getWritableDatabase().update(TABLE_NOTEBOOK, this.getContentValues(notebook), KEY_NOTEBOOK_ID + "=? AND xxx=?", new String[]{"" + id});
        //Con esta linea evitamos SQL Inyection
        //Array de string (Marcador posicional) y es mas eficiente
        int numberOfRowsUpdated = db.getWritableDatabase().update(DBConstants.TABLE_NOTEBOOK, this.getContentValues(notebook), DBConstants.KEY_NOTEBOOK_ID + "=?", new String[]{"" + id});

        db.close();
		db=null;
		return numberOfRowsUpdated;
	}

    //Borra un registro
	public void delete(long id) {
		//DBHelper db = DBHelper.getInstance();

		if (id == INVALID_ID_DELETE_ALL_RECORDS) {
			db.getWritableDatabase().delete(DBConstants.TABLE_NOTEBOOK,  null, null); //Sin where
		} else {
			db.getWritableDatabase().delete(DBConstants.TABLE_NOTEBOOK, DBConstants.KEY_NOTEBOOK_ID + " = " + id, null); //null marcador de posición
		}
		db.close();
		db=null;
	}

    //Borrar la tabla

	public void deleteAll() {

        delete(INVALID_ID_DELETE_ALL_RECORDS);
	}

    //Genero sus contentValues
	public static ContentValues getContentValues(Notebook notebook) {
		ContentValues content = new ContentValues();
		content.put(DBConstants.KEY_NOTEBOOK_NAME, notebook.getName());
		//content.put (DESCRIPTION,notebook.getDescription());
		return content;
	}


	// convenience method
	public static @NonNull Notebook elementFromCursor(final @NonNull Cursor c) { //el final es para que el cursor no se modifique

        //Los assert solamente estan en los build de debug cuando están activas
        //Sirven para comprobar que, en este caso, el cursor no es nulo
        //Se tienen que activar
        //Lanza un error que se llama AssertionError
        assert c != null;

        /*Métodos de los cursores (Cursor)
        getDouble (int),g etString(int),
        */

		//Hacemos los Objetos Inmutables
        //
        final String name = c.getString(c.getColumnIndex(DBConstants.KEY_NOTEBOOK_NAME));
		final long id = c.getLong(c.getColumnIndex(DBConstants.KEY_NOTEBOOK_ID));

		final Notebook notebook = new Notebook(id, name);
		return notebook;
	}

	/**
	 * Returns all notebook in DB inside a Cursor
	 * @return cursor with all records
	 */

	public Cursor queryCursor() {
		// select
		//DBHelper db = DBHelper.getInstance();

		Cursor c = db.getReadableDatabase().query(DBConstants.TABLE_NOTEBOOK,
                                                      DBConstants.allColumns,
                                                            null, //selection (where)
                                                            null, //selectionArgs
                                                            null, //group by
                                                            null, //having
                                                            DBConstants.KEY_NOTEBOOK_ID);//order by (conviene pasarlo)
		return c;
	}

    //Devuelve la lista de Notebook
	public Notebooks query() {

        List<Notebook> notebookList = new LinkedList<>();

		Cursor cursor = queryCursor();
		if (cursor != null && cursor.getCount() > 0) {
			cursor.moveToFirst();//Me muevo al beforeFirst del Cursor
			do {
				//El método notebookFromCursor lo que hace es crearme un objeto Notebook
                //de mi modelo a partir de los datos recuperados en el cursor
                Notebook notebook = elementFromCursor(cursor);
				notebookList.add(notebook);
			} while (cursor.moveToNext());
		}

        Notebooks notebooks = Notebooks.createNotebooks(notebookList);

        return notebooks;
	}

	/**
	 * Returns a Radar object from its id
	 * @param id - the radar id in db
	 * @return Radar object if found, null otherwise
	 */

	public @Nullable Notebook query(long id) {
		Notebook notebook = null;

		//DBHelper db = DBHelper.getInstance();

		String where = DBConstants.KEY_NOTEBOOK_NAME + "=" + id;
		Cursor c = db.getReadableDatabase().query(DBConstants.TABLE_NOTEBOOK, DBConstants.allColumns, where, null, null, null, null);
		if (c != null) {
			if (c.getCount() > 0) {
				c.moveToFirst();
				notebook = elementFromCursor(c);
			}
            c.close();
		}
		db.close();
		return notebook;
	}

}
