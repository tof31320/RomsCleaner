/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package romscleaner;

/**
 * Tags.java
 * 
 * Todo
 *
 * @author Christophe Leblond
 */
public enum Tags {

    EUROPE("(E)", "[Europe]", 5),
    EUROPE_USA("(UE)", "[Europe/USA]", 4),
    USA("(U)", "[USA]", 4),
    BETA("(Beta)", "[Beta]"),
    PUBLIC_DOMAIN("(PD)", "[Public Domain}"),
    NEW_TRANSLATION("[T+Fre", "[New Traduction FR]", 6),
    OLD_TRANSLATION("[T-Fre", "[Old Traduction FR]", 5),
    JAPAN("(J)", "[Japan]"),
    BEST("[!]", "[Best]", 6),
    BAD("[b", "[Bad]"),
    ALTERNATE("[a]", "[Alternate]"),
    PIRATE("[p]", "[Pirate]"),
    TRAINED("[t", "[Trained]"),
    FIXED("[f", "[Fixed]"),
    HACK("[h", "[Hack]"),
    OVERDUMP("[o]", "[Overdump]"),
    UNKOWN_YEAR("[-]", "[Unknow year]"),
    UNLICENSED("[Unl]", "[Unlicensed]")
    ;
    
    String sigle = null;
    
    String label = null;
    
    int score = 0;

    Tags(String sigle, String label){
        this.sigle = sigle;
        this.label = label;
    }

    private Tags(String sigle, String label, int score) {
        this.sigle = sigle;
        this.label = label;
        this.score = score;
    }
    
    public String getSigle() {
        return sigle;
    }

    public void setSigle(String sigle) {
        this.sigle = sigle;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
