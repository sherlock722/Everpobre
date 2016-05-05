package fjc.com.everpobre.model;

/**
 * Created by javier on 5/5/16.
 */
public class Notebook {
    public static final String DEFAULT_NAME = "John Doe";
    private int id;
    private String name;

    public Notebook(int id, String name) {

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
