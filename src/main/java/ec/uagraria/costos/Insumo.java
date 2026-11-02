package ec.uagraria.costos;

/**
 * Un insumo agrícola (fertilizante, fungicida, semilla, etc.).
 * Es un dato inmutable, por eso se modela como record.
 */
public record Insumo(String nombre, double cantidad, String unidad,
                     double precioUnitario) {

    // Constructor compacto: valida los datos antes de construir el objeto
    public Insumo {
        if (cantidad < 0 || precioUnitario < 0) {
            throw new IllegalArgumentException(
                    "Cantidad y precio deben ser mayores o iguales a cero");
        }
    }

    /** Costo total del insumo = cantidad x precio unitario. */
    public double costo() {
        return cantidad * precioUnitario;
    }
}
