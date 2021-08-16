import java.io.Serializable;

/**
 * Enumeracion de todos los comandos necesarios para el programa
 * Reconoce los comandos como validos o no
 */
public class Comandos implements Serializable
{
    // matriz que almacena los comandos de la pantalla principal de la interfaz
    private static final String[] comandosGeneral = {
        "quit"
    };
    // matriz para almacenar los comandos validos para el administrador
    private static final String[] comandosAdministrador = {
        "add paciente", "add tecnico", "add enfermero", "corregir paciente", 
        "corregir enfermero", "corregir tecnico", "cita prueba", "cita vacuna", 
        "pacientes enfermero", "pacientes tecnico", "pacientes confinados", 
        "logout", "help", "borrar paciente", "borrar enfermero", "borrar tecnico", "borrar sesion"
    };
    //matriz para almacenar los comandos validos para el enfermero
    private static final String[] comandosEnfermero = { 
        "lista pcr", "lista antigenos", "lista serologicas", 
        "lista pfizer", "lista moderna", "lista johnson", "lista pacientes", 
        "realizar pcr", "realizar antigenos", "realizar serologica", "administrar pfizer", 
        "administrar moderna", "administrar johnson", "logout", "help"
    };
    //comandos validos para el tecnico
    private static final String[] comandosTecnico = {
        "lista pruebas", "resultado pcr", 
        "resultado antigenos", "resultado serologico","logout", "help"
    };

    /**
     * Constructor - initialise the command words.
     */
    public Comandos()
    {
        
    }
    
    /**
     * Analiza si el comando introducido pertenece al administrador
     * @return true si pertenece o false si no
     */
    public boolean esComandoAdministrador(String string){
       boolean esComando = false;
       for(int i = 0; i < comandosAdministrador.length; i++) {
            if(comandosAdministrador[i].equals(string))
                esComando = true;
       }      
       return esComando;
    }
    
    /**
     * Analiza si el comando introducido pertenece al enfermero
     * @return true si pertenece o false si no
     */
    public boolean esComandoEnfermero(String string){
       boolean esComando = false;
       for(int i = 0; i < comandosEnfermero.length; i++) {
            if(comandosEnfermero[i].equals(string))
                esComando = true;
       }      
       return esComando;
    }
    
    /**
     * Analiza si el comando introducido pertenece al tecnico
     * @return true si pertenece o false si no
     */
    public boolean esComandoTecnico(String string){
       boolean esComando = false;
       for(int i = 0; i < comandosTecnico.length; i++) {
            if(comandosTecnico[i].equals(string))
                esComando = true;
       }      
       return esComando;
    }
    
    /**
     * Imprime todos los comandos pertenecientes al administrador
     */
    public void mostrarComandosAdministrador() 
    {
        for(String comando: comandosAdministrador) {
            System.out.print(comando + ",  ");
        }
        System.out.println();
    }
    
    /**
     * Imprime todos los comandos que pertenecen al enfermero
     */
    public void mostrarComandosEnfermero() 
    {
        for(String comando: comandosEnfermero) {
            System.out.print(comando + ",  ");
        }
        System.out.println();
    }
    
    /**
     * Imprime todos los comandos que pertenecen al tecnico
     */
    public void mostrarComandosTecnico() 
    {
        for(String comando: comandosTecnico) {
            System.out.print(comando + ",  ");
        }
        System.out.println();
    }
}