
package prog2.vista;

import java.util.Scanner;
import prog2.adaptador.Adaptador;

/**
 * Classe que defineix la forma de la central. 
 * 
 * @author dortiz
 */
public class CentralUB {
    
    public final static float DEMANDA_MAX = 1600;
    public final static float DEMANDA_MIN = 200;
    public final static float VAR_NORM_MEAN = 1000;
    public final static float VAR_NORM_STD = 600;
    public final static long VAR_NORM_SEED = 123;
    
    /** Generador aleatori de la demanda de potència **/
    private VariableNormal variableNormal;
    
    /** Demanda de potència del dia actual **/
    private float demandaPotencia;
    
    private Adaptador adaptador;
    
    /**
     * Constructor per defecte.
     */
    public CentralUB() {
        variableNormal = new VariableNormal(VAR_NORM_MEAN, VAR_NORM_STD, VAR_NORM_SEED);
        demandaPotencia = generaDemandaPotencia();
        
        // Inicialitzem l'adaptador
        adaptador = new Adaptador();

    }
    
    
    // Declarem les opcions per a referir-se a les opcions del menú.
    static private enum OpcionsMenuPrincipal {MENU_PRINCIPAL_OPCIO1, MENU_PRINCIPAL_OPCIO2, MENU_PRINCIPAL_OPCIO3, MENU_PRINCIPAL_OPCIO4, 
                                              MENU_PRINCIPAL_OPCIO5, MENU_PRINCIPAL_OPCIO6, MENU_PRINCIPAL_OPCIO7, MENU_PRINCIPAL_OPCIO8, 
                                              MENU_PRINCIPAL_OPCIO9, MENU_PRINCIPAL_SORTIR};
    
    static private enum OpcionsSubmenu1 {MENU_S1_OPCIO1,MENU_S1_OPCIO2,MENU_S1_SORTIR};
    
    static private enum OpcionsSubmenu2 {MENU_S2_OPCIO1,MENU_S2_OPCIO2,MENU_S2_OPCIO3,MENU_S2_SORTIR}
    
    static private enum OpcionsSubmenu3 {MENU_S3_OPCIO1,MENU_S3_OPCIO2,MENU_S3_OPCIO3,MENU_S3_SORTIR}
    
    // Declarem descripcions personalitzades per a les opcions del menú principal
    static private String[] descMenuPrincipal = {"Gestió Barres de Control",
                                                 "Gestió Reactor",
                                                 "Gestió Sistema Refrigeració",
                                                 "Mostrar Estat Central",
                                                 "Mostrar Bitàcola",
                                                 "Mostrar Incidències",
                                                 "Finalitzar Dia",
                                                 "Guardar Dades",
                                                 "Carrega Dades",
                                                 "Sortir"};
    

    // Declarem descripcions personalitzades per a les opcions dels menús secundaris
    static private String[] descSubMenu1 = {"Obtenir Inserció Barres",
                                            "Establir Inserció Barres",
                                            "Sortir"};
    
    static private String[] descSubMenu2 = {"Activar Reactor",
                                            "Desactivar Reactor",
                                            "Mostrar Estat",
                                            "Sortir"};
    
