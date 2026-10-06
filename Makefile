# Compila y ejecuta las prácticas sin necesidad de NetBeans.
# Requiere JDK 11 o superior (javac).

JAVAC = javac -encoding UTF-8 --release 11

SRC2 = $(shell find Practica2/src -name '*.java')
SRC3 = $(shell find Practica3/src -name '*.java')

all: p2 p3

p2:
	@mkdir -p build/practica2
	$(JAVAC) -d build/practica2 $(SRC2)

p3:
	@mkdir -p build/practica3
	$(JAVAC) -d build/practica3 $(SRC3)

run2: p2
	java -Dfile.encoding=UTF-8 -cp build/practica2 prog2.vista.IniciadorEstacioTren

run3: p3
	java -Dfile.encoding=UTF-8 -cp build/practica3 prog2.vista.IniciadorCentralUB

clean:
	rm -rf build

.PHONY: all p2 p3 run2 run3 clean
