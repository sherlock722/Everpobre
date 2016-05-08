package fjc.com.everpobre.model.db;

import java.lang.ref.WeakReference;
import java.util.Date;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DBHelper extends SQLiteOpenHelper {

	public static final int DATABASE_VERSION = 1;

	//Array de Strings que contiene los scripts para crear las tablas de Notas, libretas , etc
	public static final String[] CREATE_DATABASE_SCRIPTS = {
			DBConstants.SQL_CREATE_RADAR_TABLE
	};

	//Aquí irian los scripts necesarios para actualizar la bbdd (alter table.....etc)
	public static final String DROP_DATABASE = "";

	private static DBHelper sInstance;
	private static String dbName;

	//Guardar un contexto con una referencia weak
	private static WeakReference<Context> weakContext;
	
	//Este constructor me crea el fichero de la bbdd la primera vez si no existe (crea la carpeta database)
	//Tambien recibe la versión DATABASE_VERSION. Si yo le paso una versión distinta del que ya
	//tenemos creado en disco, dispara el método OnUpdate()

	private DBHelper(String databaseName, Context context) {
		super(context, databaseName, null, DATABASE_VERSION);
	}

	//Creo una referencia al nombre de la bbdd y al contexto
	//Inicializa la base de datos
	public static void configure(final String databaseName, final Context context){
		dbName=databaseName;
		weakContext=new WeakReference<>(context);
	}

	//Este método getInstance junto con el constructor (private DBHelper) es un ejemplo del
	//patron de diseño Singleton ya que sólo me va a crear una instancia de la clase DBHelper
	public static DBHelper getInstance() {
	    if (dbName == null || weakContext == null){
			throw new IllegalStateException ("No database name provided, no context");
		}

	    // Use the application context, which will ensure that you
	    // don't accidentally leak an Activity's context.
	    // See this article for more information: http://bit.ly/6LRzfx
	    if (sInstance == null) {
	      sInstance = new DBHelper(dbName, weakContext.get().getApplicationContext());
	    }
	    return sInstance;
	 }


	//Metodo cuando se abre la bbdd
	@Override
	public void onOpen(SQLiteDatabase db) {
		super.onOpen(db); //antipatron call super: Tener que llamar al super del padre (como curiosidad)
		
		// called everytime a DB connection is opened. We activate foreing keys to have ON_CASCADE deletion

		//Esta sentencia sirve para activar los borrados en cascada (delete on cascade funcionen)
		db.execSQL("PRAGMA foreign_keys = ON"); //Para todos los API Level

		// if API LEVEL > 16, use this
		// db.setForeignKeyConstraintsEnabled(true);
	}

	//Metodo cuando se crea la bbdd
	@Override
	public void onCreate(SQLiteDatabase db) { //SQLiteDatabase = es una conexion contra la bbdd)
		//Llamo al metodo createDB
		createDB(db);
	}


	//Metodo cuando se actualiza la bbdd
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		switch (oldVersion) {
		case 1:
		// upgrades for version 1->2
			Log.i("DBHelper", "Migrating from V1 to V2");
			/*
			db.execSQL("alter table " + NoteDAO.TABLE_NOTE + " ADD COLUMN " + NoteDAO.KEY_NOTE_LATITUDE + " real;");
			db.execSQL("alter table " + NoteDAO.TABLE_NOTE + " ADD COLUMN " + NoteDAO.KEY_NOTE_LONGITUDE + " real;");
			db.execSQL("alter table " + NoteDAO.TABLE_NOTE + " ADD COLUMN " + NoteDAO.KEY_NOTE_HAS_COORDINATES + " integer;");
			db.execSQL("alter table " + NoteDAO.TABLE_NOTE + " ADD COLUMN " + NoteDAO.KEY_NOTE_ADDRESS + " text;");
*/
			break;
		case 2:
		// upgrades for version 2->3

		case 3:
		// upgrades for version 3->4
		}
	}
	// utility method to create DB
	private void createDB(SQLiteDatabase db) {
		//Recorre las cadenas de texto de la constante CREATE_DATABASE_SCRIPTS y los ejecuto (db.execSQL)
		for (String sql: CREATE_DATABASE_SCRIPTS) {
			db.execSQL(sql);
		}
	}
	
	// convenience methods to convert types
	//Métodos que vienen vienen bien para tratar los booleanos y las fechas en SQLite
	
	public static int convertBooleanToInt(boolean b) {
		return b ? 1 : 0;
	}
	
	public static boolean convertIntToBoolean(int b) {
		return b == 0 ? false : true;
	}
	
	
	public static Long convertDateToLong(Date date) {
	    if (date != null) {
	        return date.getTime();
	    }
	    return null;
	}
	
	public static Date convertLongToDate(Long dateAsLong) {
	    if (dateAsLong == null) {
	        return null;
	    }
	    return new Date(dateAsLong);
	}

}
