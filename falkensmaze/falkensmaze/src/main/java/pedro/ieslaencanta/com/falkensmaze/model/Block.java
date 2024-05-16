/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.falkensmaze.model;

import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Crear una clase que representa un bloque básico del Laberinto.
 * El bloque tiene un valor que define su comportamiento dentro del laberinto.
 * @author sk
 */
@XmlRootElement
public class Block implements  Serializable {
    private String value;
    
    /**
     *Constructor por defecto de la clase Block.
     */
    public Block(){
        this.value=null;
    }

    /**
     *
     * @return devuelve el valor del bloque como una cadena de texto.
     */
    public String getValue(){
        return this.value;
    }

    /**
     *
     * @param value El nuevo valor del bloque como cadena de texto.
     */
    public void setValue(String value){
        this.value=value;
    }

    /**
     *
     * @return devuelve true si el valor del bloque es null, false en caso contrario.
     */
    public boolean isEmpty(){
        return this.value==null;
    }
}
