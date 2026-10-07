# DomiExpress

Núcleo de dominio de una app de domicilios en Java 17+ (POO + SOLID).

## Ejecutar (IntelliJ)
Marca `src` como Sources Root y ejecuta `domiexpress.Main`.
Pruebas sin framework: `domiexpress.pruebas.PruebasServicio`.

## Ejecutar (terminal)
    javac -encoding UTF-8 -d out $(find src -name "*.java")
    java -cp out domiexpress.Main
    java -cp out domiexpress.pruebas.PruebasServicio
