## 🎥 Proyecto: App Contenido Audiovisual

Este proyecto es una aplicación en Java que permite **gestionar contenidos audiovisuales** (Películas, Series y Documentales) desde consola.
Incluye almacenamiento en archivos CSV, lectura y escritura de datos relacionados (actores, investigadores y temporadas) y pruebas unitarias con JUnit.

---

## 📂 Estructura del proyecto

```
app-contenido-audiovisual/
│
├── src/
│   ├── main/java/
│   │   ├── ec/edu/ups/app_contenido_audiovisual/modelo/      # Modelos (Pelicula, SerieTV, Documental, etc.)
│   │   ├── ec/edu/ups/app_contenido_audiovisual/controlador/  # Lógica de negocio (ContenidoController)
│   │   ├── ec/edu/ups/app_contenido_audiovisual/persistencia/ # Gestión de archivos CSV (GestorArchivos)
│   │   └── ec/edu/ups/app_contenido_audiovisual/vista/        # Vista en consola (Consola.java)
│   │
│   ├── main/resources/                                        # Archivos CSV
│   │   ├── contenidos.csv
│   │   ├── actores.csv
│   │   ├── investigadores.csv
│   │   └── temporadas.csv
│   │
│   └── test/java/                                             # Pruebas unitarias (JUnit)
│       └── ec/edu/ups/app_contenido_audiovisual/test/
│
├── pom.xml                                                     # Proyecto Maven
└── README.md                                                   # Este archivo
```

---

## 📝 Cambios realizados

1. **Soporte de IDs en contenidos y relaciones**

   * Ahora cada contenido audiovisual tiene un `contenidoId` único.
   * Actores, investigadores y temporadas se asocian mediante este `contenidoId`.

2. **Persistencia mejorada**

   * Implementado `GestorArchivos` con métodos para leer y escribir CSV de:

     * Contenidos
     * Actores
     * Investigadores
     * Temporadas

3. **Controlador mejorado**

   * `ContenidoController` ahora asocia automáticamente los datos de actores, investigadores y temporadas con los contenidos.

4. **Vista en consola mejorada**

   * Listado con íconos y detalle por tipo de contenido.
   * Funcionalidad para agregar contenidos con sus datos relacionados.

5. **Pruebas unitarias con JUnit**

   * Se implementaron pruebas para validar la lectura/escritura en CSV y el correcto manejo de relaciones.

---

## 🔧 Requisitos

* **Java 17+**
* **Maven 3.8+**

---

## 🚀 Cómo clonar y ejecutar el proyecto

1. **Clonar el repositorio**

   ```bash
   git clone git@github.com:jorgerivera12/app-contenido-visual.git
   cd app-contenido-visual
   ```

2. **Compilar el proyecto**

   ```bash
   mvn clean compile
   ```

3. **Ejecutar la aplicación**

   ```bash
   mvn exec:java -Dexec.mainClass="ec.edu.ups.app_contenido_audiovisual.vista.Consola"
   ```

---

## 🧪 Cómo ejecutar las pruebas unitarias

El proyecto incluye pruebas con **JUnit 5**.
Para ejecutarlas:

```bash
mvn test
```

> Esto correrá todas las pruebas en `src/test/java/` y mostrará el reporte en consola.

---

## 📊 Datos iniciales (CSV)

Ejemplo de datos iniciales que vienen en los archivos CSV:

**contenidos.csv**

```
1,Pelicula,Inception,2010,Christopher Nolan
2,SerieTV,Stranger Things,2016
3,Documental,Planeta Tierra,2006,Naturaleza
```

**actores.csv**

```
1,Leonardo DiCaprio,Dominick Cobb,3
1,Joseph Gordon-Levitt,Arthur,1
```

**investigadores.csv**

```
3,David Attenborough,Biología,BBC
3,Jane Goodall,Primates,Universidad de Cambridge
```

**temporadas.csv**

```
2,1,8
2,2,9
```
