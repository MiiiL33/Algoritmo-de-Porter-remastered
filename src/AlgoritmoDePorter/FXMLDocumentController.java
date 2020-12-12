package AlgoritmoDePorter;

import java.net.URL;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

public class FXMLDocumentController implements Initializable{
    @FXML private Label label;
    @FXML private Label AlgDePort;
    @FXML private Label labelCantPalabras;
    @FXML private TextArea campoDeTexto;
    @FXML private TextArea campoDeResumen;
    @FXML private Hyperlink hyperlink;
    @FXML private Button buttonPrefijos;
    @FXML private Button buttonPronombres;
    @FXML private Button buttonNormalizar;
    @FXML private Button buttonSufijos;
    @FXML private Button buttonArticulos;
    @FXML private Button buttonObtener;
    @FXML private Button leerTxt;
    @FXML private Label labelTxt;
    ContarPalabras cp = new ContarPalabras();
    int cantidad;
    
    @FXML private void handleButtonleerTxt(ActionEvent event) throws IOException{
        FileChooser fc = new FileChooser();
        FileChooser.ExtensionFilter ef = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fc.getExtensionFilters().add(ef);
        File file = fc.showOpenDialog(null);
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "Cp1252"));
        String linea = null;
        StringBuilder sb = new StringBuilder();
        String ls = System.getProperty("line.separator");
        try{
            while((linea = br.readLine()) != null) {
                sb.append(linea);
                sb.append(ls);
            }
            String texto = sb.toString();
            campoDeTexto.setText(texto);
            labelTxt.setText("Archivo .txt leido correctamente");
            cantidad=cp.cuentaPalabras(texto);
            labelCantPalabras.setText("Cantidad de palabras: " + cantidad);
        } finally {
            br.close();
        }
    }
    
    @FXML private void contarPalabras(){
        String texto=campoDeTexto.getText();
        cantidad=cp.cuentaPalabras(texto);
        labelCantPalabras.setText("Cantidad de palabras: " + cantidad);
    }
    @FXML private void handleHyperlink(ActionEvent event){ //abre un enlace en el navegador predeterminado al repositorio
        try{
            Desktop d=Desktop.getDesktop();
            d.browse(new URI("https://github.com/MiiiL33/Algoritmo-de-Porter-remastered"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @FXML private void handleButtonNormalizarTexto(ActionEvent event){
        if (campoDeTexto.getText().isEmpty())
            label.setText("Texto vacío");
        else{
            System.out.println("Pasando a minúsculas");        
            String texto=campoDeTexto.getText();
            texto=texto.toLowerCase();
            System.out.println("Eliminando tildes");
            Regex regex=new Regex();
            texto=regex.borrarTildes(texto);
            System.out.println("Quitando signos de puntuación y números");
            texto=regex.borrarPuntuacionYNumeros(texto);
            System.out.println("Eliminando texto residual");
            texto=regex.borrarTextoResidual(texto);
            if (texto.charAt(0) == ' ') campoDeTexto.setText(texto.substring(1));
            else campoDeTexto.setText(texto);
            label.setText("Texto normalizado");
            cantidad=cp.cuentaPalabras(texto);
            labelCantPalabras.setText("Cantidad de palabras: " + cantidad);
        }
    }
    @FXML private void handleButtonEliminarPronombres(ActionEvent event){
        if (campoDeTexto.getText().isEmpty()) label.setText("Texto vacío");
        else{
            System.out.println("Eliminando pronombres");
            String texto=campoDeTexto.getText();
            Regex regex=new Regex();
            texto=regex.borrarPronombres(texto);
            System.out.println("Eliminando texto residual");
            texto=regex.borrarTextoResidual(texto);
            campoDeTexto.setText(texto);
            label.setText("Pronombres eliminados");
            cantidad=cp.cuentaPalabras(texto);
            labelCantPalabras.setText("Cantidad de palabras: " + cantidad);
        }
    }
    @FXML private void handleButtonEliminarPrefijos(ActionEvent event){
        if (campoDeTexto.getText().isEmpty()) label.setText("Texto vacío");
        else{
            System.out.println("Eliminando prefijos");
            String texto=campoDeTexto.getText();
            Regex regex=new Regex();
            texto=regex.borrarPrefijos(texto);
            System.out.println("Eliminando texto residual");
            texto=regex.borrarTextoResidual(texto);
            campoDeTexto.setText(texto);
            label.setText("Prefijos eliminados");
            cantidad=cp.cuentaPalabras(texto);
            labelCantPalabras.setText("Cantidad de palabras: " + cantidad);
        }
    }
    @FXML private void handleButtonEliminarSufijos(ActionEvent event){
        if (campoDeTexto.getText().isEmpty())label.setText("Texto vacío");
        else{
            System.out.println("Eliminando sufijos");
            String texto=campoDeTexto.getText();
            Regex regex=new Regex();
            texto=regex.borrarSufijos(texto);
            System.out.println("Eliminando texto residual");
            texto=regex.borrarTextoResidual(texto);
            campoDeTexto.setText(texto);
            label.setText("Sufijos eliminados");
            cantidad=cp.cuentaPalabras(texto);
            labelCantPalabras.setText("Cantidad de palabras: " + cantidad);
        }
    }
    @FXML private void handleButtonEliminarArticulos(ActionEvent event){
        if (campoDeTexto.getText().isEmpty()) label.setText("Texto vacío");
        else{
            System.out.println("Eliminando artículos");
            String texto=campoDeTexto.getText();
            Regex regex=new Regex();
            texto=regex.borrarArticulos(texto);
            System.out.println("Eliminando texto residual");
            texto=regex.borrarTextoResidual(texto);
            campoDeTexto.setText(texto);
            label.setText("Artículos eliminados");
            cantidad=cp.cuentaPalabras(texto);
            labelCantPalabras.setText("Cantidad de palabras: " + cantidad);
        }
    }
    @FXML private void handleButtonObtener(ActionEvent event){
        if (campoDeTexto.getText().isEmpty()) label.setText("Texto vacío");
        else{
            System.out.println("Obteniendo palabras repetidas");
            String texto=campoDeTexto.getText(), aux;
            ContarPalabras cp=new ContarPalabras();
            try {
                aux = cp.AñadirPalabras(texto);
                campoDeResumen.setText(aux);
            } catch (Exception e){
                campoDeResumen.setText("Texto no contiene palabras repetidas");
            }
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb){
    }
}
