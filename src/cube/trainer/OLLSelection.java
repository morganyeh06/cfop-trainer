/*
 * Morgan Yeh 
 * 2024/07/04
 * OLL Selection Screen for trainer
 */
package cube.trainer;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author morga
 */
public class OLLSelection extends javax.swing.JFrame {

    //variable declarations
    TrainerSelect t;
    AlgTrainer trainer;
    
    JToggleButton buttons[];
    JToggleButton selectButtons[];
    boolean buttonSelected[];
    
    ArrayList<Algorithm> cases;
    /**
     * Creates new form OLLSelection
     */
    public OLLSelection(TrainerSelect t) {
        initComponents();
        this.t = t;

        initButtonArray();
        
        //change scroll speed
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(20);
        
        //change background color
        this.getContentPane().setBackground(new Color(102,204,255));
        
        //make frame not resizable
        setResizable(false);

    }

    /**
     * Initialize all the buttons
     */
    private void initButtonArray() {
        //array of buttons for each OLL case
        JToggleButton[] btn = {btn1, btn3, btn2, btn4, btn17, btn18, btn19, btn20,
            btn21, btn22, btn23, btn24, btn25, btn26, btn27,
            btn51, btn52, btn55, btn56, btn33, btn45,
            btn31, btn32, btn43, btn44, btn5, btn6,
            btn7, btn8, btn11, btn12, btn39, btn40,
            btn47, btn48, btn49, btn50, btn53, btn54,
            btn9, btn10, btn35, btn37, btn36, btn38,
            btn13, btn14, btn15, btn16, btn34, btn46,
            btn29, btn30, btn41, btn42, btn28, btn57};

        //array of "select all" buttons for each OLL group
        JToggleButton[] selectBtn = {btnDot, btnOCLL, btnLine, btnT,
            btnP, btnSquare, btnLightning, btnL, btnFish,
            btnW, btnKnightMove, btnC, btnAwkward, btnACO};

        buttons = btn;
        selectButtons = selectBtn;

        //resize each image on button
        for (int i = 0; i < btn.length; i++) {
            Image img = ((ImageIcon) btn[i].getIcon()).getImage();
            img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
            btn[i].setIcon(new ImageIcon(img));
        }
    }

    /**
     * change state of a certain number of buttons
     * @param button - array of buttons to manipulate
     * @param state - state to change to (true/false)
     * @param start - starting index of array
     * @param fin - end index of array
     */
    private void buttonStateChange(JToggleButton button[], boolean state, int start, int fin) {
        int startP;
        int endP;
        if (start == 0 && fin == 0) {
            startP = 0;
            endP = button.length;
        } else {
            startP = start;
            endP = fin + 1;
        }

        //change state of buttons
        for (int i = startP; i < endP; i++) {
            button[i].setSelected(state);

        }

    }

    /**
     * select multiple buttons at the same time
     * @param btn - the "select all" button that has been pressed
     * @param start - starting index
     * @param fin - end index
     */
    private void selectAllToggle(JToggleButton btn, int start, int fin) {
        if (btn.isSelected()) {

            btn.setBackground(new Color(255, 51, 51));
            buttonStateChange(buttons, true, start, fin);
        } else {

            btn.setBackground(new Color(153, 255, 153));
            for (int i = 0; i < buttons.length; i++) {
                buttonStateChange(buttons, false, start, fin);
            }
        }
    }
    
    /**
     * changes state of select all buttons for each group
     * @param selectBtn - array of select all buttons
     * @param selected - whether the buttons should be selected
     * @param c - color to change the buttons to
     */
    private void caseSelectToggle(JToggleButton[] selectBtn, boolean selected, Color c) {
        //change state of every select all button
        for (int i = 0; i < selectBtn.length; i++) {
            //change selection state and colour of button
            selectBtn[i].setSelected(selected);
            selectBtn[i].setBackground(c);
        }
    }
    
    /**
     * Load array with selected PLL cases
     * @param selected - whether the cases have been selected
     * @return - ArrayList of cases to be used in trainer
     */
    public static ArrayList<Algorithm> loadArray(boolean[] selected) {
        ArrayList<Algorithm> cases = new ArrayList();
        
        try {
            FileInputStream in = new FileInputStream(System.getProperty("user.dir") + "/src/Data Files/OLL.txt"); 
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
            
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
        
        
        return cases;
        
    }
    
    /**
     * Change to Alg Trainer window
     *
     * @param recap - whether to recap the selected cases
     */
    public void changeWindows(boolean recap) {
        int numSelected = 0;
        
        //reset Alg Trainer window
        trainer = null;
        
        buttonSelected = new boolean[buttons.length];
        for (int i = 0; i < buttons.length; i++) {
            buttonSelected[i] = buttons[i].isSelected();
            //increment if selected
            if (buttonSelected[i]) {
                numSelected++;
            }
        }
        
        //only proceed if there is at least one case selected
        if (numSelected > 0) {
            //load array
        cases = loadArray(buttonSelected);
        
        //close window and go to Trainer screen
        trainer = new AlgTrainer(this, cases, recap);
        trainer.setVisible(true);
        this.setVisible(false);
        
        //set focus to trainer
        trainer.setFocusable(true);
        trainer.requestFocus();
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
        pnl1 = new javax.swing.JPanel();
        btnDot = new javax.swing.JToggleButton();
        btn1 = new javax.swing.JToggleButton();
        btn2 = new javax.swing.JToggleButton();
        btn3 = new javax.swing.JToggleButton();
        btn4 = new javax.swing.JToggleButton();
        btn17 = new javax.swing.JToggleButton();
        btn18 = new javax.swing.JToggleButton();
        btn19 = new javax.swing.JToggleButton();
        btn20 = new javax.swing.JToggleButton();
        btnOCLL = new javax.swing.JToggleButton();
        btn21 = new javax.swing.JToggleButton();
        btn22 = new javax.swing.JToggleButton();
        btn23 = new javax.swing.JToggleButton();
        btn24 = new javax.swing.JToggleButton();
        btn25 = new javax.swing.JToggleButton();
        btn26 = new javax.swing.JToggleButton();
        btn27 = new javax.swing.JToggleButton();
        btnLine = new javax.swing.JToggleButton();
        btn51 = new javax.swing.JToggleButton();
        btn52 = new javax.swing.JToggleButton();
        btn55 = new javax.swing.JToggleButton();
        btn56 = new javax.swing.JToggleButton();
        btnT = new javax.swing.JToggleButton();
        btn33 = new javax.swing.JToggleButton();
        btn45 = new javax.swing.JToggleButton();
        btnP = new javax.swing.JToggleButton();
        btn31 = new javax.swing.JToggleButton();
        btn32 = new javax.swing.JToggleButton();
        btn43 = new javax.swing.JToggleButton();
        btn44 = new javax.swing.JToggleButton();
        btnSquare = new javax.swing.JToggleButton();
        btn5 = new javax.swing.JToggleButton();
        btn6 = new javax.swing.JToggleButton();
        btnLightning = new javax.swing.JToggleButton();
        btn7 = new javax.swing.JToggleButton();
        btn8 = new javax.swing.JToggleButton();
        btn11 = new javax.swing.JToggleButton();
        btn12 = new javax.swing.JToggleButton();
        btn39 = new javax.swing.JToggleButton();
        btn40 = new javax.swing.JToggleButton();
        btnL = new javax.swing.JToggleButton();
        btn47 = new javax.swing.JToggleButton();
        btn48 = new javax.swing.JToggleButton();
        btn49 = new javax.swing.JToggleButton();
        btn50 = new javax.swing.JToggleButton();
        btn53 = new javax.swing.JToggleButton();
        btn54 = new javax.swing.JToggleButton();
        btnFish = new javax.swing.JToggleButton();
        btn9 = new javax.swing.JToggleButton();
        btn10 = new javax.swing.JToggleButton();
        btn35 = new javax.swing.JToggleButton();
        btn37 = new javax.swing.JToggleButton();
        btnW = new javax.swing.JToggleButton();
        btn36 = new javax.swing.JToggleButton();
        btn38 = new javax.swing.JToggleButton();
        btnKnightMove = new javax.swing.JToggleButton();
        btn13 = new javax.swing.JToggleButton();
        btn14 = new javax.swing.JToggleButton();
        btn15 = new javax.swing.JToggleButton();
        btn16 = new javax.swing.JToggleButton();
        btnC = new javax.swing.JToggleButton();
        btn34 = new javax.swing.JToggleButton();
        btn46 = new javax.swing.JToggleButton();
        btnAwkward = new javax.swing.JToggleButton();
        btn29 = new javax.swing.JToggleButton();
        btn30 = new javax.swing.JToggleButton();
        btn41 = new javax.swing.JToggleButton();
        btn42 = new javax.swing.JToggleButton();
        btnACO = new javax.swing.JToggleButton();
        btn28 = new javax.swing.JToggleButton();
        btn57 = new javax.swing.JToggleButton();
        lblTitle = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        btnAll = new javax.swing.JToggleButton();
        btnStart = new javax.swing.JButton();
        btnRecap = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setHorizontalScrollBar(null);

        pnl1.setBackground(new java.awt.Color(102, 204, 255));
        pnl1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

        btnDot.setBackground(new java.awt.Color(102, 255, 102));
        btnDot.setFont(new java.awt.Font("DengXian", 1, 24)); // NOI18N
        btnDot.setText(" Dot");
        btnDot.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        btnDot.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnDot.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnDot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDotActionPerformed(evt);
            }
        });

