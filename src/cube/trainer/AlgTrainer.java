/*
 * Morgan Yeh
 * 2024/07/12
 * Algorithm trainer screen
 */
package cube.trainer;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.Timer;

/**
 *
 * @author morga
 */
public class AlgTrainer extends javax.swing.JFrame implements KeyListener, ActionListener {

    private OLLSelection o;
    private PLLSelection p;

    //ArrayList of algorithms to train and recap
    ArrayList<Algorithm> toTrain;
    ArrayList<Algorithm> toRecap;
    String algSet; //which set of algs to train

    //variable declarations
    private boolean recap; //whether trainer is in recap mode
    //variable to control if the timer is running
    private String timerStatus;

    //variables for the previous case and previous scramble
    private Algorithm prevCase;
    private String prevScram;

    private int elapsedTime = 0;
    private int seconds = 0;
    private int milliseconds = 0;

    private String sSeconds = String.format("%01d", seconds);
    private String sMilliseconds = String.format("%02d", milliseconds);

    //create new timer that updates every 10 milliseconds
    private Timer timer = new Timer(10, new ActionListener() {

        public void actionPerformed(ActionEvent e) {

            //increase the amount of time elapsed
            elapsedTime += 15;
            //get number of seconds passed
            seconds = elapsedTime / 1000;
            //get number of milliseconds
            milliseconds = (elapsedTime - seconds * 1000) / 10;

            sSeconds = String.format("%01d", seconds);
            sMilliseconds = String.format("%02d", milliseconds);

            lblTime.setText(sSeconds + "." + sMilliseconds);

        }

    });

    /**
     * Creates new form AlgTrainer
     */
    public AlgTrainer(OLLSelection o, ArrayList<Algorithm> algs, boolean recap) {
        this.o = o;
        this.toTrain = algs;
        this.recap = recap;
        initComponents();

        //setup the JFrame
        setup();

    }

    public AlgTrainer(PLLSelection p, ArrayList<Algorithm> algs, boolean recap) {
        this.p = p;
        this.toTrain = algs;
        this.recap = recap;
        initComponents();

        //setup the JFrame
        setup();

    }

    /**
     * set variables and display information
     */
    public void setup() {
        timerStatus = "Stop";

        lblTime.setText(sSeconds + "." + sMilliseconds);

        //get the alg set the arraylist stores
        algSet = toTrain.get(0).getSet();

        //set title of screen to the alg set
        lblTitle.setText(algSet + " Trainer");

        //display number of cases selected
        if(toTrain.size() == 1) {
            lblSelected.setText("Case Selected");
        } else {
            lblSelected.setText("Cases Selected");
        }  
        
        lblNumSelected.setText(toTrain.size() + "");

        //if recap mode is selected, display number of cases left to train
        if (recap) {
            //make a copy of the algs to train
            toRecap = copy();
            
            if (toRecap.size() == 1) {
                    
                    lblRecap.setText("Case to Recap");
                } else {
                    lblRecap.setText("Case to Recap");
                }
            
            lblNumRecap.setText(toRecap.size() + "");

        } else {
            lblRecap.setText(" ");
            lblNumRecap.setText(" ");
        }

        //generate a scramble
        prevCase = getScramble();

        //add KeyListener and focus to frame
        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocus();
        
        //change background color
        this.getContentPane().setBackground(new Color(102,204,255));
        
        //make frame not resizable
        setResizable(false);

    }

    /**
     * create a copy of the toTrain ArrayList
     *
     * @return - an exact copy of the ArrayList of algs to train
     */
    public ArrayList<Algorithm> copy() {
        ArrayList<Algorithm> copied = new ArrayList();

        for (int i = 0; i < toTrain.size(); i++) {
            copied.add(toTrain.get(i));
        }

        return copied;
    }

    /**
     * Select a random algorithm and generate the scramble
     *
     * @return - alg that the scramble belongs to
     */
    public Algorithm getScramble() {
        Algorithm a;
        ArrayList<Algorithm> array;
        String scramble;

        //take from different arrays depending on trainer mode
        if (recap) {
            //generate scramble using algs to recap
            array = toRecap;
        } else {
            //generate scramble using algs to train
            array = toTrain;
        }

        //pick a random alg from the arraylist
        int rNum = (int) (Math.random() * array.size());
        a = array.get(rNum);

        //generate a scramble and display on screen
        scramble = a.getSetup();
        lblScramble.setText("Scramble: " + scramble);

        return a;
    }

