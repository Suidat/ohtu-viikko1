package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    @Test
    public void tyhjastaEiVoiOttaa(){
        varasto.otaVarastosta(2);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public  void taysiOnTaynna(){
        varasto.lisaaVarastoon(10);
        varasto.lisaaVarastoon(2);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void varastonLuominenToimii(){
        Varasto v = new Varasto(-2);
        assertEquals(0.0, v.getTilavuus(), vertailuTarkkuus);
        v = new Varasto(-1,-2);
        assertEquals(0.0, v.getSaldo(), vertailuTarkkuus);
        v = new Varasto(2, 3);
        assertEquals(2.0, v.getSaldo(), vertailuTarkkuus);
        v = new Varasto(-1, 3);
        assertEquals(0.0, v.getTilavuus(), vertailuTarkkuus);
        v = new Varasto(10, 3);
        assertEquals(3.0, v.getSaldo(), vertailuTarkkuus);

    }

    @Test
    public void tekstiksiMuuntaminenToimii(){
        String ans = "saldo = 0.0, vielä tilaa 10.0";
        String s = varasto.toString();
        assertEquals(ans, s);
    }

    @Test
    public void varastotToimivatOikein(){
        varasto.lisaaVarastoon(-2);
        assertEquals(0.0, varasto.getSaldo(), vertailuTarkkuus);
        varasto.otaVarastosta(-2);
        assertEquals(0.0, varasto.getSaldo(),vertailuTarkkuus);
    }
}