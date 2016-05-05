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


    private void add(Notebook n) {
        getNotebooks().add(n);
    }

    //Leasy getter
    public List<Notebook> getNotebooks(){
        if (this.notebooks == null){
            this.notebooks = new LinkedList<Notebook>();
        }
        return this.notebooks;
    }


}
