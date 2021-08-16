import java.util.Scanner;
import java.io.Serializable;

/**
 * Lee la entrada del usuario y la interpreta como un comando. 
 * Devuelve el comando como un objeto.
 * 
 * Si el comando analizado no se encuentra entre los existentes devuelve un
 * objeto comando vacio
 */
public class Analizador implements Serializable
{
    private Comandos comandos;  // guarda un comando         // guarda la entrada del usuario

    /**
     * Create a parser to read from the terminal window.
     */
    public Analizador() 
    {
        comandos = new Comandos();
    }
    
    /**
     * Obtiene el comando introducido por el usuario
     * @return el siguiente comando
     */
    public String getComando(){
        String lineaEntrada;   // guarda la entrada del usuario
        String cadena = null;  //guarda la entrada en forma de cadena para ser usada como comando
        Scanner lector = new Scanner(System.in);

        System.out.print("> ");     

        lineaEntrada = lector.nextLine();

        //Encuentra las palabras en la linea entrada
        Scanner tokenizer = new Scanner(lineaEntrada);
        if(tokenizer.hasNext()) {
            cadena = tokenizer.next();
            if(tokenizer.hasNext()){
                cadena = cadena + " " + tokenizer.next();
            }
        }
        return cadena;
    }
    
    /**
     * Analiza si el comando pertenece a los comandos del administrador
     * @return comando
     */
    public String getComandoAdministrador() 
    {
        String comando = getComando();

        // Si la cadena es conocida crea un comando
        if(comandos.esComandoAdministrador(comando)) {
            return comando;
        }
        else {
            return null; 
        }
    }
    
    /**
     * Analiza si el comando pertenece a los comandos del enfermero
     * @return comando
     */
    public String getComandoEnfermero() 
    {
        String comando = getComando();

        // Si la cadena es conocida crea un comando
        if(comandos.esComandoEnfermero(comando)) {
            return comando;
        }
        else {
            return null; 
        }
    }
    
    /**
     * Analiza si el comando pertenece a los comandos del tecnico
     * @return comando.
     */
    public String getComandoTecnico() 
    {
        String comando = getComando();

        // Si la cadena es conocida crea un comando
        if(comandos.esComandoTecnico(comando)) {
            return comando;
        }
        else {
            return null; 
        }
    }

    /**
     * Imprime los comandos validos para el perfil administrador.
     */
    public void mostrarComandosAdministrador()
    {
        comandos.mostrarComandosAdministrador();
    }
    
    /**
     * Imprime los comandos validos para el perfil de enfermero.
     */
    public void mostrarComandosEnfermero()
    {
        comandos.mostrarComandosEnfermero();
    }
    
    /**
     * Imprime los comandos validos para el perfil de tecnico.
     */
    public void mostrarComandosTecnico()
    {
        comandos.mostrarComandosTecnico();
    }
}
