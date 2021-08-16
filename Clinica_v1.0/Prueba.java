import java.time.*;
import java.io.Serializable;
/**
 * Clase de la que heredan los distintos
 * tipos de pruebas que se pueden realizar
 */
public class Prueba implements Serializable
{
    private LocalDate fechaPrueba;
    private String nombrePrueba;
    private String nombrePaciente;
    private String nombreEnfermero;
    private String nombreTecnico;
    private boolean positivo;
    private boolean procesada;
    
    public Prueba(String nombrePaciente, String nombreEnfermero, String nombreTecnico){
        this.nombrePaciente = nombrePaciente;
        this.nombreEnfermero = nombreEnfermero;
        this.nombreTecnico = nombreTecnico;
        fechaPrueba = LocalDate.now();
        procesada = false;
    }
    
    /**
     * Devuelve la fecha en la que se hizo la PCR
     * @return: fechaPrueba
     */
    protected LocalDate getFechaPrueba(){
        return fechaPrueba;
    }
    
    /**
     * Añade un nombre a la prueba
     */
    protected void addNombrePrueba(String nombrePrueba){this.nombrePrueba = nombrePrueba.toLowerCase();}
    
    /**
     * Devuelve el nombre de la prueba
     */
    protected String getNombrePrueba(){return nombrePrueba.toLowerCase();}
    
    /**
     * Devuelve el nombre del paciente
     */
    protected String getNombrePaciente(){return nombrePaciente.toLowerCase();}
    
    /**
     * Devuelve el nombre del enfermero
     */
    protected String getNombreEnfermero(){return nombreEnfermero.toLowerCase();}
    
    /**
     * Devuelve el nombre del tecnico
     */
    protected String getNombreTecnico(){return nombreTecnico.toLowerCase();}
    
    /**
     * Establece el resultado de la prueba
     */
    protected void setPositivo(boolean positivo){this.positivo = positivo;}
    
    /**
     * Devuelve el resultado de la prueba
     */
    protected String getPositivo(){
        if(positivo){return "si";}
        else{return "no";}
    }
    
    /**
     * Establece si la prueba ha sido procesada por le tecnico o no
     */
    public void setProcesada(boolean procesada){this.procesada = procesada;}
    
    /**
     * Devuelve una cadena indicando si la prueba ha sido procesada o no
     */
    public String getProcesada(){
        if(procesada){return "si";}
        else{return "no";}
    }
}
