/**
 * Clase para crear las instancias Moderna
 */
public class Moderna extends Vacuna
{
    public Moderna(String nombrePaciente, String nombreEnfermero){
        super(nombrePaciente, nombreEnfermero);
        setNombreVacuna("Moderna");
    }
}