        btn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/OLL Images/1.png"))); // NOI18N
        btn1.setMaximumSize(new java.awt.Dimension(100, 100));
        btn1.setMinimumSize(new java.awt.Dimension(100, 100));
        btn1.setPreferredSize(new java.awt.Dimension(100, 100));
        btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1ActionPerformed(evt);
            }
        });

        btn2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/OLL Images/2.png"))); // NOI18N
        btn2.setPreferredSize(new java.awt.Dimension(100, 100));
        btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn2ActionPerformed(evt);
            }
        });

        btn3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/OLL Images/3.png"))); // NOI18N
        btn3.setPreferredSize(new java.awt.Dimension(100, 100));
        btn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn3ActionPerformed(evt);
            }
        });

        btn4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/OLL Images/4.png"))); // NOI18N
        btn4.setMaximumSize(new java.awt.Dimension(100, 100));
        btn4.setMinimumSize(new java.awt.Dimension(100, 100));
        btn4.setPreferredSize(new java.awt.Dimension(100, 100));
        btn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn4ActionPerformed(evt);
            }
        });

        btn17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/OLL Images/17.png"))); // NOI18N
        btn17.setMaximumSize(new java.awt.Dimension(100, 100));
        btn17.setMinimumSize(new java.awt.Dimension(100, 100));
        btn17.setPreferredSize(new java.awt.Dimension(100, 100));
        btn17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn17ActionPerformed(evt);
            }
        });

        btn18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/OLL Images/18.png"))); // NOI18N
        btn18.setMaximumSize(new java.awt.Dimension(100, 100));
        btn18.setMinimumSize(new java.awt.Dimension(100, 100));
        btn18.setPreferredSize(new java.awt.Dimension(100, 100));
        btn18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn18ActionPerformed(evt);
            }
        });

        btn19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/OLL Images/19.png"))); // NOI18N
        btn19.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn19.setMaximumSize(new java.awt.Dimension(100, 100));
        btn19.setMinimumSize(new java.awt.Dimension(100, 100));
        btn19.setPreferredSize(new java.awt.Dimension(100, 100));
        btn19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn19ActionPerformed(evt);
            }
        });

        btn20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/OLL Images/20.png"))); // NOI18N
        btn20.setMaximumSize(new java.awt.Dimension(100, 100));
        btn20.setMinimumSize(new java.awt.Dimension(100, 100));
        btn20.setPreferredSize(new java.awt.Dimension(100, 100));
        btn20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn20ActionPerformed(evt);
            }
        });

        btnOCLL.setBackground(new java.awt.Color(102, 255, 102));
        btnOCLL.setFont(new java.awt.Font("DengXian", 1, 24)); // NOI18N
        btnOCLL.setText(" OCLL");
        btnOCLL.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        btnOCLL.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnOCLL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOCLLActionPerformed(evt);
            }
        });

        btn21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/OLL Images/21.png"))); // NOI18N
        btn21.setMaximumSize(new java.awt.Dimension(100, 100));
        btn21.setMinimumSize(new java.awt.Dimension(100, 100));
        btn21.setPreferredSize(new java.awt.Dimension(100, 100));
        btn21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn21ActionPerformed(evt);
            }
        });

        btn22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/OLL Images/22.png"))); // NOI18N
        btn22.setMaximumSize(new java.awt.Dimension(100, 100));
        btn22.setMinimumSize(new java.awt.Dimension(100, 100));
        btn22.setPreferredSize(new java.awt.Dimension(100, 100));
        btn22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn22ActionPerformed(evt);
            }
        });

        btn23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/OLL Images/23.png"))); // NOI18N
        btn23.setMaximumSize(new java.awt.Dimension(100, 100));
        btn23.setMinimumSize(new java.awt.Dimension(100, 100));
        btn23.setPreferredSize(new java.awt.Dimension(100, 100));
        btn23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn23ActionPerformed(evt);
            }
        });

        btn24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/OLL Images/24.png"))); // NOI18N
        btn24.setMaximumSize(new java.awt.Dimension(100, 100));
        btn24.setMinimumSize(new java.awt.Dimension(100, 100));
        btn24.setPreferredSize(new java.awt.Dimension(100, 100));
        btn24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn24ActionPerformed(evt);
            }
        });

        btn25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/OLL Images/25.png"))); // NOI18N
        btn25.setMaximumSize(new java.awt.Dimension(100, 100));
        btn25.setMinimumSize(new java.awt.Dimension(100, 100));
        btn25.setPreferredSize(new java.awt.Dimension(100, 100));
        btn25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn25ActionPerformed(evt);
            }
        });

        btn26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/OLL Images/26.png"))); // NOI18N
        btn26.setMaximumSize(new java.awt.Dimension(100, 100));
        btn26.setMinimumSize(new java.awt.Dimension(100, 100));
        btn26.setPreferredSize(new java.awt.Dimension(100, 100));
        btn26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn26ActionPerformed(evt);
            }
        });

        btn27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/OLL Images/27.png"))); // NOI18N
        btn27.setMaximumSize(new java.awt.Dimension(100, 100));
        btn27.setMinimumSize(new java.awt.Dimension(100, 100));
        btn27.setPreferredSize(new java.awt.Dimension(100, 100));
        btn27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn27ActionPerformed(evt);
            }
        });

        btnLine.setBackground(new java.awt.Color(102, 255, 102));
        btnLine.setFont(new java.awt.Font("DengXian", 1, 24)); // NOI18N
        btnLine.setText(" Line Shapes");
        btnLine.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        btnLine.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnLine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLineActionPerformed(evt);
            }
        });

        btn51.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/OLL Images/51.png"))); // NOI18N
        btn51.setMaximumSize(new java.awt.Dimension(100, 100));
        btn51.setMinimumSize(new java.awt.Dimension(100, 100));
        btn51.setPreferredSize(new java.awt.Dimension(100, 100));
        btn51.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn51ActionPerformed(evt);
            }
        });

        btn52.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/OLL Images/52.png"))); // NOI18N
        btn52.setMaximumSize(new java.awt.Dimension(100, 100));
        btn52.setMinimumSize(new java.awt.Dimension(100, 100));
        btn52.setPreferredSize(new java.awt.Dimension(100, 100));
        btn52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn52ActionPerformed(evt);
            }
        });

        btn55.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/OLL Images/55.png"))); // NOI18N
        btn55.setMaximumSize(new java.awt.Dimension(100, 100));
        btn55.setMinimumSize(new java.awt.Dimension(100, 100));
        btn55.setPreferredSize(new java.awt.Dimension(100, 100));
        btn55.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn55ActionPerformed(evt);
            }
        });

        btn56.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/OLL Images/56.png"))); // NOI18N
        btn56.setMaximumSize(new java.awt.Dimension(100, 100));
        btn56.setMinimumSize(new java.awt.Dimension(100, 100));
        btn56.setPreferredSize(new java.awt.Dimension(100, 100));
        btn56.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn56ActionPerformed(evt);
            }
        });

        btnT.setBackground(new java.awt.Color(102, 255, 102));
        btnT.setFont(new java.awt.Font("DengXian", 1, 24)); // NOI18N
        btnT.setText(" T Shapes");
        btnT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        btnT.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTActionPerformed(evt);
            }
        });

        btn33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/OLL Images/33.png"))); // NOI18N
        btn33.setMaximumSize(new java.awt.Dimension(100, 100));
        btn33.setMinimumSize(new java.awt.Dimension(100, 100));
        btn33.setPreferredSize(new java.awt.Dimension(100, 100));
        btn33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn33ActionPerformed(evt);
            }
        });

        btn45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/OLL Images/45.png"))); // NOI18N
        btn45.setMaximumSize(new java.awt.Dimension(100, 100));
        btn45.setMinimumSize(new java.awt.Dimension(100, 100));
        btn45.setPreferredSize(new java.awt.Dimension(100, 100));
        btn45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn45ActionPerformed(evt);
            }
        });

        btnP.setBackground(new java.awt.Color(102, 255, 102));
        btnP.setFont(new java.awt.Font("DengXian", 1, 24)); // NOI18N
        btnP.setText(" P Shapes");
        btnP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        btnP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPActionPerformed(evt);
            }
        });

        btn31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/OLL Images/31.png"))); // NOI18N
        btn31.setMaximumSize(new java.awt.Dimension(100, 100));
        btn31.setMinimumSize(new java.awt.Dimension(100, 100));
        btn31.setPreferredSize(new java.awt.Dimension(100, 100));
        btn31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn31ActionPerformed(evt);
            }
        });

        btn32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/OLL Images/32.png"))); // NOI18N
        btn32.setMaximumSize(new java.awt.Dimension(100, 100));
        btn32.setMinimumSize(new java.awt.Dimension(100, 100));
        btn32.setPreferredSize(new java.awt.Dimension(100, 100));
        btn32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn32ActionPerformed(evt);
            }
        });

        btn43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/OLL Images/43.png"))); // NOI18N
        btn43.setMaximumSize(new java.awt.Dimension(100, 100));
        btn43.setMinimumSize(new java.awt.Dimension(100, 100));
        btn43.setPreferredSize(new java.awt.Dimension(100, 100));
        btn43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn43ActionPerformed(evt);
            }
        });

        btn44.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/OLL Images/44.png"))); // NOI18N
        btn44.setMaximumSize(new java.awt.Dimension(100, 100));
        btn44.setMinimumSize(new java.awt.Dimension(100, 100));
        btn44.setPreferredSize(new java.awt.Dimension(100, 100));
        btn44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn44ActionPerformed(evt);
            }
        });

        btnSquare.setBackground(new java.awt.Color(102, 255, 102));
        btnSquare.setFont(new java.awt.Font("DengXian", 1, 24)); // NOI18N
        btnSquare.setText(" Square Shapes");
        btnSquare.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        btnSquare.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnSquare.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSquareActionPerformed(evt);
            }
        });

        btn5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/OLL Images/5.png"))); // NOI18N
        btn5.setMaximumSize(new java.awt.Dimension(100, 100));
        btn5.setMinimumSize(new java.awt.Dimension(100, 100));
        btn5.setPreferredSize(new java.awt.Dimension(100, 100));
        btn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn5ActionPerformed(evt);
            }
        });

        btn6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/OLL Images/6.png"))); // NOI18N
        btn6.setMaximumSize(new java.awt.Dimension(100, 100));
        btn6.setMinimumSize(new java.awt.Dimension(100, 100));
        btn6.setPreferredSize(new java.awt.Dimension(100, 100));
        btn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn6ActionPerformed(evt);
            }
        });

        btnLightning.setBackground(new java.awt.Color(102, 255, 102));
        btnLightning.setFont(new java.awt.Font("DengXian", 1, 24)); // NOI18N
        btnLightning.setText(" Lightning Shapes");
        btnLightning.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        btnLightning.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnLightning.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLightningActionPerformed(evt);
            }
        });

        btn7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/OLL Images/7.png"))); // NOI18N
        btn7.setMaximumSize(new java.awt.Dimension(100, 100));
        btn7.setMinimumSize(new java.awt.Dimension(100, 100));
        btn7.setPreferredSize(new java.awt.Dimension(100, 100));
        btn7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn7ActionPerformed(evt);
            }
        });

        btn8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/OLL Images/8.png"))); // NOI18N
        btn8.setMaximumSize(new java.awt.Dimension(100, 100));
        btn8.setMinimumSize(new java.awt.Dimension(100, 100));
        btn8.setPreferredSize(new java.awt.Dimension(100, 100));
        btn8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn8ActionPerformed(evt);
            }
        });

        btn11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/OLL Images/11.png"))); // NOI18N
        btn11.setMaximumSize(new java.awt.Dimension(100, 100));
        btn11.setMinimumSize(new java.awt.Dimension(100, 100));
        btn11.setPreferredSize(new java.awt.Dimension(100, 100));
        btn11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn11ActionPerformed(evt);
            }
        });

        btn12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/OLL Images/12.png"))); // NOI18N
        btn12.setMaximumSize(new java.awt.Dimension(100, 100));
        btn12.setMinimumSize(new java.awt.Dimension(100, 100));
        btn12.setPreferredSize(new java.awt.Dimension(100, 100));
        btn12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn12ActionPerformed(evt);
            }
        });

        btn39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/OLL Images/39.png"))); // NOI18N
        btn39.setMaximumSize(new java.awt.Dimension(100, 100));
        btn39.setMinimumSize(new java.awt.Dimension(100, 100));
        btn39.setPreferredSize(new java.awt.Dimension(100, 100));
        btn39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn39ActionPerformed(evt);
            }
        });

        btn40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/OLL Images/40.png"))); // NOI18N
        btn40.setMaximumSize(new java.awt.Dimension(100, 100));
        btn40.setMinimumSize(new java.awt.Dimension(100, 100));
        btn40.setPreferredSize(new java.awt.Dimension(100, 100));
        btn40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn40ActionPerformed(evt);
            }
        });

        btnL.setBackground(new java.awt.Color(102, 255, 102));
        btnL.setFont(new java.awt.Font("DengXian", 1, 24)); // NOI18N
        btnL.setText(" L Shape");
        btnL.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        btnL.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLActionPerformed(evt);
            }
        });

        btn47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/OLL Images/47.png"))); // NOI18N
        btn47.setMaximumSize(new java.awt.Dimension(100, 100));
        btn47.setMinimumSize(new java.awt.Dimension(100, 100));
        btn47.setPreferredSize(new java.awt.Dimension(100, 100));
        btn47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn47ActionPerformed(evt);
            }
        });

        btn48.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/OLL Images/48.png"))); // NOI18N
        btn48.setMaximumSize(new java.awt.Dimension(100, 100));
        btn48.setMinimumSize(new java.awt.Dimension(100, 100));
        btn48.setPreferredSize(new java.awt.Dimension(100, 100));
        btn48.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn48ActionPerformed(evt);
            }
        });

        btn49.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/OLL Images/49.png"))); // NOI18N
        btn49.setMaximumSize(new java.awt.Dimension(100, 100));
        btn49.setMinimumSize(new java.awt.Dimension(100, 100));
        btn49.setPreferredSize(new java.awt.Dimension(100, 100));
        btn49.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn49ActionPerformed(evt);
            }
        });

        btn50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/OLL Images/50.png"))); // NOI18N
        btn50.setMaximumSize(new java.awt.Dimension(100, 100));
        btn50.setMinimumSize(new java.awt.Dimension(100, 100));
        btn50.setPreferredSize(new java.awt.Dimension(100, 100));
        btn50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn50ActionPerformed(evt);
            }
        });

        btn53.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/OLL Images/53.png"))); // NOI18N
        btn53.setMaximumSize(new java.awt.Dimension(100, 100));
        btn53.setMinimumSize(new java.awt.Dimension(100, 100));
        btn53.setPreferredSize(new java.awt.Dimension(100, 100));
        btn53.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn53ActionPerformed(evt);
            }
        });

        btn54.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/OLL Images/54.png"))); // NOI18N
        btn54.setMaximumSize(new java.awt.Dimension(100, 100));
        btn54.setMinimumSize(new java.awt.Dimension(100, 100));
        btn54.setPreferredSize(new java.awt.Dimension(100, 100));
        btn54.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn54ActionPerformed(evt);
            }
        });

        btnFish.setBackground(new java.awt.Color(102, 255, 102));
        btnFish.setFont(new java.awt.Font("DengXian", 1, 24)); // NOI18N
        btnFish.setText(" Fish Shapes");
        btnFish.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        btnFish.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnFish.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFishActionPerformed(evt);
            }
        });

        btn9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/OLL Images/9.png"))); // NOI18N
        btn9.setMaximumSize(new java.awt.Dimension(100, 100));
        btn9.setMinimumSize(new java.awt.Dimension(100, 100));
        btn9.setPreferredSize(new java.awt.Dimension(100, 100));
        btn9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn9ActionPerformed(evt);
            }
        });

        btn10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/OLL Images/10.png"))); // NOI18N
        btn10.setMaximumSize(new java.awt.Dimension(100, 100));
        btn10.setMinimumSize(new java.awt.Dimension(100, 100));
        btn10.setPreferredSize(new java.awt.Dimension(100, 100));
        btn10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn10ActionPerformed(evt);
            }
        });

        btn35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/OLL Images/35.png"))); // NOI18N
        btn35.setMaximumSize(new java.awt.Dimension(100, 100));
        btn35.setMinimumSize(new java.awt.Dimension(100, 100));
        btn35.setPreferredSize(new java.awt.Dimension(100, 100));
        btn35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn35ActionPerformed(evt);
            }
        });

        btn37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/OLL Images/37.png"))); // NOI18N
        btn37.setMaximumSize(new java.awt.Dimension(100, 100));
        btn37.setMinimumSize(new java.awt.Dimension(100, 100));
        btn37.setPreferredSize(new java.awt.Dimension(100, 100));
        btn37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn37ActionPerformed(evt);
            }
        });

        btnW.setBackground(new java.awt.Color(102, 255, 102));
        btnW.setFont(new java.awt.Font("DengXian", 1, 24)); // NOI18N
        btnW.setText(" W Shapes");
        btnW.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        btnW.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnWActionPerformed(evt);
            }
        });

        btn36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/OLL Images/36.png"))); // NOI18N
        btn36.setMaximumSize(new java.awt.Dimension(100, 100));
        btn36.setMinimumSize(new java.awt.Dimension(100, 100));
        btn36.setPreferredSize(new java.awt.Dimension(100, 100));
        btn36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn36ActionPerformed(evt);
            }
        });

        btn38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/OLL Images/38.png"))); // NOI18N
        btn38.setMaximumSize(new java.awt.Dimension(100, 100));
        btn38.setMinimumSize(new java.awt.Dimension(100, 100));
        btn38.setPreferredSize(new java.awt.Dimension(100, 100));
        btn38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn38ActionPerformed(evt);
            }
        });

        btnKnightMove.setBackground(new java.awt.Color(102, 255, 102));
        btnKnightMove.setFont(new java.awt.Font("DengXian", 1, 24)); // NOI18N
        btnKnightMove.setText(" Knight Move Shapes");
        btnKnightMove.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        btnKnightMove.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnKnightMove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKnightMoveActionPerformed(evt);
            }
        });

        btn13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/OLL Images/13.png"))); // NOI18N
        btn13.setMaximumSize(new java.awt.Dimension(100, 100));
        btn13.setMinimumSize(new java.awt.Dimension(100, 100));
        btn13.setPreferredSize(new java.awt.Dimension(100, 100));
        btn13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn13ActionPerformed(evt);
            }
        });

        btn14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/OLL Images/14.png"))); // NOI18N
        btn14.setMaximumSize(new java.awt.Dimension(100, 100));
        btn14.setMinimumSize(new java.awt.Dimension(100, 100));
        btn14.setPreferredSize(new java.awt.Dimension(100, 100));
        btn14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn14ActionPerformed(evt);
            }
        });

        btn15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/OLL Images/15.png"))); // NOI18N
        btn15.setMaximumSize(new java.awt.Dimension(100, 100));
        btn15.setMinimumSize(new java.awt.Dimension(100, 100));
        btn15.setPreferredSize(new java.awt.Dimension(100, 100));
        btn15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn15ActionPerformed(evt);
            }
        });

        btn16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/OLL Images/16.png"))); // NOI18N
        btn16.setMaximumSize(new java.awt.Dimension(100, 100));
        btn16.setMinimumSize(new java.awt.Dimension(100, 100));
        btn16.setPreferredSize(new java.awt.Dimension(100, 100));
        btn16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn16ActionPerformed(evt);
            }
        });

        btnC.setBackground(new java.awt.Color(102, 255, 102));
        btnC.setFont(new java.awt.Font("DengXian", 1, 24)); // NOI18N
        btnC.setText(" C Shapes");
        btnC.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        btnC.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCActionPerformed(evt);
            }
        });

        btn34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/OLL Images/34.png"))); // NOI18N
        btn34.setMaximumSize(new java.awt.Dimension(100, 100));
        btn34.setMinimumSize(new java.awt.Dimension(100, 100));
        btn34.setPreferredSize(new java.awt.Dimension(100, 100));
        btn34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn34ActionPerformed(evt);
            }
        });

        btn46.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/OLL Images/46.png"))); // NOI18N
        btn46.setMaximumSize(new java.awt.Dimension(100, 100));
        btn46.setMinimumSize(new java.awt.Dimension(100, 100));
        btn46.setPreferredSize(new java.awt.Dimension(100, 100));
        btn46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn46ActionPerformed(evt);
            }
        });

        btnAwkward.setBackground(new java.awt.Color(102, 255, 102));
        btnAwkward.setFont(new java.awt.Font("DengXian", 1, 24)); // NOI18N
        btnAwkward.setText(" Awkward Shapes");
        btnAwkward.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        btnAwkward.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnAwkward.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAwkwardActionPerformed(evt);
            }
        });

        btn29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/OLL Images/29.png"))); // NOI18N
        btn29.setMaximumSize(new java.awt.Dimension(100, 100));
        btn29.setMinimumSize(new java.awt.Dimension(100, 100));
        btn29.setPreferredSize(new java.awt.Dimension(100, 100));
        btn29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn29ActionPerformed(evt);
            }
        });

        btn30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/OLL Images/30.png"))); // NOI18N
        btn30.setMaximumSize(new java.awt.Dimension(100, 100));
        btn30.setMinimumSize(new java.awt.Dimension(100, 100));
        btn30.setPreferredSize(new java.awt.Dimension(100, 100));
        btn30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn30ActionPerformed(evt);
            }
        });

        btn41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/OLL Images/41.png"))); // NOI18N
        btn41.setMaximumSize(new java.awt.Dimension(100, 100));
        btn41.setMinimumSize(new java.awt.Dimension(100, 100));
        btn41.setPreferredSize(new java.awt.Dimension(100, 100));
        btn41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn41ActionPerformed(evt);
            }
        });

        btn42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/OLL Images/42.png"))); // NOI18N
        btn42.setMaximumSize(new java.awt.Dimension(100, 100));
        btn42.setMinimumSize(new java.awt.Dimension(100, 100));
        btn42.setPreferredSize(new java.awt.Dimension(100, 100));
        btn42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn42ActionPerformed(evt);
            }
        });

        btnACO.setBackground(new java.awt.Color(102, 255, 102));
        btnACO.setFont(new java.awt.Font("DengXian", 1, 18)); // NOI18N
        btnACO.setText(" All Corners Oriented");
        btnACO.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        btnACO.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnACO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnACOActionPerformed(evt);
            }
        });

        btn28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/OLL Images/28.png"))); // NOI18N
        btn28.setMaximumSize(new java.awt.Dimension(100, 100));
        btn28.setMinimumSize(new java.awt.Dimension(100, 100));
        btn28.setPreferredSize(new java.awt.Dimension(100, 100));
        btn28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn28ActionPerformed(evt);
            }
        });

        btn57.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/OLL Images/57.png"))); // NOI18N
        btn57.setMaximumSize(new java.awt.Dimension(100, 100));
        btn57.setMinimumSize(new java.awt.Dimension(100, 100));
        btn57.setPreferredSize(new java.awt.Dimension(100, 100));
        btn57.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn57ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl1Layout = new javax.swing.GroupLayout(pnl1);
        pnl1.setLayout(pnl1Layout);
        pnl1Layout.setHorizontalGroup(
            pnl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl1Layout.createSequentialGroup()
                        .addGroup(pnl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnOCLL, javax.swing.GroupLayout.PREFERRED_SIZE, 736, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnl1Layout.createSequentialGroup()
                                .addComponent(btn9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnl1Layout.createSequentialGroup()
                                .addComponent(btn47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn53, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn54, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnl1Layout.createSequentialGroup()
                                .addComponent(btn7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnl1Layout.createSequentialGroup()
                                .addComponent(btn21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn26, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnl1Layout.createSequentialGroup()
                                .addComponent(btn31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(btn5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnl1Layout.createSequentialGroup()
                                .addComponent(btn1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnl1Layout.createSequentialGroup()
                                .addComponent(btn51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn55, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn56, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(174, Short.MAX_VALUE))
                    .addGroup(pnl1Layout.createSequentialGroup()
                        .addGroup(pnl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnDot, javax.swing.GroupLayout.PREFERRED_SIZE, 842, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLightning, javax.swing.GroupLayout.PREFERRED_SIZE, 630, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnL, javax.swing.GroupLayout.PREFERRED_SIZE, 630, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnl1Layout.createSequentialGroup()
                                .addComponent(btn29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn57, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnl1Layout.createSequentialGroup()
                                .addComponent(btnFish, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnW, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnl1Layout.createSequentialGroup()
                                .addComponent(btnKnightMove, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnC, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnl1Layout.createSequentialGroup()
                                .addComponent(btnAwkward, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnACO, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnl1Layout.createSequentialGroup()
                                .addComponent(btn13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(btn34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnl1Layout.createSequentialGroup()
                                .addComponent(btnLine, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnT, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnl1Layout.createSequentialGroup()
                                .addComponent(btnP, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSquare, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        pnl1Layout.setVerticalGroup(
            pnl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnDot)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnOCLL)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl1Layout.createSequentialGroup()
                        .addGroup(pnl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnLine)
                            .addComponent(btnT)))
                    .addComponent(btn27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn55, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn56, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnP)
                    .addComponent(btnSquare, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLightning)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnL)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn53, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn54, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnW, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnFish, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl1Layout.createSequentialGroup()
                        .addGroup(pnl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnKnightMove, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnC))
                        .addGap(4, 4, 4))
                    .addGroup(pnl1Layout.createSequentialGroup()
                        .addGroup(pnl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(pnl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAwkward, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnACO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn57, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jScrollPane1.setViewportView(pnl1);

        lblTitle.setFont(new java.awt.Font("DengXian", 1, 60)); // NOI18N
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("Select OLL Cases");
        lblTitle.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btnBack.setBackground(new java.awt.Color(153, 153, 153));
        btnBack.setFont(new java.awt.Font("DengXian", 1, 36)); // NOI18N
        btnBack.setText("Back");
        btnBack.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnAll.setBackground(new java.awt.Color(102, 255, 102));
        btnAll.setFont(new java.awt.Font("DengXian", 1, 36)); // NOI18N
        btnAll.setText("Select All");
        btnAll.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        btnAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAllActionPerformed(evt);
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
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 880, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnStart, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRecap, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(80, 80, 80))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnAll, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50))))
            .addGroup(layout.createSequentialGroup()
                .addGap(390, 390, 390)
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTitle)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnBack)
                        .addGap(141, 141, 141)
                        .addComponent(btnAll, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80)
                        .addComponent(btnStart)
                        .addGap(18, 18, 18)
                        .addComponent(btnRecap)
                        .addGap(151, 151, 151))))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn3ActionPerformed
        //if all cases are selected activate the select all Dot toggle
        if(btn1.isSelected() && btn2.isSelected() 
                && btn3.isSelected() && btn4.isSelected() && btn17.isSelected()
                && btn18.isSelected() && btn19.isSelected() && btn20.isSelected()) {
            
            btnDot.setSelected(true);
            selectAllToggle(btnDot, 0, 7);
        } else {
            btnDot.setSelected(false);
            btnDot.setBackground(new Color(153, 255, 153));
        }
    }//GEN-LAST:event_btn3ActionPerformed

    private void btn27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn27ActionPerformed
        //if all OCLL cases are selected activate the select all OCLL toggle
        if(btn21.isSelected() && btn22.isSelected() && btn23.isSelected() 
                && btn24.isSelected() && btn25.isSelected()
                && btn26.isSelected() && btn27.isSelected()) {
            
            btnOCLL.setSelected(true);
            selectAllToggle(btnOCLL, 8, 14);
        } else {
            btnOCLL.setSelected(false);
            btnOCLL.setBackground(new Color(153, 255, 153));
        }
    }//GEN-LAST:event_btn27ActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        //switch back to selection screen
        t.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
        //do not recap cases, change windows
        changeWindows(false);
    }//GEN-LAST:event_btnStartActionPerformed

    private void btnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAllActionPerformed
        Color c;

        //select every case
        selectAllToggle(btnAll, 0, 56);
        
        //change state of case-specific "select all" buttons
        if(btnAll.isSelected()) {
            //change select all buttons to selected
            c = new Color(255, 51, 51);
            caseSelectToggle(selectButtons, true, c);
            
            //change text on btnAll
            btnAll.setText("Deselect All");
        } else {
            //change all buttons to not selected
            c = new Color(153, 255, 153);
            caseSelectToggle(selectButtons, false, c);
            
            //change text on btnAll
            btnAll.setText("Select All");
        }

    }//GEN-LAST:event_btnAllActionPerformed

    private void btnDotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDotActionPerformed
        selectAllToggle(btnDot, 0, 7);
        
        if (btnDot.isSelected() && btnOCLL.isSelected()
            && btnLine.isSelected() && btnT.isSelected()
            && btnP.isSelected() && btnSquare.isSelected()
            && btnLightning.isSelected() && btnL.isSelected()
            && btnFish.isSelected() && btnW.isSelected()
            && btnKnightMove.isSelected() && btnC.isSelected()
            && btnAwkward.isSelected() && btnACO.isSelected()) {
            
            //btnAll is now selected
            btnAll.setSelected(true);
            btnAll.setBackground(new Color(255, 51, 51)); 
            btnAll.setText("Deselect All");
        } else {
            //btnAll is not selected
            btnAll.setSelected(false);
            btnAll.setBackground(new Color(153, 255, 153));
            btnAll.setText("Select All");
        }
    }//GEN-LAST:event_btnDotActionPerformed

    private void btnLineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLineActionPerformed
        selectAllToggle(btnLine, 15, 18);
        
        selectAllToggle(btnDot, 0, 7);
        
        if (btnDot.isSelected() && btnOCLL.isSelected()
            && btnLine.isSelected() && btnT.isSelected()
            && btnP.isSelected() && btnSquare.isSelected()
            && btnLightning.isSelected() && btnL.isSelected()
            && btnFish.isSelected() && btnW.isSelected()
            && btnKnightMove.isSelected() && btnC.isSelected()
            && btnAwkward.isSelected() && btnACO.isSelected()) {
            
            //btnAll is now selected
            btnAll.setSelected(true);
            btnAll.setBackground(new Color(255, 51, 51)); 
            btnAll.setText("Deselect All");
        } else {
            //btnAll is not selected
            btnAll.setSelected(false);
            btnAll.setBackground(new Color(153, 255, 153));
            btnAll.setText("Select All");
        }
    }//GEN-LAST:event_btnLineActionPerformed

    private void btnTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTActionPerformed
        selectAllToggle(btnT, 19, 20);
        
        selectAllToggle(btnDot, 0, 7);
        
        if (btnDot.isSelected() && btnOCLL.isSelected()
            && btnLine.isSelected() && btnT.isSelected()
            && btnP.isSelected() && btnSquare.isSelected()
            && btnLightning.isSelected() && btnL.isSelected()
            && btnFish.isSelected() && btnW.isSelected()
            && btnKnightMove.isSelected() && btnC.isSelected()
            && btnAwkward.isSelected() && btnACO.isSelected()) {
            
            //btnAll is now selected
            btnAll.setSelected(true);
            btnAll.setBackground(new Color(255, 51, 51)); 
            btnAll.setText("Deselect All");
        } else {
            //btnAll is not selected
            btnAll.setSelected(false);
            btnAll.setBackground(new Color(153, 255, 153));
            btnAll.setText("Select All");
        }
    }//GEN-LAST:event_btnTActionPerformed

    private void btnPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPActionPerformed
        selectAllToggle(btnP, 21, 24);
        
        selectAllToggle(btnDot, 0, 7);
        
        if (btnDot.isSelected() && btnOCLL.isSelected()
            && btnLine.isSelected() && btnT.isSelected()
            && btnP.isSelected() && btnSquare.isSelected()
            && btnLightning.isSelected() && btnL.isSelected()
            && btnFish.isSelected() && btnW.isSelected()
            && btnKnightMove.isSelected() && btnC.isSelected()
            && btnAwkward.isSelected() && btnACO.isSelected()) {
            
            //btnAll is now selected
            btnAll.setSelected(true);
            btnAll.setBackground(new Color(255, 51, 51)); 
            btnAll.setText("Deselect All");
        } else {
            //btnAll is not selected
            btnAll.setSelected(false);
            btnAll.setBackground(new Color(153, 255, 153));
            btnAll.setText("Select All");
        }
    }//GEN-LAST:event_btnPActionPerformed

    private void btnSquareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSquareActionPerformed
        selectAllToggle(btnSquare, 25, 26);
        
        selectAllToggle(btnDot, 0, 7);
        
        if (btnDot.isSelected() && btnOCLL.isSelected()
            && btnLine.isSelected() && btnT.isSelected()
            && btnP.isSelected() && btnSquare.isSelected()
            && btnLightning.isSelected() && btnL.isSelected()
            && btnFish.isSelected() && btnW.isSelected()
            && btnKnightMove.isSelected() && btnC.isSelected()
            && btnAwkward.isSelected() && btnACO.isSelected()) {
            
            //btnAll is now selected
            btnAll.setSelected(true);
            btnAll.setBackground(new Color(255, 51, 51)); 
            btnAll.setText("Deselect All");
        } else {
            //btnAll is not selected
            btnAll.setSelected(false);
            btnAll.setBackground(new Color(153, 255, 153));
            btnAll.setText("Select All");
        }
    }//GEN-LAST:event_btnSquareActionPerformed

    private void btnLightningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLightningActionPerformed
        selectAllToggle(btnLightning, 27, 32);
        
        selectAllToggle(btnDot, 0, 7);
        
        if (btnDot.isSelected() && btnOCLL.isSelected()
            && btnLine.isSelected() && btnT.isSelected()
            && btnP.isSelected() && btnSquare.isSelected()
            && btnLightning.isSelected() && btnL.isSelected()
            && btnFish.isSelected() && btnW.isSelected()
            && btnKnightMove.isSelected() && btnC.isSelected()
            && btnAwkward.isSelected() && btnACO.isSelected()) {
            
            //btnAll is now selected
            btnAll.setSelected(true);
            btnAll.setBackground(new Color(255, 51, 51)); 
            btnAll.setText("Deselect All");
        } else {
            //btnAll is not selected
            btnAll.setSelected(false);
            btnAll.setBackground(new Color(153, 255, 153));
            btnAll.setText("Select All");
        }
    }//GEN-LAST:event_btnLightningActionPerformed

    private void btnLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLActionPerformed
        selectAllToggle(btnL, 33, 38);
        
        selectAllToggle(btnDot, 0, 7);
        
        if (btnDot.isSelected() && btnOCLL.isSelected()
            && btnLine.isSelected() && btnT.isSelected()
            && btnP.isSelected() && btnSquare.isSelected()
            && btnLightning.isSelected() && btnL.isSelected()
            && btnFish.isSelected() && btnW.isSelected()
            && btnKnightMove.isSelected() && btnC.isSelected()
            && btnAwkward.isSelected() && btnACO.isSelected()) {
            
            //btnAll is now selected
            btnAll.setSelected(true);
            btnAll.setBackground(new Color(255, 51, 51)); 
            btnAll.setText("Deselect All");
        } else {
            //btnAll is not selected
            btnAll.setSelected(false);
            btnAll.setBackground(new Color(153, 255, 153));
            btnAll.setText("Select All");
        }
    }//GEN-LAST:event_btnLActionPerformed

    private void btnFishActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFishActionPerformed
        selectAllToggle(btnFish, 39, 42);
        
        selectAllToggle(btnDot, 0, 7);
        
        if (btnDot.isSelected() && btnOCLL.isSelected()
            && btnLine.isSelected() && btnT.isSelected()
            && btnP.isSelected() && btnSquare.isSelected()
            && btnLightning.isSelected() && btnL.isSelected()
            && btnFish.isSelected() && btnW.isSelected()
            && btnKnightMove.isSelected() && btnC.isSelected()
            && btnAwkward.isSelected() && btnACO.isSelected()) {
            
            //btnAll is now selected
            btnAll.setSelected(true);
            btnAll.setBackground(new Color(255, 51, 51)); 
            btnAll.setText("Deselect All");
        } else {
            //btnAll is not selected
            btnAll.setSelected(false);
            btnAll.setBackground(new Color(153, 255, 153));
            btnAll.setText("Select All");
        }
    }//GEN-LAST:event_btnFishActionPerformed

    private void btnWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWActionPerformed
        selectAllToggle(btnW, 43, 44);
        
        selectAllToggle(btnDot, 0, 7);
        
        if (btnDot.isSelected() && btnOCLL.isSelected()
            && btnLine.isSelected() && btnT.isSelected()
            && btnP.isSelected() && btnSquare.isSelected()
            && btnLightning.isSelected() && btnL.isSelected()
            && btnFish.isSelected() && btnW.isSelected()
            && btnKnightMove.isSelected() && btnC.isSelected()
            && btnAwkward.isSelected() && btnACO.isSelected()) {
            
            //btnAll is now selected
            btnAll.setSelected(true);
            btnAll.setBackground(new Color(255, 51, 51)); 
            btnAll.setText("Deselect All");
        } else {
            //btnAll is not selected
            btnAll.setSelected(false);
            btnAll.setBackground(new Color(153, 255, 153));
            btnAll.setText("Select All");
        }
    }//GEN-LAST:event_btnWActionPerformed

    private void btnOCLLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOCLLActionPerformed
        selectAllToggle(btnOCLL, 8, 14);
        
        selectAllToggle(btnDot, 0, 7);
        
        if (btnDot.isSelected() && btnOCLL.isSelected()
            && btnLine.isSelected() && btnT.isSelected()
            && btnP.isSelected() && btnSquare.isSelected()
            && btnLightning.isSelected() && btnL.isSelected()
            && btnFish.isSelected() && btnW.isSelected()
            && btnKnightMove.isSelected() && btnC.isSelected()
            && btnAwkward.isSelected() && btnACO.isSelected()) {
            
            //btnAll is now selected
            btnAll.setSelected(true);
            btnAll.setBackground(new Color(255, 51, 51)); 
            btnAll.setText("Deselect All");
        } else {
            //btnAll is not selected
            btnAll.setSelected(false);
            btnAll.setBackground(new Color(153, 255, 153));
            btnAll.setText("Select All");
        }
    }//GEN-LAST:event_btnOCLLActionPerformed

    private void btnKnightMoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKnightMoveActionPerformed
        selectAllToggle(btnKnightMove, 45, 48);
        
        selectAllToggle(btnDot, 0, 7);
        
        if (btnDot.isSelected() && btnOCLL.isSelected()
            && btnLine.isSelected() && btnT.isSelected()
            && btnP.isSelected() && btnSquare.isSelected()
            && btnLightning.isSelected() && btnL.isSelected()
            && btnFish.isSelected() && btnW.isSelected()
            && btnKnightMove.isSelected() && btnC.isSelected()
            && btnAwkward.isSelected() && btnACO.isSelected()) {
            
            //btnAll is now selected
            btnAll.setSelected(true);
            btnAll.setBackground(new Color(255, 51, 51)); 
            btnAll.setText("Deselect All");
        } else {
            //btnAll is not selected
            btnAll.setSelected(false);
            btnAll.setBackground(new Color(153, 255, 153));
            btnAll.setText("Select All");
        }
    }//GEN-LAST:event_btnKnightMoveActionPerformed

    private void btnCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCActionPerformed
        selectAllToggle(btnC, 49, 50);
        
        selectAllToggle(btnDot, 0, 7);
        
        if (btnDot.isSelected() && btnOCLL.isSelected()
            && btnLine.isSelected() && btnT.isSelected()
            && btnP.isSelected() && btnSquare.isSelected()
            && btnLightning.isSelected() && btnL.isSelected()
            && btnFish.isSelected() && btnW.isSelected()
            && btnKnightMove.isSelected() && btnC.isSelected()
            && btnAwkward.isSelected() && btnACO.isSelected()) {
            
            //btnAll is now selected
            btnAll.setSelected(true);
            btnAll.setBackground(new Color(255, 51, 51)); 
            btnAll.setText("Deselect All");
        } else {
            //btnAll is not selected
            btnAll.setSelected(false);
            btnAll.setBackground(new Color(153, 255, 153));
            btnAll.setText("Select All");
        }
    }//GEN-LAST:event_btnCActionPerformed

    private void btnAwkwardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAwkwardActionPerformed
        selectAllToggle(btnAwkward, 51, 54);
        
        selectAllToggle(btnDot, 0, 7);
        
        if (btnDot.isSelected() && btnOCLL.isSelected()
            && btnLine.isSelected() && btnT.isSelected()
            && btnP.isSelected() && btnSquare.isSelected()
            && btnLightning.isSelected() && btnL.isSelected()
            && btnFish.isSelected() && btnW.isSelected()
            && btnKnightMove.isSelected() && btnC.isSelected()
            && btnAwkward.isSelected() && btnACO.isSelected()) {
            
            //btnAll is now selected
            btnAll.setSelected(true);
            btnAll.setBackground(new Color(255, 51, 51)); 
            btnAll.setText("Deselect All");
        } else {
            //btnAll is not selected
            btnAll.setSelected(false);
            btnAll.setBackground(new Color(153, 255, 153));
            btnAll.setText("Select All");
        }
    }//GEN-LAST:event_btnAwkwardActionPerformed

    private void btnACOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnACOActionPerformed
        selectAllToggle(btnACO, 55, 56);
        
        selectAllToggle(btnDot, 0, 7);
        
        if (btnDot.isSelected() && btnOCLL.isSelected()
            && btnLine.isSelected() && btnT.isSelected()
            && btnP.isSelected() && btnSquare.isSelected()
            && btnLightning.isSelected() && btnL.isSelected()
            && btnFish.isSelected() && btnW.isSelected()
            && btnKnightMove.isSelected() && btnC.isSelected()
            && btnAwkward.isSelected() && btnACO.isSelected()) {
            
            //btnAll is now selected
            btnAll.setSelected(true);
            btnAll.setBackground(new Color(255, 51, 51)); 
            btnAll.setText("Deselect All");
        } else {
            //btnAll is not selected
            btnAll.setSelected(false);
            btnAll.setBackground(new Color(153, 255, 153));
            btnAll.setText("Select All");
        }
    }//GEN-LAST:event_btnACOActionPerformed

    private void btn33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn33ActionPerformed
        //if both T cases are selected activate the select all T toggle
        if(btn33.isSelected() && btn45.isSelected()) {
            btnT.setSelected(true);
            selectAllToggle(btnT, 19, 20);
        } else {
            btnT.setSelected(false);
            btnT.setBackground(new Color(153, 255, 153));
        }
    }//GEN-LAST:event_btn33ActionPerformed

    private void btnRecapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecapActionPerformed
        //recap cases, change windows
        changeWindows(true);
    }//GEN-LAST:event_btnRecapActionPerformed

    private void btn45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn45ActionPerformed
        //if both T cases are selected activate the select all T toggle
        if(btn33.isSelected() && btn45.isSelected()) {
            btnT.setSelected(true);
            selectAllToggle(btnT, 19, 20);
        } else {
            btnT.setSelected(false);
            btnT.setBackground(new Color(153, 255, 153));
        }
    }//GEN-LAST:event_btn45ActionPerformed

    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1ActionPerformed
        //if all dot cases are selected activate the select all Dot toggle
        if(btn1.isSelected() && btn2.isSelected() 
                && btn3.isSelected() && btn4.isSelected() && btn17.isSelected()
                && btn18.isSelected() && btn19.isSelected() && btn20.isSelected()) {
            
            btnDot.setSelected(true);
            selectAllToggle(btnDot, 0, 7);
        } else {
            btnDot.setSelected(false);
            btnDot.setBackground(new Color(153, 255, 153));
        }
    }//GEN-LAST:event_btn1ActionPerformed

    private void btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn2ActionPerformed
        //if all dot cases are selected activate the select all Dot toggle
        if(btn1.isSelected() && btn2.isSelected() 
                && btn3.isSelected() && btn4.isSelected() && btn17.isSelected()
                && btn18.isSelected() && btn19.isSelected() && btn20.isSelected()) {
            
            btnDot.setSelected(true);
            selectAllToggle(btnDot, 0, 7);
        } else {
            btnDot.setSelected(false);
            btnDot.setBackground(new Color(153, 255, 153));
        }
    }//GEN-LAST:event_btn2ActionPerformed

    private void btn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn4ActionPerformed
        //if all dot cases are selected activate the select all Dot toggle
        if(btn1.isSelected() && btn2.isSelected() 
                && btn3.isSelected() && btn4.isSelected() && btn17.isSelected()
                && btn18.isSelected() && btn19.isSelected() && btn20.isSelected()) {
            
            btnDot.setSelected(true);
            selectAllToggle(btnDot, 0, 7);
        } else {
            btnDot.setSelected(false);
            btnDot.setBackground(new Color(153, 255, 153));
        }
    }//GEN-LAST:event_btn4ActionPerformed

    private void btn17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn17ActionPerformed
        //if all dot cases are selected activate the select all Dot toggle
        if(btn1.isSelected() && btn2.isSelected() 
                && btn3.isSelected() && btn4.isSelected() && btn17.isSelected()
                && btn18.isSelected() && btn19.isSelected() && btn20.isSelected()) {
            
            btnDot.setSelected(true);
            selectAllToggle(btnDot, 0, 7);
        } else {
            btnDot.setSelected(false);
            btnDot.setBackground(new Color(153, 255, 153));
        }
    }//GEN-LAST:event_btn17ActionPerformed

    private void btn18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn18ActionPerformed
        //if all dot cases are selected activate the select all Dot toggle
        if(btn1.isSelected() && btn2.isSelected() 
                && btn3.isSelected() && btn4.isSelected() && btn17.isSelected()
                && btn18.isSelected() && btn19.isSelected() && btn20.isSelected()) {
            
            btnDot.setSelected(true);
            selectAllToggle(btnDot, 0, 7);
        } else {
            btnDot.setSelected(false);
            btnDot.setBackground(new Color(153, 255, 153));
        }
    }//GEN-LAST:event_btn18ActionPerformed

    private void btn19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn19ActionPerformed
        //if all dot cases are selected activate the select all Dot toggle
        if(btn1.isSelected() && btn2.isSelected() 
                && btn3.isSelected() && btn4.isSelected() && btn17.isSelected()
                && btn18.isSelected() && btn19.isSelected() && btn20.isSelected()) {
            
            btnDot.setSelected(true);
            selectAllToggle(btnDot, 0, 7);
        } else {
            btnDot.setSelected(false);
            btnDot.setBackground(new Color(153, 255, 153));
        }
    }//GEN-LAST:event_btn19ActionPerformed

    private void btn20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn20ActionPerformed
        //if all dot cases are selected activate the select all Dot toggle
        if(btn1.isSelected() && btn2.isSelected() 
                && btn3.isSelected() && btn4.isSelected() && btn17.isSelected()
                && btn18.isSelected() && btn19.isSelected() && btn20.isSelected()) {
            
            btnDot.setSelected(true);
            selectAllToggle(btnDot, 0, 7);
        } else {
            btnDot.setSelected(false);
            btnDot.setBackground(new Color(153, 255, 153));
        }
    }//GEN-LAST:event_btn20ActionPerformed

    private void btn21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn21ActionPerformed
        //if all OCLL cases are selected activate the select all OCLL toggle
        if(btn21.isSelected() && btn22.isSelected() && btn23.isSelected() 
                && btn24.isSelected() && btn25.isSelected()
                && btn26.isSelected() && btn27.isSelected()) {
            
            btnOCLL.setSelected(true);
            selectAllToggle(btnOCLL, 8, 14);
        } else {
            btnOCLL.setSelected(false);
            btnOCLL.setBackground(new Color(153, 255, 153));
        }
    }//GEN-LAST:event_btn21ActionPerformed

    private void btn22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn22ActionPerformed
        //if all OCLL cases are selected activate the select all OCLL toggle
        if(btn21.isSelected() && btn22.isSelected() && btn23.isSelected() 
                && btn24.isSelected() && btn25.isSelected()
                && btn26.isSelected() && btn27.isSelected()) {
            
            btnOCLL.setSelected(true);
            selectAllToggle(btnOCLL, 8, 14);
        } else {
            btnOCLL.setSelected(false);
            btnOCLL.setBackground(new Color(153, 255, 153));
        }
    }//GEN-LAST:event_btn22ActionPerformed

    private void btn23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn23ActionPerformed
        //if all OCLL cases are selected activate the select all OCLL toggle
        if(btn21.isSelected() && btn22.isSelected() && btn23.isSelected() 
                && btn24.isSelected() && btn25.isSelected()
                && btn26.isSelected() && btn27.isSelected()) {
            
            btnOCLL.setSelected(true);
            selectAllToggle(btnOCLL, 8, 14);
        } else {
            btnOCLL.setSelected(false);
            btnOCLL.setBackground(new Color(153, 255, 153));
        }
    }//GEN-LAST:event_btn23ActionPerformed

    private void btn24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn24ActionPerformed
        //if all OCLL cases are selected activate the select all OCLL toggle
        if(btn21.isSelected() && btn22.isSelected() && btn23.isSelected() 
                && btn24.isSelected() && btn25.isSelected()
                && btn26.isSelected() && btn27.isSelected()) {
            
            btnOCLL.setSelected(true);
            selectAllToggle(btnOCLL, 8, 14);
        } else {
            btnOCLL.setSelected(false);
            btnOCLL.setBackground(new Color(153, 255, 153));
        }
    }//GEN-LAST:event_btn24ActionPerformed

    private void btn25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn25ActionPerformed
        //if all OCLL cases are selected activate the select all OCLL toggle
        if(btn21.isSelected() && btn22.isSelected() && btn23.isSelected() 
                && btn24.isSelected() && btn25.isSelected()
                && btn26.isSelected() && btn27.isSelected()) {
            
            btnOCLL.setSelected(true);
            selectAllToggle(btnOCLL, 8, 14);
        } else {
            btnOCLL.setSelected(false);
            btnOCLL.setBackground(new Color(153, 255, 153));
        }
    }//GEN-LAST:event_btn25ActionPerformed

    private void btn26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn26ActionPerformed
        //if all OCLL cases are selected activate the select all OCLL toggle
        if(btn21.isSelected() && btn22.isSelected() && btn23.isSelected() 
                && btn24.isSelected() && btn25.isSelected()
                && btn26.isSelected() && btn27.isSelected()) {
            
            btnOCLL.setSelected(true);
            selectAllToggle(btnOCLL, 8, 14);
        } else {
            btnOCLL.setSelected(false);
            btnOCLL.setBackground(new Color(153, 255, 153));
        }
    }//GEN-LAST:event_btn26ActionPerformed

    private void btn51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn51ActionPerformed
        //if all Line cases are selected activate the select all Line toggle
        if(btn51.isSelected() && btn52.isSelected() 
                && btn55.isSelected() && btn56.isSelected()) {
            
            btnLine.setSelected(true);
            selectAllToggle(btnLine, 15, 18);
        } else {
            btnLine.setSelected(false);
            btnLine.setBackground(new Color(153, 255, 153));
        }
    }//GEN-LAST:event_btn51ActionPerformed

    private void btn52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn52ActionPerformed
        //if all Line cases are selected activate the select all Line toggle
        if(btn51.isSelected() && btn52.isSelected() 
                && btn55.isSelected() && btn56.isSelected()) {
            
            btnLine.setSelected(true);
            selectAllToggle(btnLine, 15, 18);
        } else {
            btnLine.setSelected(false);
            btnLine.setBackground(new Color(153, 255, 153));
        }
    }//GEN-LAST:event_btn52ActionPerformed

    private void btn55ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn55ActionPerformed
        //if all Line cases are selected activate the select all Line toggle
        if(btn51.isSelected() && btn52.isSelected() 
                && btn55.isSelected() && btn56.isSelected()) {
            
            btnLine.setSelected(true);
            selectAllToggle(btnLine, 15, 18);
        } else {
            btnLine.setSelected(false);
            btnLine.setBackground(new Color(153, 255, 153));
        }
    }//GEN-LAST:event_btn55ActionPerformed

    private void btn56ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn56ActionPerformed
        //if all Line cases are selected activate the select all Line toggle
        if(btn51.isSelected() && btn52.isSelected() 
                && btn55.isSelected() && btn56.isSelected()) {
            
            btnLine.setSelected(true);
            selectAllToggle(btnLine, 15, 18);
        } else {
            btnLine.setSelected(false);
            btnLine.setBackground(new Color(153, 255, 153));
        }
    }//GEN-LAST:event_btn56ActionPerformed

    private void btn31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn31ActionPerformed
        //if all P cases are selected activate the select all P toggle
        if(btn31.isSelected() && btn32.isSelected() 
                && btn43.isSelected() && btn44.isSelected()) {
            
            btnP.setSelected(true);
            selectAllToggle(btnP, 21, 24);
        } else {
            btnP.setSelected(false);
            btnP.setBackground(new Color(153, 255, 153));
        }
    }//GEN-LAST:event_btn31ActionPerformed

    private void btn32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn32ActionPerformed
        //if all P cases are selected activate the select all P toggle
        if(btn31.isSelected() && btn32.isSelected() 
                && btn43.isSelected() && btn44.isSelected()) {
            
            btnP.setSelected(true);
            selectAllToggle(btnP, 21, 24);
        } else {
            btnP.setSelected(false);
            btnP.setBackground(new Color(153, 255, 153));
        }
    }//GEN-LAST:event_btn32ActionPerformed

    private void btn43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn43ActionPerformed
        //if all P cases are selected activate the select all P toggle
        if(btn31.isSelected() && btn32.isSelected() 
                && btn43.isSelected() && btn44.isSelected()) {
            
            btnP.setSelected(true);
            selectAllToggle(btnP, 21, 24);
        } else {
            btnP.setSelected(false);
            btnP.setBackground(new Color(153, 255, 153));
        }
    }//GEN-LAST:event_btn43ActionPerformed

    private void btn44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn44ActionPerformed
        //if all P cases are selected activate the select all P toggle
        if(btn31.isSelected() && btn32.isSelected() 
                && btn43.isSelected() && btn44.isSelected()) {
            
            btnP.setSelected(true);
            selectAllToggle(btnP, 21, 24);
        } else {
            btnP.setSelected(false);
            btnP.setBackground(new Color(153, 255, 153));
        }
    }//GEN-LAST:event_btn44ActionPerformed

    private void btn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn5ActionPerformed
        //if all square cases are selected activate the select all square toggle
        if(btn5.isSelected() && btn6.isSelected()) {
            
            btnSquare.setSelected(true);
            selectAllToggle(btnSquare, 25, 26);
        } else {
            btnSquare.setSelected(false);
            btnSquare.setBackground(new Color(153, 255, 153));
        }
    }//GEN-LAST:event_btn5ActionPerformed

    private void btn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn6ActionPerformed
        //if all square cases are selected activate the select all square toggle
        if(btn5.isSelected() && btn6.isSelected()) {
            
            btnSquare.setSelected(true);
            selectAllToggle(btnSquare, 25, 26);
        } else {
            btnSquare.setSelected(false);
            btnSquare.setBackground(new Color(153, 255, 153));
        }
    }//GEN-LAST:event_btn6ActionPerformed

    private void btn7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn7ActionPerformed
        //if all lightning cases are selected activate the select all lightning toggle
        if(btn7.isSelected() && btn8.isSelected()
                && btn11.isSelected() && btn12.isSelected()
                && btn39.isSelected() && btn40.isSelected()) {
            
            btnLightning.setSelected(true);
            selectAllToggle(btnLightning, 27, 32);
        } else {
            btnLightning.setSelected(false);
            btnLightning.setBackground(new Color(153, 255, 153));
        }
    }//GEN-LAST:event_btn7ActionPerformed

    private void btn8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn8ActionPerformed
        //if all lightning cases are selected activate the select all lightning toggle
        if(btn7.isSelected() && btn8.isSelected()
                && btn11.isSelected() && btn12.isSelected()
                && btn39.isSelected() && btn40.isSelected()) {
            
            btnLightning.setSelected(true);
            selectAllToggle(btnLightning, 27, 32);
        } else {
            btnLightning.setSelected(false);
            btnLightning.setBackground(new Color(153, 255, 153));
        }
    }//GEN-LAST:event_btn8ActionPerformed

    private void btn11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn11ActionPerformed
        //if all lightning cases are selected activate the select all lightning toggle
        if(btn7.isSelected() && btn8.isSelected()
                && btn11.isSelected() && btn12.isSelected()
                && btn39.isSelected() && btn40.isSelected()) {
            
            btnLightning.setSelected(true);
            selectAllToggle(btnLightning, 27, 32);
        } else {
            btnLightning.setSelected(false);
            btnLightning.setBackground(new Color(153, 255, 153));
        }
    }//GEN-LAST:event_btn11ActionPerformed

    private void btn12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn12ActionPerformed
        //if all lightning cases are selected activate the select all lightning toggle
        if(btn7.isSelected() && btn8.isSelected()
                && btn11.isSelected() && btn12.isSelected()
                && btn39.isSelected() && btn40.isSelected()) {
            
            btnLightning.setSelected(true);
            selectAllToggle(btnLightning, 27, 32);
        } else {
            btnLightning.setSelected(false);
            btnLightning.setBackground(new Color(153, 255, 153));
        }
    }//GEN-LAST:event_btn12ActionPerformed

    private void btn39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn39ActionPerformed
        //if all lightning cases are selected activate the select all lightning toggle
        if(btn7.isSelected() && btn8.isSelected()
                && btn11.isSelected() && btn12.isSelected()
                && btn39.isSelected() && btn40.isSelected()) {
            
            btnLightning.setSelected(true);
            selectAllToggle(btnLightning, 27, 32);
        } else {
            btnLightning.setSelected(false);
            btnLightning.setBackground(new Color(153, 255, 153));
        }
    }//GEN-LAST:event_btn39ActionPerformed

    private void btn40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn40ActionPerformed
        //if all lightning cases are selected activate the select all lightning toggle
        if(btn7.isSelected() && btn8.isSelected()
                && btn11.isSelected() && btn12.isSelected()
                && btn39.isSelected() && btn40.isSelected()) {
            
            btnLightning.setSelected(true);
            selectAllToggle(btnLightning, 27, 32);
        } else {
            btnLightning.setSelected(false);
            btnLightning.setBackground(new Color(153, 255, 153));
        }
    }//GEN-LAST:event_btn40ActionPerformed

    private void btn47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn47ActionPerformed
        //if all L cases are selected activate the select all L toggle
        if(btn47.isSelected() && btn48.isSelected()
                && btn49.isSelected() && btn50.isSelected()
                && btn53.isSelected() && btn54.isSelected()) {
            
            btnL.setSelected(true);
            selectAllToggle(btnL, 33, 38);
        } else {
            btnL.setSelected(false);
            btnL.setBackground(new Color(153, 255, 153));
        }
    }//GEN-LAST:event_btn47ActionPerformed

    private void btn48ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn48ActionPerformed
        //if all L cases are selected activate the select all L toggle
        if(btn47.isSelected() && btn48.isSelected()
                && btn49.isSelected() && btn50.isSelected()
                && btn53.isSelected() && btn54.isSelected()) {
            
            btnL.setSelected(true);
            selectAllToggle(btnL, 33, 38);
        } else {
            btnL.setSelected(false);
            btnL.setBackground(new Color(153, 255, 153));
        }
    }//GEN-LAST:event_btn48ActionPerformed

    private void btn49ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn49ActionPerformed
        //if all L cases are selected activate the select all L toggle
        if(btn47.isSelected() && btn48.isSelected()
                && btn49.isSelected() && btn50.isSelected()
                && btn53.isSelected() && btn54.isSelected()) {
            
            btnL.setSelected(true);
            selectAllToggle(btnL, 33, 38);
        } else {
            btnL.setSelected(false);
            btnL.setBackground(new Color(153, 255, 153));
        }
    }//GEN-LAST:event_btn49ActionPerformed

    private void btn50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn50ActionPerformed
        //if all L cases are selected activate the select all L toggle
        if(btn47.isSelected() && btn48.isSelected()
                && btn49.isSelected() && btn50.isSelected()
                && btn53.isSelected() && btn54.isSelected()) {
            
            btnL.setSelected(true);
            selectAllToggle(btnL, 33, 38);
        } else {
            btnL.setSelected(false);
            btnL.setBackground(new Color(153, 255, 153));
        }
    }//GEN-LAST:event_btn50ActionPerformed

    private void btn53ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn53ActionPerformed
        //if all L cases are selected activate the select all L toggle
        if(btn47.isSelected() && btn48.isSelected()
                && btn49.isSelected() && btn50.isSelected()
                && btn53.isSelected() && btn54.isSelected()) {
            
            btnL.setSelected(true);
            selectAllToggle(btnL, 33, 38);
        } else {
            btnL.setSelected(false);
            btnL.setBackground(new Color(153, 255, 153));
        }
    }//GEN-LAST:event_btn53ActionPerformed

    private void btn54ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn54ActionPerformed
        //if all L cases are selected activate the select all L toggle
        if(btn47.isSelected() && btn48.isSelected()
                && btn49.isSelected() && btn50.isSelected()
                && btn53.isSelected() && btn54.isSelected()) {
            
            btnL.setSelected(true);
            selectAllToggle(btnL, 33, 38);
        } else {
            btnL.setSelected(false);
            btnL.setBackground(new Color(153, 255, 153));
        }
    }//GEN-LAST:event_btn54ActionPerformed

    private void btn9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn9ActionPerformed
        //if all Fish cases are selected activate the select all Fish toggle
        if(btn9.isSelected() && btn10.isSelected()
                && btn35.isSelected() && btn37.isSelected()) {
            
            btnFish.setSelected(true);
            selectAllToggle(btnFish, 39, 42);
        } else {
            btnFish.setSelected(false);
            btnFish.setBackground(new Color(153, 255, 153));
        }
    }//GEN-LAST:event_btn9ActionPerformed

    private void btn10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn10ActionPerformed
        //if all Fish cases are selected activate the select all Fish toggle
        if(btn9.isSelected() && btn10.isSelected()
                && btn35.isSelected() && btn37.isSelected()) {
            
            btnFish.setSelected(true);
            selectAllToggle(btnFish, 39, 42);
        } else {
            btnFish.setSelected(false);
            btnFish.setBackground(new Color(153, 255, 153));
        }
    }//GEN-LAST:event_btn10ActionPerformed

    private void btn35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn35ActionPerformed
        //if all Fish cases are selected activate the select all Fish toggle
        if(btn9.isSelected() && btn10.isSelected()
                && btn35.isSelected() && btn37.isSelected()) {
            
            btnFish.setSelected(true);
            selectAllToggle(btnFish, 39, 42);
        } else {
            btnFish.setSelected(false);
            btnFish.setBackground(new Color(153, 255, 153));
        }
    }//GEN-LAST:event_btn35ActionPerformed

    private void btn37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn37ActionPerformed
        //if all Fish cases are selected activate the select all Fish toggle
        if(btn9.isSelected() && btn10.isSelected()
                && btn35.isSelected() && btn37.isSelected()) {
            
            btnFish.setSelected(true);
            selectAllToggle(btnFish, 39, 42);
        } else {
            btnFish.setSelected(false);
            btnFish.setBackground(new Color(153, 255, 153));
        }
    }//GEN-LAST:event_btn37ActionPerformed

    private void btn36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn36ActionPerformed
        //if all W cases are selected activate the select all W toggle
        if(btn36.isSelected() && btn38.isSelected()) {
            
            btnW.setSelected(true);
            selectAllToggle(btnW, 43, 44);
        } else {
            btnW.setSelected(false);
            btnW.setBackground(new Color(153, 255, 153));
        }
    }//GEN-LAST:event_btn36ActionPerformed

    private void btn38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn38ActionPerformed
        //if all W cases are selected activate the select all W toggle
        if(btn36.isSelected() && btn38.isSelected()) {
            
            btnW.setSelected(true);
            selectAllToggle(btnW, 43, 44);
        } else {
            btnW.setSelected(false);
            btnW.setBackground(new Color(153, 255, 153));
        }
    }//GEN-LAST:event_btn38ActionPerformed

    private void btn34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn34ActionPerformed
        //if all C cases are selected activate the select all C toggle
        if(btn34.isSelected() && btn46.isSelected()) {
            
            btnC.setSelected(true);
            selectAllToggle(btnC, 49, 50);
        } else {
            btnC.setSelected(false);
            btnC.setBackground(new Color(153, 255, 153));
        }
    }//GEN-LAST:event_btn34ActionPerformed

    private void btn46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn46ActionPerformed
        //if all C cases are selected activate the select all C toggle
        if(btn34.isSelected() && btn46.isSelected()) {
            
            btnC.setSelected(true);
            selectAllToggle(btnC, 49, 50);
        } else {
            btnC.setSelected(false);
            btnC.setBackground(new Color(153, 255, 153));
        }
    }//GEN-LAST:event_btn46ActionPerformed

    private void btn28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn28ActionPerformed
        //if all ACO cases are selected activate the select all ACO toggle
        if(btn28.isSelected() && btn57.isSelected()) {
            
            btnACO.setSelected(true);
            selectAllToggle(btnACO, 55, 56);
        } else {
            btnACO.setSelected(false);
            btnACO.setBackground(new Color(153, 255, 153));
        }
    }//GEN-LAST:event_btn28ActionPerformed

    private void btn57ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn57ActionPerformed
        //if all ACO cases are selected activate the select all ACO toggle
        if(btn28.isSelected() && btn57.isSelected()) {
            
            btnACO.setSelected(true);
            selectAllToggle(btnACO, 55, 56);
        } else {
            btnACO.setSelected(false);
            btnACO.setBackground(new Color(153, 255, 153));
        }
    }//GEN-LAST:event_btn57ActionPerformed

    private void btn13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn13ActionPerformed
        //if all Knight cases are selected activate the select all Knight toggle
        if(btn13.isSelected() && btn14.isSelected()
                && btn15.isSelected() && btn16.isSelected()) {
            
            btnKnightMove.setSelected(true);
            selectAllToggle(btnKnightMove, 45, 48);
        } else {
            btnKnightMove.setSelected(false);
            btnKnightMove.setBackground(new Color(153, 255, 153));
        }
    }//GEN-LAST:event_btn13ActionPerformed

    private void btn14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn14ActionPerformed
        //if all Knight cases are selected activate the select all Knight toggle
        if(btn13.isSelected() && btn14.isSelected()
                && btn15.isSelected() && btn16.isSelected()) {
            
            btnKnightMove.setSelected(true);
            selectAllToggle(btnKnightMove, 45, 48);
        } else {
            btnKnightMove.setSelected(false);
            btnKnightMove.setBackground(new Color(153, 255, 153));
        }
    }//GEN-LAST:event_btn14ActionPerformed

    private void btn15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn15ActionPerformed
        //if all Knight cases are selected activate the select all Knight toggle
        if(btn13.isSelected() && btn14.isSelected()
                && btn15.isSelected() && btn16.isSelected()) {
            
            btnKnightMove.setSelected(true);
            selectAllToggle(btnKnightMove, 45, 48);
        } else {
            btnKnightMove.setSelected(false);
            btnKnightMove.setBackground(new Color(153, 255, 153));
        }
    }//GEN-LAST:event_btn15ActionPerformed

    private void btn16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn16ActionPerformed
        //if all Knight cases are selected activate the select all Knight toggle
        if(btn13.isSelected() && btn14.isSelected()
                && btn15.isSelected() && btn16.isSelected()) {
            
            btnKnightMove.setSelected(true);
            selectAllToggle(btnKnightMove, 45, 48);
        } else {
            btnKnightMove.setSelected(false);
            btnKnightMove.setBackground(new Color(153, 255, 153));
        }
    }//GEN-LAST:event_btn16ActionPerformed

    private void btn29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn29ActionPerformed
        //if all Awkward cases are selected activate the select all Awkward toggle
        if(btn29.isSelected() && btn30.isSelected()
                && btn41.isSelected() && btn42.isSelected()) {
            
            btnAwkward.setSelected(true);
            selectAllToggle(btnAwkward, 51, 54);
        } else {
            btnAwkward.setSelected(false);
            btnAwkward.setBackground(new Color(153, 255, 153));
        }
    }//GEN-LAST:event_btn29ActionPerformed

    private void btn30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn30ActionPerformed
        //if all Awkward cases are selected activate the select all Awkward toggle
        if(btn29.isSelected() && btn30.isSelected()
                && btn41.isSelected() && btn42.isSelected()) {
            
            btnAwkward.setSelected(true);
            selectAllToggle(btnAwkward, 51, 54);
        } else {
            btnAwkward.setSelected(false);
            btnAwkward.setBackground(new Color(153, 255, 153));
        }
    }//GEN-LAST:event_btn30ActionPerformed

    private void btn41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn41ActionPerformed
        //if all Awkward cases are selected activate the select all Awkward toggle
        if(btn29.isSelected() && btn30.isSelected()
                && btn41.isSelected() && btn42.isSelected()) {
            
            btnAwkward.setSelected(true);
            selectAllToggle(btnAwkward, 51, 54);
        } else {
            btnAwkward.setSelected(false);
            btnAwkward.setBackground(new Color(153, 255, 153));
        }
    }//GEN-LAST:event_btn41ActionPerformed

    private void btn42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn42ActionPerformed
        //if all Awkward cases are selected activate the select all Awkward toggle
        if(btn29.isSelected() && btn30.isSelected()
                && btn41.isSelected() && btn42.isSelected()) {
            
            btnAwkward.setSelected(true);
            selectAllToggle(btnAwkward, 51, 54);
        } else {
            btnAwkward.setSelected(false);
            btnAwkward.setBackground(new Color(153, 255, 153));
        }
    }//GEN-LAST:event_btn42ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btn1;
    private javax.swing.JToggleButton btn10;
    private javax.swing.JToggleButton btn11;
    private javax.swing.JToggleButton btn12;
    private javax.swing.JToggleButton btn13;
    private javax.swing.JToggleButton btn14;
    private javax.swing.JToggleButton btn15;
    private javax.swing.JToggleButton btn16;
    private javax.swing.JToggleButton btn17;
    private javax.swing.JToggleButton btn18;
    private javax.swing.JToggleButton btn19;
    private javax.swing.JToggleButton btn2;
    private javax.swing.JToggleButton btn20;
    private javax.swing.JToggleButton btn21;
    private javax.swing.JToggleButton btn22;
    private javax.swing.JToggleButton btn23;
    private javax.swing.JToggleButton btn24;
    private javax.swing.JToggleButton btn25;
    private javax.swing.JToggleButton btn26;
    private javax.swing.JToggleButton btn27;
    private javax.swing.JToggleButton btn28;
    private javax.swing.JToggleButton btn29;
    private javax.swing.JToggleButton btn3;
    private javax.swing.JToggleButton btn30;
    private javax.swing.JToggleButton btn31;
    private javax.swing.JToggleButton btn32;
    private javax.swing.JToggleButton btn33;
    private javax.swing.JToggleButton btn34;
    private javax.swing.JToggleButton btn35;
    private javax.swing.JToggleButton btn36;
    private javax.swing.JToggleButton btn37;
    private javax.swing.JToggleButton btn38;
    private javax.swing.JToggleButton btn39;
    private javax.swing.JToggleButton btn4;
    private javax.swing.JToggleButton btn40;
    private javax.swing.JToggleButton btn41;
    private javax.swing.JToggleButton btn42;
    private javax.swing.JToggleButton btn43;
    private javax.swing.JToggleButton btn44;
    private javax.swing.JToggleButton btn45;
    private javax.swing.JToggleButton btn46;
    private javax.swing.JToggleButton btn47;
    private javax.swing.JToggleButton btn48;
    private javax.swing.JToggleButton btn49;
    private javax.swing.JToggleButton btn5;
    private javax.swing.JToggleButton btn50;
    private javax.swing.JToggleButton btn51;
    private javax.swing.JToggleButton btn52;
    private javax.swing.JToggleButton btn53;
    private javax.swing.JToggleButton btn54;
    private javax.swing.JToggleButton btn55;
    private javax.swing.JToggleButton btn56;
    private javax.swing.JToggleButton btn57;
    private javax.swing.JToggleButton btn6;
    private javax.swing.JToggleButton btn7;
    private javax.swing.JToggleButton btn8;
    private javax.swing.JToggleButton btn9;
    private javax.swing.JToggleButton btnACO;
    private javax.swing.JToggleButton btnAll;
    private javax.swing.JToggleButton btnAwkward;
    private javax.swing.JButton btnBack;
    private javax.swing.JToggleButton btnC;
    private javax.swing.JToggleButton btnDot;
    private javax.swing.JToggleButton btnFish;
    private javax.swing.JToggleButton btnKnightMove;
    private javax.swing.JToggleButton btnL;
    private javax.swing.JToggleButton btnLightning;
    private javax.swing.JToggleButton btnLine;
    private javax.swing.JToggleButton btnOCLL;
    private javax.swing.JToggleButton btnP;
    private javax.swing.JButton btnRecap;
    private javax.swing.JToggleButton btnSquare;
    private javax.swing.JButton btnStart;
    private javax.swing.JToggleButton btnT;
    private javax.swing.JToggleButton btnW;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnl1;
    // End of variables declaration//GEN-END:variables
}
