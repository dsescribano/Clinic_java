import java.time.*;
/**
 * Clase para crear las instancias Paciente
 */
public class Paciente extends Persona
{
    private String[] vacuna;
    private boolean contagiado;
    private LocalDate fechaFinCuarentena, fechaCitaPrueba, fechaCitaVacuna, fechaFinInmunidad;
    private String pruebaSolicitada, vacunaAsignada, enfermeroPrueba, enfermeroVacuna, tecnicoAsignado;
    
    
    public Paciente(String nombre, String apellido1, String apellido2){
        super(nombre, apellido1, apellido2);
        contagiado = false;
        vacuna = new String[2];
    }
    
    //Métodos relacionados con la cuarentena
    /**
     * Devuelve la fecha final de la cuarentena como cadena de caracteres
     * @return String fechaFinCuarentena
     */
    public String getFechaFinCuarentena(){
        if(fechaFinCuarentena != null){
            return fechaFinCuarentena.toString();
        }
        else{return "";}
    }
    
    /**
     * Devuelve la fecha final de la cuarentena como un objeto de la clase LocalDate
     * @return LocalDate fechaFinCuarentena
     */
    public LocalDate getFinCuarentena(){
        return fechaFinCuarentena;
    }
    
    //Métodos relacionados con la inmunidad del paciente
    /**
     * Establece la inmunidad del paciente
     */
    public void setFechaInmunidad(){
        fechaFinInmunidad = LocalDate.now().plusMonths(6);
    }
    
    /**
     * Devuelve una cadena con el estado de inmunización del paciente
     * @return si= paciente inmunizado; no= paciente no inmunizado
     */
    public String getInmunizacion(){
        if(fechaFinInmunidad != null){
            if(fechaFinInmunidad.compareTo(LocalDate.now()) >= 0){return "si";}
            else{return "no";}
        }
        else{return "";}
    }
    
    //Métodos relacionados con el estado de contagio del paciente
    /**
     * Comprueba si el paciente está contagiado o no.
     * @return: contagiado false o true. 
     */
    public boolean getPacienteContagiado(){
        if(fechaFinCuarentena != null){
          if(fechaFinCuarentena.compareTo(LocalDate.now()) >= 0){
              contagiado = true;
          }
          else{
              contagiado = false; 
          }
        }
        return contagiado;
    }
    
    /**
     * Establece el estado de contagio del paciente -> contagiado =  true
     */
    public void setPacienteContagiado(){
        contagiado = true;
        fechaFinCuarentena = LocalDate.now().plusDays(10);
    }
    
    //Métodos relacionados con la vacunación del paciente
    /**
     * Comprueba las dosis administradas al paciente
     */
    private int getDosisVacuna(){
        int dosis = 0;
        for(int n = 0; n <= 1; n++){
            if(vacuna[n] != null){
                dosis++;
            }
        }
        return dosis;
    }
        
    /**
     * Establece una fecha para vacunar al paciente
     */
    public void setFechaCitaVacuna(int year, int month, int dayOfMonth){fechaCitaVacuna = LocalDate.of(year, month, dayOfMonth);}
    
    /**
     * Borra la fecha de la cita de la vacuna
     */
    public void deleteVacunaAsignada(){
        fechaCitaVacuna = null;
        vacunaAsignada = null;
        enfermeroVacuna = null;
    }
    
    /**
     * Devuelve el nombre de la vacuna asignada
     */
    public String getVacunaAsignada(){
        if(vacunaAsignada != null){
            return vacunaAsignada;
        }
        else{return "";}
    }
    
    /**
     * Devuelve la fecha de la cita de la vacunación
     */
    public String getFechaCitaVacuna(){
        if(fechaCitaVacuna != null){
            return fechaCitaVacuna.toString();
        }
        else{return "";}
    }
    
    /**
     * Establece el nombre de la vacuna asignada
     */
    public void setNombreVacuna(String nombreVacuna){vacunaAsignada = nombreVacuna;}
    
    /**
     * Establece el nombre del enfermero que administra la vacuna
     */
    public void setEnfermeroVacuna(String nombreEnfermero){enfermeroVacuna = nombreEnfermero;}
    
    /**
     * Devuelve el enfernero asociado a la vacuna
     */
    public String getEnfermeroVacuna(){
        if(enfermeroVacuna != null){
            return enfermeroVacuna;
        }
        else{return "";}
    }
    
    /**
     * Introduce el nombre de una vacuna en la matriz vacuna
     */
    public void setVacuna(String nombreVacuna){
        if(nombreVacuna.toLowerCase().equals("pfizer") || nombreVacuna.toLowerCase().equals("moderna")){
            for(int n = 0; n <= 1; n++){
                if(vacuna[n] == null){
                    vacuna[n] = nombreVacuna;
                    n = 2;
                }
                if(getDosisVacuna() == 2){
                    setFechaInmunidad();
                }
            }
        }
        else if(nombreVacuna.toLowerCase().equals("johnson & johnson")){
            vacuna[0] = nombreVacuna;
        }
    }
    
    /**
     * Devuelve las vacunas administradas al paciente
     */
    public String getVacunas(){
        if(vacuna[0]!= null && vacuna[0] == null){return vacuna[0] + ".";}
        else if(vacuna[0]!= null && vacuna[0] != null) {return vacuna[0] + ", " + vacuna[1] + ".";}
        else{return "";}
    }
    
    //Métodos relacionados con la cita de la prueba solicitada
    /**
     * Devuelve el nombre de la prueba solicitada
     */
    public String getPruebaSolicitada(){
        if(pruebaSolicitada != null){
            return pruebaSolicitada;
        }
        else{return "";}
    }
    
    /**
     * Borra los datos de la prueba solicitada
     */
    public void deletePruebaSolicitada(){
        fechaCitaPrueba = null;
        pruebaSolicitada = null;
        enfermeroPrueba = null;
        tecnicoAsignado = null;
    }
    
    /**
     * Devuelve una cadena con la fecha de la cita de la prueba
     */
    public String getFechaCitaPrueba(){
        if(fechaCitaPrueba!= null){
            return fechaCitaPrueba.toString();
        }
        else{return "";}
    }
    
    /**
     * Establece una fecha para realizar una prueba a un paciente
     */
    public void setFechaCitaPrueba(int year, int month, int dayOfMonth){fechaCitaPrueba = LocalDate.of(year, month, dayOfMonth);}
    
    /**
     * Establece una fecha para realizar una prueba serologica a un paciente
     */
    public void setFechaTestSerologico(LocalDate date){fechaCitaPrueba = date;}
    
    /**
     * Establece el nombre de la prueba solicitada
     */
    public void setNombrePrueba(String nombrePrueba){pruebaSolicitada = nombrePrueba;}
    
    /**
     * Establece el nombre del tecnico que comprueba la prueba
     */
    public void setTecnicoSolicitado(String nombreTecnico){tecnicoAsignado = nombreTecnico;}
    
    /**
     * Establece el nombre del enfermero que hace la prueba
     */
    public void setEnfermeroPrueba(String nombreEnfermero){enfermeroPrueba = nombreEnfermero;}
    
    /**
     * Devuelve el enfermero asociado a la prueba
     */
    public String getEnfermeroPrueba(){
        if(enfermeroPrueba!= null){
            return enfermeroPrueba;
        }
        else{return "";}
    }
    
    /**
     * Devuelve el tecnico asociado a la prueba
     */
    public String getTecnicoAsignado(){
        if(tecnicoAsignado != null){
            return tecnicoAsignado;
        }
        else{return "";}
    }
}
