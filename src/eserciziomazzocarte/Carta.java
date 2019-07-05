package eserciziomazzocarte;

import javafx.scene.image.Image;

public class Carta {
    public int numero;
    public char segno;
    public Image immagine;
    
    public Carta(char segno, int numero)
    {
        String URL = "/img/" + numero + segno +  ".png";
        this.segno = segno;
        this.numero = numero;
        this.immagine = new Image(getClass().getResource(URL).toString());
    }
    
    
   
}
   

