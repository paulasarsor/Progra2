
package prog2.vista;

import java.util.Scanner;
import prog2.model.EstacioTren;

/**
 *
 * @author paula
 *
 * La classe VistaEstacioTren és responsable de la interacció amb l’usuari. 
 * Conté el mètode main.
 * 
 */
public class VistaEstacioTren {
    
    /**
     * Estació de tren 
     */
    private EstacioTren estacioTren_;
    
    
    /**
     * Constructor amb paràmetres. Instancia l'estació de tren i la inicialitza.
     * @param nom String
     */
    public VistaEstacioTren(String nom) {
        estacioTren_ = new EstacioTren(nom);
        try {
            estacioTren_.inicialitzaDadesEstacioTren();
        } catch(ExcepcioEstacio e) {
            System.out.println(e.getMessage());
        }
    }

    
    // Declarem les opcions per a referir-se a les opcions del menú.
    static private enum OpcionsMenu{MENU_OPCIO1, MENU_OPCIO2, MENU_OPCIO3, MENU_OPCIO4, MENU_OPCIO5, MENU_OPCIO6, MENU_OPCIO7, 
                                    MENU_OPCIO8, MENU_OPCIO9, MENU_OPCIO10, MENU_OPCIO11, MENU_OPCIO12, MENU_SORTIR};
    
    // Declarem descripcions personalitzades per a les opcions del menú principal
    static private String[] descMenu = {"Llistar la informació de totes les vies",
                                        "Llistar la informació de les vies obertes",
                                        "Llistar la informació de les vies tancades",
                                        "Llistar la informació dels accessos oberts",
                                        "Llistar la informació dels accessos tancats",
                                        "Llistar la informació de les incidències actuals",
                                        "Afegir una incidència",
                                        "Eliminar una incidència",
                                        "Calcular i mostrar el número total d’accessos que proporcionen accessibilitat",
                                        "Calcular i mostrar el número total de metres de longitud d’accessos de nivell",
                                        "Guardar estació",
                                        "Recuperar estació",
                                        "Sortir de l'aplicació"};

    
    
