
package prog2.vista;

/**
 *
 * @author claudia
 * 
 * Classe que crea una estació de tren anomenada Green i la  gestiona.
 * 
 */
public class IniciadorEstacioTren {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Definim el nom de l'estació de tren
        String nomEstacio = "Green";

        // Creem un objecte de la vista i li passem el nom de l'estació
        VistaEstacioTren vistaEstacioTren = new VistaEstacioTren(nomEstacio);
            
        // Inicialitzem l'execució de la vista
        vistaEstacioTren.gestioEstacioTren();
    }
        
}

