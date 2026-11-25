# Práctica 2 — Arranque Hito 01 (3.2 + 3.3) — Opción A + 2 parches (15 nov 2026)

Parches aplicados en esta copia (la copia Opción A original queda intacta): `@JsonManagedReference` en Poliza.beneficiarios y `@JsonBackReference` en Beneficiario.poliza, para que los GET /polizas y /beneficiarios no se ciclen ni den error 500 al ligar un beneficiario; y un TODO en ClienteService.modificacion para copiar los campos cuando se agreguen los atributos de la ERS, como ya hace PolizaService con el tipo. No compilado aquí: se prueba con `mvn spring-boot:run` en tu computadora.

Preparado el 2026-11-13 para Tona, corregido el 2026-11-14 (Opción A).
Esto NO es código inventado a ciegas:
solo usa datos verificados el 2026-11-12. Lo que falta confirmar con la
ERS va marcado `[confirmar con ERS]` y no se inventó.

Corrección aplicada: `src/main/resources/schema.sql` con las 3 tablas
(cliente, poliza, beneficiario, solo campos verificados) y 2 líneas en
`application.properties` (`spring.sql.init.mode=always` y
`spring.jpa.defer-datasource-initialization=true`), para que el script
corra ANTES de la validación de Hibernate (`ddl-auto=validate`) y el
primer `mvn spring-boot:run` ya no falle por tablas inexistentes.
El script usa `CREATE TABLE IF NOT EXISTS`, así que se puede arrancar
varias veces sin error.

## Qué hay aquí
- `diagrama-c4.png` — 3.2: diagrama de componentes estilo C4 (15 pts).
  Contenedores reutilizados de la Práctica 1. El portal NUNCA habla
  directo con el Sistema Dueño; solo el servidor lo consulta por REST.
- `pom.xml` — Java 25, Spring Boot 4.1.1, Spring Web, Spring Data JPA /
  Hibernate, PostgreSQL. IntelliJ IDEA (el PDF dice Eclipse).
  Checkstyle NO está en el pom todavía: se pone en el 3.4, no aquí.
- Entidades `Poliza`, `Cliente`, `Beneficiario` — 3.3, 5 pts cada una.
  Solo se afirma lo verificado: `id` y en Póliza `tipo` (0 Auto, 1 Vida,
  2 Médico). El resto de atributos va como TODO, no inventado.
- Repositorios JPA (sin código extra) y controladores REST con las rutas
  ligadas a los 9 casos de uso verificados (NRM-AC, NRM-AB, NRM-AP,
  NRM-BP, NRM-CB, NRM-CP, NRM-MC, NRM-MP, PRM-GP).
- `src/main/resources/application.properties` — conexión a PostgreSQL
  local con valores por rellenar (sin contraseña inventada).

## Cómo usarlo en IntelliJ (5 minutos)
1. En el repo https://github.com/Tnnmx/proyecto-arqui crea la rama
   `hito-01` (3.1: una sola rama, equipo de uno).
2. Copia esta carpeta como contenido del proyecto Maven (o crea el
   proyecto con este `pom.xml` y pega las clases en `com.proyectoarqui`).
3. Confirma con tu ERS los atributos de cada entidad y completa los TODO.
4. Rellena la base de datos en `application.properties` (url, usuario y
   tu contraseña) y verifica que la plantilla Maven arranca
   (`mvn spring-boot:run`). El `schema.sql` ya viene incluido, no hay
   que copiarlo aparte. Ese arranque sigue pendiente desde la
   Práctica 1, y sin él el código no se puede probar.
5. Cuando arranque, prueba primero las lecturas: GET /polizas y
   GET /clientes, y luego las escrituras (POST /polizas, POST /clientes).

## Qué sigue después de esto
- 3.4 Checkstyle + pruebas con captura de cumplimiento (20 pts).
- 3.5 Arquitectura de API (20 pts). 3.6 Reporte de hito (10 pts, única
  entrega): anotar ahí la fecha 22 oct (PDF) vs 26 oct (Classroom),
  IntelliJ en vez de Eclipse, y la corrección del diseño rechazado.
- Proyecto final: jueves 26 de noviembre de 2026 (160 pts). Hoy, sábado
  14 de noviembre, quedan 12 días. Práctica 1 (88 pts) y Práctica 2
  (140 pts) siguen en "Sin entregar" en Classroom.

## 3.4 Checkstyle + pruebas (20 pts) — agregado el 17 nov 2026
- `checkstyle.xml` en la raíz: reglas propias del proyecto (nombres Java 25,
  sin tabs, sin `import *`, llaves en la misma línea, líneas de 120 máx.).
  No es `google_checks.xml`: Google exigiría Javadoc en cada método público
  y 100 caracteres, y este código no cumple eso. Las líneas de más de 120
  caracteres se partieron en esta copia (controladores, `PolizaService`,
  setter de `Poliza`); el comportamiento y las rutas no cambian.
- `pom.xml`: plugin `maven-checkstyle-plugin` 3.6.0 (corre en `verify`) y
  dependencia H2 solo para pruebas. El Checkstyle 9.3 que trae el plugin
  parsea este código porque no usa rasgos de Java 25 (sin records ni
  module imports); si luego se agregan, habrá que subir la versión.
- Pruebas en `src/test/java/com/proyectoarqui/`: `PolizaServiceTest` (6),
  `ClienteServiceTest` (3), `PolizaControllerTest` con MockMvc (2) y
  `PolizaRepositoryTest` con `@DataJpaTest` + H2 (1). 12 en total.
- Cómo correrlo (en tu computadora, con Java 25 y Maven):
  `mvn test` — solo pruebas. `mvn verify` — pruebas + Checkstyle.
  Captura para el 3.4: la salida de `mvn verify` con `BUILD SUCCESS` y
  `Tests run: 12, Failures: 0`, y el reporte en `target/checkstyle-result.xml`.
  Si el test de repositorio falla en H2, corre solo el resto con
  `mvn -Dtest=PolizaServiceTest,ClienteServiceTest,PolizaControllerTest test`
  y mándame el error. No compilado aquí: no hay Java ni Maven en este equipo.