    /**
     * get information about the previous case
     */
    public void getPrevious() {
        String filePath = "src/Images/" + algSet + " Images/";

        //get name, group, image, and alg for previous case
        String pName = prevCase.getName();
        String pGroup = prevCase.getGroup();
        String pImgFile = prevCase.getImg();
        String pAlg = prevCase.getAlg();

        //display info about previous case on screen
        lblPrevious.setText("Previous");
        lblPrevName.setText(pName);
        lblPrevGroup.setText(pGroup);
        lblPrevAlg.setText("Alg: " + pAlg);
        lblPrevScramble.setText(prevScram);
        lblPrevImg.setIcon(new ImageIcon(filePath + pImgFile));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //check status of timer
        if (timerStatus.equals("Stop")) {
            //reset timer
            elapsedTime = 0;
            seconds = 0;
            milliseconds = 0;

            //change timer status and start timer
            timerStatus = "Start";
            timer.start();
        } else if (timerStatus.equals("Start")) {

            //change timer status and stop timer
            timerStatus = "Stop";
            timer.stop();

            //generate a new scramble and get previous one
            prevScram = lblScramble.getText();
            //display info about previous case
            getPrevious();
            
            //remove previous case if in recap mode
            if (recap) {
                removeElement();
                
                //display remaining number of cases to recap
                if (toRecap.size() == 1) {
                    lblRecap.setText("Case to Recap");
                } else {
                    lblRecap.setText("Cases to Recap");
                }
                
                lblNumRecap.setText(toRecap.size() + "");
                       
                //check number of cases left to recap
                if (toRecap.isEmpty()) {
                    //go back to train mode (non-recap mode)
                    recap = false;
                    lblRecap.setText(" ");
                    lblNumRecap.setText(" ");
                }
            }
            
            //generate a new scramble and store the case
            prevCase = getScramble();

        }
    }

    /**
     * remove the previous algorithm that was recapped
     */
    public void removeElement() {
        int index; //index of element to remove
        String currName;
        String nameToRemove;

        //go through all elements in toRecap
        for (int i = 0; i < toRecap.size(); i++) {
            //get name of current element
            currName = toRecap.get(i).getName();
            nameToRemove = prevCase.getName();

            //record index of element to remove
            if (currName.equals(nameToRemove)) {
                index = i;
                //remove element
                toRecap.remove(index);
            }
        }

    }

