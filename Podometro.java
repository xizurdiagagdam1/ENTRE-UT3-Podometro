 /**
 * La clase modela un sencillo pod�metro que registra informaci�n
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
     * Inicializa el pod�metro con la marca indicada por el par�metro.
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
     * Simula la configuraci�n del pod�metro.
     * Recibe como par�metros la altura y el sexo de una persona
     * y asigna estos valores a los atributos correspondiente.
     * Asigna adem�s el valor adecuado al atributo longitudZancada
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
     *  Recibe cuatro par�metros que supondremos correctos:
     *    pasos - el n� de pasos caminados
     *    dia - n� de d�a de la semana en que se ha hecho la caminata 
     *              (1 - Lunes, 2 - Martes - .... - 6 - S�bado, 7 - Domingo)
     *    horaInicio � hora de inicio de la caminata
     *    horaFina � hora de fin de la caminata
     *    
     *    A partir de estos par�metros el m�todo debe realizar ciertos c�lculos
     *    y  actualizar� el pod�metro adecuadamente  
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
     * Muestra en pantalla la configuraci�n del pod�metro
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
            System.out.println("Los car�cteres permitidos son: H para hombre y M para mujer");
        }                      
        System.out.println("Longitud zancada: " + longitudZancada + " mtos");
    }

    /**
     * Muestra en pantalla informaci�n acerca de la distancia recorrida,
     * pasos, tiempo total caminado, ....
     * 
     * (leer enunciado)
     *  
     */
    public void printEstad�sticas() {
        System.out.println("Estad�sticas" + 
             "\n*************************** " +
             "\nDisancia recorrida toda la semana: " + totalDistanciaSemana + " Km" +
             "\nDistancia recorrida fin de semana: " + totalDistanciaFinSemana + "Km " + 
             "\n" + 
             "\nN� pasos dias laborales: " + totalPasosLaborales +
             "\nN� pasos SABADO: " + totalPasosSabado +
             "\nN� pasos DOMINGO: " + totalPasosDomingo +
             "\n" + 
             "\nN� caminatas raelizadas a aprtir de las 21h: " + caminatasNoche +
             "\n" +
             "\nTiempo total caminado en la semana: " + tiempo / 100 + "h. y "+ tiempo % 100 + "m");
    }


    /**
     *  Calcula y devuelve un String que representa el nombre del d�a
     *  en el que se ha caminado m�s pasos - "S�BADO"   "DOMINGO" o  "LABORABLES"
     */
    public String diaMayorNumeroPasos() {
        if (totalPasosLaborales > totalPasosSabado && totalPasosLaborales > totalPasosDomingo){
            return "LABORALES";
        }else if(totalPasosSabado > totalPasosLaborales && totalPasosSabado > totalPasosLaborales){
            return "S�BADO";
        }
        return "DOMINGO";
    }
    
    /**
     * Restablecer los valores iniciales del pod�metro
     * Todos los atributos se ponen a cero salvo el sexo
     * que se establece a MUJER. La marca no var�a
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
