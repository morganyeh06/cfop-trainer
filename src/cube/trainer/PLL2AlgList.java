/*
 * Morgan Yeh 
 * 2024/07/14
 * GUI for 2-Look PLL alg list
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
public class PLL2AlgList extends javax.swing.JFrame {

    private AlgorithmSelect a;

    //Arraylist of PLL Algs
    private ArrayList<Algorithm> algs;

    /**
     * Creates new form PLL2AlgList
     *
     * @param a - Alg selection page
     * @param algs - arrayLIst of algs
     */
    public PLL2AlgList(AlgorithmSelect a, ArrayList<Algorithm> algs) {
        initComponents();
        this.a = a;
        this.algs = algs;
        
        DefaultTableCellRenderer alignment = new DefaultTableCellRenderer();
        alignment.setHorizontalAlignment(JLabel.CENTER);

        //display alg and other information in table
        for (int i = 0; i < algs.size(); i++) {
            //change alignment of all cells to center
            for (int j = 1; j < 5; j++) {
                tablePLL2.getColumnModel().getColumn(j).setCellRenderer(alignment);
            }
            
            tablePLL2.setValueAt(algs.get(i).isLearned(), i, 0);
            tablePLL2.setValueAt(algs.get(i).getName(), i, 1);
            tablePLL2.setValueAt(algs.get(i).getImg(), i, 2);
            tablePLL2.setValueAt(algs.get(i).getAlg(), i, 3);
            tablePLL2.setValueAt(algs.get(i).getGroup(), i, 4);
        }

        //display images
        tablePLL2.getColumnModel().getColumn(2).setCellRenderer(new PLL2AlgList.ImageRender());

        //change scroll speed
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(20);

        //change header font size
        Font f = new Font("DengXian", Font.BOLD, 24);
        tablePLL2.getTableHeader().setFont(f);
        
        //change header alignment
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tablePLL2.getTableHeader().getDefaultRenderer();
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
            ImageIcon img = new ImageIcon(new ImageIcon("src/Images/2-Look PLL Images/" + photoName).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
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
            FileWriter fw = new FileWriter("src/Data Files/2LookPLL.txt", false);
            BufferedWriter bw = new BufferedWriter(fw);
            
            //clear contents of file
            bw.write("");
            bw.flush();

            for (int i = 0; i < algs.size(); i++) {
                a  = algs.get(i);
                
                set = a.getSet();
                group = a.getGroup();
                name = a.getName();
                algorithm = a.getAlg();
                imgFile = a.getImg();
                learned = a.isLearned();
                
                toWrite += set + "\n" + group + "\n" + name + "\n"
                        + algorithm + "\n" + imgFile + "\n" + learned;
                
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
            status = (boolean)tablePLL2.getValueAt(i, 0);
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
        tablePLL2 = new javax.swing.JTable();
        btnBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTitle.setFont(new java.awt.Font("DengXian", 1, 48)); // NOI18N
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("2-Look PLL Algorithms");

        tablePLL2.setFont(new java.awt.Font("DengXian", 0, 18)); // NOI18N
        tablePLL2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
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
        tablePLL2.setGridColor(new java.awt.Color(0, 0, 0));
        tablePLL2.setRowHeight(100);
        tablePLL2.setRowSelectionAllowed(false);
        tablePLL2.setShowGrid(true);
        tablePLL2.getTableHeader().setResizingAllowed(false);
        tablePLL2.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tablePLL2);
        if (tablePLL2.getColumnModel().getColumnCount() > 0) {
            tablePLL2.getColumnModel().getColumn(0).setResizable(false);
            tablePLL2.getColumnModel().getColumn(0).setPreferredWidth(0);
            tablePLL2.getColumnModel().getColumn(1).setResizable(false);
            tablePLL2.getColumnModel().getColumn(1).setPreferredWidth(0);
            tablePLL2.getColumnModel().getColumn(2).setResizable(false);
            tablePLL2.getColumnModel().getColumn(2).setPreferredWidth(0);
            tablePLL2.getColumnModel().getColumn(3).setResizable(false);
            tablePLL2.getColumnModel().getColumn(3).setPreferredWidth(300);
            tablePLL2.getColumnModel().getColumn(4).setResizable(false);
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
                .addGap(90, 90, 90)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(90, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(228, 228, 228)
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitle)
                    .addComponent(btnBack))
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

        //go back to selection screen
        a.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnBackActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JTable tablePLL2;
    // End of variables declaration//GEN-END:variables
}
