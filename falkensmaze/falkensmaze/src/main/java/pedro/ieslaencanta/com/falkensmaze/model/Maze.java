/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.falkensmaze.model;

import com.google.gson.Gson;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Serializable;


import java.io.UnsupportedEncodingException;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import pedro.ieslaencanta.com.falkensmaze.Size;

/**
 *Esta clase representa un laberinto que está compuesto por size, blocks, time y sound.
 * @author sk
 */
@XmlRootElement
public class Maze implements  Serializable{

    private Size size;
    private Block[][] blocks;
    private double time;
    private String sound;

    /**
     *Crear el constructor de la clase Maze.
     */
    public Maze() {
    }

    /**
     *Crear el método init que inicializa el laberinto creando una matriz de bloques vacía.
     */
    public void init() {
        this.setBlocks(new Block[this.getSize().getHeight()][this.getSize().getWidth()]);
        for (int i = 0; i < this.getBlocks().length; i++) {
            for (int j = 0; j < this.getBlocks()[i].length; j++) {
                this.getBlocks()[i][j] = new Block();

            }
        }
    }

    /**
     * Crear el método reset que restablece el estado del laberinto borrando el contenido del bloque.
     */
    public void reset() {
        for (int i = 0; i < this.getBlocks().length; i++) {
            for (int j = 0; j < this.getBlocks()[i].length; j++) {
                this.getBlocks()[i][j] = null;

            }
        }
        this.setBlocks(null);
    }

    /**
     * Crear el método reset que restablece el estado del laberinto con un nuevo tamaño.
     * @param newsize
     */
    public void reset(Size newsize) {
        this.reset();;
        this.setSize(newsize);
        this.init();
    }

    /**
     *El método setBlockValue para establecer el valor de un bloque específico en el laberinto.
     * @param value El nuevo valor que se asignará al bloque.
     * @param row La posición de la fila del bloque.
     * @param col La posición de la columna del bloque.
     */
    public void setBlockValue(String value, int row, int col) {
        this.getBlocks()[col][row].setValue(value);
    }

    /**
     * Para recuperar el valor del bloque en una posición específica en el laberinto.
     * @param row La posición de la fila del bloque
     * @param col La posición de la columna del bloque
     * @return  El valor del bloque ubicado en la posición indicada 
     */
    public String getBlockValue(int row, int col) {
        return this.getBlocks()[row][col].getValue();
    }

    /**
     *Obtiene el tamaño del laberinto
     * @return devuelve el objeto `Size` que define las dimensiones del laberinto.
     */
    public Size getSize() {
        return size;
    }

    /**
     *Establece el tamaño del laberinto.
     * @param size El nuevo tamaño que se asignará al laberinto (objeto `Size`).
     */
    public void setSize(Size size) {
        this.size = size;
    }

    /**
     *Obtiene el tiempo asociado al laberinto.
     * @return devuelve el valor del tiempo asociado al laberinto
     */
    public double getTime() {
        return time;
    }

    /**
     *Establece el tiempo asociado al laberinto.
     * @param time El nuevo valor del tiempo que se asociará al laberinto.
     */
    public void setTime(double time) {
        this.time = time;
    }

    /**
     *recupera el nombre o identificador del sonido asociado con el laberinto.
     * @return devuelve el nombre o identificador del sonido asociado al laberinto.
     */
    public String getSound() {
        return sound;
    }

    /**
     *establece el nombre o identificador del sonido asociado al laberinto
     * @param sound El nuevo nombre o identificador del sonido asociado al laberinto.
     */
    public void setSound(String sound) {
        this.sound = sound;
    }

    /**
     *Obtiene la matriz que representa los bloques del laberinto.
     * @return  devuelve la matriz que representa los bloques del laberinto.
     */
    public Block[][] getBlocks() {
        return blocks;
    }

    /**
     *Establece la matriz de bloques del laberinto.
     * @param blocks  La nueva matriz que representa los bloques del laberinto.
     */
    public void setBlocks(Block[][] blocks) {
        this.blocks = blocks;
    }

