/*
 * David Zhang 
 * 2024/07/12
 * PLL Selection screen for trainer
 */
package cube.trainer;

import javax.swing.JToggleButton;
import java.awt.Color;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author David Zhang
 */
public class PLLSelection extends javax.swing.JFrame {

    AlgTrainer trainer;
    TrainerSelect t;
    //arrayList of algs to be trained
    ArrayList<Algorithm> cases;
    JToggleButton buttons[];

    public PLLSelection(TrainerSelect t) {
        initComponents();
        initButtonArray();
        this.t = t;
        
        //change background color
        this.getContentPane().setBackground(new Color(102,204,255));
        
        //make frame not resizable
        setResizable(false);

    }

    /**
     * create array of buttons
     */
    private void initButtonArray() {
        JToggleButton btn[] = {btnAa, btnAb, btnE, btnF, btnGa, btnGb, btnGc, btnGd, btnH, btnJa, btnJb, btnNa, btnNb, btnRa, btnRb, btnT, btnUa, btnUb, btnV, btnY, btnZ};
        buttons = btn;
    }

    /**
     * Load array with selected PLL cases
     *
     * @param selected - whether the cases have been selected
     * @return - ArrayList of cases to be used in trainer
     */
    public static ArrayList<Algorithm> loadArray(boolean[] selected) {
        ArrayList<Algorithm> cases = new ArrayList();

        try {
            FileInputStream in = new FileInputStream(System.getProperty("user.dir") + "/Data Files/PLL.txt"); 
            Scanner s = new Scanner(in);

            for (int i = 0; i < selected.length; i++) {
                //read in data from file
                String set = s.nextLine();
                String group = s.nextLine();
                String name = s.nextLine();

                //create array of 4 setup moves and load it
                String[] setups = new String[4];
                for (int j = 0; j < 4; j++) {
                    setups[j] = s.nextLine();
                }

                String alg = s.nextLine();
                String imgFile = s.nextLine();
                boolean learned = Boolean.parseBoolean(s.nextLine());

                //create new Algorithm object
                Algorithm a = new Algorithm(set, group, name, setups, alg, imgFile, learned, selected[i]);

                //add to arraylist if alg has been selected
                if (a.isSelected()) {
                    cases.add(a);
                }
            }
            
            System.out.println(cases.size());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        return cases;

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAa = new javax.swing.JToggleButton();
        btnAb = new javax.swing.JToggleButton();
        btnE = new javax.swing.JToggleButton();
        btnF = new javax.swing.JToggleButton();
        btnGd = new javax.swing.JToggleButton();
        btnGa = new javax.swing.JToggleButton();
        btnGb = new javax.swing.JToggleButton();
        btnGc = new javax.swing.JToggleButton();
        btnJa = new javax.swing.JToggleButton();
        btnH = new javax.swing.JToggleButton();
        btnRa = new javax.swing.JToggleButton();
        btnUb = new javax.swing.JToggleButton();
        btnRb = new javax.swing.JToggleButton();
        btnT = new javax.swing.JToggleButton();
        btnUa = new javax.swing.JToggleButton();
        btnY = new javax.swing.JToggleButton();
        btnV = new javax.swing.JToggleButton();
        btnJb = new javax.swing.JToggleButton();
        btnNa = new javax.swing.JToggleButton();
        btnNb = new javax.swing.JToggleButton();
        btnZ = new javax.swing.JToggleButton();
        btnStart = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnSelAll = new javax.swing.JButton();
        btnDesAll = new javax.swing.JButton();
        btnRecap = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnAa.setBackground(new java.awt.Color(204, 204, 204));
        btnAa.setFont(new java.awt.Font("DengXian", 1, 48)); // NOI18N
        btnAa.setText("Aa");
        btnAa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        btnAa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAaActionPerformed(evt);
            }
        });
        btnAa.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                btnAaPropertyChange(evt);
            }
        });

        btnAb.setBackground(new java.awt.Color(204, 204, 204));
        btnAb.setFont(new java.awt.Font("DengXian", 1, 48)); // NOI18N
        btnAb.setText("Ab");
        btnAb.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        btnAb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbActionPerformed(evt);
            }
        });

        btnE.setBackground(new java.awt.Color(204, 204, 204));
        btnE.setFont(new java.awt.Font("DengXian", 1, 48)); // NOI18N
        btnE.setText("E");
        btnE.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        btnE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEActionPerformed(evt);
            }
        });

        btnF.setBackground(new java.awt.Color(204, 204, 204));
        btnF.setFont(new java.awt.Font("DengXian", 1, 48)); // NOI18N
        btnF.setText("F");
        btnF.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        btnF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFActionPerformed(evt);
            }
        });

        btnGd.setBackground(new java.awt.Color(204, 204, 204));
        btnGd.setFont(new java.awt.Font("DengXian", 1, 48)); // NOI18N
        btnGd.setText("Gd");
        btnGd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        btnGd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGdActionPerformed(evt);
            }
        });

        btnGa.setBackground(new java.awt.Color(204, 204, 204));
        btnGa.setFont(new java.awt.Font("DengXian", 1, 48)); // NOI18N
        btnGa.setText("Ga");
        btnGa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        btnGa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGaActionPerformed(evt);
            }
        });

        btnGb.setBackground(new java.awt.Color(204, 204, 204));
        btnGb.setFont(new java.awt.Font("DengXian", 1, 48)); // NOI18N
        btnGb.setText("Gb");
        btnGb.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        btnGb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGbActionPerformed(evt);
            }
        });

        btnGc.setBackground(new java.awt.Color(204, 204, 204));
        btnGc.setFont(new java.awt.Font("DengXian", 1, 48)); // NOI18N
        btnGc.setText("Gc");
        btnGc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        btnGc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGcActionPerformed(evt);
            }
        });

        btnJa.setBackground(new java.awt.Color(204, 204, 204));
        btnJa.setFont(new java.awt.Font("DengXian", 1, 48)); // NOI18N
        btnJa.setText("Ja");
        btnJa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        btnJa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJaActionPerformed(evt);
            }
        });

        btnH.setBackground(new java.awt.Color(204, 204, 204));
        btnH.setFont(new java.awt.Font("DengXian", 1, 48)); // NOI18N
        btnH.setText("H");
        btnH.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        btnH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHActionPerformed(evt);
            }
        });

        btnRa.setBackground(new java.awt.Color(204, 204, 204));
        btnRa.setFont(new java.awt.Font("DengXian", 1, 48)); // NOI18N
        btnRa.setText("Ra");
        btnRa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        btnRa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRaActionPerformed(evt);
            }
        });

        btnUb.setBackground(new java.awt.Color(204, 204, 204));
        btnUb.setFont(new java.awt.Font("DengXian", 1, 48)); // NOI18N
        btnUb.setText("Ub");
        btnUb.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        btnUb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbActionPerformed(evt);
            }
        });

        btnRb.setBackground(new java.awt.Color(204, 204, 204));
        btnRb.setFont(new java.awt.Font("DengXian", 1, 48)); // NOI18N
        btnRb.setText("Rb");
        btnRb.setToolTipText("");
        btnRb.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        btnRb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRbActionPerformed(evt);
            }
        });

        btnT.setBackground(new java.awt.Color(204, 204, 204));
        btnT.setFont(new java.awt.Font("DengXian", 1, 48)); // NOI18N
        btnT.setText("T");
        btnT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        btnT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTActionPerformed(evt);
            }
        });

        btnUa.setBackground(new java.awt.Color(204, 204, 204));
        btnUa.setFont(new java.awt.Font("DengXian", 1, 48)); // NOI18N
        btnUa.setText("Ua");
        btnUa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        btnUa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUaActionPerformed(evt);
            }
        });

        btnY.setBackground(new java.awt.Color(204, 204, 204));
        btnY.setFont(new java.awt.Font("DengXian", 1, 48)); // NOI18N
        btnY.setText("Y");
        btnY.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        btnY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnYActionPerformed(evt);
            }
        });

        btnV.setBackground(new java.awt.Color(204, 204, 204));
        btnV.setFont(new java.awt.Font("DengXian", 1, 48)); // NOI18N
        btnV.setText("V");
        btnV.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        btnV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVActionPerformed(evt);
            }
        });

        btnJb.setBackground(new java.awt.Color(204, 204, 204));
        btnJb.setFont(new java.awt.Font("DengXian", 1, 48)); // NOI18N
        btnJb.setText("Jb");
        btnJb.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        btnJb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJbActionPerformed(evt);
            }
        });

        btnNa.setBackground(new java.awt.Color(204, 204, 204));
        btnNa.setFont(new java.awt.Font("DengXian", 1, 48)); // NOI18N
        btnNa.setText("Na");
        btnNa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        btnNa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNaActionPerformed(evt);
            }
        });

        btnNb.setBackground(new java.awt.Color(204, 204, 204));
        btnNb.setFont(new java.awt.Font("DengXian", 1, 48)); // NOI18N
        btnNb.setText("Nb");
        btnNb.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        btnNb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNbActionPerformed(evt);
            }
        });

        btnZ.setBackground(new java.awt.Color(204, 204, 204));
        btnZ.setFont(new java.awt.Font("DengXian", 1, 48)); // NOI18N
        btnZ.setText("Z");
        btnZ.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        btnZ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnZActionPerformed(evt);
            }
        });

        btnStart.setBackground(new java.awt.Color(0, 204, 204));
        btnStart.setFont(new java.awt.Font("DengXian", 1, 48)); // NOI18N
        btnStart.setText("Start");
        btnStart.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
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

        jLabel1.setFont(new java.awt.Font("DengXian", 1, 60)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Select PLL Cases");
        jLabel1.setToolTipText("");

        btnSelAll.setBackground(new java.awt.Color(102, 255, 102));
        btnSelAll.setFont(new java.awt.Font("DengXian", 1, 24)); // NOI18N
        btnSelAll.setText("Select All");
        btnSelAll.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        btnSelAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelAllActionPerformed(evt);
            }
        });

        btnDesAll.setBackground(new java.awt.Color(255, 51, 51));
        btnDesAll.setFont(new java.awt.Font("DengXian", 1, 24)); // NOI18N
        btnDesAll.setText("Deselect All");
        btnDesAll.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        btnDesAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDesAllActionPerformed(evt);
            }
        });

        btnRecap.setBackground(new java.awt.Color(0, 204, 204));
        btnRecap.setFont(new java.awt.Font("DengXian", 1, 48)); // NOI18N
        btnRecap.setText("Recap");
        btnRecap.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        btnRecap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecapActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnAa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnAb, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnE, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnF, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnGa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnGb, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnGc, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnGd, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnH, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnJa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnStart, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnJb, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnNa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnNb, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnRa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnRb, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnT, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnUa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnUb, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnV, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnY, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(btnRecap, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(59, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnZ, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDesAll, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btnSelAll, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47))))
            .addGroup(layout.createSequentialGroup()
                .addGap(390, 390, 390)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(btnBack)))
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAb, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnE, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnF, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGb, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGc, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGd, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnH, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnJa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnJb, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNb, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRb, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnT, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUb, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnV, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnY, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnZ, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDesAll, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSelAll, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnStart, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRecap, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 87, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Change to Alg Trainer window
     *
     * @param recap - whether to recap the selected cases
     */
    public void changeWindows(boolean recap) {
        int numSelected = 0;

        //array of status of buttons
        boolean[] selected = new boolean[21];
        //check which buttons are selected
        for (int i = 0; i < buttons.length; i++) {
            selected[i] = buttons[i].isSelected();
            //increment if selected
            if (selected[i]) {
                numSelected++;
            }
            
        }

        //only proceed if there is at least one case selected
        if (numSelected > 0) {
            //load the arraylist of cases to train
            cases = loadArray(selected);

            //close window and go to Trainer screen
            trainer = new AlgTrainer(this, cases, recap);
            trainer.setVisible(true);
            this.setVisible(false);

            //set focus to trainer
            trainer.setFocusable(true);
            trainer.requestFocus();
        }

    }


    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
        //go to Alg Trainer Screen, do not recap
        changeWindows(false);

    }//GEN-LAST:event_btnStartActionPerformed

    private void btnAaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_btnAaPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAaPropertyChange

    private void btnAaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAaActionPerformed
        if (btnAa.isSelected()) {
            btnAa.setBackground(new Color(102,255,102));
        } else {
            btnAa.setBackground(new Color(204, 204, 204));
    }//GEN-LAST:event_btnAaActionPerformed

    }
    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        //return to trainer select window
        t.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnAbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbActionPerformed
        if (btnAb.isSelected()) {
            btnAb.setBackground(new Color(102,255,102));
        } else {
            btnAb.setBackground(new Color(204, 204, 204));
        }
    }//GEN-LAST:event_btnAbActionPerformed

    private void btnEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEActionPerformed
        if (btnE.isSelected()) {
            btnE.setBackground(new Color(102,255,102));
        } else {
            btnE.setBackground(new Color(204, 204, 204));
        }
    }//GEN-LAST:event_btnEActionPerformed

    private void btnFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFActionPerformed
        if (btnF.isSelected()) {
            btnF.setBackground(new Color(102,255,102));
        } else {
            btnF.setBackground(new Color(204, 204, 204));
        }
    }//GEN-LAST:event_btnFActionPerformed

    private void btnGaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGaActionPerformed
        if (btnGa.isSelected()) {
            btnGa.setBackground(new Color(102,255,102));
        } else {
            btnGa.setBackground(new Color(204, 204, 204));
        }
    }//GEN-LAST:event_btnGaActionPerformed

    private void btnGbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGbActionPerformed
        if (btnGb.isSelected()) {
            btnGb.setBackground(new Color(102,255,102));
        } else {
            btnGb.setBackground(new Color(204, 204, 204));
        }
    }//GEN-LAST:event_btnGbActionPerformed

    private void btnGcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGcActionPerformed
        if (btnGc.isSelected()) {
            btnGc.setBackground(new Color(102,255,102));
        } else {
            btnGc.setBackground(new Color(204, 204, 204));
        }
    }//GEN-LAST:event_btnGcActionPerformed

    private void btnGdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGdActionPerformed
        if (btnGd.isSelected()) {
            btnGd.setBackground(new Color(102,255,102));
        } else {
            btnGd.setBackground(new Color(204, 204, 204));
        }
    }//GEN-LAST:event_btnGdActionPerformed

    private void btnHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHActionPerformed
        if (btnH.isSelected()) {
            btnH.setBackground(new Color(102,255,102));
        } else {
            btnH.setBackground(new Color(204, 204, 204));
        }
    }//GEN-LAST:event_btnHActionPerformed

    private void btnJaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJaActionPerformed
        if (btnJa.isSelected()) {
            btnJa.setBackground(new Color(102,255,102));
        } else {
            btnJa.setBackground(new Color(204, 204, 204));
        }
    }//GEN-LAST:event_btnJaActionPerformed

    private void btnSelAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelAllActionPerformed
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setSelected(true);
            buttons[i].setBackground(new Color(102,255,102));
        }
    }//GEN-LAST:event_btnSelAllActionPerformed

    private void btnDesAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDesAllActionPerformed
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setSelected(false);
            buttons[i].setBackground(new Color(204, 204, 204));
        }
    }//GEN-LAST:event_btnDesAllActionPerformed

    private void btnJbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJbActionPerformed
        if (btnJb.isSelected()) {
            btnJb.setBackground(new Color(102,255,102));
        } else {
            btnJb.setBackground(new Color(204, 204, 204));
        }
    }//GEN-LAST:event_btnJbActionPerformed

    private void btnNaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNaActionPerformed
        if (btnNa.isSelected()) {
            btnNa.setBackground(new Color(102,255,102));
        } else {
            btnNa.setBackground(new Color(204, 204, 204));
        }
    }//GEN-LAST:event_btnNaActionPerformed

    private void btnNbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNbActionPerformed
        if (btnNb.isSelected()) {
            btnNb.setBackground(new Color(102,255,102));
        } else {
            btnNb.setBackground(new Color(204, 204, 204));
        }
    }//GEN-LAST:event_btnNbActionPerformed

    private void btnRaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRaActionPerformed
        if (btnRa.isSelected()) {
            btnRa.setBackground(new Color(102,255,102));
        } else {
            btnRa.setBackground(new Color(204, 204, 204));
        }
    }//GEN-LAST:event_btnRaActionPerformed

    private void btnRbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRbActionPerformed
        if (btnRb.isSelected()) {
            btnRb.setBackground(new Color(102,255,102));
        } else {
            btnRb.setBackground(new Color(204, 204, 204));
        }
    }//GEN-LAST:event_btnRbActionPerformed

    private void btnTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTActionPerformed
        if (btnT.isSelected()) {
            btnT.setBackground(new Color(102,255,102));
        } else {
            btnT.setBackground(new Color(204, 204, 204));
        }
    }//GEN-LAST:event_btnTActionPerformed

    private void btnUaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUaActionPerformed
        if (btnUa.isSelected()) {
            btnUa.setBackground(new Color(102,255,102));
        } else {
            btnUa.setBackground(new Color(204, 204, 204));
        }
    }//GEN-LAST:event_btnUaActionPerformed

    private void btnUbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbActionPerformed
        if (btnUb.isSelected()) {
            btnUb.setBackground(new Color(102,255,102));
        } else {
            btnUb.setBackground(new Color(204, 204, 204));
        }
    }//GEN-LAST:event_btnUbActionPerformed

    private void btnVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVActionPerformed
        if (btnV.isSelected()) {
            btnV.setBackground(new Color(102,255,102));
        } else {
            btnV.setBackground(new Color(204, 204, 204));
        }
    }//GEN-LAST:event_btnVActionPerformed

    private void btnYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnYActionPerformed
        if (btnY.isSelected()) {
            btnY.setBackground(new Color(102,255,102));
        } else {
            btnY.setBackground(new Color(204, 204, 204));
        }
    }//GEN-LAST:event_btnYActionPerformed

    private void btnZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnZActionPerformed
        if (btnZ.isSelected()) {
            btnZ.setBackground(new Color(102,255,102));
        } else {
            btnZ.setBackground(new Color(204, 204, 204));
        }
    }//GEN-LAST:event_btnZActionPerformed

    private void btnRecapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecapActionPerformed
        //go to Alg Trainer Screen, recap
        changeWindows(true);
    }//GEN-LAST:event_btnRecapActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnAa;
    private javax.swing.JToggleButton btnAb;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnDesAll;
    private javax.swing.JToggleButton btnE;
    private javax.swing.JToggleButton btnF;
    private javax.swing.JToggleButton btnGa;
    private javax.swing.JToggleButton btnGb;
    private javax.swing.JToggleButton btnGc;
    private javax.swing.JToggleButton btnGd;
    private javax.swing.JToggleButton btnH;
    private javax.swing.JToggleButton btnJa;
    private javax.swing.JToggleButton btnJb;
    private javax.swing.JToggleButton btnNa;
    private javax.swing.JToggleButton btnNb;
    private javax.swing.JToggleButton btnRa;
    private javax.swing.JToggleButton btnRb;
    private javax.swing.JButton btnRecap;
    private javax.swing.JButton btnSelAll;
    private javax.swing.JButton btnStart;
    private javax.swing.JToggleButton btnT;
    private javax.swing.JToggleButton btnUa;
    private javax.swing.JToggleButton btnUb;
    private javax.swing.JToggleButton btnV;
    private javax.swing.JToggleButton btnY;
    private javax.swing.JToggleButton btnZ;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
