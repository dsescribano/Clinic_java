import java.io.*;
/**
 * Clase principal que contiene el método main() para iniciar el programa
 */

public abstract class Clinica
{
    private static Interfaz interfaz; 
    
    public Clinica(){
        
    }
    
    /**
     * Inicia el programa Clinica
     */
    public static void main(String[] args){
        interfaz = new Interfaz();
        File file = new File("interfaz.txt");
        
        if(file.exists()){
            interfaz = abrirSesion();
            interfaz.inicio();
        }else{
            interfaz.inicioAdministrador();
            guardarSesion();
            interfaz.inicio();       
        }
    }
    
    /**
     * Guarda los datos de la interfaz en un archivo txt
     */
    public static void guardarSesion(){
        Output salida; 
        salida = new Output();
        
        try{
            salida.abrir();
            salida.escribir(interfaz);
            salida.cerrar();
        }
        catch(IOException e){
            System.out.println("Sesion no guardada");
        }
    }
    
    /**
     * Almacena en la variable interfaz los datos guardados en el archivo txt
     */
    private static Interfaz abrirSesion(){
        Input entrada = new Input();
        Interfaz interfaz = new Interfaz();
        try{
            entrada.abrir();
            interfaz = entrada.leer();
            entrada.cerrar();
        }
        catch(IOException e){
            System.out.println("Fallo en el inicio de sesion");
        }
        return interfaz;
    }
}
