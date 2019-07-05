package eserciziomazzocarte;

import java.util.ArrayList;
import java.util.Collections;
import java.lang.Math;

public class Mazzo extends ArrayList<Carta> {
    public Mazzo()
    {
        for (int i = 1; i < 14; i++)
        {
            this.add(new Carta('C', i));  
            this.add(new Carta('D', i));
            this.add(new Carta('H', i));
            this.add(new Carta('S', i));
            this.add(new Carta('C', i));  
            this.add(new Carta('D', i));
            this.add(new Carta('H', i));
            this.add(new Carta('S', i));
        }
    }
    
    public void mescola()
    {
        Collections.shuffle(this);
    }
    
    public Carta cartaBonus()
    {
        int numero = (int)(Math.random() * (105));
        return this.get(numero);
    }
    
    public int haiVinto(int n)
    {
        int vittoria = -1;
        for (int i = 0; i < n - 1; i++)
        {
            for(int j = i+1; j < n; j++)
            {
                if((this.get(i).numero == this.get(j).numero) && (this.get(i).segno == this.get(j).segno))
                {
                    vittoria = i;
                }
            }
        }
        return vittoria;
    }
}
