/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Alumno;
import Modelo.Curso;
import Modelo.Examen;
import Modelo.Matricula;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class ConexionBBDD {

    //Objeto conexion a la base de datos
    private static Connection con;
    private CallableStatement cs;
    private Statement st;
    private int nfa;
    private PreparedStatement ps;
    private String ins;
    private ResultSet rs;

    public Connection conectar() {
        try {
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "AD_TEMA02", "AD_TEMA02");

        } catch (SQLException ex) {
            con = null;
        } finally {
            return con;
        }
    }

    public ArrayList<Alumno> readAlumnos() {
        ArrayList<Alumno> alumnos = new ArrayList<Alumno>();
        ins = "SELECT * FROM ALUMNOS";
        try {
            st = con.createStatement();
            rs = st.executeQuery(ins);
            while (rs.next() != false) {
                alumnos.add(new Alumno(rs.getString("cCodAlu"), rs.getString("cNomAlu")));
            }

        } catch (SQLException ex) {
            alumnos = null;
        }

        return alumnos;
    }

    public ArrayList<Curso> readCursos() {
        ArrayList<Curso> cursos = new ArrayList<Curso>();
        ins = "SELECT * FROM CURSOS";
        try {
            st = con.createStatement();
            rs = st.executeQuery(ins);
            while (rs.next() != false) {
                cursos.add(new Curso(rs.getString("cCodCurso"), rs.getString("cNomCurso"),
                        rs.getInt("nNumExa")));
            }

        } catch (SQLException ex) {
            cursos = null;
        }

        return cursos;
    }

    public ResultSet readMatriculas(String cCodAlu) {
        ins = "SELECT M.CCODALU,A.CNOMALU,M.CCODCURSO,C.CNOMCURSO,M.NNOTAMEDIA FROM MATRICULAS M\n"
                + "INNER JOIN CURSOS C ON M.CCODCURSO = C.CCODCURSO\n"
                + "INNER JOIN ALUMNOS A ON M.CCODALU = A.CCODALU\n"
                + "WHERE A.CCODALU = " + "'" + cCodAlu + "'"
                + " ORDER BY CCODCURSO";
        try {
            st = con.createStatement();
            rs = st.executeQuery(ins);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            rs = null;
        }

        return rs;
    }

    public ArrayList<Examen> readExamenes(String cCodAlu, String cCodCurso) {
        ArrayList<Examen> examenes = new ArrayList<Examen>();
        ins = "SELECT * FROM EXAMENES WHERE CCODALU = " + cCodAlu + " AND " + "CCODCURSO = '" + cCodCurso + "'";
        try {
            st = con.createStatement();
            rs = st.executeQuery(ins);
            while (rs.next() != false) {
                examenes.add(new Examen(rs.getString("CCODALU"), rs.getString("CCODCURSO"),
                        rs.getInt("NNUMEXAM"), rs.getDate("DFECEXAM"), rs.getInt("NNOTAEXAM")));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            examenes = null;
        }

        return examenes;
    }


    public int sp_AltaMatricula(String xcCodAlu, String xcCodCurso) {
        int xError = 0;
        ins = "{call sp_AltaMatricula(?,?,?)}";
        try {
            cs = con.prepareCall(ins);
            cs.setString(1, xcCodAlu);
            cs.setString(2, xcCodCurso);
            cs.registerOutParameter(3, Types.INTEGER);

            if (cs.execute() == false) {
                xError = -1;
            }
            xError = cs.getInt(3);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            xError = -1;
        }

        return xError;
    }

    public int updateNotaExamen(LocalDate fecha, Double nota, int numeroExamen, String codCurso, String codAlu) {
        
        String ins = "UPDATE EXAMENES SET NNOTAEXAM = ?, DFECEXAM = "
                     + "TO_DATE(?, 'YYYY-MM-DD') WHERE NNUMEXAM = ? AND CCODCURSO = ? AND CCODALU = ?";
        try {
            PreparedStatement ps = con.prepareStatement(ins);
            ps.setDouble(1, nota);
            ps.setString(2, fecha.toString());
            ps.setInt(3, numeroExamen);
            ps.setString(4, codCurso);
            ps.setString(5, codAlu);

            int nfa = ps.executeUpdate();
            return nfa;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return 0; // Manejo del error
        }
    
    }

    
    
    /*
        
        */
    
}
