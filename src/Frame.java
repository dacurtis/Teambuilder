
import au.com.bytecode.opencsv.CSVReader;
import java.awt.Desktop;
import java.awt.FileDialog;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author David Curtis
 */
public class Frame extends javax.swing.JFrame {
    String inputPath  = null;  
    String outputPath = "Teams.csv";
    /** Creates new form Frame */
    public Frame() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        inputFileLabel = new javax.swing.JLabel();
        inputFileButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        outputFileLabel = new javax.swing.JLabel();
        outputFileButton = new javax.swing.JButton();
        numTeamsSpinner = new javax.swing.JSpinner();
        jLabel5 = new javax.swing.JLabel();
        goButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Path to CSV of Competitors:");

        inputFileLabel.setForeground(new java.awt.Color(255, 0, 0));
        inputFileLabel.setText("No file selected!");

        inputFileButton.setText("Select File...");
        inputFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputFileButtonActionPerformed(evt);
            }
        });

        jLabel3.setText("Output File:");

        outputFileLabel.setText("Teams.csv");

        outputFileButton.setText("Save As...");
        outputFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                outputFileButtonActionPerformed(evt);
            }
        });

        jLabel5.setText("Number of Teams:");

        goButton.setText("Go");
        goButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(outputFileLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(inputFileLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(numTeamsSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(90, 90, 90)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(inputFileButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(outputFileButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(goButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputFileLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inputFileButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(outputFileLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(outputFileButton))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numTeamsSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(33, 33, 33)
                .addComponent(goButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void inputFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputFileButtonActionPerformed
        FileDialog fd = new FileDialog(this, "Select an input file",FileDialog.LOAD);
        fd.setVisible(true);
        if (fd.getFile() != null){
            inputPath = fd.getDirectory() + fd.getFile();
            inputFileLabel.setText(fd.getFile());
        }   
    }//GEN-LAST:event_inputFileButtonActionPerformed

    private void outputFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_outputFileButtonActionPerformed
        FileDialog fd = new FileDialog(this, "Select an ouput file",FileDialog.SAVE);
        fd.setVisible(true);
        if (fd.getFile() != null){
            outputPath = fd.getDirectory() + fd.getFile();
            outputFileLabel.setText(fd.getFile());
        }   
    }//GEN-LAST:event_outputFileButtonActionPerformed

    private void goButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goButtonActionPerformed
        if (inputPath ==null){
            JOptionPane.showMessageDialog(this, "No input file selected!", "Please select an input file", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int numTeams = (Integer) numTeamsSpinner.getModel().getValue();
        if (numTeams <= 1){
            JOptionPane.showMessageDialog(this, "There must be at least 2 teams", "Please select the number of teams", JOptionPane.WARNING_MESSAGE);
            return;
        }
                 try {
        	FileReader file = new FileReader(inputPath);  	
        	CSVReader reader = new CSVReader(file);	
        	List<String[]> fileContents = reader.readAll();
        	
			//Need to call parser method to parse information.
			Parser toParse = new Parser(fileContents);
			
			ArrayList<Person> peopleToSort = toParse.parseForPeople();
                        Sorter sorter = new Sorter(peopleToSort, numTeams);
                        ArrayList<Team> teams = sorter.makeTeams();
                        
                        Writer writer = new Writer(teams,outputPath);
                        writer.write();
                        Desktop.getDesktop().open(new File(outputPath));
			
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "IOException thrown while reading input file", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
    }//GEN-LAST:event_goButtonActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Frame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton goButton;
    private javax.swing.JButton inputFileButton;
    private javax.swing.JLabel inputFileLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JSpinner numTeamsSpinner;
    private javax.swing.JButton outputFileButton;
    private javax.swing.JLabel outputFileLabel;
    // End of variables declaration//GEN-END:variables
}
