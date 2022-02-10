/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */


import hu.gov.allamkincstar.devizavalto.DevizaArfolyam;
import hu.gov.allamkincstar.devizavalto.DevizaValto;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author majercsiknede
 */
public class AtvaltasUnitTest {
    private List<DevizaArfolyam> arfolyamLista = new ArrayList<>();
    DevizaArfolyam dev1 = new DevizaArfolyam("USD", 1.1286f);
    DevizaArfolyam dev2 = new DevizaArfolyam("JPY", 129.63f);
    DevizaArfolyam dev3 = new DevizaArfolyam("BGN", 1.9558f);
    DevizaArfolyam dev4 = new DevizaArfolyam("CZK", 24.135f);
    DevizaArfolyam dev5 = new DevizaArfolyam("DKK", 7.4388f);
    DevizaArfolyam dev6 = new DevizaArfolyam("GBP", 0.83208f);    

   @Test
    public void ValtasUSD_HUF_jo() throws IOException {
        float valtandoOsszeg=100;
        String valasztottValuta="USD";
        arfolyamListaTolt();
        float ftraValtottOsszeg =0;
        DevizaValto devizavalto = new DevizaValto();
        System.out.println("* Átváltás számolás 100 USD (1,1435) -> 30865 kerekítéssel a helyes, 30865 az assert");
        assertEquals(30865f, devizavalto.atvaltasFtra(valtandoOsszeg, valasztottValuta));
    }     
    
    
    @Test
    public void ValtasUSD_HUF_rossz() throws IOException {
        float valtandoOsszeg=100;
        String valasztottValuta="USD";
        arfolyamListaTolt();
        float ftraValtottOsszeg =0;
        DevizaValto devizavalto = new DevizaValto();
        System.out.println("* Átváltás számolás 100 USD (1,1435) -> 30865 kerekítéssel a a helyes, 30000 az assert,");
        assertEquals(30000f, devizavalto.atvaltasFtra(valtandoOsszeg, valasztottValuta));
    }    
        public void arfolyamListaTolt() {
        arfolyamLista.add(dev1);
        arfolyamLista.add(dev2);
        arfolyamLista.add(dev3);
        arfolyamLista.add(dev4);
        arfolyamLista.add(dev5);
        arfolyamLista.add(dev6);
    }
     @BeforeAll
    public static void setUpClass() {

    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }
}
