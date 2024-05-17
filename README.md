# Falken´s Maze.

Falken´s Maze permite a los usuarios crear y resolver laberintos personalizados utilizando una interfaz fácil de usar y controles intuitivos.

![Screenshot_20240516_182942](https://github.com/sanar02/falkensmaze/assets/170052760/3ede4bda-72ad-4156-97ea-11211d46329b)

El Laberinto de Falken es un juego interactivo que permite diseñar y navegar por laberintos personalizados con una estructura sólida y características atractivas.
Las características que ha de soportar el juego son:
- Los jugadores pueden crear laberintos personalizados utilizando una variedad de bloques de construcción, incluyendo muros, caminos y obstáculos. Pueden personalizar el tamaño, los puntos de inicio y final, y la disposición general de sus laberintos, permitiendo diseños únicos y personalizados.
- El juego debe proporcionar controles intuitivos para que los jugadores naveguen por los laberintos que han creado o los generados aleatoriamente. La navegación debe ser desafiante pero agradable, requiriendo que los jugadores utilicen sus habilidades para resolver problemas y su pensamiento estratégico para superar obstáculos y alcanzar la meta.
- El juego debe ofrecer una variedad de niveles de dificultad para adaptarse a jugadores de todos los niveles.
- Guardar sus creaciones de laberintos y su progreso, permitiéndoles revisarlos más tarde y continuar su experiencia de juego.
- El juego debe tener una interfaz de usuario bien diseñada e intuitiva que sea fácil de navegar y comprender.

## Interfaces

###  IBlockListener

una interfaz para manejar eventos relacionados con las interacciones de bloques, como hacer clic o hacer doble clic.

## Clases

### ILevelEditorPanel

es una clase que define métodos para administrar los bloques dentro de un panel editor de niveles. Permite establecer, obtener, agregar, eliminar, seleccionar y deseleccionar bloques.

### IDialogSize/DialogTime

Son clases que crean cuadros de diálogo modales para que los usuarios ingresen parámetros del laberinto (tamaño y límite de tiempo, respectivamente). Definen los elementos de la interfaz de usuario del diálogo y manejan la entrada del usuario.

### Size

Esta clase representa el tamaño de un objeto visual en la interfaz gráfica de usuario de la aplicación. Implementa las siguientes interfaces:
Cloneable: Esta interfaz permite crear copias de Size objetos.
Comparable<Size>: Esta interfaz permite la comparación entre Sizeobjetos para determinar su orden relativo.
Serializable: Esta interfaz facilita la serialización de Sizeobjetos, permitiendo almacenarlos o transmitirlos en un formato adecuado para su reconstrucción.

### Block

Esta clase representa un bloque básico dentro del laberinto. 

### Maze

Gestiona la estructura del laberinto, incluido el tamaño, los bloques, el tiempo, el sonido y proporciona funcionalidades para cargar/guardar laberintos desde archivos. 

### BlocksPanel

Una clase que representa un panel que contiene múltiples Blockobjetos. Proporciona métodos para agregar bloques, inicializar el diseño del panel y manejar clics en bloques.

### MazeCanvas

Muestra el laberinto visualmente y maneja la interacción del usuario para seleccionar bloques y modificar el laberinto.

## Principal

la clase Principal desempeña un papel central en la coordinación y gestión de varios componentes para proporcionar la interfaz de usuario y las funcionalidades para crear y editar laberintos. Define métodos como: 
- start(Stage stage): este método se invoca cuando se inicia la aplicación. Configura la ventana principal, crea el diseño de la interfaz de usuario utilizando componentes como BorderPane, y muestra la ventana BlocksPanel MazeCanvas y MenuBar.
- createBlockMenu(): Crea el panel en el lado izquierdo que contiene los diferentes tipos de bloques que el usuario puede colocar en el laberinto.
- createMaze(): Crea el MazeCanvasobjeto que representa la cuadrícula del laberinto visual y maneja la interacción del usuario para colocar bloques.
- createMenu(): Crea la barra de menú en la parte superior de la ventana con opciones para Archivo (Nuevo, Guardar, Abrir), Opciones (Sonido, Hora) y Salir.



## Recomendaciones de desarrollo.



## Descargo de responsabilidad.

Esta aplicación puede contener errores o limitaciones. Por favor informe cualquier problema o sugerencia de mejora.
