/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbolavl;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JFrame;

// @author Diego Gayou
// garciagayou@gmail.com

public class ArbolAVL <T extends Comparable <T>>{
    
    private NodoAVL <T> raiz;
    private int contador;
    
    public ArbolAVL (){
        raiz = null;
        contador = 0;
    }
    
    public ArbolAVL (NodoAVL <T> raiz){
        this.raiz = raiz;
        contador = 0;
    }

    public NodoAVL <T> getRaiz (){
        return raiz;
    }
    
    public int size (){
        return contador;
    }

    public boolean isEmpty (){
        return contador == 0;
    }
    
    public NodoAVL <T> find (T elemento){
        return find (elemento, raiz);
    }
    
    private NodoAVL <T> find (T elemento, NodoAVL <T> actual){
        if (actual == null)
            return null;
        if (actual.elemento.equals(elemento))
            return actual;
        NodoAVL <T> respuesta = find (elemento, actual.izquierdo);
        if (respuesta == null)
            respuesta = find (elemento, actual.derecho);
        return respuesta;
    }
    
    private int calculaFE(NodoAVL <T> nodo) { 
        return this.altura(nodo.derecho) - this.altura(nodo.izquierdo);
    }
    
    private NodoAVL<T> izquierdaIzquierda(NodoAVL<T> actual) {
        NodoAVL<T> alfa = actual;
        NodoAVL<T> beta = alfa.izquierdo;
        NodoAVL<T> C = beta.derecho;
        if (alfa == this.raiz)
            this.raiz = beta;
        beta.padre = alfa.padre;
        alfa.padre = beta;
        alfa.izquierdo = C;
        if (C != null)
            C.padre = alfa;
        beta.derecho = alfa;
        if (beta.padre != null) 
            if (beta.padre.izquierdo == alfa) 
                beta.padre.izquierdo = beta;
            else 
                beta.padre.derecho = beta;
        alfa.factorEquilibrio = calculaFE(alfa);
        beta.factorEquilibrio = calculaFE(beta);
        return beta;
    }

    private NodoAVL<T> derechaDerecha(NodoAVL<T> actual) {
        NodoAVL<T> alfa = actual;
        NodoAVL<T> beta = alfa.derecho;
        NodoAVL<T> B = beta.izquierdo;
        if (alfa == this.raiz)
            this.raiz = beta;
        beta.padre = alfa.padre;
        alfa.padre = beta;
        if (B != null)
            B.padre = alfa;
        alfa.derecho = B;
        beta.izquierdo = alfa;
        if (beta.padre != null) 
            if (beta.padre.izquierdo == alfa) 
                beta.padre.izquierdo = beta;
            else 
                beta.padre.derecho = beta;
        alfa.factorEquilibrio = calculaFE(alfa);
        beta.factorEquilibrio = calculaFE(beta);
        return beta;
    }

    private NodoAVL<T> izquierdaDerecha(NodoAVL<T> actual) {
        NodoAVL<T> alfa = actual;
        NodoAVL<T> beta = alfa.izquierdo;
        NodoAVL<T> gamma = beta.derecho;
        NodoAVL<T> B = gamma.izquierdo;
        NodoAVL<T> C = gamma.derecho;
        if (alfa == this.raiz)
            this.raiz = gamma;
        gamma.padre = alfa.padre;
        if (B != null)
            B.padre = beta;
        beta.derecho = B;
        if (C != null)
            C.padre = alfa;
        alfa.izquierdo = C;
        alfa.padre = gamma;
        beta.padre = gamma;
        gamma.izquierdo = beta;
        gamma.derecho = alfa;
        if (gamma.padre != null) 
            if (gamma.padre.izquierdo == alfa) 
                gamma.padre.izquierdo = gamma;
            else 
                gamma.padre.derecho = gamma;
        alfa.factorEquilibrio = calculaFE(alfa);
        beta.factorEquilibrio = calculaFE(beta);
        gamma.factorEquilibrio = calculaFE(gamma);
        return gamma; 
    }

    private NodoAVL<T> derechaIzquierda(NodoAVL<T> actual) {
        NodoAVL<T> alfa = actual;
        NodoAVL<T> beta = alfa.derecho;
        NodoAVL<T> gamma = beta.izquierdo;
        NodoAVL<T> B = gamma.izquierdo;
        NodoAVL<T> C = gamma.derecho;
        if (alfa == this.raiz)
            this.raiz = gamma;
        gamma.padre = alfa.padre;
        if (B != null)
            B.padre = alfa;
        alfa.derecho = B;
        if (C != null)
            C.padre = beta;
        beta.izquierdo = C;
        gamma.derecho = beta;
        gamma.izquierdo = alfa;
        alfa.padre = gamma;
        beta.padre = gamma;
        if (gamma.padre != null)
            if (gamma.padre.izquierdo == alfa) 
                gamma.padre.izquierdo = gamma;
            else 
                gamma.padre.derecho = gamma;
        alfa.factorEquilibrio = calculaFE(alfa);
        beta.factorEquilibrio = calculaFE(beta);
        gamma.factorEquilibrio = calculaFE(gamma);
        return gamma;
    }
    
