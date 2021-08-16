import java.util.Scanner;
import java.io.*;

/**
 * Crea la interfaz del programa para su uso por parte del usuario. 
 */
public class Interfaz implements Serializable
{
    private Analizador analizador; //procesa los comandos introducidos
    private BaseDatos baseDatos; //almacena todos los datos de personas, pruebas y vacunas
    private Administrador administrador; 
    private TecnicoDeLaboratorio tecnico;
    private Enfermero enfermero; //escanea los comandos introducidos en la interfaz
    private boolean acabar = false; //se utiliza para determinar si hay que finalizar el programa o no
    
    public Interfaz(){
        analizador = new Analizador();
        baseDatos = new BaseDatos();
    }
    
    //Ejecucion del programa
    
    /**
     * Permite registrar un administrador cuando el programa se ejecuta por primera vez 
     * @post: administrador = new Administrador()
     */
    public void inicioAdministrador(){
        String lineaEntrada1;   // guarda el nombre del administrador introducido
        String nombreAdministrador = null;
        String apellido1Administrador = null;
        String apellido2Administrador = null;
        Scanner lector = new Scanner(System.in);
        boolean acabar = false;
        
        try{
            System.out.println("Iniciado el registro del administrador");
                
            administrador = new Administrador(introducirNombre(), introducirApellido1(), introducirApellido2());
            System.out.println("Aministrador creado");
        }
        catch(Exception e){
            System.out.println("Los campos no pueden estar vacios");
        }
        
        System.out.println();
    }
    
    /**
     * Crea la primera pantalla de la interfaz en la que se pide el tipo de empleado y el nombre. Funciona en forma de bucle
     * hasta que se introduce el comando quit 
     */
    public void inicio(){
        String lineaEntrada = null;
        String accion = null;
        String tipoEmpleado = null;
        String nombreEmpleado = null;
        Scanner lector = new Scanner(System.in);
        
        
        
        while(!acabar){
                System.out.println("¿Qué desea hacer?");
                System.out.println(">");
                lineaEntrada = lector.nextLine();
                
                Scanner tokenizer = new Scanner(lineaEntrada);
                
                if(tokenizer.hasNext()) {
                        accion = tokenizer.next();
                        if(tokenizer.hasNext()){
                            accion = accion + " " + tokenizer.next();
                        }
                }
                
                if(accion.equals("help")){
                    System.out.println("abrir sesion," + " quit," + " help");
                }
                else if(accion.equals("abrir sesion")){
                    System.out.println("Tipo de empleado:");
                    System.out.print("> ");
                    lineaEntrada = lector.nextLine();
            
                    //Procesa la entrada 1
                    Scanner tokenizer1 = new Scanner(lineaEntrada);

                    if(tokenizer1.hasNext()) {
                        tipoEmpleado = tokenizer1.next();
                    }
                
                    if(tipoEmpleado == null){
                        System.out.println("Valor incorrecto\n");
                    }
                    
                    System.out.println("Nombre:");
                    System.out.println(">");
                    lineaEntrada = lector.nextLine();
                    System.out.println("");

                    //Procesa la entrada 2
                    Scanner tokenizer2 = new Scanner(lineaEntrada);
                    if(tokenizer2.hasNext()) {
                        nombreEmpleado = tokenizer2.next();
                        if(tokenizer2.hasNext()){
                            nombreEmpleado = nombreEmpleado + " " + tokenizer2.next() + " ";
                            if(tokenizer2.hasNext()){
                                nombreEmpleado = nombreEmpleado + tokenizer2.next();
                            }
                        }
                    }
                
                    // Ejecuta una instruccion dependiendo del tipo de empleado
                    if(tipoEmpleado.toLowerCase().equals("administrador")){
                        if(administrador.getNombre().toLowerCase().equals(nombreEmpleado.toLowerCase())){
                            interfazAdministrador();
                        }else{System.out.println("Nombre incorrecto.\n");}
                    }
                    else if(tipoEmpleado.toLowerCase().equals("enfermero")){
                        if(baseDatos.existePersona(nombreEmpleado, baseDatos.getEnfermeros())){
                            enfermero = baseDatos.buscarEnfermero(nombreEmpleado);
                            interfazEnfermero();
                        }else{System.out.println("El enfermero descrito no existe.\n");}
                    }
                    else if(tipoEmpleado.toLowerCase().equals("tecnico")){
                        if(baseDatos.existePersona(nombreEmpleado, baseDatos.getTecnicos())){
                            tecnico = baseDatos.buscarTecnico(nombreEmpleado);
                            interfazTecnico();
                        }else{System.out.println("El tecnico descrito no existe.\n");}
                    }
                    else{System.out.println("Empleado introducido incorrecto");}
                
                    tipoEmpleado = null;
                }
                else if(accion.equals("quit")){
                    acabar = true;
                }
            }
        
        System.out.println("Hasta pronto\n");
    }
        
