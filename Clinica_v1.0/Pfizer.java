
/**
 *Clase para crear las instancias Pfizer
 */
public class Pfizer extends Vacuna
{
    public Pfizer(String nombrePaciente, String nombreEnfermero){
        super(nombrePaciente, nombreEnfermero);
        setNombreVacuna("Pfizer");
    }
}