    public void inserta(T elemento) {
        if (raiz == null){
            raiz = new NodoAVL(elemento);
            contador ++;
        }
        else if (this.find(elemento) == null){
            NodoAVL <T> nuevoNodo = inserta(raiz, null, elemento);
            revisaInsercion(nuevoNodo, nuevoNodo.padre);
            contador ++;
        }
    }

    private NodoAVL <T> inserta(NodoAVL<T> actual, NodoAVL<T> padre, T elemento) {
        if (actual == null) {
            NodoAVL<T> nuevoNodo = new NodoAVL(elemento);
            padre.cuelga(nuevoNodo);
            return nuevoNodo;
        }
        if (elemento.compareTo(actual.elemento) > 0)
            return inserta(actual.derecho, actual, elemento);
        return inserta(actual.izquierdo, actual, elemento);
    }
    
    private void revisaInsercion(NodoAVL <T> actual, NodoAVL <T> padre) {
        if (actual == raiz)
            return;
        int factor;
        if (actual.elemento.compareTo(padre.elemento) > 0)
            factor = padre.factorEquilibrio + 1;
        else
            factor = padre.factorEquilibrio - 1;
        padre.setFactorEquilibrio(factor);
            
        if (factor == 0)
            return;
        if (factor == 2){
            if (actual.factorEquilibrio >= 0)
                derechaDerecha(padre);
            else
                derechaIzquierda(padre);
            return;
        }
        if (factor == -2){
           if (actual.factorEquilibrio <= 0)
                izquierdaIzquierda(padre);
            else
                izquierdaDerecha(padre);
            return; 
        }
        
        revisaInsercion(actual.padre, padre.padre);
    }
    
    public T borra (T elem){
        
        NodoAVL<T> actual = find(elem);
        if (actual == null){ 
            throw new RuntimeException("No está el dato");
        }
        
        contador --;
        NodoAVL<T> papa = actual.getPadre();
        NodoAVL<T> temp;
        T res = actual.elemento;

        if (actual.getIzquierda() == null && actual.getDerecha() == null) {
            if (actual == raiz) 
                raiz = null;
            if ((res.compareTo(actual.getPadre().getElemento())) > 0)
                papa.setDerecha(null);
            else
                papa.setIzquierda(null);
            actual.setPadre(null);
            temp = papa;
        }
      
        if (actual.getIzquierda() == null || actual.getDerecha() == null) {
            NodoAVL<T> hijo;
            if (actual.getIzquierda() == null) 
                hijo = actual.getDerecha();
            else 
                hijo = actual.getIzquierda();
            
            if (actual.equals(raiz)) 
                raiz = hijo;
            else 
                papa.cuelga(hijo);
            actual.setPadre(null);
            temp = papa;
        }
        else {
            NodoAVL<T> aux = actual.getDerecha();
            while (aux.getIzquierda() != null) 
                aux = aux.getIzquierda(); 
            actual.setElemento(aux.elemento);
            if (aux != actual.getDerecha()){ 
                temp = aux.getPadre();
                if (aux.getDerecha() == null) {
                    aux.getPadre().setIzquierda(null);
                    aux.setPadre(null);
                }
                else{
                    aux.getPadre().cuelga(aux.getDerecha()); 
                    aux.setPadre(null);
                }
            }
            else{ 
                temp = actual;
                if (aux.getDerecha() == null){
                    actual.setDerecha(null);
                    aux.setPadre(null);
                }
                else{
                    aux.getDerecha().setPadre(actual.getPadre());
                    aux.getPadre().setDerecha(aux.getDerecha());
                    aux.setDerecha(null);
                    aux.setPadre(null);
                }
            }
        }
      
        boolean bandera = false;
        NodoAVL<T> papaTemp = temp;
        while (!bandera && papaTemp != null){
            papaTemp.setFactorEquilibrio(calculaFE(papaTemp));
            if (papaTemp.factorEquilibrio == 1 || papaTemp.factorEquilibrio == -1)
                bandera = true;
            if (papaTemp.factorEquilibrio == 2)
                if (actual.factorEquilibrio >= 0)
                    papaTemp = derechaDerecha(papaTemp);
                else
                    papaTemp = derechaIzquierda(papaTemp);
            
            if (papaTemp.factorEquilibrio == -2)
                if (actual.factorEquilibrio <= 0)
                    papaTemp = izquierdaIzquierda(papaTemp);
                else
                    papaTemp = izquierdaDerecha(papaTemp);
            
            temp = papaTemp;
            papaTemp = papaTemp.getPadre();
        }
        return res;
    }

