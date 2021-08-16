import java.time.*;
/**
 * Clase para crear las instancias PCR
 */
public class PCR extends Prueba
{
    private LocalDate fechaValidez;
    
    public PCR(String nombrePaciente, String nombreEnfermero, String nombreTecnico){
        super(nombrePaciente, nombreEnfermero, nombreTecnico);
        fechaValidez = getFechaPrueba().plusDays(15);
        addNombrePrueba("pcr");
    }
    
    /**
     * Devuelve la fecha en la que la validez de la prueba caduca
     * @return: fechaCaducidad
     */
    public String getFechaValidez(){
        return fechaValidez.toString();
    }
}
