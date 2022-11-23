package co.uniquindio.fundamentos.encriptacion;

import java.awt.Component;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * @author JaemT1
 */
public class Aplicacion {
    // private static String RUTA = "C:\\textoEncriptado\\textoEncriptado.txt";
    private static String RUTA = "";
    private static Component panelBase;
    
    public static void main(String[] args) {
       String mensaje = verificarMensaje();
        try {
            encriptar(mensaje);
        } catch (IOException ex) {
            ex.getMessage();
        }
    }
    
    
    /**
     * Método que encripta un mensaje determinado sumandole 8 al código ascii de cada letra
     * @param mensaje Mensaje introducido por el usuario 
     * @throws java.io.IOException 
     */
    public static void encriptar(String mensaje) throws IOException{
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
        
        //El usuario escoge la ruta donde quiere que se guarde el mensaje y se obtiene la ruta
        JFileChooser selectorCarpeta = new JFileChooser();
        selectorCarpeta.setCurrentDirectory(new File("*"));
        selectorCarpeta.setDialogTitle("Seleccione la carpeta");
        selectorCarpeta.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        if (selectorCarpeta.showOpenDialog(panelBase) == JFileChooser.APPROVE_OPTION) {

            File carpetaSelecciona = selectorCarpeta.getSelectedFile();
            String ruta1 = carpetaSelecciona.toString();
            RUTA  = ruta1 + "\\archivoEncriptado.txt";
        }
        
        //Se manda el mensaje encriptado para ser guardado en un archivo de texto
        guardarArchivo(RUTA,mensajeEncriptado,false);
        
        JOptionPane.showMessageDialog(null, "Su mensaje ha sido encriptado con exito!");
        JOptionPane.showMessageDialog(null, "Recuerde donde guardó el mensaje para luego ser desencriptado");
      
    }
    
    /**
     * Este metodo recibe una cadena con el contenido que se quiere guardar en
     * el archivo
     *
     * @param ruta es la ruta o path donde esta ubicado el archivo
     * @throws IOException
     */
    public static void guardarArchivo(String ruta, String contenido, Boolean flagAnexarContenido) throws IOException {
  
        FileWriter fw = new FileWriter(ruta, flagAnexarContenido);
        BufferedWriter bfw = new BufferedWriter(fw);
        bfw.write(contenido);
        bfw.close();
        fw.close();
        System.out.println("Se guardaron los archivos");
    }

   
    //---------------------------------VERIFICACIONES-------------------------------------------------------------------
    
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