    //Metodos administrador
    /**
     *  Permite ejecutar los comandos del administrador en loop hasta que se sale.
     */
    private void interfazAdministrador() 
    {            
        System.out.println("Ha iniciado sesion como administrador");
                
        boolean acabar = false;
        while (! acabar) {
            String comando = analizador.getComandoAdministrador();
            if(comando != null){
                acabar = procesarComandoAdministrador(comando);
            }else{System.out.println("Comando desconocido\n");}
        }
        System.out.println("Sesion cerrada.\n");
    }
    
    /**
     * Procesa los comandos introducidos como administrador
     */
    private boolean procesarComandoAdministrador(String comando){
        boolean salir = false;
        String fraseComando = comando;
        if (fraseComando.toLowerCase().equals("help")) {
            printHelpAdministrador();
            Clinica.guardarSesion();
        }
        else if (fraseComando.toLowerCase().equals("logout")) {
            salir = quit();
            Clinica.guardarSesion();
        }
        else if(fraseComando.toLowerCase().equals("add paciente")){
            addPaciente();
            Clinica.guardarSesion();
        }
        else if(fraseComando.toLowerCase().equals("add enfermero")){
            addEnfermero();
            Clinica.guardarSesion();
        }
        else if(fraseComando.toLowerCase().equals("add tecnico")){
            addTecnico();
            Clinica.guardarSesion();
        }
        else if(fraseComando.toLowerCase().equals("borrar paciente")){
            borrarPaciente();
            Clinica.guardarSesion();
        }
        else if(fraseComando.toLowerCase().equals("borrar enfermero")){
            borrarEnfermero();
            Clinica.guardarSesion();
        }
        else if(fraseComando.toLowerCase().equals("borrar tecnico")){
            borrarTecnico();
            Clinica.guardarSesion();
        }
        else if(fraseComando.toLowerCase().equals("corregir paciente")){
            corregirPaciente();
            Clinica.guardarSesion();
        }
        else if(fraseComando.toLowerCase().equals("corregir enfermero")){
            corregirEnfermero();
            Clinica.guardarSesion();
        }
        else if(fraseComando.toLowerCase().equals("corregir tecnico")){
            corregirTecnico();
            Clinica.guardarSesion();
        }
        else if(fraseComando.toLowerCase().equals("cita prueba")){
            citaPrueba();
            Clinica.guardarSesion();
        }
        else if(fraseComando.toLowerCase().equals("cita vacuna")){
            citaVacuna();
            Clinica.guardarSesion();
        }
        else if(fraseComando.toLowerCase().equals("pacientes enfermero")){
            pacientesEnfermero();
            Clinica.guardarSesion();
        }
        else if(fraseComando.toLowerCase().equals("pacientes tecnico")){
            pacientesTecnico();
            Clinica.guardarSesion();
        }
        else if(fraseComando.toLowerCase().equals("pacientes confinados")){
            pacientesConfinados();
            Clinica.guardarSesion();
        }
        else if(fraseComando.toLowerCase().equals("borrar sesion")){
            borrarSesion();
            salir = true;
            acabar = true;
        }
        
        return salir;
    }
    
    /**
     * Borra la sesion guardada
     * @post: archivo interfaz.txt eliminado
     */
    private void borrarSesion(){
        File sesion = new File("interfaz.txt");
        if(sesion.exists()){
            if(sesion.delete()){
                System.out.println("Sesion borrada.");
            }
        }
    }
    
    /**
     * Imprime informacion de ayuda
     * @post: imprime los comandos del administrador
     */
    private void printHelpAdministrador() 
    {
        analizador.mostrarComandosAdministrador();
    }
    
