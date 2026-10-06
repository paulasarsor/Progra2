# Programació 2: prácticas 2 y 3 (Java)

Prácticas realizadas en pareja por Paula Sarrión Soriano y Claudia Ramírez.

Dos aplicaciones de consola en Java, orientadas a objetos (herencia, interfaces, excepciones, serialización) y organizadas en capas **modelo / vista** (y **adaptador** en la práctica 3).

| Carpeta | Proyecto |
|---|---|
| [`Practica2/`](Practica2) | Gestión de una **estación de tren**: vías, accesos (ascensores, escaleras, pasillos...) e incidencias |
| [`Practica3/`](Practica3) | Simulación de una **central nuclear PWR** (parte 1): reactor, barras de control, refrigeración, bitácora y economía |

## Requisitos

- JDK 11 o superior
- `make` (opcional, también se puede abrir cada carpeta como proyecto de NetBeans)

## Compilar y ejecutar

```bash
make all      # compila las dos prácticas en build/
make run2     # ejecuta la estación de tren
make run3     # ejecuta la central nuclear
make clean    # borra build/
```

Sin `make`, por ejemplo para la práctica 2:

```bash
mkdir -p build/practica2
javac -encoding UTF-8 -d build/practica2 $(find Practica2/src -name '*.java')
java -cp build/practica2 prog2.vista.IniciadorEstacioTren
```

(La práctica 3 usa `prog2.vista.IniciadorCentralUB`.)

En Windows, si los acentos salen mal, añade `-Dfile.encoding=UTF-8` al comando `java`.

## Práctica 2: estación de tren

Menú interactivo (13 opciones) para:

- listar vías (todas, abiertas o cerradas) y accesos (abiertos o cerrados),
- añadir y eliminar incidencias (`Reparacio`, `Objecte`, `Tancament`), que actualizan el estado de vías y accesos,
- calcular el número de accesos accesibles y los metros de accesos a nivel,
- guardar y recuperar la estación en un fichero (serialización).

Estructura: `prog2.model` (vías, accesos, listas, incidencias) y `prog2.vista` (menú y arranque).

## Práctica 3: central nuclear (parte 1)

Menú principal con submenús para:

- barres de control: consultar y establecer el grado de inserción,
- reactor: activar, desactivar y ver su estado,
- sistema de refrigeración: activar y desactivar bombas (0 a 3),
- ver estado de la central, bitácora e incidencias,
- finalizar el día (genera una nueva demanda de potencia aleatoria),
- guardar y cargar datos.

Estructura: `prog2.model` (componentes, bitácora y páginas), `prog2.adaptador` (conecta vista y modelo) y `prog2.vista`.

## Estructura del repositorio

```
.
├── Practica2/
│   ├── src/            # código fuente
│   ├── nbproject/      # proyecto NetBeans
│   ├── build.xml
│   └── manifest.mf
├── Practica3/          # misma estructura
├── Makefile
├── .gitignore
└── README.md
```

## Autoría

Prácticas conjuntas de:

- Paula Sarrión Soriano
- Claudia Ramírez
