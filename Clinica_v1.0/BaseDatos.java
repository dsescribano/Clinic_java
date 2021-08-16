import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashMap;
import java.io.Serializable;

/**
 * Clase que contiene todas las bases de datos de la clínica
 */
public class BaseDatos implements Serializable
{
    //Listas de personas
    private ArrayList<Paciente> pacientes; 
    private ArrayList<Enfermero> enfermeros; 
    private ArrayList<TecnicoDeLaboratorio> tecnicos; 
    //HashMap de pruebas
    private HashMap<String, PCR> PCRsRealizadas;
    private HashMap<String, Antigenos> antigenosRealizadas;
    private HashMap<String, Serologico> serologicosRealizadas;
    //Lista de vacunas
    private ArrayList<Vacuna> vacunasAdministradas;
    
    public BaseDatos(){
        pacientes = new ArrayList<>();
        enfermeros = new ArrayList<>();
        tecnicos = new ArrayList<>();
        PCRsRealizadas = new HashMap<>();
        antigenosRealizadas = new HashMap<>();
        serologicosRealizadas = new HashMap<>();
        vacunasAdministradas = new ArrayList<>();
    }
    
    //Métodos para obtener las listas de personas
    /**
     * Método para obtener la lista pacientes
     * @return: pacientes
     */
    public ArrayList getPacientes(){
        return pacientes;
    }
    
    /**
     * Método para obtener la lista efermeros.
     * @return: enfermeros
     */
    public ArrayList getEnfermeros(){
        return enfermeros;
    }
    
    /**
     * Método para obtener la lista de tecnicos
     * @return: tecnicos
     */
    public ArrayList getTecnicos(){
        return tecnicos;
    }
    
    //Métodos para añadir o quitar personas de la base de datos
    /**
     * Método para añadir un subtipo de persona a una de las litas.
     * @param: persona, lista
     * @post: persona añadida en lista
     */
    public void addPersona(Persona persona, ArrayList<Persona> lista){
        lista.add(persona);
    }
    
    /**Método para quitar una persona de una lista
     * @param: nombre, lista
     * @post: eliminada la persona con el nombre de la lista
     */
    public void deletePersona(String nombre, ArrayList<Persona> lista){
        //Primero comprueba si la lista está vacía
        if(lista.size() >= 1){
            boolean nombreExiste = false;
            //comprueba si el nombre introducido está en la lista
            for(Persona persona: lista){
                if(persona.getNombre().equals(nombre)){
                    nombreExiste = true;
                }
            }
            //si el nombre no está en la lista imprime el error
            if(!nombreExiste){
                System.out.println("El nombre que ha introducido no se encuentra en la lista");
            }
            //si el nombre existe en la lista lo borra
            else{
                for(int n = 0; n <= lista.size() - 1; n++){
                    if(lista.get(n).getNombre().equals(nombre)){
                        lista.remove(n);
                        n = lista.size(); 
                    }
                }
            }
        }
        //si la lista está vacía imprime el error
        else{
            System.out.println("La lista a la que quiere acceder esta vacia");
        }
    }
    
    //Métodos para buscar pesonas en la base de datos
    /**
     * Busca y devuelve un enfermero de la base de datos
     * @param: nomber, lista
     * @return: Persona persona
     */
    public Enfermero buscarEnfermero(String nombre){
        int posicionLista = 0;
        for(int n = 0; n <= enfermeros.size() - 1; n++){
            if(enfermeros.get(n).getNombre().equals(nombre)){
                posicionLista = n;                    
                n = enfermeros.size();
            }
        }
        return enfermeros.get(posicionLista);
    }
    
    /**
     * Busca y devuelve un tecnico de la base de datos
     * @param: nomber, lista
     * @return: Persona persona
     */
    public TecnicoDeLaboratorio buscarTecnico(String nombre){
        int posicionLista = 0;
        for(int n = 0; n <= tecnicos.size() - 1; n++){
            if(tecnicos.get(n).getNombre().equals(nombre)){
                posicionLista = n;                    
                n = tecnicos.size();
            }
        }
        return tecnicos.get(posicionLista);
    }
    
    /**
     * Busca y devuelve un paciente de la base de datos
     * @param: nomber, lista
     * @return: Persona persona
     */
    public Paciente buscarPaciente(String nombre){
        int posicionLista = 0;
        for(int n = 0; n <= pacientes.size() - 1; n++){
            if(pacientes.get(n).getNombre().equals(nombre)){
                posicionLista = n;                    
                n = pacientes.size();
            }
        }
        return pacientes.get(posicionLista);
    }
    
    /**
     * Comprueba si existe una persona en una lista concreta
     * @param: nombre, lista
     * @return: true si existe la pesona o false si no existe
     */
    public boolean existePersona(String nombre, ArrayList<Persona> lista){
        boolean personaEncontrada = false;
        for(int n = 0; n <= lista.size() - 1; n++){
            if(lista.get(n).getNombre().equals(nombre)){
                n = lista.size();
                personaEncontrada = true;
            }
        }
        return personaEncontrada;
    }
    
    /**
     * Devuelve una lista con todos los pacientes que tienen asignado a un sanitario concreto
     * @param: nombreSanitario, tipoSanitario
     * @post: lista creada con los pacientes
     */
    private ArrayList<Paciente> listaPacienteXSanitario(String nombreSanitario, String tipoSanitario){
        //Lista que será devuelta con los pacientes de dicho sanitario
        ArrayList<Paciente> lista = new ArrayList<>();
        if(tipoSanitario.equals("enfermero") || tipoSanitario.equals("enfermera")){
            for(Paciente paciente: pacientes){
                if(paciente.getEnfermeroVacuna().equals(nombreSanitario) || paciente.getEnfermeroPrueba().equals(nombreSanitario)){
                    lista.add(paciente);
                }
            }
        }
        else if(tipoSanitario.equals("tecnico")){
            for(Paciente paciente: pacientes){
                if(paciente.getTecnicoAsignado().equals(nombreSanitario)){
                    lista.add(paciente);
                }
            }
        }
        return lista;
    }
    
