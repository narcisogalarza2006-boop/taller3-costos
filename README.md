# Taller Grupal 3 – Lambdas y patrón Strategy

**Asignatura:** Programación 3 (POO Avanzada) · Universidad Agraria del Ecuador
**Semana:** 3 · **Paralelo:** [completar]
**Grupo:** [nombre del grupo]

## Integrantes y roles

| Integrante | Rol |
|---|---|
| [Nombre y apellido] | Coordinador (organiza el avance y el repositorio) |
| [Nombre y apellido] | Desarrollador principal (escribe el núcleo del código) |
| [Nombre y apellido] | Revisor de calidad (pruebas, pull request y formato) |
| [Nombre y apellido] | Integrante |
| [Nombre y apellido] | Integrante |

> Los roles pueden rotar; si cambian durante el taller, se actualizan aquí y en el informe.

## ¿De qué trata el proyecto?

Una finca del litoral ecuatoriano produce banano, cacao y arroz y necesita calcular
el costo de producción de cada lote de varias maneras. En lugar de meter todas las
fórmulas dentro de una sola clase, las dejamos como **estrategias intercambiables**
(patrón Strategy) escritas con **lambdas**, sobre una **jerarquía sellada** de cultivos
que se recorre con **pattern matching**. La ausencia de resultado se maneja con
`Optional`, nunca con `null`.

Las cinco estrategias disponibles son:

1. Costo directo (solo insumos)
2. Insumos + mano de obra
3. Costo por hectárea
4. Con subsidio al fertilizante (urea)
5. Con IVA (envuelve a cualquier otra estrategia)

## Estructura del paquete `ec.uagraria.costos`

| Archivo | Qué contiene |
|---|---|
| `Insumo` | record con validación en el constructor compacto |
| `Cultivo` | interfaz sellada (`permits Banano, Cacao, Arroz`) |
| `Banano`, `Cacao`, `Arroz` | records que implementan `Cultivo` |
| `Lote` | record con el `switch` con patrones (`jornalesPorHectarea`) |
| `EstrategiaCosto` | interfaz funcional (contrato de Strategy) |
| `Estrategias` | fábrica con las cinco lambdas |
| `CalculadoraCostos` | contexto del patrón; devuelve `Optional<Double>` |
| `Main` | programa de demostración |

Las pruebas están en `src/test/java/ec/uagraria/costos/CalculadoraCostosTest.java`.

## Cómo ejecutar

Requisitos: JDK 21 y Maven 3.9 o superior (o IntelliJ IDEA Community con JDK 21).

```bash
git clone <url-del-repositorio>
cd <carpeta-del-proyecto>
git checkout taller-03

mvn -q clean compile          # compila sin errores
mvn -q test                   # ejecuta las pruebas de JUnit 5
mvn -q exec:java              # corre la demostración (clase Main)
```

En IntelliJ: abrir el proyecto, verificar en *File → Project Structure → Project SDK*
que diga 21, y ejecutar `Main` con el botón verde. Para las pruebas, clic derecho sobre
`CalculadoraCostosTest` → *Run*.

Si en Windows los acentos salen como símbolos raros en la consola, ejecutar con
`-Dstdout.encoding=UTF-8`.

## Resultados esperados (lote de banano de 4 ha)

| Estrategia | Resultado |
|---|---|
| Sin estrategia configurada | `Optional.empty` |
| Costo directo | USD 744,00 |
| Insumos + mano de obra | USD 2 076,00 |
| Costo por hectárea | USD 519,00 |
| Subsidio del 20 % a la urea | USD 1 998,00 |
| Con IVA del 15 % | USD 2 387,40 |
| Cacao orgánico (2,5 ha): jornales por hectárea | 25 |
| Cacao orgánico: insumos + mano de obra | USD 1 612,75 |

## Flujo de trabajo con Git

- Rama de trabajo: `taller-03`, con pull request hacia `main`.
- Todos los integrantes tienen al menos dos commits propios.
- El revisor de calidad deja su comentario de revisión en el pull request antes de fusionar.
