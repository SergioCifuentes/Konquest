package konquest.jflex.Json;
import java_cup.runtime.Symbol;
import konquest.cup.Json.SimbolosJson;
%%
%class AnalizadorLexicoJson
%cupsym SimbolosJson
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
        "MAPA"                                  {System.out.println("map "+yyline+" "+yycolumn);return new Symbol(SimbolosJson.MAPA, yycolumn,yyline,yytext());}
    //Atributos de Mapa:
            "id"                                         {System.out.println("id"+yyline+" "+yycolumn);return new Symbol(SimbolosJson.ID, yycolumn,yyline,yytext());}
            "tamaño"                                     {System.out.println("ta "+yyline+" "+yycolumn);return new Symbol(SimbolosJson.TAMAÑO, yycolumn,yyline,yytext());}
                "filas"                                         {return new Symbol(SimbolosJson.FILAS, yycolumn,yyline,yytext());}
                "columnas"                                      {return new Symbol(SimbolosJson.COLUMNAS, yycolumn,yyline,yytext());}
            "alAzar"                                     {return new Symbol(SimbolosJson.AL_AZAR, yycolumn,yyline,yytext());}
                "true"                                          {return new Symbol(SimbolosJson.TRUE, yycolumn,yyline,yytext());}
                "false"                                         {return new Symbol(SimbolosJson.FALSE, yycolumn,yyline,yytext());}
            "planetasNeutrales"                          {return new Symbol(SimbolosJson.PLANETAS_NEUTRALES_MIN, yycolumn,yyline,yytext());}
            "mapaCiego"                                  {return new Symbol(SimbolosJson.MAPA_CIEGO, yycolumn,yyline,yytext());}
            "acumular"                                   {return new Symbol(SimbolosJson.ACUMULAR, yycolumn,yyline,yytext());}
            "NEUTRALES"                                  {return new Symbol(SimbolosJson.NEUTRALES, yycolumn,yyline,yytext());}
                "mostrarNaves"                                  {return new Symbol(SimbolosJson.MOSTRAR_NAVES, yycolumn,yyline,yytext());}
                "mostrarEstadisticas"                           {return new Symbol(SimbolosJson.MOSTRAR_ESTADISTICAS, yycolumn,yyline,yytext());}
                "produccion"                                    {return new Symbol(SimbolosJson.PRODUCCION, yycolumn,yyline,yytext());}
            "finalizacion"                               {return new Symbol(SimbolosJson.FINALIZACION, yycolumn,yyline,yytext());}    
        "PLANETAS"                              {return new Symbol(SimbolosJson.PLANETAS, yycolumn,yyline,yytext());}    
            "nombre"                                     {return new Symbol(SimbolosJson.NOMBRE, yycolumn,yyline,yytext());}
            "naves"                                      {return new Symbol(SimbolosJson.NAVES, yycolumn,yyline,yytext());}
            "porcentajeMuertes"                         {return new Symbol(SimbolosJson.PORCENTAJE_MUERTES, yycolumn,yyline,yytext());}
        "PLANETAS_NEUTRALES"                    {return new Symbol(SimbolosJson.PLANETAS_NEUTRALES, yycolumn,yyline,yytext());}
        "JUGADORES"                             {return new Symbol(SimbolosJson.JUGADORES, yycolumn,yyline,yytext());}
            "planetas"                                   {return new Symbol(SimbolosJson.PLANETASMIN, yycolumn,yyline,yytext());}
            "tipo"                                       {return new Symbol(SimbolosJson.TIPO, yycolumn,yyline,yytext());}
                "HUMANO"                                        {return new Symbol(SimbolosJson.HUMANO, yycolumn,yyline,yytext());}
                "FACIL"                                         {return new Symbol(SimbolosJson.FACIL, yycolumn,yyline,yytext());}
                "DIFICIL"                                       {return new Symbol(SimbolosJson.DIFICIL, yycolumn,yyline,yytext());}
        "{"                                     {return new Symbol(SimbolosJson.LLAVE_A, yycolumn,yyline,yytext());}
        "}"                                     {return new Symbol(SimbolosJson.LLAVE_C, yycolumn,yyline,yytext());}
        ":"                                     {return new Symbol(SimbolosJson.DOS_PUNTOS, yycolumn,yyline,yytext());}
        ","                                     {return new Symbol(SimbolosJson.COMA, yycolumn,yyline,yytext());}
        "["                                     {return new Symbol(SimbolosJson.CORCHETE_A, yycolumn,yyline,yytext());}
        "]"                                     {return new Symbol(SimbolosJson.CORCHETE_C, yycolumn,yyline,yytext());}
        
        ("\""({Letra}({Letra}){0,2})"\"")                                 {System.out.println("ID_NOMBRE "+yyline+" "+yycolumn);return new Symbol(SimbolosJson.ID_NOMBRE, yycolumn,yyline,yytext());}
        ("\""({Letra}({Letra}|{Digito}){0,9})"\"")                                 {System.out.println("NOMBRE_JUGADOR "+yyline+" "+yycolumn);return new Symbol(SimbolosJson.NOMBRE_JUGADOR, yycolumn,yyline,yytext());}
        ("\""({Letra}|{Signo}) ({Letra}|{Signo}|{Digito}|" ")* "\"")                {System.out.println("ID_MAPA "+yyline+" "+yycolumn);return new Symbol(SimbolosJson.ID_MAPA, yycolumn,yyline,yytext()); }
        ({Digito})+                                                                 {System.out.println("ENTERO "+yyline+" "+yycolumn);return new Symbol(SimbolosJson.NUMERO, yycolumn,yyline,yytext());}
        ("0."({Digito})+)                                                           {System.out.println("DECIMAL "+yyline+" "+yycolumn);return new Symbol(SimbolosJson.DECIMAL, yycolumn,yyline,yytext());}
        [ \t\r\f\n]                            {}
        
        .                                            {System.out.println("ERROR "+yyline+" "+yycolumn);return new Symbol(SimbolosJson.ERROR,yycolumn,yyline,yytext());}

}

