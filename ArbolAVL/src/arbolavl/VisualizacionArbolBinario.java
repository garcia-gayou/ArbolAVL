/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbolavl;
import javax.swing.*;
import java.awt.*;

// @author Diego Gayou
// garciagayou@gmail.com

public class VisualizacionArbolBinario <T extends Comparable<T>> extends JPanel {
    
    private NodoAVL <T> raiz;
    private final int nodeRadius = 20;
    private final int yOffset = 50;
    private final int levelHeight = 80;

    public VisualizacionArbolBinario(NodoAVL <T> raiz) {
        this.raiz = raiz;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int rootX = getWidth() / 2;
        int rootY = yOffset;
        drawTree(g, rootX, rootY, raiz, getWidth() / 4);
    }

    private void drawTree(Graphics g, int x, int y, NodoAVL <T> node, int width) {
        g.setColor(Color.black);
        g.fillOval(x - nodeRadius, y - nodeRadius, 2 * nodeRadius, 2 * nodeRadius);
        g.setColor(Color.white);
        g.drawString(String.valueOf(node.elemento), x - 5, y + 5);
        g.setColor(Color.black);
        g.drawString(String.valueOf(node.factorEquilibrio), x + 30, y + 5);

        if (node.izquierdo != null) {
            int leftX = x - width;
            int leftY = y + levelHeight;
            g.drawLine(x - nodeRadius, y + nodeRadius, leftX, leftY - nodeRadius);
            drawTree(g, leftX, leftY, node.izquierdo, width / 2);
        }

        if (node.derecho != null) {
            int rightX = x + width;
            int rightY = y + levelHeight;
            g.drawLine(x + nodeRadius, y + nodeRadius, rightX, rightY - nodeRadius);
            drawTree(g, rightX, rightY, node.derecho, width / 2);
        }
    }     
}