package konquest.jflex.Replay;
import java_cup.runtime.Symbol;
import konquest.cup.Replay.SimbolosReplay;
%%
%class AnalizadorLexicoReplays
%cupsym SimbolosReplay
%cup
%cupdebug
%line
%column

/*Identifiers*/
Letra = [a-zA-Z]
Signo = [-_$]
Digito = [0123456789]
%%
//Reglas Lexicas

<YYINITIAL>{
    "PLANETAS"                              {return new Symbol(SimbolosReplay.PLANETAS, yycolumn,yyline,yytext());}    
    "RONDAS"                              {return new Symbol(SimbolosReplay.RONDAS, yycolumn,yyline,yytext());}    
    "fila"                                         {return new Symbol(SimbolosReplay.FILA, yycolumn,yyline,yytext());}
     "columna"                                      {return new Symbol(SimbolosReplay.COLUMNA, yycolumn,yyline,yytext());}
     "dueño"                                     {return new Symbol(SimbolosReplay.DUE, yycolumn,yyline,yytext());}
    "nombre"                                     {return new Symbol(SimbolosReplay.NOMBRE, yycolumn,yyline,yytext());}
    "naves"                                      {return new Symbol(SimbolosReplay.NAVES, yycolumn,yyline,yytext());}
    "produccion"                                    {return new Symbol(SimbolosReplay.PRODUCCION, yycolumn,yyline,yytext());}
    "porcentajeMuertes"                         {return new Symbol(SimbolosReplay.PORCENTAJE_MUERTES, yycolumn,yyline,yytext());}
     "origen"                                     {return new Symbol(SimbolosReplay.ORIGEN, yycolumn,yyline,yytext());}   
     "destino"                                     {return new Symbol(SimbolosReplay.DESTINO, yycolumn,yyline,yytext());}   
     "true"                                          {return new Symbol(SimbolosReplay.TRUE, yycolumn,yyline,yytext());}
     "false"                                         {return new Symbol(SimbolosReplay.FALSE, yycolumn,yyline,yytext());}
     "terminado"                                         {return new Symbol(SimbolosReplay.TERMINADO, yycolumn,yyline,yytext());}
           
        "{"                                     {return new Symbol(SimbolosReplay.LLAVE_A, yycolumn,yyline,yytext());}
        "}"                                     {return new Symbol(SimbolosReplay.LLAVE_C, yycolumn,yyline,yytext());}
        ":"                                     {return new Symbol(SimbolosReplay.DOS_PUNTOS, yycolumn,yyline,yytext());}
        ","                                     {return new Symbol(SimbolosReplay.COMA, yycolumn,yyline,yytext());}
        "["                                     {return new Symbol(SimbolosReplay.CORCHETE_A, yycolumn,yyline,yytext());}
        "]"                                     {return new Symbol(SimbolosReplay.CORCHETE_C, yycolumn,yyline,yytext());}
        
        ("\""({Letra}({Letra}){0,2})"\"")                                 {System.out.println("ID_NOMBRE "+yyline+" "+yycolumn);return new Symbol(SimbolosReplay.ID_NOMBRE, yycolumn,yyline,yytext());}
        ("\""({Letra}({Letra}|{Digito}){0,9})"\"")                                 {System.out.println("NOMBRE_JUGADOR "+yyline+" "+yycolumn);return new Symbol(SimbolosReplay.NOMBRE_JUGADOR, yycolumn,yyline,yytext());}
        
        ({Digito})+                                                                 {System.out.println("ENTERO "+yyline+" "+yycolumn);return new Symbol(SimbolosReplay.ENTERO, yycolumn,yyline,yytext());}
        ("0."({Digito})+)                                                           {System.out.println("DECIMAL "+yyline+" "+yycolumn);return new Symbol(SimbolosReplay.DECIMAL, yycolumn,yyline,yytext());}
        [ \t\r\f\n]                            {}
        
        .                                            {System.out.println("ERROR "+yyline+" "+yycolumn);return new Symbol(SimbolosReplay.ERROR,yycolumn,yyline,yytext());}

}