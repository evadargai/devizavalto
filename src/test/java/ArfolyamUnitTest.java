
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
/**
 *
 * @author majercsiknede
 */
public class ArfolyamUnitTest {

    private List<DevizaArfolyam> arfolyamLista = new ArrayList<>();
    DevizaArfolyam dev1 = new DevizaArfolyam("USD", 1.1286f);
    DevizaArfolyam dev2 = new DevizaArfolyam("JPY", 129.63f);
    DevizaArfolyam dev3 = new DevizaArfolyam("BGN", 1.9558f);
    DevizaArfolyam dev4 = new DevizaArfolyam("CZK", 24.135f);
    DevizaArfolyam dev5 = new DevizaArfolyam("DKK", 7.4388f);
    DevizaArfolyam dev6 = new DevizaArfolyam("GBP", 0.83208f);

    @Test
    public void ArfolyamUnitTestUSDjo() throws IOException {

        System.out.println("* ArfolyamUnitTest USD - 1.1286");
        arfolyamListaTolt();
        DevizaValto devizavalto = new DevizaValto();
        assertEquals(1.1286f, devizavalto.arfolyam("USD", arfolyamLista));
    }

    @Test
    public void ArfolyamUnitTestUSDrossz() throws IOException {
        System.out.println("* ArfolyamUnitTest USD - 1.3");
        arfolyamListaTolt();
        DevizaValto devizavalto = new DevizaValto();
        assertEquals(1.3f, devizavalto.arfolyam("USD", arfolyamLista));
    }

    @Test
    public void ArfolyamUnitTestCZKjo() throws IOException {
        System.out.println("* ArfolyamUnitTest CZK - 24.135");
        arfolyamListaTolt();
        DevizaValto devizavalto = new DevizaValto();
        assertEquals(24.135f, devizavalto.arfolyam("CZK", arfolyamLista));
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
