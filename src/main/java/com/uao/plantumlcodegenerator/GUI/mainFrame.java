/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.uao.plantumlcodegenerator.GUI;
import com.uao.plantumlcodegenerator.codeGeneratorJava;
import com.uao.plantumlcodegenerator.codeGeneratorPython;
import java.awt.Color;
import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;
import java.awt.dnd.*;
import java.awt.datatransfer.*;
import java.util.List;
/**
 *
 * @author santiago
 */
public class mainFrame extends javax.swing.JFrame {
    private String languageSelected;
    private final Color selectedColor = new Color(139, 233, 255);
    private final Color defaultColor = new Color(238, 238, 238);
    private char dataChar;
    private String line;
    private StringBuilder plantUMLtext = new StringBuilder();
    private String reader;
    private byte fileOrText = 2; //0 si se subio un archivo, 1 si se escribio o se arrastro, o cualquier otro caracter si no es ninguna de las dos
    private String plantUMLStirng;
    /**
     * Creates new form mainFrame
     */
    public mainFrame() {
        initComponents();
        enableDragAndDropForTextArea(textAreaDropFIle);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        jPanel1 = new javax.swing.JPanel();
        Title = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        selectLanguagetext = new javax.swing.JLabel();
        Java = new javax.swing.JButton();
        Python = new javax.swing.JButton();
        selectArchive = new javax.swing.JLabel();
        selectArchiveButton = new javax.swing.JButton();
        grabNDropText = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textAreaDropFIle = new javax.swing.JTextArea();
        loadText = new javax.swing.JButton();
        generateCode = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(400, 430));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setPreferredSize(new java.awt.Dimension(400, 400));
        jPanel1.setLayout(new java.awt.BorderLayout());

        Title.setFont(new java.awt.Font("Noto Sans", 0, 36)); // NOI18N
        Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Title.setText("Generador de código");
        jPanel1.add(Title, java.awt.BorderLayout.CENTER);

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 10));

        selectLanguagetext.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        selectLanguagetext.setText("Seleccione el lenguaje");
        selectLanguagetext.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        selectLanguagetext.setName(""); // NOI18N
        jPanel2.add(selectLanguagetext);

        Java.setText("Java");
        Java.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JavaMouseClicked(evt);
            }
        });
        jPanel2.add(Java);

        Python.setText("Python");
        Python.setToolTipText("");
        Python.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PythonMouseClicked(evt);
            }
        });
        jPanel2.add(Python);

        selectArchive.setText("Seleccione el archivo ");
        selectArchive.setAlignmentY(1.0F);
        selectArchive.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel2.add(selectArchive);

        selectArchiveButton.setText("Click para seleccionar archivo");
        selectArchiveButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectArchiveButtonMouseClicked(evt);
            }
        });
        jPanel2.add(selectArchiveButton);

        grabNDropText.setText("Arrastre el archivo abajo");
        jPanel2.add(grabNDropText);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setName(""); // NOI18N
        jScrollPane1.setPreferredSize(new java.awt.Dimension(300, 100));

        textAreaDropFIle.setColumns(20);
        textAreaDropFIle.setRows(5);
        textAreaDropFIle.setDragEnabled(true);
        textAreaDropFIle.setPreferredSize(new java.awt.Dimension(390, 84));
        jScrollPane1.setViewportView(textAreaDropFIle);

        jPanel2.add(jScrollPane1);

        loadText.setText("Cargar texto");
        loadText.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loadTextMouseClicked(evt);
            }
        });
        jPanel2.add(loadText);

        generateCode.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        generateCode.setText("Generar codigo");
        generateCode.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        generateCode.setPreferredSize(new java.awt.Dimension(380, 30));
        generateCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateCodeActionPerformed(evt);
            }
        });
        jPanel2.add(generateCode);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void generateCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateCodeActionPerformed
        plantUMLStirng = plantUMLtext.toString();
        try {
            switch (languageSelected) {
            case "Java":
                if (fileOrText == 0 || fileOrText ==1 ){
                    codeGeneratorJava codeGeneratorJava1 = new codeGeneratorJava(plantUMLStirng);
                    codeGeneratorJava1.generateCode();
                    JOptionPane.showMessageDialog(null, "Archivo creado en: " + codeGeneratorJava1.returnPathFileCreated());
                }else{
                    JOptionPane.showMessageDialog(null, "No se a detectado archivo");}
                break;
            case "Python":
                if (fileOrText==0 || fileOrText==1){
                    codeGeneratorPython codeGeneratorPython1 = new codeGeneratorPython(plantUMLStirng);
                    codeGeneratorPython1.generateCode();
                    JOptionPane.showMessageDialog(null,"Archivo creado en: " + codeGeneratorPython1.returnPathFileCreated());
                }else{
                    JOptionPane.showMessageDialog(null, "No se a detectado archivo");}
                break;
            }
        } catch (Exception e) {JOptionPane.showMessageDialog(null,"No se a elegido un lenguaje");}
        
        resetGUI();
    }//GEN-LAST:event_generateCodeActionPerformed

    
    /**
     * Create a file atribute to storage the absolute path of the selected file.
     * Then, create the atribute plantUMLText wich is going to recieve the file method
     * to read the file.
     * @param evt 
     */
    
    private void selectArchiveButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectArchiveButtonMouseClicked
        if(evt.getSource()==selectArchiveButton){
            fileOrText = 0;
            
            jFileChooser1.showOpenDialog(null);
            File file = new File(jFileChooser1.getSelectedFile().getAbsolutePath());
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                
                while ((line = reader.readLine()) != null) {
                plantUMLtext.append(line).append(System.lineSeparator()); // Preserva saltos de línea
                }
                textAreaDropFIle.setEnabled(false);
                textAreaDropFIle.updateUI();
                selectArchiveButton.setBackground(selectedColor);
                loadText.setEnabled(false); 
                loadText.repaint();
                System.out.print(plantUMLtext);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(mainFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(mainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
                
        }        
    }//GEN-LAST:event_selectArchiveButtonMouseClicked

    private void JavaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JavaMouseClicked
        languageSelected = "Java";
        Java.setBackground(selectedColor);  
        Python.setBackground(defaultColor);
    }//GEN-LAST:event_JavaMouseClicked

    private void PythonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PythonMouseClicked
        languageSelected = "Python";
        Java.setBackground(defaultColor);  
        Python.setBackground(selectedColor);
    }//GEN-LAST:event_PythonMouseClicked

    private void loadTextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loadTextMouseClicked
        File file = new File(textAreaDropFIle.getText().trim());//Obtiene el texto del AreaDropFile
        if(file.exists() && file.isFile() ){
            fileOrText = 1;
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            selectArchiveButton.setEnabled(false);
            
            
            // Lee cada línea y la agrega a content
            while ((line = reader.readLine()) != null) {
            plantUMLtext.append(line).append(System.lineSeparator()); // Preserva saltos de línea
            }
            /**
             * captura de excepciones 
             */
            } catch (FileNotFoundException ex) {
                Logger.getLogger(mainFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(mainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }    
        }else {JOptionPane.showMessageDialog(null,"Ruta no valida");}
    }//GEN-LAST:event_loadTextMouseClicked

    
