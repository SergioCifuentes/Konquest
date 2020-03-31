/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest.mapa;

import java.util.ArrayList;
import konquest.Replay.Objetos.PartidaInicial;
import konquest.ui.FramePrincipal;

/**
 *
 * @author sergio
 */
public class Planeta {
    private String nombre;
    protected  int naves;
    protected  Integer produccion;
    private double porcentajeMuertes;
    protected  Jugador owner;
    protected Integer produccionOriginal;
    protected boolean neutral;
    private PartidaInicial pi;
    
    public static boolean verificarObligatorios(Object[] atributos){
        for (int i = 0; i < 4; i++) {
            if (atributos[i]==null||atributos[i]=="") {
                return false;
            }
        }
        return true;
    }
    public void setNeutral(boolean b){
        neutral=b;
    }

    public void setPorcentajeMuertes(double porcentajeMuertes) {
        this.porcentajeMuertes = porcentajeMuertes;
    }

    public void setPi(PartidaInicial pi) {
        this.pi = pi;
    }

    public PartidaInicial getPi() {
        return pi;
    }
    
    
    public Planeta(Object[] atributos) {
        String aux = ((String)atributos[0]).substring(1,((String)atributos[0]).length()-1);
        this.nombre = aux;
        this.naves = (Integer)atributos[1];
        if (atributos[2]==null) {
            this.produccion=null;
            this.produccionOriginal=null;
        }else{
            this.produccion = (Integer)atributos[2];
            this.produccionOriginal=produccion;
        }
        
        
        this.porcentajeMuertes = (Double)atributos[3];
        neutral=false;
    }

    public boolean isNeutral() {
        return neutral;
    }

    public Jugador getOwner() {
        return owner;
    }

    public void setOwner(Jugador owner) {
        this.owner = owner;
    }
     
    public void restarNaves(int naves){
        this.naves-=naves;
    }
    public void recibirNavesAleadas(int naves){
        this.naves+=naves;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNaves(int naves) {
        this.naves = naves;
    }
    public void aumentarProduccion(){
        produccion++;
    }
        public void disminuirProduccion(){
        produccion--;
    }
    
    public int getNaves() {
        return naves;
    }

    public Integer getProduccion() {
        return produccion;
    }

    public void setpr(int pro){
        this.produccion=pro;
    }
    
    public void setProduccion(Integer produccion) {
        this.produccion = produccion;
        this.produccionOriginal=produccion;
    }

    public double getPorcentajeMuertes() {
        return porcentajeMuertes;
    }
    public void serConquistado(Jugador jugadorNuevo,int navesRestantes){
        produccion=produccionOriginal;
        
        this.owner=jugadorNuevo;
        this.naves=navesRestantes;
    }
    
    public static boolean verificarNombresIguales(ArrayList<Planeta> planetas,FramePrincipal fp){
        boolean aux=false;
        for (int i = 0; i < planetas.size()-1; i++) {
            for (int j = i+1; j < planetas.size(); j++) {
                if (planetas.get(i).getNombre().equals(planetas.get(j).getNombre())) {
                    fp.agregarTextoAcciones("Planetas con el mismo Nombre: \""+planetas.get(i).nombre+"\"");
                    aux=true;
                }
            }
        }
        
        return aux;
    }
    
}
