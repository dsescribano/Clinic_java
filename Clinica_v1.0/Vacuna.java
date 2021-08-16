import java.time.*;
import java.io.Serializable;

/**
 * Clase que sirve como herencia para
 * las clases que implementarán los distintos
 * tipos de vacunas
 */
public class Vacuna implements Serializable
{
    private String nombreVacuna;
    private LocalDate fechaVacuna; //fecha en la que la vacuna es administrada
    private String nombrePaciente;
    private String nombreEnfermero; //enfermero que la administra
    
    public Vacuna(String nombrePaciente, String nombreEnfermero){
        this.nombrePaciente = nombrePaciente;
        this.nombreEnfermero = nombreEnfermero;
        fechaVacuna = LocalDate.now();
    }
    
    /**
     * Establece el nombre de la vacuna creada
     * @param nombreVacuna
     */
    protected void setNombreVacuna(String nombreVacuna){
        this.nombreVacuna = nombreVacuna;
    }
    
    /**
     * Obtiene el nombre de la vacuna
     */
    public String getNombreVacuna(){
        return nombreVacuna.toLowerCase();
    }
    
    /**
     * Devuelve el nombre del paciente
     */
    public String getNombrePaciente(){return nombrePaciente.toLowerCase();}
    
    /**
     * Devuelve la fecha de administración de la vacuna
     */
    public String getFechaVacuna(){return fechaVacuna.toString();}
    
    /**
     * Devuelve el nombre del enfermero que administra la vacuna
     */
    public String getNombreEnfermero(){return nombreEnfermero.toLowerCase();}
}
