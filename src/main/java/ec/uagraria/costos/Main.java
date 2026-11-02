package ec.uagraria.costos;

import java.util.List;
import java.util.Locale;

/**
 * Programa de demostración: aplica las cinco estrategias al lote de banano
 * y luego revisa el lote de cacao orgánico de la tabla de verificación.
 */
public class Main {

    public static void main(String[] args) {
        Lote lote = new Lote("L-01", new Banano("Cavendish"), 4.0, List.of(
                new Insumo("Urea 50 kg", 12, "saco", 32.50),
                new Insumo("Fungicida", 8, "litro", 21.75),
                new Insumo("Semilla certificada", 1, "lote", 180.00)));

        CalculadoraCostos calc = new CalculadoraCostos();

        System.out.println("=== Lote " + lote.codigo() + ": " + lote.cultivo().nombreComun()
                + " (" + lote.hectareas() + " ha) ===");

        // Sin estrategia configurada: Optional vacío, sin NullPointerException
        System.out.println(String.format("%-24s-> %s", "Sin estrategia", calc.calcular(lote)));

        calc.setEstrategia(Estrategias.costoDirecto());
        mostrar("Costo directo", calc, lote);

        calc.setEstrategia(Estrategias.costoConManoDeObra());
        mostrar("Insumos + mano de obra", calc, lote);

        calc.setEstrategia(Estrategias.costoPorHectarea());
        mostrar("Costo por hectárea", calc, lote);

        calc.setEstrategia(Estrategias.costoConSubsidioFertilizante(0.20));
        mostrar("Subsidio 20 % a la urea", calc, lote);

        calc.setEstrategia(Estrategias.conIva(Estrategias.costoConManoDeObra()));
        mostrar("Con IVA del 15 %", calc, lote);

        // Lote de cacao orgánico: activa la rama "when" del switch
        Lote cacao = new Lote("L-02", new Cacao("Nacional", true), 2.5, List.of(
                new Insumo("Urea 50 kg", 5, "saco", 32.50),
                new Insumo("Abono orgánico", 30, "saco", 9.80)));

        System.out.println();
        System.out.println("=== Lote " + cacao.codigo() + ": " + cacao.cultivo().nombreComun()
                + " orgánico (" + cacao.hectareas() + " ha) ===");
        System.out.println("Jornales por hectárea -> " + cacao.jornalesPorHectarea());

        calc.setEstrategia(Estrategias.costoConManoDeObra());
        mostrar("Insumos + mano de obra", calc, cacao);
    }

    /** Imprime el resultado en dólares; si no hay valor, lo indica. */
    private static void mostrar(String etiqueta, CalculadoraCostos calc, Lote lote) {
        String texto = calc.calcular(lote)
                .map(CalculadoraCostos::redondear)
                .map(v -> String.format(Locale.of("es", "EC"), "USD %,.2f", v))
                .orElse("sin resultado");
        System.out.println(String.format("%-24s-> %s", etiqueta, texto));
    }
}
