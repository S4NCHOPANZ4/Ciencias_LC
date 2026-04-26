# Juego Lista Circular

## 1. Descripción general

Juego de eliminación basado en una lista circular enlazada. En cada turno el jugador activo lanza un dado: si es par queda salvado y el turno pasa al siguiente; si es impar es eliminado. El último jugador restante gana.

Dos modos de ejecución: consola y GUI Swing.

---

## 2. Estructura de paquetes

| Paquete | Clase | Rol |
|---|---|---|
| model | Jugador | Nodo de la lista circular |
| model | ListaJugadores | Lista circular con operaciones del juego |
| view | VistaConsola | Entrada/salida por consola |
| view | VistaSwing | Interfaz gráfica Swing con tablero visual |
| controller | Controller | Lógica pura + flujo de consola |
| controller | ControllerSwing | Flujo GUI, delega lógica al Controller |
| controller | AppMain | Punto de entrada — consola |
| controller | AppMainSwing | Punto de entrada — GUI |

---

## 3. Modelo

### Jugador
Nodo de la lista circular. Almacena el número del jugador y una referencia al siguiente nodo.

| Campo / Método | Tipo retorno | Descripción |
|---|---|---|
| `numero` | int | Identificador del jugador |
| `next` | Jugador | Puntero al siguiente nodo |
| `getNumero()` | int | Retorna el número del jugador |
| `getNext()` | Jugador | Retorna el siguiente jugador en la lista |
| `setNext(Jugador)` | void | Enlaza el siguiente nodo |

### ListaJugadores
Lista circular enlazada. Mantiene referencia al `tail` para agregar en O(1) y acceder a la cabeza vía `tail.getNext()`.

| Método | Tipo retorno | Descripción |
|---|---|---|
| `agregarJugador(int numero)` | void | Agrega un nodo al final de la lista circular |
| `eliminarYObtenerSiguiente(int numero)` | int | Elimina el jugador y retorna el ID del siguiente |
| `buscarJugador(int numero)` | Jugador | Recorre la lista y retorna el nodo con ese ID |
| `getCabeza()` | Jugador | Retorna `tail.getNext()` (primer nodo) |
| `getTamanio()` | int | Retorna la cantidad de jugadores activos |
| `mostrarJugadores()` | void | Imprime los jugadores en orden circular |

---

## 4. Controladores

### Controller
Contiene la lógica pura del juego (sin dependencia de vista) y el flujo de consola. Los métodos de lógica pura son reutilizados por `ControllerSwing`.

| Método | Tipo retorno | Descripción |
|---|---|---|
| `inicializarJugadores(int n)` | void | Crea la lista con n jugadores numerados del 1 al n |
| `tirarDado()` | int | Genera un número aleatorio entre 1 y 6 |
| `parImpar(int n)` | boolean | Retorna true si n es par |
| `ejecutarTurno(Jugador, int dado)` | Jugador | Elimina o avanza según el dado; retorna el siguiente jugador |
| `tirarDados(Jugador)` | Jugador | Flujo consola: imprime resultado y delega a `ejecutarTurno` |
| `jugar()` | void | Bucle principal de consola hasta que quede un jugador |
| `run()` | void | Punto de entrada del controller; llama a `jugar()` |

### ControllerSwing
Orquesta la GUI. Registra los listeners de los botones y delega toda la lógica al `Controller` original.

| Método | Tipo retorno | Descripción |
|---|---|---|
| `run()` | void | Registra los ActionListeners de btnIniciar y btnSiguiente |
| `iniciarJuego()` | void | Valida entrada, inicializa lista y actualiza la vista |
| `siguienteTurno()` | void | Ejecuta un turno y refleja el resultado en la GUI |
| `idsActuales()` | List\<Integer\> | Recorre la lista y devuelve los IDs para el tablero |

---

## 5. Vistas

### VistaConsola
Envuelve `Scanner` y `System.out`. Dos métodos: `mostrarInformacion(String)` para imprimir y `leerDato(String)` para leer entrada del usuario.

### VistaSwing
Ventana principal con `BorderLayout`. Componentes públicos accedidos directamente por `ControllerSwing`:

| Componente | Tipo | Función |
|---|---|---|
| `btnIniciar` | JButton | Inicia una nueva partida |
| `btnSiguiente` | JButton | Avanza un turno (deshabilitado hasta iniciar) |
| `campoJugadores` | JTextField | Cantidad de jugadores a crear |
| `lblInfo` | JLabel | Muestra el turno actual o el ganador |
| `logArea` | JTextArea | Registro de eventos de la partida |
| `tablero` | TableroPanel | Dibuja la lista circular con `paintComponent` |

`TableroPanel` es una inner class que extiende `JPanel`. En `paintComponent` calcula las posiciones angulares de cada nodo, dibuja líneas entre ellos y resalta en azul al jugador en turno.

---

## 6. Reglas del juego

| Dado | Resultado |
|---|---|
| Par (2, 4, 6) | Jugador salvado. El turno pasa al siguiente en la lista. |
| Impar (1, 3, 5) | Jugador eliminado. El turno pasa al siguiente automáticamente. |

El juego termina cuando `getTamanio() == 1`. El jugador restante es declarado ganador.