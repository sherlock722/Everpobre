package fjc.com.everpobre.model.db.dao;

import android.test.AndroidTestCase;

import fjc.com.everpobre.model.Notebook;
import fjc.com.everpobre.model.db.DBHelper;

/**
 * Created by javier on 8/5/16.
 */
public class NotebookDAOTest extends AndroidTestCase {

    public void testCanInsertNotebook(){

        //Creo una bbdd para los test
        DBHelper.configure("TestDB.sqlite",getContext());

        Notebook notebook = new Notebook(1,"test notebook");
        //En los test puedo recuperar el contexto con getContex()
        NotebookDAO notebookDAO = new NotebookDAO();

        long id=notebookDAO.insert(notebook);
        assertTrue(id > 0);

    }

}
