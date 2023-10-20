/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbolavl;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

// @author Diego Gayou
// garciagayou@gmail.com

public class IteradorArbol <T> implements Iterator<T> {
    
    private ArrayList <T> orden;
    private int actual;
    
    public IteradorArbol(ArrayList <T> orden){
        this.orden = orden;
        this.actual = 0;
    }
    
    @Override
    public boolean hasNext() {
        return actual < orden.size()-1;
    }

    @Override
    public T next() {
        if(!this.hasNext())
            throw new NoSuchElementException();
        actual ++;
        return orden.get(actual);
    }
}