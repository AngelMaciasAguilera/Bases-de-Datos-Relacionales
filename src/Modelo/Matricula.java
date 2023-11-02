/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author tuwet
 */
public class Matricula{
    private int nNotaMedia;
    private String cCodCur;
    private String cCodAlu;

   
    public Matricula(String cCodCur, String cCodAlu, int nNotaMedia) {
        this.cCodCur = cCodCur;
        this.cCodAlu = cCodAlu;
        this.nNotaMedia = nNotaMedia;
    }
    
    
    public String getcCodCur() {
        return cCodCur;
    }

    public void setcCodCur(String cCodCur) {
        this.cCodCur = cCodCur;
    }

    public String getcCodAlu() {
        return cCodAlu;
    }

    public void setcCodAlu(String cCodAlu) {
        this.cCodAlu = cCodAlu;
    }

    public int getnNotaMedia() {
        return nNotaMedia;
    }

    public void setnNotaMedia(int nNotaMedia) {
        this.nNotaMedia = nNotaMedia;
    }
    
    
}
