/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Controlador.ConexionBBDD;
import Modelo.Alumno;
import Modelo.Curso;
import Modelo.Examen;
import Modelo.Matricula;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.w3c.dom.Attr;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

/**
 *
 * @author tuwet
 */
public class gestionAlumnos extends javax.swing.JFrame {

    ConexionBBDD con = new ConexionBBDD();

    String ins;
    DefaultTableModel dtmAlumnos;
    DefaultTableModel dtmCursos;
    DefaultTableModel dtmMatriculas;
    DefaultTableModel dtmExamenes;

    public gestionAlumnos() {
        initComponents();
        //nos creamos los modelos de las tablas de nuestra interfaz
        dtmAlumnos = (DefaultTableModel) JT_Alumnos.getModel();
        dtmCursos = (DefaultTableModel) JT_Cursos.getModel();
        dtmMatriculas = (DefaultTableModel) JT_Matriculas.getModel();
        dtmExamenes = (DefaultTableModel) JT_Examenes.getModel();
        
        //Creamos todos los listeners necesarios para las acciones que vaya realizando el usuario
        
        //Listener para la tabla Alumnos
        ListSelectionModel LSMAlumno = JT_Alumnos.getSelectionModel();
        LSMAlumno.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    if (dtmMatriculas != null) {
                        for (int i = JT_Matriculas.getRowCount() - 1; i >= 0; i--) {
                            dtmMatriculas.removeRow(i);
                        }
                    }

                    String codAlu = JT_Alumnos.getValueAt(JT_Alumnos.getSelectedRow(), 0).toString();
                    ResultSet rs = con.readMatriculas(codAlu);
                    try {
                        while (rs.next() != false) {
                            dtmMatriculas.addRow(new Object[]{rs.getString("CCODALU"), rs.getString("CNOMALU"),
                                rs.getString("CCODCURSO"), rs.getString("CNOMCURSO"), rs.getInt("NNOTAMEDIA")});
                        }
                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }

        });
        
        //Listener para la tabla Matricula
        ListSelectionModel LSMMatricula = JT_Matriculas.getSelectionModel();
        LSMMatricula.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    if (dtmExamenes != null) {
                        for (int i = JT_Examenes.getRowCount() - 1; i >= 0; i--) {
                            dtmExamenes.removeRow(i);
                        }
                    }

                    String codAlu = JT_Matriculas.getValueAt(JT_Matriculas.getSelectedRow(), 0).toString();
                    String codCur = JT_Matriculas.getValueAt(JT_Matriculas.getSelectedRow(), 2).toString();

                    ArrayList<Examen> examenes = con.readExamenes(codAlu, codCur);
                    for (Examen ex : examenes) {
                        dtmExamenes.addRow(new Object[]{ex.getnNumExam(), ex.getdFecExam(), ex.getnNotaExam()});
                    }

                    txtFechaExamen.setText("");
                    txtNota.setText("");

                }
            }

        });
        
        //Listener para la tabla Examenes
        ListSelectionModel LSMExamenes = JT_Examenes.getSelectionModel();
        LSMExamenes.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    String fecha = JT_Examenes.getValueAt(JT_Examenes.getSelectedRow(), 1).toString();
                    String nota = JT_Examenes.getValueAt(JT_Examenes.getSelectedRow(), 2).toString();
                    txtFechaExamen.setText(fecha);
                    txtNota.setText(nota);

                }
            }
        });

        if (con.conectar() == null) {
            JOptionPane.showMessageDialog(null, "no se pudo conectar a la base de datos");

        } else {
            ArrayList<Alumno> alumnos = con.readAlumnos();
            for (Alumno a : alumnos) {
                dtmAlumnos.addRow(new Object[]{a.getcCodAlu(), a.getcNomAlu()});
            }

            ArrayList<Curso> cursos = con.readCursos();
            for (Curso c : cursos) {
                dtmCursos.addRow(new Object[]{c.getcCodCurso(), c.getcNomCurso(), c.getnNumExam()});
            }

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

        jScrollPane1 = new javax.swing.JScrollPane();
        JT_Alumnos = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        JT_Cursos = new javax.swing.JTable();
        btMatricularAlumno = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        JT_Matriculas = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        JT_Examenes = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtFechaExamen = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNota = new javax.swing.JTextField();
        btActualizar = new javax.swing.JButton();
        btJSON = new javax.swing.JButton();
        btXML = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestion de los Alumnos en la Academia de Lenguas");

        JT_Alumnos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo Alumno", "Nombre"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        JT_Alumnos.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(JT_Alumnos);

        JT_Cursos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo Curso", "Nombre Curso", "NºExamenes"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        JT_Cursos.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(JT_Cursos);

        btMatricularAlumno.setText("Matricular Alumno en Curso");
        btMatricularAlumno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btMatricularAlumnoActionPerformed(evt);
            }
        });

        JT_Matriculas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo Alumno", "Nombre Alumno", "Codigo Curso", "Nombre Curso", "Nota Media"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        JT_Matriculas.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(JT_Matriculas);

        JT_Examenes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Numero Examen", "Fecha Examen", "Nota"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        JT_Examenes.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(JT_Examenes);

        jLabel1.setText("Fecha Examen:");

        jLabel2.setText("Nota:");

        btActualizar.setText("Actualizar");
        btActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btActualizarActionPerformed(evt);
            }
        });

        btJSON.setText("Boletin JSON");
        btJSON.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btJSONActionPerformed(evt);
            }
        });

        btXML.setText("Listado Matrícula XML");
        btXML.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btXMLActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 898, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(63, 63, 63)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(73, 73, 73)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(txtNota, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(txtFechaExamen, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(btJSON, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(btXML, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(343, 343, 343)
                        .addComponent(btMatricularAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addComponent(btMatricularAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtFechaExamen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btActualizar)
                        .addGap(48, 48, 48)
                        .addComponent(btJSON)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btXML))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btMatricularAlumnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btMatricularAlumnoActionPerformed
        String codAlu = "";
        String nombreAlu = "";
        String codCur = "";
        String nombreCurso = "";
        int numError = 0;

        try {
            codAlu = JT_Alumnos.getValueAt(JT_Alumnos.getSelectedRow(), 0).toString();
            codCur = JT_Cursos.getValueAt(JT_Cursos.getSelectedRow(), 0).toString();
            if ((numError = con.sp_AltaMatricula(codAlu, codCur)) != 0) {
                JOptionPane.showMessageDialog(null, "No se pudo matricular al alumno ya que ya esta matriculado en ese curso");
            } else {
                this.actualizarTablaMatriculas(codAlu);
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(null, "No se seleccionaron los datos correctos para matricular al alumno");
        }


    }//GEN-LAST:event_btMatricularAlumnoActionPerformed
    
    
    private void btActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btActualizarActionPerformed
        if (JT_Matriculas.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar la matricula y su respectivo examen para poder actualizar la nota");
        }else{
            if (this.txtFechaExamen.getText().isEmpty() == true || this.txtNota.getText().isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Para actualizar la nota los campos fecha y nota deben estar rellenos");
        } else {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate fecha = LocalDate.parse(this.txtFechaExamen.getText(), formatter);
                Double notaExamen = Double.parseDouble(this.txtNota.getText());
                int numeroExamen = Integer.parseInt(JT_Examenes.getValueAt(JT_Examenes.getSelectedRow(), 0).toString());
                String codCurso = JT_Matriculas.getValueAt(JT_Matriculas.getSelectedRow(), 2).toString();
                String codAlu = JT_Matriculas.getValueAt(JT_Matriculas.getSelectedRow(), 0).toString();
                if (notaExamen < 0.0 || notaExamen > 10.0) {
                    JOptionPane.showMessageDialog(null, "La nota debe estar entre 0 y 10");
                } else {
                    con.updateNotaExamen(fecha, notaExamen, numeroExamen, codCurso, codAlu);
                    this.actualizarTablaExamenes(codAlu, codCurso);
                    this.actualizarTablaMatriculas(codAlu);
                    this.txtFechaExamen.setText("");
                    this.txtNota.setText("");
                    

                }

            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(null, "La fecha que ha introducido no es valida, el formato de fecha que se debe emplear es: \n"
                                                                 + "yyyy-mm-dd(Ejemplo: 2023-11-02)");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "La nota introducida no es correcta");
            }

        }
        }
        
    }//GEN-LAST:event_btActualizarActionPerformed

    private void btJSONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btJSONActionPerformed
        if (JT_Matriculas.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna matricula sobre la que realizar el boletin de examen");
        } else {

            JSONArray coleccionExamenes = new JSONArray();
            String codAlu = JT_Matriculas.getValueAt(JT_Matriculas.getSelectedRow(), 0).toString();
            String codCurso = JT_Matriculas.getValueAt(JT_Matriculas.getSelectedRow(), 2).toString();
            String archivoJSON = "boletinJSON.json";
            ArrayList<Examen> examenes = con.readExamenes(codAlu, codCurso);
            for (Examen ex : examenes) {
                coleccionExamenes.put(ex);
            }

            try {
                FileWriter fw = new FileWriter(archivoJSON);
                fw.write(coleccionExamenes.toString(4));
                fw.close();
            } catch (ArrayIndexOutOfBoundsException ex) {
                JOptionPane.showMessageDialog(null, "No hay ningun examen sobre el que realizar el boletin JSON");
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }//GEN-LAST:event_btJSONActionPerformed

    private void btXMLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btXMLActionPerformed
        if (JT_Alumnos.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningun alumno para exportar su listado de matriculas a XML");
        } else {
            try {
                // Crear un documento XML
                DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                Document document = documentBuilder.newDocument();
                TransformerFactory transformerFact = TransformerFactory.newInstance();
                Transformer transformer = transformerFact.newTransformer();
                String codAlu = JT_Alumnos.getValueAt(JT_Alumnos.getSelectedRow(), 0).toString();
                ResultSet rs = con.readMatriculas(codAlu);
                Element raiz = document.createElement("Matriculas");
                document.appendChild(raiz);
                while (rs.next() != false) {
                    Element matricula = document.createElement("Matricula");
                    raiz.appendChild(matricula);
                    
                    Element codAlumno = document.createElement("codigoAlumno");
                    Text txtCodAlumno = document.createTextNode(rs.getString("CCODALU"));
                    codAlumno.appendChild(txtCodAlumno);
                    raiz.appendChild(codAlumno);

                    Element nomAlumno = document.createElement("nombreAlumno");
                    Text txtNomAlumno = document.createTextNode(rs.getString("CNOMALU"));
                    nomAlumno.appendChild(txtNomAlumno);
                    raiz.appendChild(nomAlumno);

                    Element codCurso = document.createElement("codigoCurso");
                    Text txtCodCurso = document.createTextNode(rs.getString("CCODCURSO"));
                    codCurso.appendChild(txtCodCurso);
                    raiz.appendChild(codCurso);

                    Element nomCurso = document.createElement("nombreCurso");
                    Text txtNomCurso = document.createTextNode(rs.getString("CNOMCURSO"));
                    nomCurso.appendChild(txtNomCurso);
                    raiz.appendChild(nomCurso);

                    Element notaMedia = document.createElement("notaMedia");
                    Text txtNotaMedia = document.createTextNode(rs.getString("NNOTAMEDIA"));
                    notaMedia.appendChild(txtNotaMedia);
                    raiz.appendChild(notaMedia);
                }

                DOMSource dom = new DOMSource(document);
                File xml = new File("matriculas.xml");
                StreamResult result = new StreamResult(xml);
                transformer.transform(dom, result);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }//GEN-LAST:event_btXMLActionPerformed

    //----------------------Metodos Auxiliares------------------------------------
    private void actualizarTablaMatriculas(String codAlu) {
        for (int i = JT_Matriculas.getRowCount() - 1; i >= 0; i--) {
            dtmMatriculas.removeRow(i);
        }
        ResultSet rs = con.readMatriculas(codAlu);
        try {
            while (rs.next() != false) {
                dtmMatriculas.addRow(new Object[]{rs.getString("CCODALU"), rs.getString("CNOMALU"),
                    rs.getString("CCODCURSO"), rs.getString("CNOMCURSO"), rs.getInt("NNOTAMEDIA")});
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void actualizarTablaExamenes(String codAlu, String codCur) {
        for (int i = JT_Examenes.getRowCount() - 1; i >= 0; i--) {
            dtmExamenes.removeRow(i);
        }

        ArrayList<Examen> examenes = con.readExamenes(codAlu, codCur);
        for (Examen ex : examenes) {
            dtmExamenes.addRow(new Object[]{ex.getnNumExam(), ex.getdFecExam(), ex.getnNotaExam()});
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(gestionAlumnos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(gestionAlumnos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(gestionAlumnos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(gestionAlumnos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new gestionAlumnos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable JT_Alumnos;
    private javax.swing.JTable JT_Cursos;
    private javax.swing.JTable JT_Examenes;
    private javax.swing.JTable JT_Matriculas;
    private javax.swing.JButton btActualizar;
    private javax.swing.JButton btJSON;
    private javax.swing.JButton btMatricularAlumno;
    private javax.swing.JButton btXML;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField txtFechaExamen;
    private javax.swing.JTextField txtNota;
    // End of variables declaration//GEN-END:variables
}
