/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package hu.gov.allamkincstar.devizavalto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author majercsiknede
 */
public class DevizaValto extends javax.swing.JFrame implements ActionListener {

    private static String urlString = "https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml";
    private List<DevizaArfolyam> arfolyamLista = new ArrayList<>();
    public DevizaValto() throws IOException {
        initComponents();

        arfolyamListaTolt(xmlVisszaUrl(urlString));
        if (arfolyamLista.size() > 0) {
            comboBoxTolt(arfolyamLista);
            fajlValasztoBT.setEnabled(false);
            xmlFajlTF.setEnabled(false);
        }
        else{
            fajlValasztoBT.setEnabled(true);
            xmlFajlTF.setEnabled(true);
            fajlValasztoBT.addActionListener(this);
        }
        valtasBT.addActionListener(this);
        
    }

    /**
     * Combobox feltoltese az árfolyamlistaból
     *
     * @param arfolyamLista
     */
    public void comboBoxTolt(List<DevizaArfolyam> arfolyamLista) {

        for (DevizaArfolyam arfolyam : arfolyamLista) {
            arfolyamokCB.addItem(arfolyam.getDeviza() + " - " + arfolyam.getArfolyam());
        }
    }

    /**
     * Fájlból visszakapott XML
     *
     * @param fajlNev
     * @return
     * @throws IOException
     */
    public Document xmlVisszaFajl(String fajlNev) throws IOException {
        Xml xmlFajlbol = new Xml();
        try {
            Document xml = xmlFajlbol.getXmlFajlbol(fajlNev);
            return xml;
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(DevizaValto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(DevizaValto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DevizaValto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Url-el visszakapott xml
     *
     * @param urlString
     * @return
     */
    public Document xmlVisszaUrl(String urlString) throws IOException {
        Xml xmlUrlel = new Xml();
        try {
            Document xml = xmlUrlel.getXmlUrllel(urlString);
            return xml;
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(DevizaValto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(DevizaValto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(DevizaValto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Árfolyamok betöltése XML-ből az arfolyamLista listába
     *
     * @param xml
     */
    public void arfolyamListaTolt(Document xml) {
        if (xml != null) {
            NodeList csomopontok = xml.getElementsByTagName("*");
            for (int i = 0; i < csomopontok.getLength(); i++) {
                Element elem = (Element) csomopontok.item(i);
                /* Ha a currency csomopont nem ures*/
                if (elem.getAttribute("currency") != null && !elem.getAttribute("currency").isEmpty()) {
                    arfolyamLista.add(new DevizaArfolyam(String.valueOf(elem.getAttribute("currency")), Float.valueOf(elem.getAttribute("rate"))));
                }
            }
        }
    }

    /**
     * Adott deviza árfolyama a listából
     *
     * @param deviza
     * @param arfolyamLista
     * @return
     */
    public float arfolyam(String deviza, List<DevizaArfolyam> arfolyamLista) {
        float arfolyam = 0;
        for (DevizaArfolyam arf : arfolyamLista) {
            if (arf.getDeviza().equals(deviza)) {
                arfolyam = arf.getArfolyam();
            }
        }
        return arfolyam;
    }

    /**
     * Fájl választása default útvonal, és fájl típus megadásával
     *
     * @return
     */
    public File fajlValaszto() {
        File xmlFajl = null;
        //JFileChooser fajlValaszto = new JFileChooser("d:\\majercsiknede\\Documents\\NetBeansProjects\\devizavalto\\");
        JFileChooser fajlValaszto = new JFileChooser("d:\\");
        fajlValaszto.setDialogTitle("XML fájl választása");
        fajlValaszto.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter szures = new FileNameExtensionFilter("XML fájlok", "xml");
        fajlValaszto.addChoosableFileFilter(szures);
        int returnValue = fajlValaszto.showOpenDialog(null);
        if (returnValue == fajlValaszto.APPROVE_OPTION) {
            xmlFajl = fajlValaszto.getSelectedFile();
        }
        return xmlFajl;
    }

    /**
     * Átváltás számolása
     *
     * @param Osszeg
     * @param devizaNem
     * @return
     */
    public float atvaltasFtra(float Osszeg, String devizaNem) {
        float ftraValtottOsszeg = 0;
        float valasztottArfolyam;
        valasztottArfolyam = arfolyam(devizaNem, arfolyamLista);
        //ftraValtottOsszeg = Math.round(((Osszeg / valasztottArfolyam) * arfolyam("HUF", arfolyamLista)) / 5) * 5f;
       ftraValtottOsszeg = Math.round(((Osszeg / valasztottArfolyam) * arfolyam("HUF", arfolyamLista))) ;

        return ftraValtottOsszeg;
    }

    /**
     *
     * @param actionEvent
     */
    public void actionPerformed(ActionEvent actionEvent) {
        String actCommand = actionEvent.getActionCommand();
        String valasztottValuta;
        float valtandoOsszeg;
        float ftraValtottOsszeg = 0;
        File xmlFajl;
        if (actCommand == "ftraValt") {
            if (valtandoOsszegTF.getText() != null && !valtandoOsszegTF.getText().isEmpty()) {
                valtandoOsszeg = Float.valueOf(valtandoOsszegTF.getText());
                valasztottValuta = arfolyamokCB.getSelectedItem().toString().substring(0, 3);
                ftraValtottOsszeg = atvaltasFtra(valtandoOsszeg, valasztottValuta);
            } else {
                JOptionPane.showMessageDialog(null, "Kérem adjon meg összeget ! ");
            }
            eredmenyOsszegTF.setText(String.valueOf(ftraValtottOsszeg));

        } else if (actCommand == "xmlFajlValasztas") {
            xmlFajl = fajlValaszto();
            String xmlFajlPath = xmlFajl.getAbsolutePath();
            xmlFajlTF.setText(xmlFajl.getAbsolutePath());
            try {
                arfolyamListaTolt(xmlVisszaFajl(xmlFajlPath));
            } catch (IOException ex) {
                Logger.getLogger(DevizaValto.class.getName()).log(Level.SEVERE, null, ex);
            }
            comboBoxTolt(arfolyamLista);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        valtandoOsszegTF = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        arfolyamokCB = new javax.swing.JComboBox<>();
        valtasBT = new javax.swing.JButton();
        eredmenyOsszegTF = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        xmlFajlTF = new javax.swing.JTextField();
        fajlValasztoBT = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Deviza váltás forintra");
        setAutoRequestFocus(false);
        setBackground(new java.awt.Color(204, 255, 204));

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));
        jPanel1.setPreferredSize(new java.awt.Dimension(359, 200));

        valtandoOsszegTF.setToolTipText("Átváltandó összeg");
        valtandoOsszegTF.setActionCommand("<Not Set>");
        valtandoOsszegTF.setName("valtandoOsszeg"); // NOI18N
        valtandoOsszegTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                valtandoOsszegTFKeyTyped(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(0, 51, 204));
        jLabel1.setText("Váltandó összeg :");

        valtasBT.setText("Átváltás");
        valtasBT.setActionCommand("ftraValt");

        eredmenyOsszegTF.setToolTipText("Forintra átváltott összeg");
        eredmenyOsszegTF.setName("valtottOsszeg"); // NOI18N

        jLabel3.setForeground(new java.awt.Color(0, 51, 204));
        jLabel3.setText("Átváltás eredménye HUF-ban :");

        jLabel2.setForeground(new java.awt.Color(0, 51, 204));
        jLabel2.setText("Valuta választása :");

        xmlFajlTF.setActionCommand("fajlValasztas");

        fajlValasztoBT.setText("jButton1");
        fajlValasztoBT.setActionCommand("xmlFajlValasztas");

        jLabel4.setForeground(new java.awt.Color(0, 51, 204));
        jLabel4.setText("Mentett xml fájl kiválasztása :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(valtandoOsszegTF, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(valtasBT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(eredmenyOsszegTF, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                            .addComponent(arfolyamokCB, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(45, 45, 45))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(xmlFajlTF)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(fajlValasztoBT, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {arfolyamokCB, eredmenyOsszegTF, valtandoOsszegTF, valtasBT});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fajlValasztoBT, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(xmlFajlTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(valtandoOsszegTF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(arfolyamokCB, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(eredmenyOsszegTF, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(valtasBT, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(17, 17, 17))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {arfolyamokCB, eredmenyOsszegTF, valtandoOsszegTF, xmlFajlTF});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void valtandoOsszegTFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_valtandoOsszegTFKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) || c == java.awt.event.KeyEvent.VK_BACK_SPACE || c == java.awt.event.KeyEvent.VK_DELETE) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_valtandoOsszegTFKeyTyped

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new DevizaValto().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(DevizaValto.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> arfolyamokCB;
    private javax.swing.JTextField eredmenyOsszegTF;
    private javax.swing.JButton fajlValasztoBT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField valtandoOsszegTF;
    private javax.swing.JButton valtasBT;
    private javax.swing.JTextField xmlFajlTF;
    // End of variables declaration//GEN-END:variables
}
