/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author tuwet
 */
public class Examen{
    private int nNumExam;
    private Date dFecExam;
    private int nNotaExam;
    private String cCodCur;
    private String cCodAlu;

    public Examen(String cCodCur, String cCodAlu, int nNumExam,Date dFecExam, int nNotaExam) {
        this.cCodCur = cCodCur;
        this.cCodAlu = cCodAlu;
        this.dFecExam = dFecExam;
        this.nNotaExam = nNotaExam;
        this.nNumExam = nNumExam;
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
    
    
    public int getnNumExam() {
        return nNumExam;
    }

    public void setnNumExam(int nNumExam) {
        this.nNumExam = nNumExam;
    }

    public Date getdFecExam() {
        return dFecExam;
    }

    public void setdFecExam(Date dFecExam) {
        this.dFecExam = dFecExam;
    }

    public int getnNotaExam() {
        return nNotaExam;
    }

    public void setnNotaExam(int nNotaExam) {
        this.nNotaExam = nNotaExam;
    }

    @Override
    public String toString() {
        return 
              "Examen{" + "nNumExam" + nNumExam + ", dFecExam=" + dFecExam + ", nNotaExam=" + nNotaExam + ", cCodCur=" + cCodCur + ", cCodAlu=" + cCodAlu + '}';
    }
    
   
    
}