    /**
     * Metodo para introducir datos en la interfaz que seran utilizados como comandos
     * @return: cadena introducida por entrada
     */
    private String introducirDato(){
        String lineaEntrada;
        String dato = null;
        Scanner lector = new Scanner(System.in);
        
        lineaEntrada = lector.nextLine();
        Scanner tokenizer = new Scanner(lineaEntrada);
        if(tokenizer.hasNext()) {
            dato = tokenizer.next();
            if(tokenizer.hasNext()){
                dato = dato + " " + tokenizer.next();
                if(tokenizer.hasNext()){
                    dato = dato + " " + tokenizer.next();
                }
            }
        }
        
        return dato;
    }
   
    /**
     * Metodo para introducir el nombre de una persona
     * @return: cadena introducida
     */
    private String introducirNombre(){
        String nombrePersona = null;
        
        //Pide y lee el nombre del paciente
        System.out.println("Nombre:");
        System.out.print("> ");
        
        nombrePersona = introducirDato();
        
        if(nombrePersona.equals(null)){
            throw new NullPointerException();
        }        
        
        return nombrePersona;
    }
    
    /**
     * Metodo para introducir el apellido1 de una persona
     * @return: cadena introducida
     */
    private String introducirApellido1(){
        String apellido1;
        
        //Pide y lee el primer apellido del paciente
        System.out.println("Primer apellido:");
        System.out.print("> ");
        
        apellido1 = introducirDato();
        
        if(apellido1.equals(null)){
            throw new NullPointerException();
        }
        
        return apellido1;
    }
    
    /**
     * Metodo para introducir el apellido2 de una persona
     * @return: cadena introducida
     */
    private String introducirApellido2(){
        String apellido2;
        
        //Pide y lee el segundo apellido del paciente
        System.out.println("Segundo apellido:");
        System.out.print("> ");
        
        apellido2 = introducirDato();
        
        if(apellido2.equals(null)){
            throw new NullPointerException();
        }
        
        return apellido2;
    }
    
    /**
     * Añade pacientes por parte del administrador
     * @param baseDatos, cadena "paciente", y metodos: introducirNombre(), introducirApellido1,
     * introducirApellido2.
     */
    private void addPaciente(){
        try{
            administrador.altaPersona(baseDatos, "paciente", introducirNombre(), introducirApellido1(), introducirApellido2());
            System.out.println("Paciente creado");
        }
        catch(Exception e){
            System.out.println("Los campos no pueden estar vacios");
        }
        
        System.out.println();
    }
    
    /**
     * Añade enfermeros por parte del administrador
     * @param baseDatos, cadena "enfermero", y metodos: introducirNombre(), introducirApellido1,
     * introducirApellido2.
     */
    private void addEnfermero(){
        try{
            administrador.altaPersona(baseDatos, "enfermero", introducirNombre(), introducirApellido1(), introducirApellido2());
            System.out.println("Enfermero creado");
        }
        catch(Exception e){
            System.out.println("Los campos no pueden estar vacios");
        }
        
        System.out.println();
    }
    
    /**
     * Añade tecnicos por parte del administrador
     * @param baseDatos, cadena "enfermero", y metodos: introducirNombre(), introducirApellido1,
     * introducirApellido2.
     */
    private void addTecnico(){
        try{
            administrador.altaPersona(baseDatos, "tecnico", introducirNombre(), introducirApellido1(), introducirApellido2());
            System.out.println("Tecnico creado");
        }
        catch(Exception e){
            System.out.println("Los campos no pueden estar vacios");
        }
        
        System.out.println();
    }
    
    /**
     * Borra un paciente de la base de datos
     * @post: paciente eliminado
     */
    private void borrarPaciente(){
        try{
            System.out.println("Nombre del paciente:");
            System.out.println(">");
            administrador.borrarPersona(baseDatos, "paciente", introducirDato().toLowerCase());
            System.out.println("Paciente borrado");
            System.out.println();
        }
        catch(Exception e){
            System.out.println("No se puede introducir parámetros vacíos");
        }
    }
    
    /**
     * Borra un enfermero de la base de datos
     * @post: enfermero eliminado
     */
    private void borrarEnfermero(){
        try{
            System.out.println("Nombre del enfermero/a:");
            System.out.println(">");
            administrador.borrarPersona(baseDatos, "enfermero", introducirDato().toLowerCase());
            System.out.println("Enfermero borrado");
            System.out.println();
        }
        catch(Exception e){
            System.out.println("No se puede introducir parámetros vacíos");
        }
    }
    
