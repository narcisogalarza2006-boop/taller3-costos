package ec.uagraria.costos;

/**
 * Cultivos que maneja la finca. La interfaz está sellada: solo Banano,
 * Cacao y Arroz pueden implementarla, así el compilador conoce todos los
 * casos posibles y puede exigir que el switch los cubra completos.
 */
public sealed interface Cultivo permits Banano, Cacao, Arroz {

    /** Nombre con el que se identifica el cultivo en reportes. */
    String nombreComun();
}
