package co.uniquindio.fundamentos.encriptacion;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import javax.swing.JOptionPane;

/**
 * @author JaemT1
 */
public class Aplicacion {
    public static void main(String[] args) {
       String mensaje = verificarMensaje();
       encriptar(mensaje);
    }
    
    
    /**
     * Método que encripta un mensaje determinado sumandole 8 al código ascii de cada letra
     * @param mensaje Mensaje introducido por el usuario 
     */
    public static void encriptar(String mensaje){
        /*Declaramos las variables que van a guardar el mensaje encriptado 
        y el arreglo de char que guarda cada letra del mensaje*/
        String mensajeEncriptado;
        char mensajeDividido[] = mensaje.toCharArray();
        
        //Se encripta el mensaje sumandole 8 al valor ascii de cada letra
        for (int i = 0; i < mensajeDividido.length; i++) {
            mensajeDividido[i] = (char)(mensajeDividido[i]+(char)8);
        }
        
        //Se obtiene el mensaje encriptado en String
        mensajeEncriptado = String.valueOf(mensajeDividido);
        
        //Se manda el mensaje encriptado para ser guardado en un archivo de texto
        guardarEnArchivo(mensajeEncriptado);
        JOptionPane.showMessageDialog(null, "Su mensaje ha sido encriptado con exito!");
      
    }
    
    /**
     * Método que guarda un el mensaje encriptado en un archivo .txt
     * @param mensajeEncriptado mensaje ya encriptado
     */
    public static void guardarEnArchivo(String mensajeEncriptado){
        try {
            String ruta = "textoEncriptado\\textoEncriptado.txt";
            String contenido = mensajeEncriptado;
            File file = new File(ruta);
            
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(contenido);
            bw.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Método que verifica si el mensaje introducido por el usuario es mayor a 14 carácteres
     * @param mensaje El mensaje a encriptar
     * @return Retorna true si es mayor a 14, sino retorna false
     */
    public static boolean verificarLongitudMensaje(String mensaje){
        return mensaje.length() > 14;
    }
    
    /**
     * Método que verifica si el mensaje tiene espacios
     * @param mensaje Mensaje a encriptar
     * @return Retorna true si contiene espacios y false si no los contiene
     */
    public static boolean verificarEspacios(String mensaje){
        return mensaje.contains(" ");
    }
    
    /**
     * Método que verifica si el mensaje tiene números
     * @param mensaje Mensaje a encriptar
     * @return Retorna true si hay numeros y false si no los hay
     */
    public static boolean verificarNumeros(String mensaje){
        char[] mensajeDividido = mensaje.toCharArray();
        for (char caracter : mensajeDividido) {
            if(Character.isDigit(caracter))              {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Método que hace las verificaciones pertinentes sobre el mensaje a encriptar
     * @return retorna el mensaje ya verificado
     */
    public static String verificarMensaje(){
        boolean esMayor, contieneEspacios, contieneNumeros, esApto;
        String mensaje = "";
                
        do {            
            mensaje = JOptionPane.showInputDialog("Ingrese el mensaje a encriptar sin espacios ni números: ");
            
            esApto = false;
            
            esMayor = verificarLongitudMensaje(mensaje);
            contieneEspacios = verificarEspacios(mensaje);
            contieneNumeros = verificarNumeros(mensaje);
                      
            if (contieneNumeros) {
                JOptionPane.showMessageDialog(null, "Su mensaje contiene números, intentelo de nuevo");
                esApto = true;
            }

            if (contieneEspacios) {
                JOptionPane.showMessageDialog(null, "Su mensaje contiene espacios, intentelo de nuevo");
                esApto = true;
            }

            if (esMayor) {
                JOptionPane.showMessageDialog(null, "Su mensaje es mayor a 14 carácteres, intentelo de nuevo");
                esApto = true;
            } 

        } while (esApto);
        return mensaje;
    }
}