    public T findMin() {
        return findMin(raiz);
    }
    
    public T findMin(NodoAVL <T> actual) {
        if (actual.izquierdo == null)
            return actual.elemento;
        return findMax(actual.izquierdo);
    }

    public T findMax() {
        return findMax(raiz);
    }
    
    private T findMax(NodoAVL <T> actual) {
        if (actual.derecho == null)
            return actual.elemento;
        return findMax(actual.derecho);
    }
    
    public Iterator<T> preorden (){
        ArrayList <T> respuesta = new ArrayList <> ();
        preorden(raiz, respuesta);
        return new IteradorArbol(respuesta);
    }
    
    private void preorden (NodoAVL <T> actual, ArrayList<T> lista){
        if (actual != null){
            lista.add(actual.elemento);
            preorden (actual.izquierdo, lista);
            preorden (actual.derecho, lista);
        }
    }
    
    public Iterator<T> inorden (){
        ArrayList <T> respuesta = new ArrayList <> ();
        inorden(raiz, respuesta);
        return new IteradorArbol(respuesta);
    }
    
    private void inorden (NodoAVL <T> actual, ArrayList<T> lista){
        if (actual != null){
            inorden (actual.izquierdo, lista);
            lista.add(actual.elemento);
            inorden (actual.derecho, lista);
        }
    }
    
    public Iterator<T> postorden (){
        ArrayList <T> respuesta = new ArrayList <> ();
        postorden(raiz, respuesta);
        return new IteradorArbol(respuesta);
    }

    private void postorden (NodoAVL <T> actual, ArrayList<T> lista){
        if (actual != null){
            postorden (actual.izquierdo, lista);
            postorden (actual.derecho, lista);
            lista.add(actual.elemento);
        }
    }
    
    public Iterator<T> levelorder (){
        ArrayList <T> respuesta = new ArrayList <> ();
        levelorder(raiz, respuesta);
        return new IteradorArbol(respuesta);
    }
    
    public void levelorder (NodoAVL <T> actual, ArrayList<T> lista){
        PilaA <NodoAVL <T>> pila = new PilaA();
        pila.push(raiz);
        actual = raiz;
        while(!pila.isEmpty()){
            actual = pila.pop();
            if (actual.derecho != null)
                pila.push(actual.derecho);
            if (actual.izquierdo != null)
                pila.push(actual.izquierdo);
            lista.add(actual.elemento);
        }
    }
    
    public void visual(){
        JFrame frame = new JFrame("Visualización Árbol Binario");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        if (raiz != null){
            VisualizacionArbolBinario vista = new VisualizacionArbolBinario(raiz);
        frame.add(vista);
        frame.setSize(800, 600);
        frame.setVisible(true);
        }
    }
    
    public void imprimeNiveles() {
        ArrayList <NodoAVL <T>> lista = new ArrayList <> ();
        lista.add(raiz);
        imprimeNiveles(lista, 1);
    }
    
    private void imprimeNiveles(ArrayList <NodoAVL <T>> nodos, int nivel) {
        if (nodos.isEmpty() || todosNulos(nodos))
            return;
        ArrayList <NodoAVL <T>> nuevosNodos = new ArrayList <NodoAVL <T>> ();
        for (NodoAVL <T> nodo : nodos)
            if (nodo != null) {
                System.out.print(nodo.elemento + "(" + nodo.factorEquilibrio + ") ");
                nuevosNodos.add(nodo.izquierdo);
                nuevosNodos.add(nodo.derecho);
            } else {
                nuevosNodos.add(null);
                nuevosNodos.add(null);
                System.out.print("- ");
            }
        System.out.println("");
        imprimeNiveles(nuevosNodos, nivel+1);
    }

    private boolean todosNulos(ArrayList arreglo) {
        for (Object elemento : arreglo)
            if (elemento != null)
                return false;
        return true;
    }
    
    public int altura() {
        return altura(raiz);
    }

    private int altura(NodoAVL<T> actual) {
        if (actual == null) 
            return 0;
       
        int alturaIzquierdo = altura(actual.izquierdo);
        int alturaDerecho = altura(actual.derecho);

        return 1 + Math.max(alturaIzquierdo, alturaDerecho);
    }

    
    public static void main(String[] args){
        
        ArbolAVL <Integer> miArbol = new ArbolAVL();
        
        miArbol.inserta(100);
        miArbol.inserta(300);
        miArbol.inserta(400);
        miArbol.inserta(50);
        miArbol.inserta(200);
        miArbol.inserta(250);
        miArbol.inserta(75);
        miArbol.inserta(350);
        miArbol.inserta(500);
        miArbol.inserta(375);
        
        miArbol.imprimeNiveles();
        
        miArbol.borra(500);
        miArbol.borra(400);
        miArbol.borra(200);
        
        miArbol.imprimeNiveles();
        miArbol.visual();
        
    }
}