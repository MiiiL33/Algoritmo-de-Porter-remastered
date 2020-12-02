package AlgoritmoDePorter;

public class ContarPalabras{
    private AgregaPalabras Indice;
    public int cuentaPalabras(String Texto){
        if (Texto == null || Texto.isEmpty()){
            return 0;
        }
        String[] palabras=Texto.split("\\s+");
        return palabras.length;
    }
    public String AñadirPalabras(String Texto){
        Indice = new AgregaPalabras(cuentaPalabras(Texto));
        Texto=Texto+' ';
        String agregar="";
        int textoacodigo;
        for(int j=0; j<Texto.length();j++){
            textoacodigo=(int)Texto.charAt(j);
            if(textoacodigo!=32){ //recorre la palabra actual hasta que encuentre el caracter ascii 32, que es espacio
                agregar=agregar+Texto.charAt(j);
            }
            else{
                Indice.AñadirPalabraSingular(agregar); 
                agregar="";
            }
        }
        return Indice.obtenerDiccionario();
    }
}
