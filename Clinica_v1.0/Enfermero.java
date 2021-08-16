import java.util.Iterator;
/**
 * Clase que sirve para crear las instancias enfermero
 */
public class Enfermero extends Persona
{
    public Enfermero(String nombre, String apellido1, String apellido2){
        super(nombre, apellido1, apellido2);
    }
    
    /**
     * Imprime una lista de todas las pruebas del tipo seleccionado que ha realizado el enfermero
     * @param: basedatos, nombrePrueba
     * @post: lista de las pruebas del tipo seleccionado hechas por el enfermero
     */
    public void printPruebasRealizadas(BaseDatos baseDatos, String nombrePrueba){
        //Comprueba los datos introducidos
        if(!baseDatos.getPruebasRealizadas(nombrePrueba.toLowerCase()).isEmpty()){
            for(int n = 1; n<= baseDatos.getPruebasRealizadas(nombrePrueba.toLowerCase()).size(); n++){
                String key = Integer.toString(n);
                //Imprime los datos de la prueas pcr
                if(nombrePrueba.toLowerCase().equals("pcr")){  
                    if(baseDatos.getPCR(key).getNombreEnfermero().equals(getNombre())){
                        System.out.println("Nombre: " + baseDatos.getPCR(key).getNombrePaciente() + ". Fecha cita prueba: " + baseDatos.getPCR(key).getFechaPrueba().toString() + ". Prueba solicitada: " + baseDatos.getPCR(key).getNombrePrueba() + ". Fecha final cuarentena: " + baseDatos.buscarPaciente(baseDatos.getPCR(key).getNombrePaciente()).getFechaFinCuarentena() + ".");
                    }
                }
                //Imprime los datos de las pruebas de antigenos
                else if(nombrePrueba.toLowerCase().equals("antigenos")){
                    if(baseDatos.getAntigeno(key).getNombreEnfermero().equals(getNombre())){
                        System.out.println("Nombre: " + baseDatos.getAntigeno(key).getNombrePaciente() + ". Fecha cita prueba: " + baseDatos.getAntigeno(key).getFechaPrueba().toString() + ". Prueba solicitada: " + baseDatos.getAntigeno(key).getNombrePrueba() + ". Fecha final cuarentena: " + baseDatos.buscarPaciente(baseDatos.getAntigeno(key).getNombrePaciente()).getFechaFinCuarentena() + ".");
                    } 
                }
                //Imprime los datos de los test serologicos
                else if(nombrePrueba.toLowerCase().equals("serologico")){
                    if(baseDatos.getSerologico(key).getNombreEnfermero().equals(getNombre())){
                        System.out.println("Nombre: " + baseDatos.getSerologico(key).getNombrePaciente() + ". Fecha cita prueba: " + baseDatos.getSerologico(key).getFechaPrueba().toString() + ". Prueba solicitada: " + baseDatos.getSerologico(key).getNombrePrueba() + ". Fecha final cuarentena: " + baseDatos.buscarPaciente(baseDatos.getSerologico(key).getNombrePaciente()).getFechaFinCuarentena() + ".");
                    }
                }
            }
        }
        else{System.out.println("No hay pruebas almacenadas");}
    }
    
    /**
     * Imprime las vacunas realizadas
     * @param baseDatos, nombreVacuna
     * @post lista impresa con las vacunas realizadas
     */
    public void printVacunasRealizadas(BaseDatos baseDatos, String nombreVacuna){
        if(!baseDatos.getVacunasAdministradas().isEmpty()){
            Iterator<Vacuna> it = baseDatos.getVacunasAdministradas().iterator();
            while(it.hasNext()){
                Vacuna vacuna = it.next();
                if(vacuna.getNombreEnfermero().equals(getNombre())){
                    System.out.println("Nombre: " + vacuna.getNombrePaciente() + ". Fecha administracion: " + vacuna.getFechaVacuna() + ". Vacuna administrada: " + vacuna.getNombreVacuna() + ".");
                }
            }
        }
        else{System.out.println("No hay vacunas almacenadas");}
    }
    
    /**
     * Realizar una prueba
     * @param baseDatos, nombrePrueba, nombrePaciente, nombreTecnico
     * @post crea un objeto de la prueba solicitada y lo almacena en la base de datos
     */
    public void realizarPrueba(BaseDatos baseDatos, String nombrePrueba, String nombrePaciente, String nombreTecnico){
        if(baseDatos.existePersona(nombrePaciente, baseDatos.getPacientes())  && baseDatos.existePersona(nombreTecnico, baseDatos.getTecnicos())){
            if(nombrePrueba.equals("pcr")){baseDatos.addPrueba(new PCR(nombrePaciente, getNombre(), nombreTecnico));}
            else if(nombrePrueba.equals("antigenos")){baseDatos.addPrueba(new Antigenos(nombrePaciente, getNombre(), nombreTecnico));}
            else if(nombrePrueba.equals("serologico")){baseDatos.addPrueba(new Serologico(nombrePaciente, getNombre(), nombreTecnico));}
        }
        else{System.out.println("Datos incorrectos");}
    }
    
    /**
     * Adminstra una vacuna
     * @param baseDatos, nombreVacuna, nombrePaciente
     * @post vacuna añadida a la lista vacunasAdministradas en la base de datos
     */
    public void administrarVacuna(BaseDatos baseDatos, String nombreVacuna, String nombrePaciente){
        if(baseDatos.existePersona(nombrePaciente, baseDatos.getPacientes())){
            baseDatos.setVacuna(nombreVacuna, nombrePaciente, getNombre());
            baseDatos.buscarPaciente(nombrePaciente).setVacuna(nombreVacuna);
            baseDatos.buscarPaciente(nombrePaciente).deleteVacunaAsignada();
        }
        else{System.out.println("Los datos introducidos no son corretos");}
    }
    
    /**
     * Imprime un listado de los pacientes asignados al enfermero
     */
    public void printListaPacientes(BaseDatos baseDatos){
        baseDatos.printListaXSanitario(getNombre(), "enfermero");
    }
}
