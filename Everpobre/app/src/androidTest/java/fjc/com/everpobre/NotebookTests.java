package fjc.com.everpobre;

import android.test.AndroidTestCase;

import fjc.com.everpobre.model.Notebook;

/**
 * Created by javier on 5/5/16.
 */
public class NotebookTests extends AndroidTestCase{

    public static final String NOTEBOOK_TITLE = "notebook_title";

    //Testear que se crea un notebook
    public void testCanCreateANotebook(){

        //Utilizamos sut (para variables de testeo)
        final Notebook sut = new Notebook(1, NOTEBOOK_TITLE);

        //Puedo crear un notebook
        assertNotNull(sut);
        //Comprobar el título
        assertEquals(NOTEBOOK_TITLE,sut.getName());
        //Comprobar el id
        assertEquals(1,sut.getId());
    }

    //Testear que se crea un notebook con el nombre siempre relleno y si no lo lleva ponemos uno por defecto
    public void testCreatingANotebookWithEmptyOrNullNameSetsDefaultName(){

        final Notebook sut = new Notebook(1, null);
        assertEquals(Notebook.DEFAULT_NAME, sut.getName());

    }

}
