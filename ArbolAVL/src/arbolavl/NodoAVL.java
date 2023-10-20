/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbolavl;

// @author Diego Gayou
// garciagayou@gmail.com

public class NodoAVL <T extends Comparable <T>> {
    
    protected T elemento;
    protected  NodoAVL<T> izquierdo, derecho, padre;
    protected int factorEquilibrio;
    
    public NodoAVL (T elemento){
        this.elemento = elemento;
        this.izquierdo = null;
        this.derecho = null;
        this.padre = null;
        this.factorEquilibrio = 0;
    }

    public T getElemento() {
        return elemento;
    }

    public void setElemento(T elemento) {
        this.elemento = elemento;
    }

    public NodoAVL<T> getIzquierda() {
        return izquierdo;
    }

    public void setIzquierda(NodoAVL<T> izquierdo) {
        this.izquierdo = izquierdo;
    }

    public NodoAVL<T> getDerecha() {
        return derecho;
    }

    public void setDerecha(NodoAVL<T> derecho) {
        this.derecho = derecho;
    }

    public NodoAVL<T> getPadre() {
        return padre;
    }

    public void setPadre(NodoAVL<T> padre) {
        this.padre = padre;
    }

    public int getFactorEquilibrio() {
        return factorEquilibrio;
    }

    public void setFactorEquilibrio(int factorEquilibrio) {
        this.factorEquilibrio = factorEquilibrio;
    }
    
    public int numDescendientes(){
       int contador = 0;
       if (izquierdo!=null)
           contador = izquierdo.numDescendientes()+1;
       if (derecho!=null)
           contador+=(derecho.numDescendientes()+1);
       return contador;
    }
    
    public void cuelga(NodoAVL <T> nodo){
        if (nodo == null)
            return;
        if (nodo.elemento.compareTo(this.elemento)<= 0)
            this.izquierdo = nodo;
        else
            this.derecho = nodo;
        nodo.setPadre(this);
    }
    
    public NodoAVL <T> sucesorInOrden(){
        return sucesorInOrden(this.derecho);
    }
    
    private NodoAVL <T> sucesorInOrden(NodoAVL <T> nodo){
        if (nodo == null || nodo.izquierdo == null)
            return nodo;
        return sucesorInOrden(nodo.izquierdo);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(elemento + " - " + factorEquilibrio) ;
        return sb.toString();
    }
}