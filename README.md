#  Sistema de Gestión de Aeropuerto

Un sistema web moderno para gestionar vuelos, tiquetes y personal de un aeropuerto. Desarrollado con **Java JSP**, **MySQL** y **Apache Tomcat**.

---

## Características

- ✅ **Autenticación de Personal** - Login seguro con sesiones
- ✅ **Gestión de Vuelos** - Crear, listar, editar y eliminar vuelos
- ✅ **Gestión de Tiquetes** - Venta y administración de tiquetes
- ✅ **Gestión de Personal** - Administración del staff del aeropuerto
- ✅ **Dashboard** - Panel de control con estadísticas en tiempo real
- ✅ **Diseño Responsivo** - Compatible con dispositivos móviles
- ✅ **Base de Datos Relacional** - MySQL con integridad referencial
- ✅ **Arquitectura Escalable** - Patrón DAO para mantenibilidad

---

##  Requisitos Previos

- **Java JDK 17+**
- **Apache Maven 3.8+**
- **Apache Tomcat 10+**
- **MySQL 8.0+**
- **Git (opcional)**

---

##  Instalación

### 1. Crear la Base de Datos

Abre MySQL y ejecuta:

```bash
mysql -u root -p < sql/schema.sql
```

O en la terminal de MySQL:

```sql
SOURCE C:/path/to/sql/schema.sql;
```

Esto creará:
- Base de datos: `aeropuerto`
- Tablas: `vuelos`, `tiquetes`, `personal`, `asignaciones`, `auditoria`
- Datos de prueba listos para usar

### 2. Configurar Conexión a Base de Datos

Abre `src/main/java/com/etitc/aeropuerto/util/ConexionBD.java` y actualiza:

```java
private static final String URL = "jdbc:mysql://localhost:3306/aeropuerto";
private static final String USUARIO = "root";
private static final String CONTRASENA = ""; // Tu contraseña MySQL
```

### 3. Compilar el Proyecto

En PowerShell, navega a la carpeta del proyecto:

```powershell
cd Proyecto_Aeropuerto
mvn clean package
```

Esto generará: `target/proyecto-aeropuerto.war`

### 4. Desplegar en Tomcat

1. Copia el archivo `.war` a:
   ```
   C:\tomcat\apache-tomcat-10.0.xx\webapps\
   ```

2. Inicia Tomcat:
   ```powershell
   C:\tomcat\apache-tomcat-10.0.xx\bin\startup.bat
   ```

3. Abre tu navegador en:
   ```
   http://localhost:8080/proyecto-aeropuerto
   ```

---

## Primeros Pasos

### Credenciales de Prueba

| Usuario | Contraseña | Puesto |
|---------|-----------|--------|
| `admin` | `admin123` | Administrador |
| `carlos` | `carlos123` | Supervisor de Vuelos |
| `maria` | `maria123` | Agente de Tiquetes |
| `juan` | `juan123` | Capitán |

### Funcionalidades Principales

1. **Dashboard**
   - Resumen de vuelos activos
   - Total de pasajeros
   - Alertas y notificaciones

2. **Gestión de Vuelos**
   - Crear nuevos vuelos
   - Editar horarios y capacidades
   - Ver asientos disponibles
   - Cambiar estado de vuelos

3. **Gestión de Tiquetes**
   - Vender tiquetes
   - Asignar asientos
   - Generar reportes de ingresos

4. **Gestión de Personal**
   - Registrar empleados
   - Asignar roles
   - Controlar estado (activo/inactivo)

---

##  Estructura del Proyecto

