/*
 * David Zhang
 * 2024/07/15
 * Screen to select which alg trainer to navigate to
 */
package cube.trainer;

import java.awt.Color;

/**
 *
 * @author David Zhang
 */
public class TrainerSelect extends javax.swing.JFrame {
    MainMenu m;
    private PLLSelection PLLpage;
    private OLLSelection OLLpage;
    
    public TrainerSelect(MainMenu m) {
        initComponents();
        this.m = m;
        
        //change background color
        this.getContentPane().setBackground(new Color(102,204,255));
        
        //make frame not resizable
        setResizable(false);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnOLL = new javax.swing.JButton();
        btnPLL = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        lblOLLImg = new javax.swing.JLabel();
        lblPLLImg = new javax.swing.JLabel();
        lblTitle = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnOLL.setBackground(new java.awt.Color(0, 153, 255));
        btnOLL.setFont(new java.awt.Font("DengXian", 1, 48)); // NOI18N
        btnOLL.setText("OLL Trainer");
        btnOLL.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 5, true));
        btnOLL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOLLActionPerformed(evt);
            }
        });

        btnPLL.setBackground(new java.awt.Color(0, 153, 255));
        btnPLL.setFont(new java.awt.Font("DengXian", 1, 48)); // NOI18N
        btnPLL.setText("PLL Trainer");
        btnPLL.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 5, true));
        btnPLL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPLLActionPerformed(evt);
            }
        });

        btnBack.setBackground(new java.awt.Color(153, 153, 153));
        btnBack.setFont(new java.awt.Font("DengXian", 1, 36)); // NOI18N
        btnBack.setText("Back");
        btnBack.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        lblOLLImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/OLL Images/45.png"))); // NOI18N

        lblPLLImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/PLL Images/Aa.png"))); // NOI18N

        lblTitle.setFont(new java.awt.Font("DengXian", 1, 80)); // NOI18N
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("Alg Trainers");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(270, 270, 270)
                        .addComponent(lblOLLImg))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(205, 205, 205)
                        .addComponent(btnOLL, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 310, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnPLL, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(205, 205, 205))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblPLLImg)
                        .addGap(270, 270, 270))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
            .addGroup(layout.createSequentialGroup()
                .addGap(405, 405, 405)
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(lblTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblOLLImg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblPLLImg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOLL, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPLL, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(84, 84, 84)
                .addComponent(btnBack)
                .addGap(45, 45, 45))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnOLLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOLLActionPerformed
        //switch to OLL selection screen
        if(OLLpage == null) {
            OLLpage = new OLLSelection(this);
        }
        OLLpage.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnOLLActionPerformed

    private void btnPLLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPLLActionPerformed
        //switch to PLL selection screen
        if(PLLpage == null) {
            PLLpage = new PLLSelection(this);
        }
        PLLpage.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnPLLActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        //return to Main Menu
        m.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnBackActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnOLL;
    private javax.swing.JButton btnPLL;
    private javax.swing.JLabel lblOLLImg;
    private javax.swing.JLabel lblPLLImg;
    private javax.swing.JLabel lblTitle;
    // End of variables declaration//GEN-END:variables
}
