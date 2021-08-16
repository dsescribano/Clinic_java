import java.util.Iterator;
/**
 *Clase que sirve para crear las instancias Administrador
 */
public class Administrador extends Persona 
{
    public Administrador(String nombre, String apellido1, String apellido2){
        super(nombre, apellido1, apellido2);
    }
    
    /**
     * Método para dar de alta a una persona en el sistema, eligiendo la clase de persona que se va a añadir
     * @param: clasePersona: paciente, enfermero, tecnico; nombre, apellido1, apellido2, numeroTelefono
     * @post: crea una persona a partir de los parámetros y lo añade a la lista correspondiente
     */
    public void altaPersona(BaseDatos baseDatos, String clasePersona, String nombre, String apellido1, String apellido2){
        if(clasePersona.equals("paciente")){
            Paciente paciente = new Paciente(nombre, apellido1, apellido2);
            baseDatos.addPersona(paciente, baseDatos.getPacientes());
        }else if(clasePersona.equals("enfermero") || clasePersona.equals("enfermera")){
            Enfermero enfermero = new Enfermero(nombre, apellido1, apellido2);
            baseDatos.addPersona(enfermero, baseDatos.getEnfermeros());
        }else if(clasePersona.equals("tecnico")){
            TecnicoDeLaboratorio tecnico = new TecnicoDeLaboratorio(nombre, apellido1, apellido2);
            baseDatos.addPersona(tecnico, baseDatos.getTecnicos());
        }else{
            System.out.println("La clase de persona introducida no es correcta");
        }
    }
    
    /**
     * Cambia el nombre de la persona en el objeto por el introducido como parametro
     * @post nombre cambiado en el objeto persona seleccionado
     */
    public void corregirNombrePersona(BaseDatos baseDatos, String clasePersona, String nombrePaciente, String nombre, String apellido1, String apellido2){
        if(clasePersona.equals("paciente")){
            baseDatos.buscarPaciente(nombrePaciente).setNombre(nombre, apellido1, apellido2);
        }else if(clasePersona.equals("enfermero") || clasePersona.equals("enfermera")){
            baseDatos.buscarEnfermero(nombrePaciente).setNombre(nombre, apellido1, apellido2);
        }else if(clasePersona.equals("tecnico")){
            baseDatos.buscarTecnico(nombrePaciente).setNombre(nombre, apellido1, apellido2);
        }else{
            System.out.println("La clase de persona introducida no es correcta");
        }
    }
    
    /**
     * Método para borrar una persona de la lista
     * @param: clasePersona: paciente, enfermero, tecnico; nombre.
     * @post: borrado una persona de la lista
     */
    public void borrarPersona(BaseDatos baseDatos, String clasePersona, String nombre){
        if(clasePersona.equals("paciente")){
            baseDatos.deletePersona(nombre, baseDatos.getPacientes());
        }else if(clasePersona.equals("enfermero") || clasePersona.equals("enfermera")){
            baseDatos.deletePersona(nombre, baseDatos.getEnfermeros());
        }else if(clasePersona.equals("tecnico")){
            baseDatos.deletePersona(nombre, baseDatos.getTecnicos());
        }else{
            System.out.println("La clase de persona introducida no es correcta");
        }
    }
    
    /**
     * Método para asignar una cita a un paciente para hacerse una prueba
     * @pre: deben existir el paciente, el enfermero y el tecnico
     * @param: nombrePaciente, year, month, dayOfMonth, nombreEnfermero, nombreTecnico
     * @post: cita para prueba, nombre enfermero y nombre del tecnico añadidos en el paciente
     */
    public void setCitaPrueba(BaseDatos baseDatos, String nombrePaciente, String nombrePrueba, int year, int month, int dayOfMonth, String nombreEnfermero, String nombreTecnico){
        if(baseDatos.existePersona(nombrePaciente, baseDatos.getPacientes()) && baseDatos.existePersona(nombreEnfermero, baseDatos.getEnfermeros()) && baseDatos.existePersona(nombreTecnico, baseDatos.getTecnicos())){
            baseDatos.buscarPaciente(nombrePaciente).setFechaCitaPrueba(year, month, dayOfMonth);
            baseDatos.buscarPaciente(nombrePaciente).setEnfermeroPrueba(nombreEnfermero);
            baseDatos.buscarPaciente(nombrePaciente).setTecnicoSolicitado(nombreTecnico);
            baseDatos.buscarPaciente(nombrePaciente).setNombrePrueba(nombrePrueba);
        }else{System.out.println("Los datos introducidos no son correctos");}
    }
    
    /**
     * Método para asignar una cita a un paciente para hacerse una prueba
     * @pre: deben existir el paciente y el enfermero
     * @param: nombrePaciente, year, month, dayOfMonth, nombreEnfermero
     * @post: cita para prueba, nombre enfermero y nombre del tecnico añadidos en el paciente
     */
    public void setCitaVacuna(BaseDatos baseDatos, String nombrePaciente, String nombreVacuna, int year, int month, int dayOfMonth, String nombreEnfermero){
        if(baseDatos.existePersona(nombrePaciente, baseDatos.getPacientes()) && baseDatos.existePersona(nombreEnfermero, baseDatos.getEnfermeros())){
            baseDatos.buscarPaciente(nombrePaciente).setFechaCitaVacuna(year, month, dayOfMonth);
            baseDatos.buscarPaciente(nombrePaciente).setEnfermeroVacuna(nombreEnfermero);
            baseDatos.buscarPaciente(nombrePaciente).setNombreVacuna(nombreVacuna);
        }else{System.out.println("Los datos introducidos no son correctos");}
    }
    
    /**
     * Imprime una lista de los pacientes asociados a un enfermero
     */
    public void printPacientesEnfermero(BaseDatos baseDatos, String nombreSanitario){
        String tipoSanitario = "enfermero";
        baseDatos.printListaXSanitario(nombreSanitario, tipoSanitario);        
    }    
    
    /**
     * Imprime una lista de los pacientes asociados a un enfermero
     */
    public void printPacientesTecnico(BaseDatos baseDatos, String nombreSanitario){
        String tipoSanitario = "tecnico";
        baseDatos.printListaXSanitario(nombreSanitario, tipoSanitario);        
    }
    
    /**
     * Imprime una lista de los pacientes confinados
     */
    public void printPacientesConfinados(BaseDatos baseDatos){
        Iterator<Paciente> it = baseDatos.getPacientes().iterator();
        while(it.hasNext()){
            Paciente paciente = it.next();
            if(paciente.getPacienteContagiado()){
                System.out.println("Nombre: " + paciente.getNombre() + ". Fecha final de la cuarentena: " + paciente.getFechaFinCuarentena() + ".");
            }
        }
    }
}
