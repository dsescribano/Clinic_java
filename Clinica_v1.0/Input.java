import java.io.*;

/**
 * Obtiene la informacion almacenada en la base de datos del archivo baseDatos.txt
 */

public class Input
{
    private FileInputStream file;
    private ObjectInputStream input;
    
    public Input()
    {
    }
    
    /**
     * Abre el archivo txt en el que estan almacenados los datos de la base de datos
     * @post: objetos file e input creados
     * @throw: IOException
     */
    public void abrir() throws IOException{
        file = new FileInputStream("interfaz.txt");
        input = new ObjectInputStream(file);
    }
    
    /**
     * Cierra el archivo txt
     * @throw: IOException
     */
    public void cerrar() throws IOException{
        if(input != null)
        input.close();
    }
    
    /**
     * Obtiene la informacion guardada en el archivo txt y la devuelve como un objeto de la clase BaseDatos
     * @return: baseDatosGuardada
     * @throw: IOException, ClassNotFoundException
     */
    public Interfaz leer() throws IOException{
        Interfaz interfazGuardada = null;
        if(input != null){
            try{
                interfazGuardada = (Interfaz) input.readObject();
            }
            catch (java.lang.ClassNotFoundException cnfe)
            {
            cnfe.printStackTrace();
            }
            catch(EOFException eof){
                //Fin del fichero
            }
        }
        return interfazGuardada;
    }
}
