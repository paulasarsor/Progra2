
package prog2.model;

import java.io.Serializable;
import java.util.Random;

/**
 * Classe que s'usa per determinar aleatòriament si una bomba refrigerant estarà fora de servei al començar un nou dia. 
 * 
 * @author dortiz
 */
public class VariableUniforme implements Serializable {
    private Random random;

    public VariableUniforme(long seed) {
        this.random = new Random(seed);
    }

    public int seguentValor() {
        return random.nextInt(100); // Retorna un valor enter pseudoaleatori entre 0 i 100
    }
}
