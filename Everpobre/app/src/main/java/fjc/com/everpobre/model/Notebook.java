package fjc.com.everpobre.model;

import java.io.Serializable;

/**
 * Created by javier on 5/5/16.
 */
public class Notebook implements Serializable{
    public static final String DEFAULT_NAME = "John Doe";
    private long id;
    private String name;

    public Notebook(long id, String name) {

        if (name == null || name.isEmpty()){

            this.name = DEFAULT_NAME;

        }else{

            this.name = name;

        }
        this.id = id;
        //this.name = name;
    }

    private Notebook(){
        //Desactivo el constructor por defecto
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
