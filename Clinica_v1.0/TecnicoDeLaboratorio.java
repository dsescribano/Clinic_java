import java.util.Iterator;
/**
 * Clase que sirve para crear las instancias TecnicoDeLaboratorio
 */
public class TecnicoDeLaboratorio extends Persona
{
    public TecnicoDeLaboratorio(String nombre, String apellido1, String apellido2){
        super(nombre, apellido1, apellido2);
    }
    
    /**
     * Imprime una lista de las pruebas procesadas por el tecnico
     * @param baseDatos
     * @post lista con las pruebas procesadas
     */
    public void printPruebasProcesadas(BaseDatos baseDatos){
        //Imprime las pruebas pcr
        if(!baseDatos.getPruebasRealizadas("pcr").isEmpty()){
            for(int n = 1; n<= baseDatos.getPruebasRealizadas("pcr").size(); n++){
                String key = Integer.toString(n);
                if(baseDatos.getPCR(key).getNombreTecnico().equals(getNombre())){
                    System.out.println("Nombre: " + baseDatos.getPCR(key).getNombrePaciente() + ". Fecha cita prueba: " + baseDatos.getPCR(key).getFechaPrueba().toString() + ". Prueba solicitada: " + baseDatos.getPCR(key).getNombrePrueba() + ". Codigo de la prueba: " + key + ". Procesada: " + baseDatos.getPCR(key).getProcesada() + ". Positivo: " + baseDatos.getPCR(key).getPositivo() + ".");
                }
            }
        }
        //imprime las pruebas de antigenos
        else if(!baseDatos.getPruebasRealizadas("antigenos").isEmpty()){
            for(int n = 1; n<= baseDatos.getPruebasRealizadas("antigenos").size(); n++){
                String key = Integer.toString(n);
                if(baseDatos.getAntigeno(key).getNombreTecnico().equals(getNombre())){
                    System.out.println("Nombre: " + baseDatos.getAntigeno(key).getNombrePaciente() + ". Fecha cita prueba: " + baseDatos.getAntigeno(key).getFechaPrueba().toString() + ". Prueba solicitada: " + baseDatos.getAntigeno(key).getNombrePrueba() + ". Codigo de la prueba: " + key + ". Procesada: " + baseDatos.getAntigeno(key).getProcesada() + ". Positivo: " + baseDatos.getAntigeno(key).getPositivo() + ".");
                }
            }
        }
        //imprime las pruebas serologicas
        else if(!baseDatos.getPruebasRealizadas("serologico").isEmpty()){
            for(int n = 1; n<= baseDatos.getPruebasRealizadas("serologico").size(); n++){
                String key = Integer.toString(n);
                if(baseDatos.getSerologico(key).getNombreTecnico().equals(getNombre())){
                    System.out.println("Nombre: " + baseDatos.getSerologico(key).getNombrePaciente() + ". Fecha cita prueba: " + baseDatos.getSerologico(key).getFechaPrueba().toString() + ". Prueba solicitada: " + baseDatos.getSerologico(key).getNombrePrueba() + ". Codigo de la prueba: " + key + ". Procesada: " + baseDatos.getSerologico(key).getProcesada() + ". Positivo: " + baseDatos.getSerologico(key).getPositivo() + ".");
                }
            }
        }
        else{System.out.println("No hay pruebas almacenadas");}
    }
    
    /**
     * Establece el resultado de la prueba realizada(PCR o antigenos) y el estado del paciente
     * @param baseDatos, nombrePaciente, nombrePrueba, key, positivo
     */
    public void resultadoPrueba(BaseDatos baseDatos, String nombrePaciente, String nombrePrueba, String key, boolean positivo){
        //Se comprueba que los datos introducidos sean correctos
        if(baseDatos.existePersona(nombrePaciente, baseDatos.getPacientes()) && (nombrePrueba.toLowerCase().equals("pcr") || nombrePrueba.toLowerCase().equals("antigenos" ) || nombrePrueba.toLowerCase().equals("serologico"))){
            //Se establece el resultado de la prueba
            if(nombrePrueba.toLowerCase().equals("pcr")){ 
                baseDatos.getPCR(key).setPositivo(positivo);
                baseDatos.getPCR(key).setProcesada(true);
            }else if(nombrePrueba.toLowerCase().equals("antigenos")){
                baseDatos.getAntigeno(key).setPositivo(positivo);
                baseDatos.getAntigeno(key).setProcesada(true);
            }
            //Si la prueba da positivo se establece una cita automáticamente para realizarle al paciente una prueba serológica
            if(positivo){
                 baseDatos.buscarPaciente(nombrePaciente).setPacienteContagiado();
                 baseDatos.buscarPaciente(nombrePaciente).setFechaTestSerologico(baseDatos.buscarPaciente(nombrePaciente).getFinCuarentena());
                 baseDatos.buscarPaciente(nombrePaciente).setNombrePrueba("Serologico");
                 baseDatos.buscarPaciente(nombrePaciente).setEnfermeroPrueba(baseDatos.buscarPaciente(nombrePaciente).getEnfermeroPrueba());
                 baseDatos.buscarPaciente(nombrePaciente).setTecnicoSolicitado(baseDatos.buscarPaciente(nombrePaciente).getTecnicoAsignado());
            }
            else{
                 baseDatos.buscarPaciente(nombrePaciente).deletePruebaSolicitada();
            }
        }
        else{System.out.println("Nombre de paciente incorrecto");}
    }
    
    /**
     * Establece el resultado de la prueba serológica y la inmunidad del paciente
     * @param baseDatos, nombrePaciente, key y anticuerpos
     */
    public void resultadoPruebaSerologica(BaseDatos baseDatos, String nombrePaciente, String key, int anticuerpos){
        if(baseDatos.existePersona(nombrePaciente, baseDatos.getPacientes())){
            //Registra el número de anticuerpos obtenidos en la prueba
            baseDatos.getSerologico(key).setAnticuerpos(anticuerpos);
            baseDatos.getSerologico(key).setProcesada(true);
            //Establece hasta cuándo el paciente tendrá inmunidad, en el caso de tenerla
            if(anticuerpos > 2){
                baseDatos.getSerologico(key).setPositivo(true);
                baseDatos.buscarPaciente(nombrePaciente).setFechaInmunidad();
            }
            else{
                baseDatos.getSerologico(key).setPositivo(false);
                baseDatos.buscarPaciente(nombrePaciente).setFechaInmunidad();
            }
            baseDatos.buscarPaciente(nombrePaciente).deletePruebaSolicitada();
        }
        else{System.out.println("Los datos del paciente introducidos no son correctos");}
    }
}
