/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.falkensmaze;

import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * La clase size representa el tamaño de un objeto visual en la interfaz
 * gráfica. La clase implementa las interfaces `Cloneable`, `Comparable<Size>`,
 * y `Serializable` para permitir la clonación, comparación y serialización de
 * objetos `Size`.
 *
 * @author sk
 */
@XmlRootElement
public class Size implements Cloneable, Comparable<Size>, Serializable {

    private int width;
    private int height;

    /**
     * Crear el constructor de la clase Size.
     */
    public Size() {
    }

    /**
     * Constructor de la clase Size.
     *
     * @param width Especificar el ancho del objeto.
     * @param height Especificar el alto del objeto.
     */
    public Size(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Crear el método clone para crear una copia del objeto Size.
     *
     * @return devuelve un nuevo objeto Size que es una copia del objeto actual.
     * @throws CloneNotSupportedException nunca se lanza en esta implementación,
     * pero se incluye como lo requiere la interfaz `Cloneable`.
     */
    public Object clone() throws CloneNotSupportedException {
        return new Size(this.getWidth(), this.getHeight());
    }

    /**
     * Crear el método equals heredado de la clase Object para comparar objetos
     * Size. Este método determina si el objeto actual es igual a otro objeto o.
     *
     * @param o es el objeto a comparar con el objeto actual.
     * @return `true` si los objetos son iguales, `false` en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Size)) {
            return false;
        }
        if (this.getWidth() == ((Size) (o)).getWidth() && this.getHeight() == ((Size) (o)).getHeight()) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * Crear el método `compareTo heredado de la interfaz `Comparable<Size>`,
     * que compara dos objetos para determinar su orden relativo.
     *
     * @param o el objeto Size a comparar con el objeto actual.
     * @return Un 0 si los objetos son iguales. Un -1 si el objeto actual es
     * menor que `o`.Un 1 si el objeto actual es mayor que `o`.
     */
    @Override
    public int compareTo(Size o) {
        if (this.getWidth() == o.getWidth() && this.getHeight() == o.getHeight()) {
            return 0;
        }
        if (this.getWidth() < o.getWidth()) {
            return -1;
        } else {
            return 1;
        }
    }

    /**
     * 
     * @return el método toString devuelve una representación textual del objeto Size.
     */
    public String toString() {
        return "W:" + this.width + " H:" + this.height;
    }

    /**
     * @return the width  El ancho del objeto Size.
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return the height El alto del objeto Size.
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param width the width to set  El nuevo ancho del objeto Size.
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * @param height the height to set  El nuevo alto del objeto Size.
     */
    public void setHeight(int height) {
        this.height = height;
    }

}
