
package tetris.sovelluslogiikka.pelialue;

import tetris.sovelluslogiikka.pelialue.Pelialue;
import tetris.Apufunktiot;
import java.util.ArrayList;
import tetris.sovelluslogiikka.sekalaiset.Sijainti;
import tetris.sovelluslogiikka.sekalaiset.Palikka;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PelialueTest
{
    Pelialue pelialue;
    
    @Before public void setUp()
    {
        pelialue = new Pelialue(new Sijainti(1, 0), 4, 4);
    }
    
    @Test public void palikoitaEiVoiTunkeaPelialueenUlkopuolelle()
    {
        assertFalse( pelialue.tungePalikka(Apufunktiot.uusiPalikka(3, -1)) );
        assertFalse( pelialue.tungePalikka(Apufunktiot.uusiPalikka(0, 2)) );
        
        assertFalse( pelialue.tungePalikka(Apufunktiot.uusiPalikka(6, 4)) );
        assertFalse( pelialue.tungePalikka(Apufunktiot.uusiPalikka(2, 5)) );
    }
    
    @Test public void palikoitaVoiTunkea()
    {
        assertTrue( pelialue.tungePalikka(Apufunktiot.uusiPalikka(2, 0)) );
        assertTrue( pelialue.tungePalikka(Apufunktiot.uusiPalikka(3, 0)) );
        assertTrue( pelialue.tungePalikka(Apufunktiot.uusiPalikka(2, 1)) );
        
        assertEquals(3, pelialue.lisattyja());
    }
    
    private Palikka[] alustele()
    {
        Palikka tungettavat[] = new Palikka[]
        {
            Apufunktiot.uusiPalikka(2, 0),
            Apufunktiot.uusiPalikka(3, 0),
            Apufunktiot.uusiPalikka(2, 1)
        };
        
        for(Palikka palikka : tungettavat)
            pelialue.tungePalikka(palikka);
        
        return tungettavat;
    }
    
    @Test public void palikoidenHakeminenToimii()
    {
        Palikka[] tungetut = alustele();
        ArrayList<Palikka> palikat = pelialue.palikat();
        
        int i = 0;
        for(Palikka palikka : tungetut)
        {
            assertTrue( palikka.sijainti().equals(tungetut[i].sijainti()) );
            i++;
        }
    }
    
    @Test public void palikoitaVoiPoistaa()
    {
        alustele();
        assertEquals(3, pelialue.lisattyja());
        
        assertTrue(pelialue.poistaPalikka(new Sijainti(3, 0)));
        assertEquals(2, pelialue.lisattyja());
    }
    
    @Test public void palikoitaEiVoiPoistaaPaikoistaJoissaNiitaEiOle()
    {
        alustele();

        assertFalse(pelialue.poistaPalikka(new Sijainti(2, 2)));
        assertEquals(3, pelialue.lisattyja());
    }
    
    @Test public void palikatVoiTyhjentaa()
    {
        alustele();
        
        pelialue.tyhjenna();
        assertEquals(0, pelialue.lisattyja());
    }
}