private void enableDragAndDropForTextArea(JTextArea textArea) {
    new DropTarget(textArea, new DropTargetListener() {
        @Override
        public void dragEnter(DropTargetDragEvent dtde) {

        }

        @Override
        public void dragOver(DropTargetDragEvent dtde) {

        }

        @Override
        public void dropActionChanged(DropTargetDragEvent dtde) {
            
        }

        @Override
        public void dragExit(DropTargetEvent dte) {
           
        }

        @Override
        public void drop(DropTargetDropEvent dtde) {
            try {
                dtde.acceptDrop(DnDConstants.ACTION_COPY);
                Transferable transferable = dtde.getTransferable();
                if (transferable.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                    List<File> droppedFiles = (List<File>) transferable.getTransferData(DataFlavor.javaFileListFlavor);
                    for (File file : droppedFiles) {
                        // Muestra solo la ruta del archivo en el JTextArea
                        textArea.append(file.getAbsolutePath() + "\n");
                    }
                    dtde.dropComplete(true);
                } else {
                    dtde.dropComplete(false);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                dtde.dropComplete(false);
            }
        }
    });
    
    
}
private void resetGUI(){
    selectArchiveButton.setEnabled(true);
    loadText.setEnabled(true);
    Java.setBackground(defaultColor);
    Python.setBackground(defaultColor);
    selectArchiveButton.setBackground(defaultColor);
    textAreaDropFIle.setEnabled(true);
    textAreaDropFIle.setText("");
    plantUMLStirng = "";
    plantUMLtext.setLength(0);
    
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Java;
    private javax.swing.JButton Python;
    private javax.swing.JLabel Title;
    private javax.swing.JButton generateCode;
    private javax.swing.JLabel grabNDropText;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton loadText;
    private javax.swing.JLabel selectArchive;
    private javax.swing.JButton selectArchiveButton;
    private javax.swing.JLabel selectLanguagetext;
    private javax.swing.JTextArea textAreaDropFIle;
    // End of variables declaration//GEN-END:variables
}
