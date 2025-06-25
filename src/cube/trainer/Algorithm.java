/*
 * Morgan Yeh
 * 2024/06/19
 * Class to represent an Algorithm object
 */
package cube.trainer;

import java.util.Arrays;

/**
 *
 * @author morgan
 */
public class Algorithm {
    //attributes
    private String set; //alg set the case belongs to
    private String group; //alg subgroup
    private String name; //name of case
    private String[] setups; //setup moves for case
    private String alg; //algorithm
    private String imgFile; //image of case
    private boolean learned; //whether case has been learned
    private boolean selected; //whether case has been selected for trainer
    
    /**
     * Primary Constructor, creates Algorithm object with default attributes
     */
    public Algorithm() {
        set = "";
        group = "";
        name = "";
        setups = new String[4];
        alg = "";
        imgFile = "";
        learned = false; //default to not learned
        selected = false; //default to not selected
    }
    
    /**
     * Secondary constructor, creates Algorithm object with attributes set
     * @param set - alg set the case belongs to
     * @param group - alg subgroup
     * @param name - name of case
     * @param setups - array of moves to setup to case
     * @param alg - moves to solve case
     * @param imgFile - file name for image of case
     * @param learned - boolean for whether alg has been learned
     */
    public Algorithm(String set, String group, String name, String[] setups, String alg, String imgFile, boolean learned) {
        this(); //constructor chaining
        this.set = set;
        this.group = group;
        this.name = name;
        this.setups = setups;
        this.alg = alg;
        this.imgFile = imgFile;
        this.learned = learned;
    }
    
    /**
     * Tertiary constructor, creates Algorithm object with all attributes set
     * @param set - alg set the case belongs to
     * @param group - alg subgroup
     * @param name - name of case
     * @param setups - array of moves to setup to case
     * @param alg - moves to solve case
     * @param imgFile - file name for image of case
     * @param learned - boolean for whether alg has been learned
     * @param selected - boolean for whether alg has been selected for trainer
     */
    public Algorithm(String set, String group, String name, String[] setups, String alg, String imgFile, boolean learned, boolean selected) {
        this(set, group, name, setups, alg, imgFile, learned);
        this.selected = selected;
        
    }

    /**
     * getter for set attribute
     * @return - alg set that case belongs to
     */
    public String getSet() {
        return set;
    }

    /**
     * setter for set attribute
     * @param set - alg set that case belongs to
     */
    public void setSet(String set) {
        this.set = set;
    }

    /**
     * getter for group attribute
     * @return - group that alg belongs to
     */
    public String getGroup() {
        return group;
    }

    /**
     * setter for group attribute
     * @param group - group to set alg to
     */
    public void setGroup(String group) {
        this.group = group;
    }

    /**
     * getter for name attribute
     * @return - name of alg/case
     */
    public String getName() {
        return name;
    }

    /**
     * setter for name attribute
     * @param name - name of alg to change to
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getter for setup moves
     * @return - moves to set up to alg (used for trainer)
     */
    public String getSetup() {
        String moves = "";
        //generate 2 random numbers
        int rNum1 = (int)(Math.random()*setups.length); //which index in setups array to use
        int rNum2 = (int)(Math.random()*4) + 1; //number to decide AUF
        
        //determine which AUF to apply to cube
        if(rNum2 == 1) {
            moves = setups[rNum1]; //no AUF
        } else if (rNum2 == 2) {
            moves = setups[rNum1] + " U";//U AUF
        } else if (rNum2 == 3) {
            moves = setups[rNum1] + " U'";//U' AUF
        } else {
            moves = setups[rNum1] + " U2";//U2 AUF
        }
        
        return moves;
    }
    
    /**
     * getter for array of setup moves
     * @return - array of 4 scrambles
     */
    public String[] getSetupArray() {
        return setups;
    }

    /**
     * setter for setups variables
     * @param setups - String array of potential setup moves
     */
    public void setSetupArray(String[] setups) {
        this.setups = setups;
    }

    /**
     * getter for alg
     * @return - string of moves to solve case (the algorithm)
     */
    public String getAlg() {
        return alg;
    }

    /**
     * setter for alg
     * @param alg - alg to solve case to be set to
     */
    public void setAlg(String alg) {
        this.alg = alg;
    }

    /**
     * getter for img
     * @return - image file of case/algorithm
     */
    public String getImg() {
        return imgFile;
    }

    /**
     * setter for img
     * @param imgFile - image of alg to be set to
     */
    public void setImg(String imgFile) {
        this.imgFile = imgFile;
    }

    /**
     * getter for learned variable
     * @return - whether alg has been learned
     */
    public boolean isLearned() {
        return learned;
    }

    /**
     * setter for learned variable
     * @param learned - status of algorithm to be set to
     */
    public void setLearned(boolean learned) {
        this.learned = learned;
    }

    /**
     * getter for selected variable
     * @return - whether algorithm has been selected for trainer
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * setter for selected variable
     * @param selected - selection status of algorithm to be set to
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    
    /**
     * clone method for Algorithm
     * @return - exact copy of existing Algorithm object
     */
    public Algorithm clone() {
        return new Algorithm(set, group, name, setups, alg, imgFile, learned, selected);
    }

    /**
     * equals method for Algorithm
     * @param other - another Algorithm object to compare with
     * @return - whether the objects are the same
     */
    public boolean equals(Algorithm other) {
        if(!(other == null)) {
            return  (this.set.equals(other.set)) &&
                    (this.group.equals(other.group)) &&
                    (this.name.equals(other.name)) &&
                    (Arrays.equals(this.setups, other.setups)) &&
                    (this.alg.equals(other.alg)) &&
                    (this.imgFile.equals(other.imgFile)) &&
                    (this.learned == other.learned) &&
                    (this.selected == other.selected);
                
        } else {
            return false;
        }
    }

    @Override
    /**
     * toString method for Algorithm
     * return - string representation of Algorithm object
     */
    public String toString() {
        return "Algorithm{" + "set=" + set + ", group=" + group + ", name=" + name + ", setups=" + "{" + setups[0] + ", " + setups[1] + ", " + setups[2] + ", " + setups[3] + "}" + ", alg=" + alg + ", imgFile=" + imgFile + ", learned=" + learned + ", selected=" + selected + '}';
    }
}
