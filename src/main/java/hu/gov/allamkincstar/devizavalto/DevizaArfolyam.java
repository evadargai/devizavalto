/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hu.gov.allamkincstar.devizavalto;

/**
 *
 * @author majercsiknede
 */
public class DevizaArfolyam {
String deviza;
float  arfolyam;

/**
 * 
 * @param deviza
 * @param arfolyam 
 */
    public DevizaArfolyam(String deviza, float arfolyam) {
        this.deviza = deviza;
        this.arfolyam = arfolyam;
    }

    public String getDeviza() {
        return deviza;
    }

    public float getArfolyam() {
        return arfolyam;
    }

}