    /** 
     * Mètode que gestiona el menú principal de l'estació.
     */
    public void gestioEstacioTren() {
        // Creem un objecte per llegir des del teclat
        Scanner sc = new Scanner(System.in);

        // Creem un objecte de tipus Menu
        Menu<OpcionsMenu> menu = new Menu<>("Menu de l'estació", OpcionsMenu.values());
         
        // Assignem la descripció de les opcions
        menu.setDescripcions(descMenu);

        // Obtenim una opció des del menú i fem les accions pertinents
        OpcionsMenu opcio;
        
        do {
            // Mostrem les opcions del menú
            menu.mostrarMenu();

            // Demanem una opció
            opcio = menu.getOpcio(sc);

            // Fem les accions necessàries
            switch (opcio) {
                
                case MENU_OPCIO1: // Llistar la informació de totes les vies
                    try {
                        System.out.println(estacioTren_.llistarVies("totes"));
                    } catch(ExcepcioEstacio e) {
                        System.err.println(e.getMessage());
                    }
                    
                    break;
                    
                case MENU_OPCIO2: // Llistar la informació de les vies obertes
                    try {
                        System.out.println(estacioTren_.llistarVies("oberta"));
                    } catch(ExcepcioEstacio e) {
                        System.err.println(e.getMessage());
                    }
                    
                    break;
                    
                case MENU_OPCIO3: // Llistar la informació de les vies tancades
                    try {
                        System.out.println(estacioTren_.llistarVies("tancada"));
                    } catch(ExcepcioEstacio e) {
                        System.err.println(e.getMessage());
                    }
                    
                    break;
                    
                case MENU_OPCIO4: // Llistar la informació dels accessos oberts
                    try {
                        System.out.println(estacioTren_.llistarAccessos(true));
                    } catch(ExcepcioEstacio e) {
                        System.err.println(e.getMessage());
                    }
                    
                    break;
                
                case MENU_OPCIO5: // Llistar la informació dels accessos tancats
                    try {
                        System.out.println(estacioTren_.llistarAccessos(false));
                    } catch(ExcepcioEstacio e) {
                        System.err.println(e.getMessage());
                    }
                    
                    break;
                
                case MENU_OPCIO6: // Llistar la informació de les incidències actuals 
                    try {
                        System.out.println(estacioTren_.llistarIncidencies());
                    } catch(ExcepcioEstacio e) {
                        System.err.println(e.getMessage());
                    }
                    
                    break;
                    
                case MENU_OPCIO7: // Afegir una incidència
                    try {
                        // Demanem les dades de la incidència
                        System.out.println("Número de la incidència: "); 
                        int num = sc.nextInt();
                        sc.nextLine(); // Netegem el buffer
                    
                        System.out.println("Nom de la via: "); 
                        String nomVia = sc.nextLine();
                    
                        System.out.println("Tipus d'incidència (Reparacio, Objecte, Tancament): ");
                        String tipus = sc.nextLine();
                    
                        System.out.println("Data de la incidència: ");
                        String data = sc.nextLine();
                        
                        // Intentem afegir la incidència 
                        estacioTren_.afegirIncidencia(num, tipus, nomVia, data);
                    } catch(ExcepcioEstacio e) {
                        System.err.println(e.getMessage());
                    }
                    
                    break;
                    
                case MENU_OPCIO8: // Eliminar una incidència
                    try {
                        // Demanem les dades de la incidència
                        System.out.println("Número de la incidència: "); 
                        int num = sc.nextInt();

                        // Intentem eliminar l'incidència
                        estacioTren_.eliminarIncidencia(num);
                    } catch(ExcepcioEstacio e) {
                        System.err.println(e.getMessage());
                    }
                    
                    break;
                    
                case MENU_OPCIO9: // Calcular i mostrar el número total d’accessos que proporcionen accessibilitat
                    try {
                        int num = estacioTren_.calculaAccessosAccessibles();
                        System.out.println("L'estació té " + num + " accessos accessibles.");
                    } catch(ExcepcioEstacio e) {
                        System.err.println(e.getMessage());
                    }
                    
                    break;
                
                case MENU_OPCIO10: // Calcular i mostrar el número total de metres de longitud d’accessos de nivell
                    try {
                        float longitud = estacioTren_.calculaLongitudAccessosNivell();
                        System.out.println("Els accessos a nivell de l'estació tenen un total de " + longitud + " metres de longitud.");
                    } catch(ExcepcioEstacio e) {
                        System.err.println(e.getMessage());
                    }
                    
                    break;
                
                case MENU_OPCIO11: // Guardar estació
                    try {
                        // Demanem el nom del fitxer on es vol guardar
                        System.out.println("Introdueix el nom del fitxer on vols guardar l'estació: ");
                        String nomFitxer = sc.nextLine();
                    
                        // Guardem l'estació
                        estacioTren_.guardar(nomFitxer);
                        
                        System.out.println("Estació guardada correctament.");
                    } catch(ExcepcioEstacio e) {
                        System.err.println(e.getMessage());
                    }
                    
                    break;

                
                case MENU_OPCIO12: // Recuperar estació
                    try {
                        // Demanem el nom del fitxer des d'on es vol carregar 
                        System.out.println("Introdueix el nom del fitxer des d'on vols carregar l'estació: ");
                        String nomFitxer = sc.nextLine();
                    
                        // Guardem l'estació
                        estacioTren_ = EstacioTren.carregar(nomFitxer);
                        
                        System.out.println("Estació carregada correctament.");
                    } catch(ExcepcioEstacio e) {
                        System.err.println(e.getMessage()); 
                    }
                    
                    break;


                
                case MENU_SORTIR:
                    System.out.println("Fins aviat!");
                    break;
            }

        } while (opcio != OpcionsMenu.MENU_SORTIR);
        
    }
    
}
