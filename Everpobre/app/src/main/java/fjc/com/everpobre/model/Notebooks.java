package fjc.com.everpobre.model;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by javier on 5/5/16.
 */
public class Notebooks {

    List<Notebook> notebooks;

    //factory static method
    public static Notebooks createNotebooks(List <Notebook> notebooks){

        Notebooks myNotebooks = new Notebooks();
        for (Notebook n: notebooks) {

            myNotebooks.add(n);

        }

        return myNotebooks;
    }

    private Notebooks() {
    }

    //Cuantos elementos tiene la lista
    public int size () {
        return getNotebooks().size();
    }

    //Devolver el Notebook de una posición de la lista
    public Notebook get (int index){
        return getNotebooks().get(index);
    }

    //Borra un Notebook de la lista
    public void remove (Notebook notebook){
        getNotebooks().remove(notebook);
    }

    //Metodo para añadir Notebook a la lista
    private void add(Notebook n) {
        getNotebooks().add(n);
    }

    //Leasy getter
    //Si la lista no esta creada la crea
    public List<Notebook> getNotebooks(){
        if (this.notebooks == null){
            this.notebooks = new LinkedList<Notebook>();
        }
        return this.notebooks;
    }


}
