
package prog2.vista;

/**
 * Classe principal de la vista. Crea una central i la gestiona.
 * 
 * @author claudia
 */
public class IniciadorCentralUB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Creem un objecte de la classe CentralUB
        CentralUB central = new CentralUB();
            
        // Gestió de la central
        central.gestioCentralUB();
    }
    
}
