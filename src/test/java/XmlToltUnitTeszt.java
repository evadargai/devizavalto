/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import hu.gov.allamkincstar.devizavalto.DevizaValto;
import hu.gov.allamkincstar.devizavalto.Xml;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author majercsiknede
 */
public class XmlToltUnitTeszt {

    private String urlString = "https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml";
    private String fajlNev = "d:\\majercsiknede\\Documents\\NetBeansProjects\\devizavalto\\arfolyam.xml";

    @Test
    public void xmlUrlelTesztExParserRossz() {
        Xml xmlUrlel = new Xml();
        Document xml = null;
        System.out.println("* xmlUrlelTesztExParserRossz nem létező URL kivétel rossz: ParserConfigurationException ");
        Exception exceptionParser = assertThrows(ParserConfigurationException.class, () -> {
            xmlUrlel.getXmlUrllel("nincsilyenurl");
        });
    }

    @Test
    public void xmlUrlelTesztExMailFormedJo() {
        Xml xmlUrlel = new Xml();
        Document xml = null;
        Exception exceptionMalformed = null;
        System.out.println("* xmlUrlelTesztExMailFormedJo nem létező URL kivétel jó: ava.net.MalformedURLException");
        exceptionMalformed = assertThrows(java.net.MalformedURLException.class, () -> {
            xmlUrlel.getXmlUrllel("nincsilyenurl");
        });
    }

    @Test
    public void xmlUrlelTesztJoURL() throws IOException {
        Xml xmlUrlel = new Xml();
        System.out.println("* xmlUrlelTesztJoURL " + urlString);
        try {
            Document xmldoc = xmlUrlel.getXmlUrllel(urlString);
            NodeList csomopontok = xmldoc.getElementsByTagName("*");
            Element elem = (Element) csomopontok.item(5);
            assertEquals("Cube", elem.getNodeName());
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(DevizaValto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(DevizaValto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(DevizaValto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void xmlUrlelTesztRosszURL() throws IOException {
        Xml xmlUrlel = new Xml();
        String url = "https://nincsilyenurl";
        System.out.println("* xmlUrlelTeszt Rossz URL elvárás üzenet " + url);
        try {
            Document xmldoc = xmlUrlel.getXmlUrllel(url);
            NodeList csomopontok = xmldoc.getElementsByTagName("*");
            Element elem = (Element) csomopontok.item(5);
            assertEquals("Cube", elem.getNodeName());
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(DevizaValto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(DevizaValto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(DevizaValto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void xmlFajlbolTesztJoFajl() throws ParserConfigurationException, SAXException, IOException {
        Xml xmlFajlbol = new Xml();
        System.out.println("* xmlFajlbol Teszt Jó fájl arfolyam.xml");
        Document xmldoc = xmlFajlbol.getXmlFajlbol(fajlNev);
        NodeList csomopontok = xmldoc.getElementsByTagName("*");
        Element elem = (Element) csomopontok.item(5);
        assertEquals("Cube", elem.getNodeName());
    }

    @Test
    public void xmlFajlbolTesztRosszFajl() throws ParserConfigurationException, SAXException, IOException {
        String fajl = "nincsilyenfajl";
        Xml xmlFajlbol = new Xml();
        System.out.println("* xmlFajlbol Teszt Roszz elvárás üzenet");
        Document xmldoc = xmlFajlbol.getXmlFajlbol(fajl);
        try {
            NodeList csomopontok = xmldoc.getElementsByTagName("*");
            Element elem = (Element) csomopontok.item(5);
            assertEquals("Cube", elem.getNodeName());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
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
