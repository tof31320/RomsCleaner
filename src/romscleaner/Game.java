/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package romscleaner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Game.java
 * 
 * Todo
 *
 * @author Christophe Leblond
 */
public class Game implements Comparable<Game>{

    private String name = null;
    
    private List<ROM> roms = null;
    
    private ROM main = null;
    
    private List<ROM> deleted = null;
    
    public Game(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ROM> getRoms() {
        if(roms == null){
            roms = new ArrayList<>();
        }
        return roms;
    }

    public void setRoms(List<ROM> roms) {
        this.roms = roms;
    }
    
    public void addRom(ROM rom){
        getRoms().add(rom);
        
        rom.setGame(this);
        
        sortRoms();
    }

    public ROM getMain() {
        return main;
    }

    public void setMain(ROM main) {
        this.main = main;
    }

    public List<ROM> getDeleted() {
        if(deleted == null){
            deleted = new ArrayList<>();
        }
        return deleted;
    }

    public void setDeleted(List<ROM> deleted) {
        this.deleted = deleted;
    }
    
    public void deleteRom(ROM rom){
        if(main != null && main.equals(rom)){
            main = null;
        }
        if(!getDeleted().contains(rom)){
            getDeleted().add(rom);
        }
    }
    
    public void selectRom(ROM rom){
        if(main != null){
            deleteRom(main);
        }
        
        main = rom;
        
        if(getDeleted().contains(rom)){
            getDeleted().remove(rom);
        }
    }

    @Override
    public int compareTo(Game g) {
        return getName().compareToIgnoreCase(g.getName());
    }
    
    public void sortRoms(){
        Collections.sort(getRoms());
    }

    @Override
    public String toString() {
        String str = getName() + ": " + getRoms().size() + " rom(s):\n";
        for(ROM r : getRoms()){
            str += "  - " + r + "\n";
        }
        return str;
    }
}
