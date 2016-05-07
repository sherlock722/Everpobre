package fjc.com.everpobre.model;

import android.test.AndroidTestCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by javier on 7/5/16.
 */
public class NotebooksTest extends AndroidTestCase {

    private Notebooks mNotebooks;

    //Método que se ejecuta antes de los test
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        Notebook n1 = new Notebook(1,"n1");
        Notebook n2 = new Notebook(2,"n2");

        //Vamos a construir un Array con los Notebook anteriores
        Notebook[] arrayNotebook ={n1,n2};

        //A partir del Array arrayNotebook vamos a construir una lista
        List<Notebook> notebookList = Arrays.asList(arrayNotebook);


        //Me creo el Notebooks a partir de la lista
        mNotebooks = Notebooks.createNotebooks(notebookList);
    }

    @org.junit.Test
    public void testCreateNotebooks() throws Exception {

        //Comprobamos que existe
        assertNotNull(mNotebooks);
    }

    @org.junit.Test
    public void testSize() throws Exception {

        //Comprobamos que la lista tienen dos elementos
        assertEquals(mNotebooks.size(),2);

    }

    @org.junit.Test
    public void testGet() throws Exception {

        //Comprobamos que el name del primer elemento es n1
        assertEquals(mNotebooks.get(0).getName(),"n1");

    }

    @org.junit.Test
    public void testRemove() throws Exception {

    }

    @org.junit.Test
    public void testGetNotebooks() throws Exception {

    }
}