    /**
     * Borra un tecnico de la base de datos
     * @post: tecnico elminado
     */
    private void borrarTecnico(){
        try{
            System.out.println("Nombre del tecncio:");
            System.out.println(">");
            administrador.borrarPersona(baseDatos, "tecnico", introducirDato().toLowerCase());
            System.out.println("Tecnico borrado");
            System.out.println();
        }
        catch(Exception e){
            System.out.println("No se puede introducir parámetros vacíos");
        }
    }
    
    /**
     * Corregir el nombre del paciente
     */
    private void corregirPaciente(){
        String nombreActual = null; 
        
        //pide el nombre por el que el paciente esta registrado
        System.out.println("Nombre actual:");
        System.out.println(">");
        
        try{
            nombreActual = introducirDato().toLowerCase();
            //corrige los datos
            administrador.corregirNombrePersona(baseDatos, "paciente", nombreActual, introducirNombre(), introducirApellido1(), introducirApellido2());
            System.out.println("Paciente corregido");
        }
        catch(Exception e){
            System.out.println("Debe introducir un nombre");
        }
        
        System.out.println();
    }
    
    /**
     * Corregir el nombre del enfermero
     */
    private void corregirEnfermero(){
        String nombreActual = null;
        
        System.out.println("Nombre actual:");
        System.out.println(">");
        
        try{
            nombreActual = introducirDato();
            administrador.corregirNombrePersona(baseDatos, "enfermero", nombreActual, introducirNombre(), introducirApellido1(), introducirApellido2());
            System.out.println("Enfermero corregido");
        }
        catch(Exception e){
            System.out.println("Debe introducir un nombre");
        }
        
        System.out.println();
    }
    
    /**
     * Corregir el nombre del paciente
     */
    private void corregirTecnico(){
        String nombreActual = null;
        
        System.out.println("Nombre actual:");
        System.out.println(">");
        
        try{
            nombreActual = introducirDato();
            administrador.corregirNombrePersona(baseDatos, "tecnico", nombreActual, introducirNombre(), introducirApellido1(), introducirApellido2());
            System.out.println("Tecnico corregido");
        }
        catch(Exception e){
            System.out.println("Debe introducir un nombre");
        }
        
        System.out.println();
    }
    
    /**
     * Programa una cita para una prueba
     */
    private void citaPrueba(){
        String nombrePaciente = null;
        String prueba = null;
        int dia;
        int mes;
        int anno;
        String nombreEnfermero = null;
        String nombreTecnico = null;
        
        try{
            System.out.println("Nombre del paciente:");
            System.out.println(">");
            nombrePaciente = introducirDato();
            
        
            System.out.println("Nombre de la prueba:");
            System.out.println(">");
            prueba = introducirDato();
        
            System.out.println("Dia:");
            System.out.println(">");
            dia = Integer.parseInt(introducirDato());
        
            System.out.println("Mes:");
            System.out.println(">");
            mes = Integer.parseInt(introducirDato());
        
            System.out.println("Anno:");
            System.out.println(">");
            anno = Integer.parseInt(introducirDato());
        
            System.out.println("Nombre del enfermero:");
            System.out.println(">");
            nombreEnfermero = introducirDato();
        
            System.out.println("Nombre del tecnico");
            System.out.println(">");
            nombreTecnico = introducirDato();
        
            //introduce todos los datos como parametros en el metodo del administrador
        
            administrador.setCitaPrueba(baseDatos, nombrePaciente.toLowerCase(), prueba.toLowerCase(), anno, mes, dia, nombreEnfermero.toLowerCase(), nombreTecnico.toLowerCase());
            System.out.println("Cita para prueba creada");
        }
        catch(Exception e){
            System.out.println("No se pueden introducir parámetros vacíos");
        }
        
        System.out.println();
    }
    
    /**
     * Programa una cita para una vacuna
     */
    private void citaVacuna(){
        String nombrePaciente = null;
        String vacuna = null;
        int dia;
        int mes;
        int anno;
        String nombreEnfermero = null;
        
        try{
            System.out.println("Nombre del paciente:");
            System.out.println(">");
            nombrePaciente = introducirDato();
        
            System.out.println("Nombre de la vacuna:");
            System.out.println(">");
            vacuna = introducirDato();
        
            System.out.println("Dia:");
            System.out.println(">");
            dia = Integer.parseInt(introducirDato());
        
            System.out.println("Mes:");
            System.out.println(">");
            mes = Integer.parseInt(introducirDato());
        
            System.out.println("Anno:");
            System.out.println(">");
            anno = Integer.parseInt(introducirDato());
        
            System.out.println("Nombre del enfermero:");
            System.out.println(">");
            nombreEnfermero = introducirDato();
        
            administrador.setCitaVacuna(baseDatos, nombrePaciente.toLowerCase(), vacuna.toLowerCase(), anno, mes, dia, nombreEnfermero.toLowerCase());
            System.out.println("Cita para vacuna creada");
        }
        catch(Exception e){
            System.out.println("No se puede introducir parámetros vacíos");
        }
        System.out.println();
    }
    
