/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pedro.ieslaencanta.com.falkensmaze.components;

/**
 * Crear una interfaz que define un listener para eventos relacionados con los
 * bloques del Laberinto.
 *
 * @author sk
 */
public interface IBlockListener {

    /**
     * Se invoca cuando se produce un clic simple sobre un bloque del laberinto.
     *
     * @param b El bloque sobre el que se ha hecho clic.
     */
    public void onClicked(Block b);

    /**
     * Se invoca cuando se produce un doble clic sobre un bloque del laberinto.
     *
     * @param b El bloque sobre el que se ha hecho doble clic.
     */
    public void onDoubleClicked(Block b);
}