```
Proyecto_Aeropuerto/
├── pom.xml                          # Configuración Maven
├── README.md                        # Este archivo
│
├── src/
│   ├── main/
│   │   ├── java/com/etitc/aeropuerto/
│   │   │   ├── models/              # Clases de Datos
│   │   │   │   ├── Vuelo.java
│   │   │   │   ├── Tiquete.java
│   │   │   │   └── Personal.java
│   │   │   ├── dao/                 # Data Access Objects
│   │   │   │   ├── VueloDAO.java
│   │   │   │   └── PersonalDAO.java
│   │   │   └── util/                # Utilidades
│   │   │       └── ConexionBD.java
│   │   │
│   │   └── webapp/
│   │       ├── index.jsp            # Login
│   │       ├── css/
│   │       │   └── styles.css       # Estilos globales
│   │       ├── js/                  # JavaScript (futuro)
│   │       ├── jsp/
│   │       │   ├── dashboard.jsp
│   │       │   ├── vuelos.jsp
│   │       │   ├── tiquetes.jsp
│   │       │   ├── personal.jsp
│   │       │   ├── procesar_login.jsp
│   │       │   └── logout.jsp
│   │       └── WEB-INF/
│   │           └── web.xml          # Configuración web
│   │
│   └── test/
│       └── java/                    # Pruebas unitarias (futuro)
│
├── sql/
│   └── schema.sql                   # Script de base de datos
│
└── target/
    └── proyecto-aeropuerto.war      # Aplicación compilada
```

---

##  Conceptos Implementados (Semana 10)

### JSP y Objetos Implícitos
- ✅ Directiva `page` con imports
- ✅ Objeto `out` para escribir HTML
- ✅ Objeto `request` para obtener parámetros
- ✅ Objeto `response` para redirigir
- ✅ Objeto `session` para mantener datos del usuario

### Patrón de Diseño
- ✅ **Model**: Clases `Vuelo`, `Tiquete`, `Personal`
- ✅ **DAO**: Acceso a datos desacoplado
- ✅ **Util**: Conexión centralizada a BD

### Base de Datos
- ✅ Múltiples tablas con relaciones
- ✅ JDBC con `PreparedStatement` (prevención SQL injection)
- ✅ Transacciones implícitas
- ✅ Índices para mejor rendimiento

---


```

---

##  Consideraciones de Seguridad

### Implementado
- Uso de `PreparedStatement` (prevención SQL injection)
- Validación básica de entrada
- Sesiones para mantener autenticación
- Contraseñas en base de datos

### Mejoras Futuras
- Encriptación de contraseñas (bcrypt/PBKDF2)
- HTTPS en producción
- CSRF tokens en formularios
- Validación más estricta de entrada
- Rate limiting en login
- Logs de auditoría detallados

---

##  Testing Manual

### Caso 1: Login
1. Abre http://localhost:8080/proyecto-aeropuerto
2. Ingresa: usuario=`admin`, contraseña=`admin123`
3. Deberías ver el dashboard

### Caso 2: Ver Vuelos
1. Desde dashboard, haz clic en " Vuelos"
2. Deberías ver la tabla con 5 vuelos de prueba
3. Verifica códigos: AA101, UA202, AV303, LH404, AV505

### Caso 3: Logout
1. Haz clic en "Salir" en la esquina superior derecha
2. Deberías volver a la página de login

---

## 📚 Referencias

- [Documentación de JSP](https://tomcat.apache.org/tomcat-9.0-doc/jsp.html)
- [Tutorial JDBC](https://docs.oracle.com/javase/tutorial/jdbc/)
- [Maven Guide](https://maven.apache.org/guides/)
- [MySQL Documentation](https://dev.mysql.com/doc/)
- [Apache Tomcat](https://tomcat.apache.org/)

---

##  Notas para Estudiantes

Este proyecto es una **base escalable** que demuestra:

1. **Separación de Responsabilidades**: HTML (JSP), Lógica (DAO), Datos (Models)
2. **Reutilización de Código**: DAOs genéricos que pueden extenderse
3. **Buenas Prácticas**: Nombres claros, estructura coherente, documentación
4. **Escalabilidad**: Fácil de agregar nuevas tablas/funcionalidades

**Ejercicios :**
- Crear `TiqueteDAO` con métodos CRUD
- Agregar búsqueda por filtros en vuelos
- Crear reportes de ingresos por aerolínea
- Agregar paginación a las tablas

---

