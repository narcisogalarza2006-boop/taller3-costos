package ec.uagraria.costos;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CalculadoraCostosTest {

    private static final double TOLERANCIA = 0.01;

    private Lote loteBanano() {
        return new Lote("L-01", new Banano("Cavendish"), 4.0, List.of(
                new Insumo("Urea 50 kg", 12, "saco", 32.50),
                new Insumo("Fungicida", 8, "litro", 21.75),
                new Insumo("Semilla certificada", 1, "lote", 180.00)));
    }

    private Lote loteCacaoOrganico() {
        return new Lote("L-02", new Cacao("Nacional", true), 2.5, List.of(
                new Insumo("Urea 50 kg", 5, "saco", 32.50),
                new Insumo("Abono orgánico", 30, "saco", 9.80)));
    }

    // ---- Una prueba por estrategia y una para el Optional vacío ----

    @Test
    void sinEstrategiaDevuelveOptionalVacio() {
        assertTrue(new CalculadoraCostos().calcular(loteBanano()).isEmpty());
    }

    @Test
    void costoDirectoSumaLosInsumos() {
        var calc = new CalculadoraCostos(Estrategias.costoDirecto());
        assertEquals(744.00, calc.calcular(loteBanano()).orElseThrow(), TOLERANCIA);
    }

    @Test
    void costoConManoDeObraSumaJornales() {
        var calc = new CalculadoraCostos(Estrategias.costoConManoDeObra());
        assertEquals(2076.00, calc.calcular(loteBanano()).orElseThrow(), TOLERANCIA);
    }

    @Test
    void costoPorHectareaDivideParaLaSuperficie() {
        var calc = new CalculadoraCostos(Estrategias.costoPorHectarea());
        assertEquals(519.00, calc.calcular(loteBanano()).orElseThrow(), TOLERANCIA);
    }

    @Test
    void subsidioDel20PorCientoSoloAfectaLaUrea() {
        var calc = new CalculadoraCostos(Estrategias.costoConSubsidioFertilizante(0.20));
        assertEquals(1998.00, calc.calcular(loteBanano()).orElseThrow(), TOLERANCIA);
    }

    @Test
    void ivaSeComponeSobreOtraEstrategia() {
        var calc = new CalculadoraCostos(
                Estrategias.conIva(Estrategias.costoConManoDeObra()));
        assertEquals(2387.40, calc.calcular(loteBanano()).orElseThrow(), TOLERANCIA);
    }

    // ---- Pruebas del switch con patrones y del cambio de estrategia ----

    @Test
    void cacaoOrganicoUsaLaRamaConWhen() {
        assertEquals(25, loteCacaoOrganico().jornalesPorHectarea());
    }

    @Test
    void cacaoConvencionalUsaVeinteJornales() {
        var lote = new Lote("L-03", new Cacao("CCN-51", false), 1.0, List.of());
        assertEquals(20, lote.jornalesPorHectarea());
    }

    @Test
    void arrozDependeDeLosCiclosPorAnio() {
        var lote = new Lote("L-04", new Arroz(2), 1.0, List.of());
        assertEquals(24, lote.jornalesPorHectarea());
    }

    @Test
    void cacaoOrganicoConManoDeObra() {
        var calc = new CalculadoraCostos(Estrategias.costoConManoDeObra());
        assertEquals(1612.75, calc.calcular(loteCacaoOrganico()).orElseThrow(), TOLERANCIA);
    }

    @Test
    void laCalculadoraCambiaDeFormulaSinModificarse() {
        var calc = new CalculadoraCostos(Estrategias.costoDirecto());
        double antes = calc.calcular(loteBanano()).orElseThrow();
        calc.setEstrategia(Estrategias.costoConManoDeObra());
        double despues = calc.calcular(loteBanano()).orElseThrow();
        assertTrue(despues > antes);
    }

    @Test
    void loteNuloDevuelveOptionalVacio() {
        var calc = new CalculadoraCostos(Estrategias.costoDirecto());
        assertTrue(calc.calcular(null).isEmpty());
    }

    // ---- Pruebas de Insumo ----

    @Test
    void costoDeUnInsumoEsCantidadPorPrecio() {
        assertEquals(390.00, new Insumo("Urea 50 kg", 12, "saco", 32.50).costo(), TOLERANCIA);
    }

    @Test
    void insumoConPrecioNegativoSeRechaza() {
        assertThrows(IllegalArgumentException.class,
                () -> new Insumo("Urea 50 kg", 1, "saco", -5.0));
    }
}
