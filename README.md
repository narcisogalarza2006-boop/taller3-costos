# Taller Grupal 3: Lambdas y patrón Strategy

**Universidad:** Universidad Agraria del Ecuador  
**Asignatura:** Programación 3 · POO Avanzada  
**Semana:** 3  
**Paralelo:** C  
**Grupo:** 08  
**Docente:** Ing. Hamilton Villamar  
**Fecha:** 31 de octubre de 2026  

**Repositorio:** [taller3-costos en GitHub](https://github.com/narcisogalarza2006-boop/taller3-costos)  
**Pull request:** [Rama taller-03 hacia main](https://github.com/narcisogalarza2006-boop/taller3-costos/pull/1)

Calculadora de costos de producción para una finca ecuatoriana que produce banano, cacao y arroz.

## Integrantes y roles

| N.º | Apellidos y nombres | Rol |
|---:|---|---|
| 1 | Verónica Anchundia | Coordinadora |
| 2 | Narciso Galarza | Desarrollador principal |
| 3 | Brithany Rivera | Revisora de calidad |
| 4 | Alfonso Olvera | Integrante |
| 5 | Michael Caicedo | Integrante |

## Descripción

El programa calcula los costos de producción de lotes agrícolas con distintas estrategias intercambiables. Está implementado en Java 21 y aplica el patrón **Strategy**, expresiones lambda, interfaces funcionales, una jerarquía sellada de cultivos, *pattern matching* y `Optional`.

El diseño separa los cultivos y los lotes del cálculo de costos para poder aplicar distintas fórmulas.

## Estrategias de cálculo

1. Costo directo de insumos.
2. Insumos más mano de obra.
3. Costo por hectárea.
4. Subsidio del 20 % a la urea.
5. Aplicación del IVA del 15 % sobre otra estrategia.

## Tecnologías y requisitos

- Java JDK 21.
- Apache Maven 3.9 o superior.
- JUnit 5 para las pruebas automatizadas.
- IntelliJ IDEA, opcional para abrir y ejecutar el proyecto.

Verifica las versiones instaladas desde una terminal:

```bash
java -version
mvn -version
```

Java debe mostrar la versión 21 y Maven la versión 3.9 o superior.

## Estructura del proyecto

```text
taller3-costos/
├── src/
│   ├── main/java/ec/uagraria/costos/
│   │   ├── Arroz.java
│   │   ├── Banano.java
│   │   ├── Cacao.java
│   │   ├── CalculadoraCostos.java
│   │   ├── Cultivo.java
│   │   ├── EstrategiaCosto.java
│   │   ├── Estrategias.java
│   │   ├── Insumo.java
│   │   ├── Lote.java
│   │   └── Main.java
│   └── test/java/ec/uagraria/costos/
│       └── CalculadoraCostosTest.java
├── .gitignore
├── pom.xml
└── README.md
```

## Compilar el proyecto

Abre una terminal en la carpeta del proyecto, donde está el archivo `pom.xml`, y ejecuta:

```bash
mvn clean compile
```

## Ejecutar las pruebas

El proyecto incluye 14 pruebas automatizadas con JUnit 5. Para ejecutarlas, escribe:

```bash
mvn clean test
```

Si Maven termina con `BUILD SUCCESS`, las pruebas pasaron correctamente.

En IntelliJ IDEA también puedes abrir `CalculadoraCostosTest.java`, hacer clic derecho sobre el archivo y seleccionar **Run**.

## Ejecutar el programa

Para iniciar la demostración desde la terminal:

```bash
mvn exec:java
```

En IntelliJ IDEA, abre `Main.java` y pulsa el botón verde **Run**.

## Resultados verificados

Para un lote de banano de 4 hectáreas y uno de cacao orgánico de 2,5 hectáreas:

| Caso | Resultado |
|---|---:|
| Calculadora sin estrategia | `Optional.empty` |
| Costo directo del banano | USD 744,00 |
| Insumos más mano de obra | USD 2.076,00 |
| Costo por hectárea | USD 519,00 |
| Subsidio del 20 % a la urea | USD 1.998,00 |
| Costo con IVA del 15 % | USD 2.387,40 |
| Jornales por hectárea del cacao orgánico | 25 |
| Insumos más mano de obra del cacao orgánico | USD 1.612,75 |

##Captura 
<img width="1919" height="1013" alt="image" src="https://github.com/user-attachments/assets/2fb7c70b-74d7-45c3-85e5-8f0e4e80d6b8" />

## Flujo de trabajo con Git

El desarrollo se realizó en la rama `taller-03` y se abrió un pull request hacia `main`. El grupo distribuyó las responsabilidades entre coordinación, desarrollo y revisión de calidad.

##Honestidad académica
Cada integrante realiza sus propios commits desde su cuenta de GitHub. El uso de asistentes de inteligencia artificial se declara en el informe del taller
