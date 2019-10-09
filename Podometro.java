 /**
 * La clase modela un sencillo podómetro que registra información
 * acerca de los pasos, distancia, ..... que una persona (hombre o mujer)
 * ha dado en una semana. 
 * 
 * @author    - Xavier - 
 * 
 */
public class Podometro {

    private final char HOMBRE= 'H';
    private final char MUJER = 'M';
    private final double ZANCADA_HOMBRE = 0.45;
    private final double ZANCADA_MUJER = 0.41;
    private final char SABADO = '6';
    private final char DOMINGO = '7';

    private String marca;
    private double altura;
    private char sexo;
    private double longitudZancada;
    private double totalPasosLaborales;
    private double totalPasosSabado;
    private double totalPasosDomingo;
    private double totalDistanciaSemana;
    private double totalDistanciaFinSemana;
    private int tiempo;
    private int caminatasNoche;

    /**
     * Inicializa el podómetro con la marca indicada por el parámetro.
     * El resto de atributos se ponen a 0 y el sexo, por defecto, es mujer
     */
    public Podometro(String queMarca){
        marca = queMarca;
        altura = 0;
        sexo = 'M';
        longitudZancada = 0;
        totalPasosLaborales = 0;
        totalPasosSabado = 0;
        totalPasosDomingo = 0;
        totalDistanciaSemana = 0;
        totalDistanciaFinSemana = 0;
        tiempo = 0;
        caminatasNoche = 0;
    }
    
    /**
     * Accesor para la marca
     *  
     */
    public String getMarca(){
        return marca;
    }

    /**
     * Simula la configuración del podómetro.
     * Recibe como parámetros la altura y el sexo de una persona
     * y asigna estos valores a los atributos correspondiente.
     * Asigna además el valor adecuado al atributo longitudZancada
     * 
     * (leer enunciado)
     *  
     */
    public void configurar(double queAltura, char queSexo) {
        altura = queAltura;
        sexo = queSexo;
        if (sexo == 'H'){
            longitudZancada = Math.ceil(altura * ZANCADA_HOMBRE) / 100;
        }else{
            longitudZancada = Math.ceil(altura * ZANCADA_MUJER) / 100;
        }
    }
    
    /**
     *  Recibe cuatro parámetros que supondremos correctos:
     *    pasos - el nº de pasos caminados
     *    dia - nº de día de la semana en que se ha hecho la caminata 
     *              (1 - Lunes, 2 - Martes - .... - 6 - Sábado, 7 - Domingo)
     *    horaInicio – hora de inicio de la caminata
     *    horaFina – hora de fin de la caminata
     *    
     *    A partir de estos parámetros el método debe realizar ciertos cálculos
     *    y  actualizará el podómetro adecuadamente  
     *   
     *   (leer enunciado del ejercicio)
     */
    public void registrarCaminata(int pasos, int dia, int horaInicio,
    int horaFin){
        if (horaInicio > 2100){
            caminatasNoche ++;
        }
        tiempo += horaFin - horaInicio;
        switch (dia){
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                totalPasosLaborales += pasos;
            break;
            case 6:
                totalPasosSabado += pasos;
            break;
            case 7:
                totalPasosDomingo += pasos;
            break;
            default:
            System.out.println("El dia debe ser entre 1 y 7");
        }
        totalDistanciaFinSemana = ((totalPasosSabado + totalPasosDomingo) * longitudZancada) / 1000;
        totalDistanciaSemana = ((totalPasosLaborales * longitudZancada) / 1000) + totalDistanciaFinSemana;
    }

    /**
     * Muestra en pantalla la configuración del podómetro
     * (altura, sexo y longitud de la zancada)
     * 
     * (ver enunciado)
     *  
     */
    public void printConfiguracion() {
        System.out.println("Configuracion del podometro" + 
                         "\n*************************** " + 
                         "\nAltura: " + altura / 100 + " mtos");  
        if (sexo == 'M'){
            System.out.println("Sexo: MUJER");
        }else if(sexo == 'H'){
            System.out.println("Sexo: HOMBRE");
        }else{
            System.out.println("Los carácteres permitidos son: H para hombre y M para mujer");
        }                      
        System.out.println("Longitud zancada: " + longitudZancada + " mtos");
    }

    /**
     * Muestra en pantalla información acerca de la distancia recorrida,
     * pasos, tiempo total caminado, ....
     * 
     * (leer enunciado)
     *  
     */
    public void printEstadísticas() {
        System.out.println("Estadísticas" + 
             "\n*************************** " +
             "\nDisancia recorrida toda la semana: " + totalDistanciaSemana + " Km" +
             "\nDistancia recorrida fin de semana: " + totalDistanciaFinSemana + "Km " + 
             "\n" + 
             "\nNº pasos dias laborales: " + totalPasosLaborales +
             "\nNº pasos SABADO: " + totalPasosSabado +
             "\nNº pasos DOMINGO: " + totalPasosDomingo +
             "\n" + 
             "\nNº caminatas raelizadas a aprtir de las 21h: " + caminatasNoche +
             "\n" +
             "\nTiempo total caminado en la semana: " + tiempo / 100 + "h. y "+ tiempo % 100 + "m");
    }


    /**
     *  Calcula y devuelve un String que representa el nombre del día
     *  en el que se ha caminado más pasos - "SÁBADO"   "DOMINGO" o  "LABORABLES"
     */
    public String diaMayorNumeroPasos() {
        if (totalPasosLaborales > totalPasosSabado && totalPasosLaborales > totalPasosDomingo){
            return "LABORALES";
        }else if(totalPasosSabado > totalPasosLaborales && totalPasosSabado > totalPasosLaborales){
            return "SÁBADO";
        }
        return "DOMINGO";
    }
    
    /**
     * Restablecer los valores iniciales del podómetro
     * Todos los atributos se ponen a cero salvo el sexo
     * que se establece a MUJER. La marca no varía
     *  
     */    
    public void reset() {
        altura = 0;
        sexo = 'M';
        longitudZancada = 0;
        totalPasosLaborales = 0;
        totalPasosSabado = 0;
        totalPasosDomingo = 0;
        totalDistanciaSemana = 0;
        totalDistanciaFinSemana = 0;
        tiempo = 0;
        caminatasNoche = 0;
    }
}
