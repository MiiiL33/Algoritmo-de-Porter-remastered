package AlgoritmoDePorter;

public class AgregaPalabras{
    private String[] palabras;
    private int[] cantidad;
    public AgregaPalabras(int cant){
        this.cantidad=new int [cant];
        this.palabras=new String [cant];
        for (int i=0;i<cant;i++){
            cantidad[i]=0;
            palabras[i]="&";
        }
    }
    private void bubbleSort(int[]cant, String[]palab){
        int n=cant.length;
	int temp=0;
        String strtemp="&";
	for(int i=0;i<n;i++){
            for(int j=1;j<(n-i);j++){
		if(cant[j-1]<cant[j]){
                    temp=cant[j-1];
                    strtemp=palab[j-1];
                    cant[j-1]=cant[j];
                    palab[j-1]=palab[j];
                    cant[j]=temp;
                    palab[j]=strtemp;
		}		
            }
	}
    }
    public void AñadirPalabraSingular(String palabra){
        boolean Existe=false;
        for (int i=0; i<this.palabras.length; i++){
            if((this.palabras[i].equals(palabra))){
                Existe=true;
                this.cantidad[i]++;     
            }
        }
        if(!Existe){
            for(int i=0;i<this.palabras.length;i++){
                if(this.palabras[i]=="&"){
                    this.palabras[i]=palabra;
                    this.cantidad[i]++; 
                    break;
                }
            }
        }
    }
    public String obtenerDiccionario(){
        int i;
        for(i=0; i<10; i++){
            if(this.palabras[i].equals("&")){
                break;
            }
        }
        bubbleSort(this.cantidad, this.palabras);
        String resultado="";
        for(int j=0; j<i; j++){ //
            if(this.palabras[j].equals("&"))
                break;
            if(this.cantidad[j]==1)
                resultado=resultado+(this.palabras[j] +this.cantidad[j] +" vez\n");
            else if(this.cantidad[j]>1)
                resultado=resultado+(this.palabras[j] +this.cantidad[j]+" veces\n");
        }
        return resultado.trim();
    }
}
