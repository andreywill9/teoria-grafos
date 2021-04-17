/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bellman.ford;

import java.util.List;
import model.Vertice;

/**
 *
 * @author Alvaro
 */
public class ResultCaminho{
    private int ValorPercorrido;
    private List<Vertice> vertice;
    
    ResultCaminho(List<Vertice> vertice, int ValorPercorrido){
        this.vertice = vertice;
        this.ValorPercorrido = ValorPercorrido;
    }
    
    public int getValorPercorrido(){
        return this.ValorPercorrido;
    }
    
    public List<Vertice> getVertice(){
        return this.vertice;
    }
}
