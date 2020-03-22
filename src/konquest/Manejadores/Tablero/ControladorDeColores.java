/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest.Manejadores.Tablero;

import java.awt.Color;
import java.util.ArrayList;
import konquest.mapa.Jugador;

/**
 *
 * @author sergio
 */
public class ControladorDeColores {
    public static final Color NEUTRALES=Color.LIGHT_GRAY;
    public static final Color NEUTRAL_SELECCIONADA=Color.GRAY;
    public static final Color COLOR1=new Color(57,45,245);
    public static final Color COLOR1_SELECCIONADA=new Color(46,40,144);
    public static final Color COLOR2=new Color(47,175,202);
    public static final Color COLOR2_SELECCIONADA=new Color(40,114,129);
    public static final Color COLOR3=new Color(69,205,134);
    public static final Color COLOR3_SELECCIONADA=new Color(50,139,92);
    public static final Color COLOR4=new Color(50,213,57);
    public static final Color COLOR4_SELECCIONADA=new Color(55,175,60);
    public static final Color COLOR5=new Color(226,238,18);
    public static final Color COLOR5_SELECCIONADA=new Color(189,198,37);
    public static final Color COLOR6=new Color(255,167,0);
    public static final Color COLOR6_SELECCIONADA=new Color(214,145,16);
    public static final Color COLOR7=new Color(255,45,0);
    public static final Color COLOR7_SELECCIONADA=new Color(194,37,4);
    public static final Color COLOR8=new Color(255,0,59);
    public static final Color COLOR8_SELECCIONADA=new Color(183,8,48);
    public static final Color COLOR9=new Color(250,0,154);
    public static final Color COLOR9_SELECCIONADA=new Color(177,10,110);
    public static final Color COLOR10=new Color(255,0,248);
    public static final Color COLOR10_SELECCIONADA=new Color(199,9,194);
    
    public void generarColores(ArrayList<Jugador> jugadors){
        if (jugadors.size()>10) {
            generarColores((ArrayList<Jugador>)jugadors.subList(9,jugadors.size()));
        }
        ArrayList<Integer> aux=new ArrayList<>();
        for (int i = 0; i < jugadors.size(); i++) {
            if (i==10) {
                break;
            }
            int a;
            boolean seRepite=false;
            do {    
                seRepite=false;
                 a= (int)(Math.random()*10+1);
                for (int j = 0; j < aux.size(); j++) {
                    if (aux.get(j).equals(a)) {
                        seRepite=true;
                    }
                }
            } while (seRepite);
            aux.add(a);
            jugadors.get(i).setColor(obtenerColorPorNumero(a));
        }
    }
    
    public Color obtenerColorPorNumero(int numero){
        switch (numero) {
            case 1:
                return COLOR1;
                
            case 2:
                return COLOR2;
                
            case 3:
                return COLOR3;
                
            case 4:
                return COLOR4;
            case 5:
                return COLOR5;
            case 6:
                return COLOR6;
            case 7:
                return COLOR7;
            case 8:
                return COLOR8;
            case 9:
                return COLOR9;
                
            default:
                return COLOR10;
        }
    }
    
    
    public static Color obtenerColorAlSeleccionar(Color color){
        Color colorNuevo=null;
        if (color.getRGB()==NEUTRALES.getRGB()) {
            return NEUTRAL_SELECCIONADA;
        }else if (color.getRGB()==COLOR1.getRGB()) {
            return COLOR1_SELECCIONADA;
        }else if (color.getRGB()==COLOR2.getRGB()) {
            return COLOR2_SELECCIONADA;
        }else if (color.getRGB()==COLOR3.getRGB()) {
            return COLOR3_SELECCIONADA;
        }else if (color.getRGB()==COLOR4.getRGB()) {
            return COLOR4_SELECCIONADA;
        }else if (color.getRGB()==COLOR5.getRGB()) {
            return COLOR5_SELECCIONADA;
        }else if (color.getRGB()==COLOR6.getRGB()) {
            return COLOR6_SELECCIONADA;
        }else if (color.getRGB()==COLOR7.getRGB()) {
            return COLOR7_SELECCIONADA;
        }else if (color.getRGB()==COLOR8.getRGB()) {
            return COLOR8_SELECCIONADA;
        }else if (color.getRGB()==COLOR9.getRGB()) {
            return COLOR9_SELECCIONADA;
        }else if (color.getRGB()==COLOR10.getRGB()) {
            return COLOR10_SELECCIONADA;
        }
        return colorNuevo;
    }
}
