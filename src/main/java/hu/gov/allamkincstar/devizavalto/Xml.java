/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hu.gov.allamkincstar.devizavalto;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author majercsiknede
 */
public class Xml {

    Document Xml;

    public Xml() {
        this.Xml = Xml;
    }
    
    public Document getXml() {
        return Xml;
    }
    /**
     * 
     * @param urlString
     * @return
     * @throws ParserConfigurationException
     * @throws MalformedURLException
     * @throws SAXException 
     */
    public Document getXmlUrllel(String urlString) throws ParserConfigurationException, MalformedURLException, SAXException, IOException {
        Document xml = null;
        DocumentBuilderFactory gyar = DocumentBuilderFactory.newInstance();
        DocumentBuilder dokepito = gyar.newDocumentBuilder();
        URL url = new URL(urlString);
        CertificatValidator cv=new CertificatValidator();
        HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

        try {
            InputStream stream = url.openStream();
            xml = dokepito.parse(stream);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Hiba történt az értéklista URL-el való feltöltésekor ! ");         
        }
        return xml;
    }
   /**
    * 
    * @param fajlNev
    * @return
    * @throws ParserConfigurationException
    * @throws MalformedURLException
    * @throws SAXException
    * @throws IOException 
    */

    public Document getXmlFajlbol(String fajlNev) throws ParserConfigurationException, MalformedURLException, SAXException, IOException {
        Document xml = null;
        DocumentBuilderFactory gyar = DocumentBuilderFactory.newInstance();
        DocumentBuilder dokepito = gyar.newDocumentBuilder();
        try {
            xml = dokepito.parse(new File(fajlNev));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Hiba történt az értéklista fájlból való feltöltésekor ! ");
        }
        return xml;
    }

}