    /**
     * cargar un laberinto desde un archivo.
     * @param file El archivo que contiene la representación del laberinto.
     * @return devuelve un n objeto `Maze` que representa el laberinto cargado del archivo, o `null` si se produce un error durante la carga.
     * @throws JAXBException Excepcion si se produce un error durante la carga del archivo XML 
     * @throws IOException Si se produce un error de entrada/salida durante la lectura del archivo.
     * @throws FileNotFoundException Si el archivo especificado no existe.
     * @throws ClassNotFoundException Si no se encuentra la clase necesaria para la carga.
     * @throws Exception Si se produce cualquier otra excepción no manejada durante la carga.
     */
    public static Maze load(File file) throws JAXBException, IOException, FileNotFoundException, ClassNotFoundException, Exception {
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

    }

    /**
     * Guardar un laberinto en un archivo.
     * @param maze  El objeto `Maze` que se desea guardar en el archivo.
     * @param file El archivo donde se guardará la representación del laberinto.
     * @throws JAXBException Si se produce un error durante el guardado del archivo XML.
     * @throws Exception Si se produce un error durante el guardado o si el sonido del laberinto no está definido.
     */
    public static void save(Maze maze, File file) throws JAXBException, Exception {
        if (maze.sound == null || maze.sound.equals("")) {
            throw new Exception("Es necesario indicar el sonido del laberinto");
        }
        String extension = file.getName().substring(file.getName().lastIndexOf(".") + 1);
        if (extension.equals("xml")) {
            Maze.saveXML(maze, file);
        } else {
            if (extension.equals("json")) {
                Maze.saveJSON(maze, file);

            } else {
                if (extension.equals("bin")) {
                    Maze.saveBin(maze, file);
                } else {
                    throw new Exception("Exencsión " + extension + " no permitida");

                }
            }

        }
    }

    private static Maze loadJSON(File file) throws FileNotFoundException, IOException {
        Gson gson = new Gson();
        Reader reader;
        reader = Files.newBufferedReader(file.toPath());
        Maze m = gson.fromJson(reader, Maze.class);
        reader.close();
        return m;
    }

    private static Maze loadXML(File file) throws JAXBException, IOException {

           JAXBContext context = JAXBContext.newInstance(Maze.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                Maze maze = (Maze) unmarshaller.unmarshal(
                        file);
                return maze;
          
    }

    /**
     *Cargar un laberinto desde un archivo binario.
     * @param file El archivo binario que contiene la representación del laberinto.
     * @return devolver el objeto Maze leído.
     * @throws FileNotFoundException Si el archivo especificado no existe.
     * @throws IOException Si se produce un error de entrada o salida durante la lectura del archivo.
     * @throws ClassNotFoundException  Indicar que la clase necesaria para la deserialización no se encuentra.
     */
    public static Maze loadBin(File file) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream os = new FileInputStream(file);
        
        ObjectInputStream oos = new ObjectInputStream(os);
        Maze m = (Maze) oos.readObject();
        oos.close();;
        os.close();
        return m;
    }

    private static void saveJSON(Maze maze, File file) throws FileNotFoundException, UnsupportedEncodingException {
        Gson gson = new Gson();
        String json = gson.toJson(maze);
        java.io.PrintWriter pw = new PrintWriter(file, "UTF-8");
        pw.print(json);
        pw.close();
    }

    private static void saveXML(Maze maze, File file) throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(maze.getClass());
        // create an instance of `Marshaller`
        Marshaller marshaller = context.createMarshaller();
        // enable pretty-print XML output
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        // write XML to `StringWriter`
        FileWriter fw = new FileWriter(file, StandardCharsets.UTF_8);//(file, "UTF-8");
        marshaller.marshal(maze, fw);
        fw.close();

    }

    /**
     *guardar un objeto laberinto en un archivo binario.
     * @param maze  El objeto `Maze` que se desea guardar en el archivo.
     * @param file El archivo donde se guardará la representación del laberinto.
     * @throws FileNotFoundException Si el archivo especificado no existe.
     * @throws IOException Si se produce un error de entrada o salida durante la escritura en el archivo.
     */
    public static void saveBin(Maze maze, File file) throws FileNotFoundException, IOException {
        OutputStream os = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(maze);
        oos.close();;
        os.close();
    }

}
