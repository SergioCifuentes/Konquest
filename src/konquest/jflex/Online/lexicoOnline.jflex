package konquest.jflex.Online;
import java_cup.runtime.Symbol;
import konquest.cup.Online.SimbolosOnline;
%%
%class AnalizadorLexicoOnline
%cupsym SimbolosOnline
%cup
%cupdebug
%line
%column


/*Identifiers*/
Letra = [a-zA-Z]
Digito = [0123456789]

%%

<YYINITIAL>{
"origen"                                     {return new Symbol(SimbolosOnline.ORIGEN, yycolumn,yyline,yytext());}   
     "destino"                                     {return new Symbol(SimbolosOnline.DESTINO, yycolumn,yyline,yytext());}   
     "naves"                                      {return new Symbol(SimbolosOnline.NAVES, yycolumn,yyline,yytext());}
      "{"                                     {return new Symbol(SimbolosOnline.LLAVE_A, yycolumn,yyline,yytext());}
        "}"                                     {return new Symbol(SimbolosOnline.LLAVE_C, yycolumn,yyline,yytext());}
         ":"                                     {return new Symbol(SimbolosOnline.DOS_PUNTOS, yycolumn,yyline,yytext());}
        ","                                     {return new Symbol(SimbolosOnline.COMA, yycolumn,yyline,yytext());}
       

        ("\""({Letra}({Letra}){0,2})"\"")                                 {return new Symbol(SimbolosOnline.ID_NOMBRE, yycolumn,yyline,yytext());}
         ({Digito})+                                                                 {return new Symbol(SimbolosOnline.ENTERO, yycolumn,yyline,yytext());}
          [ \t\r\f\n]                            {}
        
        .                                            {return new Symbol(SimbolosOnline.ERROR,yycolumn,yyline,yytext());}

}
