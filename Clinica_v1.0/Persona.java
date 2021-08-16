import java.io.Serializable;

/**
 * Clase que sirve como herencia para las distintas clases que representan las personas que se almacenan y hacen uso del programa
 */
public class Persona implements Serializable
{
    private String nombre;
    public Persona(String nombre, String apellido1, String apellido2){
        this.nombre = apellido1 + " " + apellido2 + ", " + nombre;
    }
    
    /**
     * Devuelve el nombre de la persona
     * @return: nombre
     */
    protected String getNombre(){
        return nombre.toLowerCase();
    }
    
    /**
     * Cambia el nombre de la persona
     */
    public void setNombre(String nombre, String apellido1, String apellido2){
        this.nombre = apellido1 + " " + apellido2 + ", " + nombre;
    }
}