    public void keyPressed(KeyEvent ke) {
        //check that spacebar is pressed
        if (ke.getKeyCode() == KeyEvent.VK_SPACE && timerStatus.equals("Stop")) {
            //change timer text to turqoise
            lblTime.setForeground(new Color(0, 220, 0));
        }
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyReleased(KeyEvent ke) {
        //check that spacebar is pressed
        if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
            //change timer text to black
            lblTime.setForeground(Color.BLACK);
            //convert KeyEvent to ActionEvent
            ActionEvent ae = new ActionEvent(ke.getSource(), ke.getID(), ke.paramString());

            //call actioPerformed method
            actionPerformed(ae);

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

        lblTime = new javax.swing.JLabel();
        lblTitle = new javax.swing.JLabel();
        lblRecap = new javax.swing.JLabel();
        lblSelected = new javax.swing.JLabel();
        pnlPrevious = new javax.swing.JPanel();
        lblPrevious = new javax.swing.JLabel();
        lblPrevImg = new javax.swing.JLabel();
        lblPrevName = new javax.swing.JLabel();
        lblPrevGroup = new javax.swing.JLabel();
        lblPrevScramble = new javax.swing.JLabel();
        lblPrevAlg = new javax.swing.JLabel();
        lblScramble = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        lblNumRecap = new javax.swing.JLabel();
        lblNumSelected = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTime.setFont(new java.awt.Font("DengXian", 1, 72)); // NOI18N
        lblTime.setText("0.00");

        lblTitle.setFont(new java.awt.Font("DengXian", 1, 80)); // NOI18N
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("Alg Trainer");

        lblRecap.setFont(new java.awt.Font("DengXian", 1, 30)); // NOI18N
        lblRecap.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblRecap.setText("Cases to Recap");

        lblSelected.setFont(new java.awt.Font("DengXian", 1, 30)); // NOI18N
        lblSelected.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblSelected.setText("Cases Selected");

        pnlPrevious.setBackground(new java.awt.Color(0, 153, 255));
        pnlPrevious.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

        lblPrevious.setFont(new java.awt.Font("DengXian", 1, 36)); // NOI18N
        lblPrevious.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPrevious.setText("Press space to begin");

        lblPrevName.setFont(new java.awt.Font("DengXian", 1, 24)); // NOI18N
        lblPrevName.setText("  ");

        lblPrevGroup.setFont(new java.awt.Font("DengXian", 1, 20)); // NOI18N
        lblPrevGroup.setText(" ");

        lblPrevScramble.setFont(new java.awt.Font("DengXian", 1, 16)); // NOI18N
        lblPrevScramble.setText(" ");

        lblPrevAlg.setFont(new java.awt.Font("DengXian", 1, 16)); // NOI18N
        lblPrevAlg.setText(" ");

        javax.swing.GroupLayout pnlPreviousLayout = new javax.swing.GroupLayout(pnlPrevious);
        pnlPrevious.setLayout(pnlPreviousLayout);
        pnlPreviousLayout.setHorizontalGroup(
            pnlPreviousLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPreviousLayout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(lblPrevImg)
                .addGap(50, 50, 50)
                .addGroup(pnlPreviousLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPreviousLayout.createSequentialGroup()
                        .addComponent(lblPrevName, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(lblPrevGroup, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(pnlPreviousLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(pnlPreviousLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPrevAlg, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPrevScramble, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
            .addComponent(lblPrevious, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlPreviousLayout.setVerticalGroup(
            pnlPreviousLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPreviousLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(lblPrevious)
                .addGroup(pnlPreviousLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPreviousLayout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(lblPrevName)
                        .addGap(18, 18, 18)
                        .addComponent(lblPrevGroup)
                        .addGap(64, 64, 64))
                    .addGroup(pnlPreviousLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblPrevImg)
                        .addGap(26, 26, 26)))
                .addComponent(lblPrevScramble)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblPrevAlg)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        lblScramble.setFont(new java.awt.Font("DengXian", 1, 30)); // NOI18N
        lblScramble.setText("Scramble: B' R' U L D F D' L' U' R B U2 F U2 F'");

        btnBack.setBackground(new java.awt.Color(51, 153, 255));
        btnBack.setFont(new java.awt.Font("DengXian", 1, 30)); // NOI18N
        btnBack.setText("Select Cases");
        btnBack.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        lblNumRecap.setFont(new java.awt.Font("DengXian", 1, 30)); // NOI18N
        lblNumRecap.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNumRecap.setText("#");

        lblNumSelected.setFont(new java.awt.Font("DengXian", 1, 30)); // NOI18N
        lblNumSelected.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNumSelected.setText("#");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(40, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblTime)
                                .addGap(301, 301, 301)
                                .addComponent(pnlPrevious, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblScramble, javax.swing.GroupLayout.PREFERRED_SIZE, 1200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblNumSelected, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblSelected, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblNumRecap, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblRecap, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(40, 40, 40)))
                .addGap(40, 40, 40))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTitle)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblRecap)
                                    .addComponent(lblNumRecap))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblSelected)
                                    .addComponent(lblNumSelected)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(btnBack)))
                .addGap(70, 70, 70)
                .addComponent(lblScramble)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addComponent(lblTime))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(pnlPrevious, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(56, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        //go back to alg selection window 
        //check which window to return to
        if (algSet.equals("OLL")) {
            //return to OLL selection window
            o.setVisible(true);
        } else {
            //return to PLL Selection window
            p.setVisible(true);
        }

        //close window
        this.setVisible(false);
    }//GEN-LAST:event_btnBackActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JLabel lblNumRecap;
    private javax.swing.JLabel lblNumSelected;
    private javax.swing.JLabel lblPrevAlg;
    private javax.swing.JLabel lblPrevGroup;
    private javax.swing.JLabel lblPrevImg;
    private javax.swing.JLabel lblPrevName;
    private javax.swing.JLabel lblPrevScramble;
    private javax.swing.JLabel lblPrevious;
    private javax.swing.JLabel lblRecap;
    private javax.swing.JLabel lblScramble;
    private javax.swing.JLabel lblSelected;
    private javax.swing.JLabel lblTime;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlPrevious;
    // End of variables declaration//GEN-END:variables
}
