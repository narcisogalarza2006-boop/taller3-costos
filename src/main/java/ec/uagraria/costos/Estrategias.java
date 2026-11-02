package ec.uagraria.costos;

/**
 * Fábrica de estrategias de costo. Cada método devuelve una lambda que
 * representa una fórmula distinta.
 */
public final class Estrategias {

    public static final double VALOR_JORNAL = 18.50;   // jornal referencial (USD)
    public static final double IVA = 0.15;             // IVA vigente en Ecuador

    private Estrategias() { }   // clase de utilidades: no se instancia

    /** 1. Solo insumos. */
    public static EstrategiaCosto costoDirecto() {
        return lote -> lote.costoInsumos();
    }

    /** 2. Insumos + mano de obra (jornales por hectárea x hectáreas x valor del jornal). */
    public static EstrategiaCosto costoConManoDeObra() {
        return lote -> lote.costoInsumos()
                + lote.jornalesPorHectarea() * lote.hectareas() * VALOR_JORNAL;
    }

    /** 3. Costo total con mano de obra dividido para las hectáreas del lote. */
    public static EstrategiaCosto costoPorHectarea() {
        return lote -> costoConManoDeObra().calcular(lote) / lote.hectareas();
    }

    /**
     * 4. Costo con mano de obra menos el subsidio aplicado a la urea.
     * El porcentaje llega como parámetro (0,20 equivale al 20 %).
     */
    public static EstrategiaCosto costoConSubsidioFertilizante(double porcentaje) {
        return lote -> {
            double descuento = lote.insumos().stream()
                    .filter(i -> i.nombre().toLowerCase().contains("urea"))
                    .mapToDouble(Insumo::costo)     // referencia a método
                    .sum() * porcentaje;
            return costoConManoDeObra().calcular(lote) - descuento;
        };
    }

    /** 5. Envuelve otra estrategia y le suma el IVA: así se componen fórmulas. */
    public static EstrategiaCosto conIva(EstrategiaCosto base) {
        return lote -> base.calcular(lote) * (1 + IVA);
    }
}
