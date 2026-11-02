package ec.uagraria.costos;

import java.util.Optional;

/**
 * Contexto del patrón Strategy. La calculadora usa la estrategia que tenga
 * configurada, pero no sabe cómo calcula: solo le delega el trabajo.
 */
public class CalculadoraCostos {

    private EstrategiaCosto estrategia;

    public CalculadoraCostos() { }

    public CalculadoraCostos(EstrategiaCosto estrategia) {
        this.estrategia = estrategia;
    }

    /** Cambia la fórmula en tiempo de ejecución, sin tocar esta clase. */
    public void setEstrategia(EstrategiaCosto estrategia) {
        this.estrategia = estrategia;
    }

    /**
     * Calcula el costo del lote. Si todavía no hay estrategia (o el lote es
     * nulo) devuelve Optional vacío en lugar de lanzar NullPointerException.
     */
    public Optional<Double> calcular(Lote lote) {
        if (estrategia == null || lote == null) {
            return Optional.empty();
        }
        return Optional.of(estrategia.calcular(lote));
    }

    /** Redondea a dos decimales para mostrar valores en dólares. */
    public static double redondear(double valor) {
        return Math.round(valor * 100.0) / 100.0;
    }
}
