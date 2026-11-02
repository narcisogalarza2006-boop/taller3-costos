package ec.uagraria.costos;

import java.util.List;

/**
 * Un lote de la finca: reúne el cultivo sembrado, la superficie en hectáreas
 * y los insumos que se usaron en él.
 */
public record Lote(String codigo, Cultivo cultivo, double hectareas,
                   List<Insumo> insumos) {

    public Lote {
        insumos = List.copyOf(insumos);   // copia defensiva: la lista queda inmutable
    }

    /** Suma el costo de todos los insumos del lote. */
    public double costoInsumos() {
        double total = 0.0;
        for (Insumo i : insumos) {
            total += i.costo();
        }
        return total;
    }

    /**
     * Jornales necesarios por hectárea según el tipo de cultivo.
     * El switch con patrones recorre la jerarquía sellada sin casteos.
     */
    public int jornalesPorHectarea() {
        return switch (cultivo) {
            case Banano b -> 18;
            case Cacao c when c.organico() -> 25;   // el caso más específico va primero
            case Cacao c -> 20;
            case Arroz a -> 12 * a.ciclosPorAnio();
        };   // sin default: la jerarquía sellada ya está completa
    }
}
