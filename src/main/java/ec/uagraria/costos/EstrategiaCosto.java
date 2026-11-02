package ec.uagraria.costos;

/**
 * Contrato del patrón Strategy: cada fórmula de costo es una implementación
 * de esta interfaz. Como tiene un único método abstracto, se puede escribir
 * como una expresión lambda.
 */
@FunctionalInterface
public interface EstrategiaCosto {

    /** Calcula el costo del lote según la fórmula de la estrategia. */
    double calcular(Lote lote);

    default String descripcion() {
        return "Estrategia de costo";
    }
}
