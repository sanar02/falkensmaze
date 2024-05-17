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

```
public class Size implements Cloneable, Comparable<Size>, Serializable {
    private int width;
    private int height;
    }
```


### Block

Esta clase representa un bloque básico dentro del laberinto. 

### Maze

Gestiona la estructura del laberinto, incluido el tamaño, los bloques, el tiempo, el sonido y proporciona funcionalidades para cargar/guardar laberintos desde archivos. 

### BlocksPanel

Una clase que representa un panel que contiene múltiples Blockobjetos. Proporciona métodos para agregar bloques, inicializar el diseño del panel y manejar clics en bloques.

### MazeCanvas

Muestra el laberinto visualmente y maneja la interacción del usuario para seleccionar bloques y modificar el laberinto.

### Principal

la clase Principal desempeña un papel central en la coordinación y gestión de varios componentes para proporcionar la interfaz de usuario y las funcionalidades para crear y editar laberintos. Define métodos como: 
- start(Stage stage): este método se invoca cuando se inicia la aplicación. Configura la ventana principal, crea el diseño de la interfaz de usuario utilizando componentes como BorderPane, y muestra la ventana BlocksPanel MazeCanvas y MenuBar.
- createBlockMenu(): Crea el panel en el lado izquierdo que contiene los diferentes tipos de bloques que el usuario puede colocar en el laberinto.
- createMaze(): Crea el MazeCanvasobjeto que representa la cuadrícula del laberinto visual y maneja la interacción del usuario para colocar bloques.
- createMenu(): Crea la barra de menú en la parte superior de la ventana con opciones para Archivo (Nuevo, Guardar, Abrir), Opciones (Sonido, Hora) y Salir.


## Funcionalidades.

- Creación del laberinto: : Definir el tamaño del laberinto y utilizar la paleta de bloques para colocarlos en el lienzo.


```
   
        this.maze = new MazeCanvas();
        this.maze.setBoard_size(new Size(this.width, this.height));
        this.maze.setRows(10);
        this.maze.setCols(10);
        // this.maze.setCell_size(new Size(this.width / 10, this.height / 10));
        this.maze.init();

        return this.maze;
    
```


- Edición del laberinto: Modificar el diseño del laberinto cambiando el tipo de bloque en cualquier posición.
  
- Visualización del laberinto: Observar una representación gráfica del laberinto completo.

    ```
        this.canvas = new Canvas(this.board_size.getWidth(), this.board_size.getHeight());
        this.bgcanvas = new Canvas(this.board_size.getWidth(), this.board_size.getHeight());
        this.ctx = canvas.getGraphicsContext2D();
        this.ctxbg = this.bgcanvas.getGraphicsContext2D();
        this.initMaze();
        this.getChildren().add(this.bgcanvas);
        this.getChildren().add(this.canvas);
        this.canvas.setOnMouseClicked(t -> {
            if (this.block != null) {
                int r = (int) (((int) t.getY()) / (this.board_size.getHeight() / this.rows));
                int c = (int) (((int) t.getX()) / (this.board_size.getWidth() / this.cols));
                this.getMaze().setBlockValue(this.block.getType(), r, c);
            }
            this.draw();
      

-  Acceder a las opciones de configuración desde el menú principal.

  
  
        MenuBar menuBar = new MenuBar();

        Menu fileMenu = new Menu("Archivo");
        MenuItem newMenuItem = new MenuItem("Nuevo");
        newMenuItem.setOnAction(eh -> {
            DialogSize ds = new DialogSize();
            ds.init();
            Optional<Size> result = ds.showAndWait();
            if (result.get() != null) {
                this.maze.reset(result.get());
            }
        });
        MenuItem saveMenuItem = new MenuItem("Guardar");
        saveMenuItem.setOnAction(actionEvent -> {

            final FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showSaveDialog(scene.getWindow());
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("XML", "*.xml*"),
                new FileChooser.ExtensionFilter("JSon", "*.json"),
                new FileChooser.ExtensionFilter("Bin", "*.bin")
            );
            if (file != null) {
                try {
                    Maze.save(this.maze.getMaze(), file);

                } catch (JAXBException ex) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Infor error");
                    alert.setHeaderText(null);
                    alert.setContentText(ex.getMessage());

                    alert.showAndWait();
                } catch (Exception ex) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Infor error");
                    alert.setHeaderText(null);
                    alert.setContentText(ex.getMessage());

                    alert.showAndWait();

                }
            }

        });
        MenuItem loadMenuItem = new MenuItem("Abrir");
        loadMenuItem.setOnAction(actionEvent -> {
            final FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("XML", "*.xml*"),
                new FileChooser.ExtensionFilter("JSon", "*.json"),
                new FileChooser.ExtensionFilter("Bin", "*.bin")
            );
            File file = fileChooser.showOpenDialog(scene.getWindow());
            if (file != null) {
                try {
                    Maze m = Maze.load(file);
                    this.maze.reset(new Size(m.getBlocks().length,m.getBlocks()[0].length));
                    this.maze.setMaze(m);
                    this.maze.draw();
                } catch (IOException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });
        Menu optionsMenu = new Menu("Options");
        MenuItem soundMenu = new MenuItem("Sound");
        optionsMenu.getItems().add(soundMenu);
        soundMenu.setOnAction(actionEvent -> {
            final FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("mp3", "*.mp3*")
             
            );
            File file = fileChooser.showOpenDialog(scene.getWindow());
            if (file != null) {

                this.maze.getMaze().setSound(file.getAbsolutePath());

            }

        });
        
           MenuItem timeMenu = new MenuItem("Time");
           timeMenu.setOnAction(eh -> {
            DialogTime dt = new DialogTime();
            dt.init();
            Optional<Double> result = dt.showAndWait();
            if (result.get() != null) {
                this.maze.getMaze().setTime(result.get());
            }
        });
        optionsMenu.getItems().add(timeMenu);
        timeMenu.setOnAction(actionEvent -> {
           

        });

        MenuItem exitMenuItem = new MenuItem("Salir");
        exitMenuItem.setOnAction(actionEvent -> Platform.exit());

        fileMenu.getItems().addAll(newMenuItem, saveMenuItem, loadMenuItem,
                new SeparatorMenuItem(), exitMenuItem);

        menuBar.getMenus().addAll(fileMenu, optionsMenu);//, webMenu, sqlMenu);
        return menuBar;
    ```

-  Definir el tiempo que tarda en resolverse el laberinto.

-  Eligir un sonido para reproducirse cuando se complete el laberinto.

-  Cargar un laberinto existente desde un archivo o guarda el laberinto actual en un archivo.
```

 String extension = file.getName().substring(file.getName().lastIndexOf(".") + 1);
        Maze m = null;
        if (extension.equals("xml")) {
            m = Maze.loadXML(file);
        } else {
            if (extension.equals("json")) {
                m = Maze.loadJSON(file);
            } else {
                if (extension.equals("bin")) {
                    m = Maze.loadBin(file);
                } else {
                    throw new Exception("Exencsión " + extension + " no permitida");
                }
            }
        }
        return m;


```
      

## Descargo de responsabilidad.

Esta aplicación puede contener errores o limitaciones. Por favor informe cualquier problema o sugerencia de mejora.
