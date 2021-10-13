# GetSeeds

Ferramenta para criação de 23 seeds de forma randômica, utilizando como base a biblioteca 
[SecureRandom](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/security/SecureRandom.html)

A seed interna da SecureRandom é calculada a partir do movimento do mouse.

A ferramenta deve ser utilizada off-line para maior segurança.

Ulilizar o [SeedPicker](https://seedpicker.net/calculator/last-word.html) para complementar a 24ª palavra

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
