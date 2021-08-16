import java.time.*;
/**
 * Clase para crear las instancias Serologico
 */
public class Serologico extends Prueba
{
    private LocalDate fechaValidez;
    private int anticuerpos;
    
    public Serologico(String nombrePaciente, String nombreEnfermero, String nombreTecnico){
        super(nombrePaciente, nombreEnfermero, nombreTecnico);
        fechaValidez = getFechaPrueba().plusMonths(6);
        addNombrePrueba("serologico");
    }
    
    /**
     * Devuelve la fecha en la que la validez de la prueba caduca
     * @return: fechaCaducidad
     */
    public String getFechaValidez(){
        return fechaValidez.toString();
    }
    
    /**
     * Establece el nivel de anticuerpos recogido en la prueba
     * @post: nivel de anticuerpos establecido
     */
    public void setAnticuerpos(int anticuerpos){
        if(anticuerpos >= 0 && anticuerpos <= 10){
            this.anticuerpos = anticuerpos;
            if(anticuerpos > 2){
              setPositivo(true);
            }else{
              setPositivo(false);
            }
        }
        else{
            System.out.println("El valor introducido no es correcto");
        }
    }
    
    /**
     * Devuelve el número de anticuerpos obtenidos en la prueba
     */
    public int getAnticuerpos(){return anticuerpos;}
    
    /**
     * Devuelve el resultado de inmunidad de la prueba
     */
    public String getInmunidad(){
        if(anticuerpos > 2){
            return "si";
        }else{
            return "no";
        }
    }
}