    /**
     * Imprime una lista de los pacientes asociados a un enfermero o un tecnico
     * @pre: tenico y enfermero existen
     * @param: nombreSanitario y tipoSanitario
     * @post: imprime lista pacientes
     */
    public void printListaXSanitario(String nombreSanitario, String tipoSanitario){
        if(tipoSanitario.equals("enfermero")){
            if(existePersona(nombreSanitario, getEnfermeros())){
                for(Paciente paciente: listaPacienteXSanitario(nombreSanitario,tipoSanitario)){
                    System.out.println("Nombre: " + paciente.getNombre() + ". Fecha cita prueba: " + paciente.getFechaCitaPrueba() + ". Prueba solicitada: " + paciente.getPruebaSolicitada() + ". Fecha cita vacunacion: " + paciente.getFechaCitaVacuna() + ". Vacuna asignada: " + paciente.getVacunaAsignada() + ". Fecha final cuarentena: " + paciente.getFechaFinCuarentena() + ". Vacunas administradas: " + paciente.getVacunas() + ". Inmunizado: " + paciente.getInmunizacion() + ".");
                }
            }
            else{System.out.println("Los datos del sanitario introducidos no son correctos");}
        }
        else if(tipoSanitario.equals("tecnico")){
            if(existePersona(nombreSanitario, getTecnicos())){
                for(Paciente paciente: listaPacienteXSanitario(nombreSanitario,tipoSanitario)){
                    System.out.println("Nombre: " + paciente.getNombre() + ". Fecha cita prueba: " + paciente.getFechaCitaPrueba() + ". Prueba solicitada: " + paciente.getPruebaSolicitada() + ". Fecha cita vacunacion: " + paciente.getFechaCitaVacuna() + ". Vacuna asignada: " + paciente.getVacunaAsignada() + ". Fecha final cuarentena: " + paciente.getFechaFinCuarentena() + ". Vacunas administradas: " + paciente.getVacunas() + ". Inmunizado: " + paciente.getInmunizacion() + ".");
                }
            }
            else{System.out.println("Los datos del sanitario introducidos no son correctos");}
        }
        else{System.out.println("Tipo sanitario introducido no es correcto");}
    }
    
    //Métodos para añadir pruebas a las listas correspondientes
    /**
     * Añade PCR a la lista de PCRs realizadas
     */
    public void addPrueba(PCR pcr){
        String key = Integer.toString(PCRsRealizadas.size()+1);
        PCRsRealizadas.put(key, pcr);
    }
    
    /**
     * Añade Antigeno a la lista de antigenos realizadas
     */
    public void addPrueba(Antigenos antigenos){
        String key = Integer.toString(antigenosRealizadas.size()+1);
        antigenosRealizadas.put(key, antigenos);
    }
    
    /**
     * Añade PCR a la lista de PCRs realizadas
     */
    public void addPrueba(Serologico serologico){
        String key = Integer.toString(serologicosRealizadas.size()+1);
        serologicosRealizadas.put(key, serologico);
    }
    
    /**
     * Devuelve la lista de pruebas seleccionada
     * @return HashMap
     */
    public HashMap getPruebasRealizadas(String prueba){
        if(prueba.equals("pcr")){
            return PCRsRealizadas;
        }
        else if(prueba.equals("antigenos")){
            return antigenosRealizadas;
        }
        else if(prueba.equals("serologico")){
            return serologicosRealizadas;
        }
        else{return null;}
    }
    
    //Métodos para obtener una prueba concreta utilizando la key del HashMap
    /**
     * Devuelve una prueba
     */
    public PCR getPCR(String key){
        return PCRsRealizadas.get(key);
    }
    
    /**
     * Devuelve una prueba
     */
    public Antigenos getAntigeno(String key){
        return antigenosRealizadas.get(key);
    }
    
    /**
     * Devuelve una prueba
     */
    public Serologico getSerologico(String key){
        return serologicosRealizadas.get(key);
    }
    
    /**
     * Añade una vacuna a la lista vacunasAdministradas
     * @pre: el paciente no puede estar vacunado de todas las dosis
     * @post: añade vacuna a la matriz vacuna, establece si el paciente está ya vacunado de todas las dosis y elimina la cita de la vacuna
     *
     */
    public void setVacuna(String nombreVacuna, String nombrePaciente, String nombreEnfermero){ 
            if(nombreVacuna.toLowerCase().equals("johnson & johnson")){
                vacunasAdministradas.add(new JohnsonAndJohnson(nombrePaciente, nombreEnfermero));
            }
            else if(nombreVacuna.toLowerCase().equals("moderna")){
                vacunasAdministradas.add(new Moderna(nombrePaciente, nombreEnfermero));
            }
            else if(nombreVacuna.toLowerCase().equals("pfizer")){
                vacunasAdministradas.add(new Pfizer(nombrePaciente, nombreEnfermero));
            }
            else{
                System.out.println("El nombre de la vacuna introducida no es correcto");
            }
    }
    
    /**
     * Devuelve la lista de vacunas seleccionada
     */
    public ArrayList getVacunasAdministradas(){
        return vacunasAdministradas;
    }
}
