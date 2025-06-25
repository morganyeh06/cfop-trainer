/*
 * Morgan Yeh 
 * 2024/07/14
 * GUI for OLL alg list
 */
package cube.trainer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author morga
 */
public class OLLAlgList extends javax.swing.JFrame {
    private AlgorithmSelect a;
    
    //Arraylist of PLL Algs
    private ArrayList<Algorithm> algs;

    /**
     * Creates new form OLLAlgList
     * @param a - Alg selection page
     * @param algs - arrayLIst of algs
     */
    public OLLAlgList(AlgorithmSelect a, ArrayList<Algorithm> algs) {
        initComponents();
        this.a = a;
        this.algs = algs;
        
        DefaultTableCellRenderer alignment = new DefaultTableCellRenderer();
        alignment.setHorizontalAlignment(JLabel.CENTER);
        
        //display alg and other information in table
        for (int i = 0; i < algs.size(); i++) {
            //change alignment of all cells to center
            for (int j = 1; j < 5; j++) {
                tableOLL.getColumnModel().getColumn(j).setCellRenderer(alignment);
            }
            
            tableOLL.setValueAt(algs.get(i).isLearned(), i, 0);
            tableOLL.setValueAt(algs.get(i).getName(), i, 1);
            tableOLL.setValueAt(algs.get(i).getImg(), i, 2);
            tableOLL.setValueAt(algs.get(i).getAlg(), i, 3);
            tableOLL.setValueAt(algs.get(i).getGroup(), i, 4);
        }
        
        //display images
        tableOLL.getColumnModel().getColumn(2).setCellRenderer(new OLLAlgList.ImageRender());
        
        //change scroll speed
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(20);
        
        //change header font size
        Font f = new Font("DengXian", Font.BOLD, 24);
        tableOLL.getTableHeader().setFont(f);
        
        //change header alignment
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tableOLL.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(0);
        
        //change background color
        this.getContentPane().setBackground(new Color(102,204,255));
        
        //make frame not resizable
        setResizable(false);
    }
    
    /**
     * Private class to render images
     */
    private class ImageRender extends DefaultTableCellRenderer {
        
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            String photoName = value.toString();
            ImageIcon img = new ImageIcon(new ImageIcon("src/Images/OLL Images/" + photoName).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
            return new JLabel(img);
        }
        
    }
    
    /**
     * Method to write alg information to the data file
     */
    public void writeFile() {
        String set;
        String group;
        String name;
        String[] setups;
        String algorithm;
        String imgFile;
        boolean learned;
        
        Algorithm a;
        
        String toWrite = "";
        
        try { //try-catch to write alg information to file
            FileWriter fw = new FileWriter("src/Data Files/OLL.txt", false);
            BufferedWriter bw = new BufferedWriter(fw);
            
            //clear contents of file
            bw.write("");
            bw.flush();

            for (int i = 0; i < algs.size(); i++) {
                a  = algs.get(i);
                
                set = a.getSet();
                group = a.getGroup();
                name = a.getName();
                setups = a.getSetupArray();
                algorithm = a.getAlg();
                imgFile = a.getImg();
                learned = a.isLearned();
                
                toWrite += set + "\n" + group + "\n" + name + "\n" + setups[0] + 
                        "\n" + setups[1] + "\n" + setups[2] + "\n" + setups[3] +
                        "\n" + algorithm + "\n" + imgFile + "\n" + learned;
                
                //add new line only if it is not the last element
                if (i != algs.size() - 1) {
                    toWrite += "\n";
                }
                
                fw.write(toWrite);
                
                toWrite = "";
            }

            bw.flush();
            bw.close();
        } catch (IOException e) { //carch IO Exception
            System.out.println("Error");
        }         
    }
    
    /**
     * change whether each algorithm has been learned
     */
    public void changeLearnedStatus() {
        boolean status;
        
        //go through each alg in ArrayList
        for (int i = 0; i < algs.size(); i++) {
            //get the learned status from the JTable and change it
            status = (boolean)tableOLL.getValueAt(i, 0);
            algs.get(i).setLearned(status);
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

        lblTitle = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableOLL = new javax.swing.JTable();
        btnBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTitle.setFont(new java.awt.Font("DengXian", 1, 48)); // NOI18N
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("OLL Algorithms");

        tableOLL.setFont(new java.awt.Font("DengXian", 0, 18)); // NOI18N
        tableOLL.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Learned", "Name", "Image", "Algorithm", "Group"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableOLL.setGridColor(new java.awt.Color(0, 0, 0));
        tableOLL.setRowHeight(100);
        tableOLL.setRowSelectionAllowed(false);
        tableOLL.setShowGrid(true);
        tableOLL.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tableOLL);
        if (tableOLL.getColumnModel().getColumnCount() > 0) {
            tableOLL.getColumnModel().getColumn(0).setResizable(false);
            tableOLL.getColumnModel().getColumn(0).setPreferredWidth(0);
            tableOLL.getColumnModel().getColumn(1).setResizable(false);
            tableOLL.getColumnModel().getColumn(1).setPreferredWidth(0);
            tableOLL.getColumnModel().getColumn(2).setResizable(false);
            tableOLL.getColumnModel().getColumn(2).setPreferredWidth(0);
            tableOLL.getColumnModel().getColumn(3).setResizable(false);
            tableOLL.getColumnModel().getColumn(3).setPreferredWidth(300);
            tableOLL.getColumnModel().getColumn(4).setResizable(false);
        }

        btnBack.setBackground(new java.awt.Color(153, 153, 153));
        btnBack.setFont(new java.awt.Font("DengXian", 1, 36)); // NOI18N
        btnBack.setText("Back");
        btnBack.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(90, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(279, 279, 279)
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(90, 90, 90))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBack)
                    .addComponent(lblTitle))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(60, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        //store algorithm information in data file
        changeLearnedStatus();
        writeFile();        

        //go back to alg selection screen
        a.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnBackActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JTable tableOLL;
    // End of variables declaration//GEN-END:variables
}
