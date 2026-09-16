# **DOSW-Taller2-Bowling-Ortiz-Camilo**

## 1. Identificación
- **Nombre:** Cristian Camilo Ortiz Sanchez
- **Código Estudiantil:** 1000105286
- **Correo Institucional:** `cristian.ortiz-s@mail.escuelaing.edu.co`
  
## 2. Descripción

BowlTech S.A.S. es una empresa que administra pistas de bolos y que quiere digitalizar su sistema de puntuación, que actualmente se realiza de forma manual. El proyecto consiste en desarrollar un motor de puntuación de bolos capaz de registrar los tiros y calcular automáticamente el puntaje de una partida, aplicando las reglas oficiales del dominio.

#### REGLAS DEL DOMINIO
|Situación|Condición|Puntuación|
|:---|:---|:---|
|Tiro normal|Derriba algunos pinos sin completar 10|Solo los pinos derribados en ese tiro|
|Spare /|Derriba los 10 pinos en 2 intentos del mismo frame| 10 + primer tiro del siguiente frame|
|Strike X |Derriba los 10 pinos en el primer intento|10 + suma de los dos tiros siguientes|
|Frame 10|Si hay strike o spare en el frame 10|Hasta 3 tiros en el frame 10|
|Juego perfecto |12 strikes consecutivos|300 puntos (maximo posible)|

#### RESPONSABILIDADES DE CADA CLASE
- `BowlingGame:` Es el motor principal del juego
- `Frame:` Representa un frame con sus tiros
- `FrameType:` enum: NORMAL, SPARE, STRIKE, TENTH
- `BowlingScorer:` calcula el puntaje total

## 3. Evidencia TDD

A continuación se muestra la evidencia de un Ciclo de TDD, específicamente el caso de prueba `B8`. Lanzar `IllegalStateException` si se llama al método `score()` antes de completar el juego.

### RED

![](bowling-tdd/docs/evidence/CapturaRED.png)

### GREEN
![](bowling-tdd/docs/evidence/CapturaGREEN.png)

## 4. JaCoCo

![](bowling-tdd/docs/evidence/jacoco-final.png)

![](bowling-tdd/docs/evidence/jacoco-final-2.png)

La primera vez que se ejecutó el `mvn clean verify`, el coverage marcó 96% en branches y un 99% en lineas. Así que no fue necesario realizar más tests que subieran el coverage.

## 5. SonarQube

### Captura del coverage
![](bowling-tdd/docs/evidence/sonarqube.png)

### Captura de los issues encontrados
![](bowling-tdd/docs/evidence/sonarqube_issues.png)

### Captura del estado del Quality Gate
![](bowling-tdd/docs/evidence/sonarqube_quality_gate.png)

## 6. Pull Requests

|Enlace al PR|Fecha de merge|Modulos cubiertos|
|:---|:---|:---|
|![https://github.com/CatrachoINXS/DOSW-Taller2-Bowling-Ortiz-Camilo/pull/1](https://github.com/CatrachoINXS/DOSW-Taller2-Bowling-Ortiz-Camilo/pull/1)|2026-09-16T14:26|Partes 1, 2, 3 y avance en documentación|
|![https://github.com/CatrachoINXS/DOSW-Taller2-Bowling-Ortiz-Camilo/pull/2](https://github.com/CatrachoINXS/DOSW-Taller2-Bowling-Ortiz-Camilo/pull/2)|2026-09-16T15:03|Partes 4 - Covertura y análisis estático|
|![https://github.com/CatrachoINXS/DOSW-Taller2-Bowling-Ortiz-Camilo/pull/2](https://github.com/CatrachoINXS/DOSW-Taller2-Bowling-Ortiz-Camilo/pull/3)|2026-09-16T16:32|Parte 5 - Documentación|

## 7. Reflexión

#### - *01 ¿Qué caso edge del Bowling fue el más difícil de implementar con TDD y por que?*

El caso más dificil de implementar con TDD fue el `B5`, porque había que resolver lo de los strikes consecutivos. Para los anteriores casos habia intentado hacer lo mínimo para que pasara la prueba sin pensar en las otras funcionalidades y al principio tenia pensado que cada frame tuviera dos atributos correspondientes a los tiros. Después me di cuenta que no tenia sentido guardar los dos atributos si el frame era strike. Entonces para este caso hubo que refactorizar bastante e implementar toda la logica con listas que guardaran los tiros, de modo que si era strike solo guardaba 10, y si no, guardaba los tiros que correspondieran.

#### - *02 ¿Qué parte del código cambió durante REFACTOR sin modificar el comportamiento observable?*

Durante la parte de REFACTOR lo que cambió después de correr las pruebas eran `if` u otros condicionales innecesarios que solo agregaban más ruido al código.

#### - *03 ¿Qué casos de prueba descubriste al revisar el reporte de cobertura de JaCoCo que no habían considerado antes?*

Realmente ninguno, en el reporte de coberturas se probaron los casos necesarios, y aunque en la clase `Frame` marca que hace falta probar un caso que es cuando la cantidad de pinos son 10 y la cantidad de intentos no es dos ni uno. La cuestión es esto no tiene sentido probarlo porque esa validacion solo aplica para el décimo frame y recordemos que cada frame que no sea TENTH tiene maximo dos intentos.

#### - *04 ¿Qué hallazgo de SonarQube produjo un cambio real en el código?*

La clase `BowlingScorerTest` no tenía pruebas, así que se agregaron pruebas. La clase `BowlingScorer` tenia el constructor publico implicito asi que lo cambié a un constructor privado, y lo otro es que en una prueba habia un `assert` con los parámetros invertidos entre el valor esperado y el valor obtenido.







