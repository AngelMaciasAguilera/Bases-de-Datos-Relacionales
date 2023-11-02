/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author tuwet
 */
public class Curso {
    private String cCodCurso;
    private String cNomCurso;
    private int nNumExam;

    public Curso() {
        this.cCodCurso = " ";
        this.cNomCurso = " ";
        this.nNumExam = 0;
    }
    
    public Curso(String cCodCurso, String cNomCurso, int nNumExam) {
        this.cCodCurso = cCodCurso;
        this.cNomCurso = cNomCurso;
        this.nNumExam = nNumExam;
    }

    public String getcCodCurso() {
        return cCodCurso;
    }

    public void setcCodCurso(String cCodCurso) {
        this.cCodCurso = cCodCurso;
    }

    public String getcNomCurso() {
        return cNomCurso;
    }

    public void setcNomCurso(String cNomCurso) {
        this.cNomCurso = cNomCurso;
    }

    public int getnNumExam() {
        return nNumExam;
    }

    public void setnNumExam(int nNumExam) {
        this.nNumExam = nNumExam;
    }
    
}
