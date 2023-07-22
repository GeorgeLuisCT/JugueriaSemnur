
package control;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UnsupportedLookAndFeelException;
import splash.SplashScreen;


public class Ejecucion {
    
    public static int idUsuario;
    
    //comprobamos que todo este correcto y cargamos la pantalla de presentacion
    public static void main(String[] args) {
        
        if(new Control().comprobar()){
            String s = "javax.swing.plaf.metal.MetalLookAndFeel";
            try {
                javax.swing.UIManager.setLookAndFeel(s);
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                Logger.getLogger(Ejecucion.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            new SplashScreen().setVisible(true);
        }else{
            System.exit(0);
        }
    }
}