    /**
     * Imprime una lista de los pacientes asignados a un enfermero
     */
    private void pacientesEnfermero(){
        try{
            System.out.println("Nombre del enfermero:");
            System.out.println(">");
            administrador.printPacientesEnfermero(baseDatos, introducirDato().toLowerCase());
            System.out.println();
        }
        catch(Exception e){
            System.out.println("No se puede introducir parámetros vacíos");
        }
    }
    
    /**
     * Imprime una lista de los pacientes asignados a un tecnico
     */
    private void pacientesTecnico(){
        try{
            System.out.println("Nombre del tecnico:");
            System.out.println(">");
            administrador.printPacientesTecnico(baseDatos, introducirDato().toLowerCase());
            System.out.println();
        }
        catch(Exception e){
            System.out.println("No se puede introducir parámetros vacíos");
        }
    }
    
    /**
     * Imprime una lista de los pacientes en cuarentena
     */
    private void pacientesConfinados(){
        administrador.printPacientesConfinados(baseDatos);
        System.out.println();
    }
    
    //Metodos enfermero
    /**
     *  Permite introducir los comandos del enfermero en loop hasta que se sale.
     */
    private void interfazEnfermero() 
    {            
        System.out.println("Ha iniciado sesion como enfermero");
                
        boolean acabar = false;
        while (! acabar) {
            String comando = analizador.getComandoEnfermero();
            if(comando != null){
                acabar = procesarComandoEnfermero(comando);
            }else{System.out.println("Comando desconocido\n");}
        }
        Clinica.guardarSesion();
        System.out.println("Sesion cerrada.\n");
    }
    
    /**
     * Procesa los comandos introducidos como enfermero
     */
    private boolean procesarComandoEnfermero(String comando){
        boolean salir = false;
        String fraseComando = comando;
        if (fraseComando.toLowerCase().equals("help")) {
            printHelpEnfermero();
        }
        else if (fraseComando.toLowerCase().equals("logout")) {
            salir = quit();
            enfermero = null;
        }
        else if (fraseComando.toLowerCase().equals("lista pcr")) {
            printPCR();
        }
        else if (fraseComando.toLowerCase().equals("lista antigenos")) {
            printAntigenos();
        }
        else if (fraseComando.toLowerCase().equals("lista serologicas")) {
            printSerologicos();
        }
        else if (fraseComando.toLowerCase().equals("lista pfizer")) {
            printPfizer();
        }
        else if (fraseComando.toLowerCase().equals("lista moderna")) {
            printModerna();
        }
        else if (fraseComando.toLowerCase().equals("lista johnson")) {
            printJohnson();
        }
        else if (fraseComando.toLowerCase().equals("lista pacientes")) {
            pacientes();
        }
        else if (fraseComando.toLowerCase().equals("realizar pcr")) {
            realizarPCR();
        }
        else if (fraseComando.toLowerCase().equals("realizar antigenos")) {
            realizarAntigenos();
        }
        else if (fraseComando.toLowerCase().equals("realizar serologica")) {
            realizarSerologica();
        }
        else if (fraseComando.toLowerCase().equals("administrar pfizer")) {
            administrarPfizer();
        }
        else if (fraseComando.toLowerCase().equals("administrar moderna")) {
            administrarModerna();
        }
        else if (fraseComando.toLowerCase().equals("administrar johnson")) {
            administrarJohnson();
        }
        
        return salir;
    }
    
    /**
     * Imprime los comandos asignados al enfermero
     * @return comandos del enfermero
     */
    private void printHelpEnfermero(){
        analizador.mostrarComandosEnfermero();
        System.out.println();
    }
    
    /**
     * Imprime lista de pruebas pcr
     */
    private void printPCR(){
        enfermero.printPruebasRealizadas(baseDatos, "pcr");
        System.out.println();
    }
    
