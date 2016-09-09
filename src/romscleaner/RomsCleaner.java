/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package romscleaner;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Christophe Leblond
 */
public class RomsCleaner {

    public static String romsDirectory = "C:\\Users\\Christophe\\Documents\\snes";
    
    private static HashMap<String, Game> games = null;
    
    private static String filespattern = ".smc";
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        games = readGamesInDirectory(romsDirectory);
        
        List<Game> gameList = new ArrayList<>(games.values());
        Collections.sort(gameList);
        
        /*for(Game g : gameList){
            System.out.println(g);
        }*/
        
        System.out.println(gameList.size() + " jeux trouvés!");
        /*for(Game g : games){
            System.out.println("- " + g.getName() + " => " + g.getRoms().size() + " roms");
        }*/
        
        cleanGameList(gameList);
    }
    
    private static List<Tags> readTags(String filename){
        List<Tags> romTags = new ArrayList<>();
        
        for(int i = 0; i < Tags.values().length; i++){
            Tags t = Tags.values()[i];
            
            if(filename.contains(t.getSigle())){
                romTags.add(t);
            }
        }
        
        return romTags;
    }
    
    private static ROM readROMFromFilename(File file){
        
        ROM rom = new ROM();
        rom.setFile(file);
        
        rom.setTags(readTags(file.getName()));
        
        return rom;
    }
    
    private static String extractGameTitle(String filename){
        
        int indexP = filename.indexOf('(');
        int indexC = filename.indexOf('[');
        
        int index = indexP;
        if(indexC >= 0 && indexC < indexP){
            index = indexC;
        }

        String title = filename;
        if(index >= 0){
            title = filename.substring(0, index);    
        }
        
        title = title.toLowerCase().replace(".smc", "");
        
        return title;
    }

    private static HashMap<String, Game> readGamesInDirectory(String romsDirectory) {
        
        HashMap<String, Game> games = new HashMap<String, Game>();
        
        File dir = new File(romsDirectory);
        File[] files = dir.listFiles(new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(filespattern);
            }
        });
        
        List<ROM> roms = new ArrayList<>();
        for(int i = 0; i < files.length; i++){
            String gameTitle = extractGameTitle(files[i].getName());
            
            Game game = games.get(gameTitle);
            if(game == null){
                game = new Game(gameTitle);
                games.put(gameTitle, game);
            }
            
            ROM rom = readROMFromFilename(files[i]);
            if(rom != null){
                game.addRom(rom);
            }
        }
        
        return games;
    }

    private static void cleanGameList(List<Game> gameList) {
        for(Game g : gameList){
            if(g.getRoms().size() == 1){
                ROM rom = g.getRoms().get(0);
                if(rom.score() == 0){
                    g.deleteRom(rom);
                    System.out.println("ATTENTION: " + g.getName() + " n'a plus de ROM!");
                    
                }else{
                    g.selectRom(rom);
                }
                
            }else if(g.getRoms().size() > 1){
                ROM rom = g.getRoms().get(0);
                if(rom.score() == 0){
                    g.deleteRom(rom);
                    System.out.println("ATTENTION: " + g.getName() + " n'a plus de ROM!");
                    
                }else{
                    g.selectRom(rom);
                    System.out.println(g.getName() + " : " + g.getMain());
                }
                
                for(int i = 1; i < g.getRoms().size(); i++){
                    g.deleteRom(g.getRoms().get(i));
                }
                
            }else{
                System.err.println("ATTENTION: " + g.getName() + " n'a pas de ROM !");
            }
        }
        
        // Déplace les fichiers
        for(Game g : gameList){
            File dir = new File(romsDirectory + "\\" + g.getName());
            
            if(!g.getDeleted().isEmpty() && !dir.exists()){
                dir.mkdirs();
            }
            
            for(ROM r : g.getDeleted()){
                try {
                    Files.move(r.getFile().toPath(), dir.toPath());
                } catch (IOException ex) {
                    Logger.getLogger(RomsCleaner.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
