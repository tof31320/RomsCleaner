/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package romscleaner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * ROM.java
 * 
 * Todo
 *
 * @author Christophe Leblond
 */
public class ROM implements Comparable<ROM> {
    
    private Game game = null;
    
    private File file = null;
    
    private List<Tags> tags = null;
    
    private int score = -1;

    public ROM() {
    }

    public ROM(Game game, String filename){
        this.game = game;
        this.file = new File(filename);
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public List<Tags> getTags() {
        if(tags == null){
            tags = new ArrayList<>();
        }
        return tags;
    }

    public void setTags(List<Tags> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        String str = "";
        for(Tags t : getTags()){
            str += t.getLabel() + " ";
        }
        return getFile().getName() + " : " + str + " => " + score();
    }
    
    public int score(){
        if(score < 0){
            score = 0;
            for(Tags t : getTags()){
                score += t.getScore();
            }
        }
        return score;
    }

    @Override
    public int compareTo(ROM r) {
        
        if(r.score() == score()){
            return getTags().size() - r.getTags().size();
            
        }else{
            return r.score() - score();
        }
    }
}