    /**
     * Imprime lista de pruebas antigenos
     */
    private void printAntigenos(){
        enfermero.printPruebasRealizadas(baseDatos, "antigenos");
        System.out.println();
    }
    
    /**
     * Imprime lista de pruebas serologicas
     */
    private void printSerologicos(){
        enfermero.printPruebasRealizadas(baseDatos, "serologico");
        System.out.println();
    }
    
    /**
     * Imprime lista de vacunas Pfizer
     */
    private void printPfizer(){
        enfermero.printVacunasRealizadas(baseDatos, "pfizer");
        System.out.println();
    }
    
    /**
     * Imprime lista de vacunas Moderna
     */
    private void printModerna(){
        enfermero.printVacunasRealizadas(baseDatos, "moderna");
        System.out.println();
    }
    
    /**
     * Imprime lista de vacunas Johnson
     */
    private void printJohnson(){
        enfermero.printVacunasRealizadas(baseDatos, "johnson & johnson");
        System.out.println();
    }
    
    /**
     * Imprime lista de pacientes adjudicados
     */
    private void pacientes(){
        enfermero.printListaPacientes(baseDatos);
        System.out.println();
    }
    
    /**
     * Realiza una pcr
     */
    private void realizarPCR(){
        try{
            System.out.println("Paciente:");
            System.out.println(">");
            String paciente = introducirDato();
            System.out.println("Tecnico:");
            System.out.println(">");
            String tecnico = introducirDato();
        
            enfermero.realizarPrueba(baseDatos, "pcr", paciente, tecnico);
        }
        catch(Exception e){
            System.out.println("No se puede introducir parámetros vacíos");
        }
        System.out.println();
    }
    
    /**
     * Realiza una antigenos
     */
    private void realizarAntigenos(){
        try{
            System.out.println("Paciente:");
            System.out.println(">");
            String paciente = introducirDato();
            System.out.println("Tecnico:");
            System.out.println(">");
            String tecnico = introducirDato();
        
            enfermero.realizarPrueba(baseDatos, "antigenos", paciente, tecnico);
        }
        catch(Exception e){
            System.out.println("No se puede introducir parámetros vacíos");
        }
        
        System.out.println();
    }
    
    /**
     * Realiza una serologica
     */
    private void realizarSerologica(){
        try{
            System.out.println("Paciente:");
            System.out.println(">");
            String paciente = introducirDato();
            System.out.println("Tecnico:");
            System.out.println(">");
            String tecnico = introducirDato();
        
            enfermero.realizarPrueba(baseDatos, "serologico", paciente, tecnico);
        }
        catch(Exception e){
            System.out.println("No se puede introducir parámetros vacíos");
        }
        
        System.out.println();
    }
    
    /**
     * Administrar pfizer
     */
    private void administrarPfizer(){
        try{
            System.out.println("Paciente:");
            System.out.println(">");
            String paciente = introducirDato();
            enfermero.administrarVacuna(baseDatos, "pfizer", paciente);
        }
        catch(Exception e){
            System.out.println("No se puede introducir parámetros vacíos");
        }
        
        System.out.println();
    }
    
    /**
     * Administrar moderna
     */
    private void administrarModerna(){
        try{
            System.out.println("Paciente:");
            System.out.println(">");
            String paciente = introducirDato();
            enfermero.administrarVacuna(baseDatos, "moderna", paciente);
        }
        catch(Exception e){
            System.out.println("No se puede introducir parámetros vacíos");
        }
        
        System.out.println();
    }
    
    /**
     * Administrar johnson & johnson
     */
    private void administrarJohnson(){
        try{
            System.out.println("Paciente:");
            System.out.println(">");
            String paciente = introducirDato();
            enfermero.administrarVacuna(baseDatos, "johnson & johnson", paciente);
        }
        catch(Exception e){
            System.out.println("No se puede introducir parámetros vacíos");
        }
        
        System.out.println();
    }
    
    //Metodos tecnico
    /**
     *  Permite introducir los parametros del tecnico en loop hasta que se sale.
     */
    private void interfazTecnico() 
    {            
        System.out.println("Ha iniciado sesion como tecnico");
                
        boolean acabar = false;
        while (! acabar) {
            String comando = analizador.getComandoTecnico();
            if(comando != null){
                acabar = procesarComandoTecnico(comando);
            }else{System.out.println("Comando desconocido\n");}
        }
        Clinica.guardarSesion();
        System.out.println("Sesion cerrada.\n");
    }
    
