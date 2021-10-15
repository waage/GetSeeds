# GetSeeds

Ferramenta para criação de 24 seeds de forma randômica, utilizando como base a biblioteca 
[SecureRandom](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/security/SecureRandom.html)
e [BIP39](https://github.com/NovaCrypto/BIP39)

O diferencial deste projeto é a seed interna da SecureRandom calculada a partir do movimento do mouse.

A ferramenta deve ser utilizada off-line para maior segurança.

## Executando

O Java 11 (ou acima) deve estar instalado na máquina

```sh
java -jar getseeds.jar
```

## Instalação

O projeto requer Java 11 (ou acima) e Maven para instalação.

```sh
cd getseeds
mvn package
cd target
java -jar getseeds.jar
```
