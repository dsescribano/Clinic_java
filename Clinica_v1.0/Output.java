import java.io.*;

/**
 * Genera el archivo txt en el que se guarda la base de datos del programa
 * y guarda la misma en dicho archivo
 */
public class Output
{
    private FileOutputStream file;
    private ObjectOutput output;
    
    public Output()
    {
        
    }
    
    /**
     * Crea el archivo txt en el que se guardan los datos
     * @post objetos file y output de las clases FileOutputStream y ObjectOutput creadas
     */
    public void abrir() throws IOException{
        file = new FileOutputStream("interfaz.txt");
        output = new ObjectOutputStream(file);
    }
    
    /**
     * Cierra el archivo baseDatos.txt cuando deja de ser usado
     */
    public void cerrar() throws IOException{
        if(output != null)
        output.close();
    }
    
    /**
     * Guarda el objeto baseDatos introducido como parámetro en el archivo baseDatos.txt
     */
    public void escribir(Interfaz interfaz) throws IOException{
        if(output != null)
        output.writeObject(interfaz);
    }
}