    static private String[] descSubMenu3 = {"Activar Bomba",
                                            "Desactivar Bomba",
                                            "Mostrar Estat",
                                            "Sortir"};
    
    
    /**
     * Gestiona el menú principal de la central. 
     */
    public void gestioCentralUB() {
        // Mostrar missatge inicial
        System.out.println("Benvingut a la planta PWR de la UB");
        System.out.println("La demanda de potència elèctrica avui es de " + demandaPotencia + " unitats");

        // Creem un objecte per llegir des del teclat
        Scanner sc = new Scanner(System.in);

        // Creem un objecte de tipus Menu
        Menu<OpcionsMenuPrincipal> menu = new Menu<>("Menú de la central", OpcionsMenuPrincipal.values());
         
        // Assignem la descripció de les opcions
        menu.setDescripcions(descMenuPrincipal);

        // Obtenim una opció des del menú i fem les accions pertinents
        OpcionsMenuPrincipal opcio;
        
        do {
            // Mostrem les opcions del menú
            menu.mostrarMenu();

            // Demanem una opció
            opcio = menu.getOpcio(sc);

            // Fem les accions necessàries
            switch (opcio) {
                
                case MENU_PRINCIPAL_OPCIO1: // Gestió de les barres de control
                    // Cridem el métode de gestió del menú secundari
                    gestioMenuSecundari1(sc);
                    
                    break;
                    
                case MENU_PRINCIPAL_OPCIO2: // Gestió del reactor
                    // Cridem el métode de gestió del menú secundari
                    gestioMenuSecundari2(sc);
                    
                    break;
                    
                case MENU_PRINCIPAL_OPCIO3: // Gestió del sistema de refrigeració
                    // Cridem el métode de gestió del menú secundari
                    gestioMenuSecundari3(sc);
                    
                    break;
                    
                case MENU_PRINCIPAL_OPCIO4: // Mostrar l'estat de la central
                    System.out.println(adaptador.mostraEstatCentral(demandaPotencia));
                    break;
                
                case MENU_PRINCIPAL_OPCIO5: // Mostrar la bitàcola
                    System.out.println(adaptador.mostraBitacola());
                    break;
                
                case MENU_PRINCIPAL_OPCIO6: // Mostrar les incidències
                    System.out.println(adaptador.mostraIncidencies());
                    break;
                    
                case MENU_PRINCIPAL_OPCIO7: // Finalitzar el dia
                    finalitzaDia();
                    break;
                    
                case MENU_PRINCIPAL_OPCIO8: // Guardar les dades
                    try {
                        // Demanem el nom del fitxer on es vol guardar
                        System.out.print("Introdueix el nom del fitxer on vols guardar les dades: ");
                        String nomFitxer = sc.nextLine();
                    
                        // Guardem les dades
                        adaptador.guardaDades(nomFitxer);
                        
                        System.out.println("Dades guardades correctament.");
                    } catch(CentralUBException e) {
                        System.err.println(e.getMessage());
                    }
                    
                    break;
                    
                case MENU_PRINCIPAL_OPCIO9: // Carregar les dades
                    try {
                        // Demanem el nom del fitxer des d'on es vol carregar
                        System.out.print("Introdueix el nom del fitxer des d'on vols carregar les dades: ");
                        String nomFitxer = sc.nextLine();
                    
                        // Carreguem les dades
                        adaptador.carregaDades(nomFitxer);
                        
                        System.out.println("Dades carregades correctament.");
                    } catch(CentralUBException e) {
                        System.err.println(e.getMessage());
                    }
                    
                    break;
                
                case MENU_PRINCIPAL_SORTIR:
                    System.out.println("Fins aviat!");
                    break;
            }

        } while (opcio != OpcionsMenuPrincipal.MENU_PRINCIPAL_SORTIR);
        
    }
    
