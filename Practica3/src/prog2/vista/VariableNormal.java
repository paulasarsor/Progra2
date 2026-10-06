
package prog2.vista;

import java.util.Random;

/**
 * Classe que s'usa per generar la demanda de potència de la central de cada nou dia.
 * 
 * @author dortiz
 */
public class VariableNormal {
    private Random random;
    private float mean;
    private float standardDeviation;

    public VariableNormal(float mean, float standardDeviation, long seed) {
        this.mean = mean;
        this.standardDeviation = standardDeviation;
        this.random = new Random(seed);
    }

    public float seguentValor() {
        return (float) mean + standardDeviation * (float) random.nextGaussian();
    }

}