    /**
     * Procesa los comandos introducidos como tecnico
     */
    private boolean procesarComandoTecnico(String comando){
        boolean salir = false;
        String fraseComando = comando;
        if (fraseComando.toLowerCase().equals("help")) {
            printHelpTecnico();
        }
        else if (fraseComando.toLowerCase().equals("logout")) {
            salir = quit();
            tecnico = null;
        }
        else if (fraseComando.toLowerCase().equals("resultado pcr")) {
            resultadoPCR();
        }
        else if (fraseComando.toLowerCase().equals("lista pruebas")) {
            listaPruebas();
        }
        else if (fraseComando.toLowerCase().equals("resultado antigenos")) {
            resultadoAntigenos();
        }
        else if (fraseComando.toLowerCase().equals("resultado serologico")) {
            resultadoSerologico();
        }
        
        return salir;
    }
    
    /**
     * Imprime las pruebas adjudicadas al tecnico
     */
    private void listaPruebas(){
        tecnico.printPruebasProcesadas(baseDatos);
        System.out.println();
    }
    
    /**
     * Añade el resultado de una prueba pcr
     */
    private void resultadoPCR(){
        boolean positivoBooleano = false;
        String nombre = null;
        String codigo =  null;
        String positivo = null;
        
        try{
            System.out.println("Paciente:");
            System.out.println(">");
            nombre = introducirDato();
            System.out.println("Codigo de la prueba:");
            System.out.println(">");
            codigo = introducirDato();
            System.out.println("Positivo (true/false):");
            System.out.println(">");
            positivo = introducirDato();
        
            if(positivo.equals("true") || positivo.equals("false")){
                if(positivo.equals("true")){
                    positivoBooleano = true;
                }
                tecnico.resultadoPrueba(baseDatos, nombre, "pcr", codigo, positivoBooleano);
            }
            else {
                System.out.println("Datos incorrectos.");
            }
        }
        catch(Exception e){
            System.out.println("No se puede introducir parámetros vacíos");
        }
        
        System.out.println();
    }
    
    /**
     * Añade el resultado de una prueba antigenos
     */
    private void resultadoAntigenos(){
        boolean positivoBooleano = false;
        String nombre = null;
        String codigo =  null;
        String positivo = null;
        
        try{
            System.out.println("Paciente:");
            System.out.println(">");
            nombre = introducirDato();
            System.out.println("Codigo de la prueba:");
            System.out.println(">");
            codigo = introducirDato();
            System.out.println("Positivo (true/false):");
            System.out.println(">");
            positivo = introducirDato();
        
            if(positivo.equals("true") || positivo.equals("false")){
                if(positivo.equals("true")){
                    positivoBooleano = true;
                }
                tecnico.resultadoPrueba(baseDatos, nombre, "antigenos", codigo, positivoBooleano);
            }
            else {
                System.out.println("Datos incorrectos.");
            }
        }
        catch(Exception e){
            System.out.println("No se puede introducir parámetros vacíos");
        }
        
        System.out.println();
    }
    
    /**
     * Añade el resultado de una prueba serologica
     */
    private void resultadoSerologico(){
        String nombre = null;
        String codigo =  null;
        String anticuerposCadena = null;
        int anticuerpos;
        
        try{
            System.out.println("Paciente:");
            System.out.println(">");
            nombre = introducirDato();
            System.out.println("Codigo de la prueba:");
            System.out.println(">");
            codigo = introducirDato();
            System.out.println("Anticuerpos (de 0 a 10):");
            System.out.println(">");
            anticuerposCadena = introducirDato();
            anticuerpos = Integer.parseInt(anticuerposCadena);
        
            tecnico.resultadoPruebaSerologica(baseDatos, nombre, codigo, anticuerpos);
        }
        catch(Exception e){
            System.out.println("No se puede introducir parámetros vacíos");
        }
        
        System.out.println();
    }
    
    /**
     * Imprime los comandos asociados a un tecnico
     * @return comandos del tecnico
     */
    private void printHelpTecnico(){
        analizador.mostrarComandosTecnico();
        System.out.println();
    }
    
    //Comando general para cerrar sesion o terminar el programa
    /** 
     * Finaliza el programa
     * @return true, si el comando termina el programa, false en el resto de casos.
     */
    private boolean quit() 
    {
        System.out.println();
        return true;  // señar para indicar salida
    }
}