    /**
     * Genera pseudoaleatòriament la demana de potència del dia.
     * @return float
     */
    private float generaDemandaPotencia(){
        float valor = Math.round(variableNormal.seguentValor());
        if (valor > DEMANDA_MAX)
            return DEMANDA_MAX;
        else
            if (valor < DEMANDA_MIN)
                return DEMANDA_MIN;
            else
                return valor;
    }
    
    
    /**
     * Gestió al final del dia.
     */
    private void finalitzaDia() {
        // Finalitzar dia i imprimir informacio de la central
        String info = new String();
        info = adaptador.finalitzaDia(demandaPotencia);
        System.out.println(info);
        System.out.println("Dia finalitzat\n");
        
        // Generar i mostrar nova demanda de potencia
        demandaPotencia = generaDemandaPotencia();
        System.out.println("La demanda de potència elèctrica avui es de " + demandaPotencia + " unitats");
    }
    
    
    /**
     * Menú secundari 1.
     * @param sc Objecte de tipus Scanner que permet accedir al teclat
     */
    private void gestioMenuSecundari1(Scanner sc) {

        // Creem l'objecte per al menú. Li passem com a primer paràmetre el nom del menú
        Menu<OpcionsSubmenu1> menu = new Menu<>("Gestió Barres Control", OpcionsSubmenu1.values());

        // Assignem la descripció de les opcions
        menu.setDescripcions(descSubMenu1);

        // Obtenim una opció des del menú i fem les accions pertinents
        OpcionsSubmenu1 opcio;
        
        do {
            // Mostrem les opcions del menú
            menu.mostrarMenu();

            // Demanem una opció
            opcio = menu.getOpcio(sc);

            // Fem les accions necessàries
            switch (opcio) {
                case MENU_S1_OPCIO1: // Obtenir inserció barres
                    System.out.println("Grau d'inserció de les barres: " + adaptador.getInsercioBarres() + " %");
                    break;
                    
                case MENU_S1_OPCIO2: // Establir inserció barres
                    // Demanem el percentatge d'inserció
                    System.out.print("Nou grau d'inserció de les barres: ");
                    float insercioBarres = sc.nextFloat();
                    sc.nextLine();
                    
                    // Intentem establir la inserció 
                    try {
                        adaptador.setInsercioBarres(insercioBarres);
                        System.out.println("Nou grau d'inserció de barres establert.");
                    } catch(CentralUBException e) {
                        System.err.println(e.getMessage());
                    }
                    
                    break;
                    
                case MENU_S1_SORTIR:
                    System.out.println("Tornant al menú principal...");
                    break;
            }

        } while (opcio != OpcionsSubmenu1.MENU_S1_SORTIR);
    }
    
    
    /**
     * Menú secundari 2.
     * @param sc Objecte de tipus Scanner que permet accedir al teclat
     */
    private void gestioMenuSecundari2(Scanner sc) {

        // Creem l'objecte per al menú. Li passem com a primer paràmetre el nom del menú
        Menu<OpcionsSubmenu2> menu = new Menu<>("Gestió Reactor", OpcionsSubmenu2.values());

        // Assignem la descripció de les opcions
        menu.setDescripcions(descSubMenu2);

        // Obtenim una opció des del menú i fem les accions pertinents
        OpcionsSubmenu2 opcio;
        
        do {
            // Mostrem les opcions del menú
            menu.mostrarMenu();

            // Demanem una opció
            opcio = menu.getOpcio(sc);

            // Fem les accions necessàries
            switch (opcio) {
                case MENU_S2_OPCIO1: // Activar reactor
                    try {
                        adaptador.activaReactor();
                        System.out.println("Reactor activat.");
                    } catch(CentralUBException e) {
                        System.err.println(e.getMessage());
                    }
                    
                    break;
                    
                case MENU_S2_OPCIO2: // Desactivar reactor
                    adaptador.desactivaReactor();
                    System.out.println("Reactor desactivat.");
                    
                    break;
                    
                case MENU_S2_OPCIO3: // Mostrar estat
                    System.out.println(adaptador.mostraReactor());
                    break;
                    
                case MENU_S2_SORTIR:
                    System.out.println("Tornant al menú principal...");
                    break;
            }

        } while (opcio != OpcionsSubmenu2.MENU_S2_SORTIR);
    }
    
    
    /**
     * Menú secundari 3.
     * @param sc Objecte de tipus Scanner que permet accedir al teclat
     */
    private void gestioMenuSecundari3(Scanner sc) {

        // Creem l'objecte per al menú. Li passem com a primer paràmetre el nom del menú
        Menu<OpcionsSubmenu3> menu = new Menu<>("Gestió Sistema Refrigeració", OpcionsSubmenu3.values());

        // Assignem la descripció de les opcions
        menu.setDescripcions(descSubMenu3);

        // Obtenim una opció des del menú i fem les accions pertinents
        OpcionsSubmenu3 opcio;
        
        do {
            // Mostrem les opcions del menú
            menu.mostrarMenu();

            // Demanem una opció
            opcio = menu.getOpcio(sc);

            // Fem les accions necessàries
            switch (opcio) {
                case MENU_S3_OPCIO1: // Activar bomba
                    // Demanem l'identificador de la bomba
                    System.out.print("Identificador de la bomba (0,1,2,3): ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    
                    // Intentem activar la bomba
                    try {
                        adaptador.activaBomba(id);
                        System.out.println("Bomba activada.");
                    } catch(CentralUBException e) {
                        System.err.println(e.getMessage());
                    }
                    
                    break;
                    
                case MENU_S3_OPCIO2: // Desactivar bomba
                    // Demanem l'identificador de la bomba
                    System.out.print("Identificador de la bomba (0,1,2,3): ");
                    id = sc.nextInt();
                    sc.nextLine();
                    
                    // Desactivem la bomba
                    adaptador.desactivaBomba(id);
                    System.out.println("Bomba desactivada.");
                    
                    break;
                    
                case MENU_S3_OPCIO3: // Mostrar estat
                    System.out.println(adaptador.mostraSistemaRefrigeracio());
                    break;
                    
                case MENU_S3_SORTIR:
                    System.out.println("Tornant al menú principal...");
                    break;
            }

        } while (opcio != OpcionsSubmenu3.MENU_S3_SORTIR);
    }
}
