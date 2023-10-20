
package arbolavl;

import java.util.ArrayList;

// @author Diego Gayou
// garciagayou@gmail.com

public class PilaA <T> {
    
    private T[] pila;
    private int tope;
    private final int MAX = 10;
    
    public PilaA(){
        pila = (T[]) new Object[MAX];
        tope = -1;
    }
    
    public PilaA (int max){
        pila = (T[]) new Object[max];
        tope = -1;
    }
    
    public void push(T dato){
        if (tope == pila.length - 1)
            expande();
        tope ++;
        pila[tope] = dato;
    }
    
    private void expande(){
        T[] masGrande = (T[]) new Object[pila.length * 2];
        for (int i = 0; i <= tope; i++)
            masGrande[i] = pila[i];
        pila = masGrande;
    }
    
    public T pop(){
        if (this.isEmpty())
            throw new RuntimeException("La pila no tiene elementos");
        T eliminado =  pila[tope];
        tope --;
        return eliminado;
    }
    
    public T peek(){
        if (this.isEmpty())
            throw new RuntimeException("La pila no tiene elementos");
        return pila[tope];
    }
    
    public boolean isEmpty(){
        return tope == -1;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= tope; i++)
            sb.append(pila[i]).append(" ");
        return sb.toString();
    }
    
    public void multiPop(int n){
        if (n-1 > tope)
            throw new RuntimeException("La pila no tiene suficientes elementos");
        for (int i = 0; i < n; i++)
            this.pop();
    }
}