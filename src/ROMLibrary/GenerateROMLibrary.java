/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ROMLibrary;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import romscleaner.Game;
import static romscleaner.RomsCleaner.romsDirectory;

/**
 * GenerateROMLibrary.java
 * 
 * Todo
 *
 * @author Christophe Leblond
 */
public class GenerateROMLibrary {

    public static String romsDirectory = "C:\\Users\\Christophe\\Documents\\snes";       
    
    private static String filespattern = ".smc";
    
    public static void main(String[] args) throws IOException{
        
        HashMap<String, Game> games = romscleaner.RomsCleaner.readGamesInDirectory(romsDirectory);
        List<Game> gameList = new ArrayList<>(games.values());
        Collections.sort(gameList);
        
        File html = generateHTMLList(gameList, romsDirectory +  "\\index.html");
    }

    private static File generateHTMLList(List<Game> games, String htmlfile) 
    throws IOException {
        
        File htmlFile = new File(htmlfile);
        
        FileWriter writer = new FileWriter(htmlFile);
        
        writer.write("<!DOCTYPE html>\n<html>");
        writer.write("<head>");
        writer.write("<title>ROM Set list</title>");
        writer.write("<meta charset=\"UTF-8\">");
        writer.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        writer.write("<script\n" +
"			  src=\"https://code.jquery.com/jquery-3.1.0.min.js\"\n" +
"			  integrity=\"sha256-cCueBR6CsyA4/9szpPfrX3s49M9vUU5BgtiJj06wt/s=\"\n" +
"			  crossorigin=\"anonymous\"></script>");
        writer.write("<script src=\"gamesdb.js\" type=\"text/javascript\"></script>");
        writer.write("</head>");
        writer.write("<body>");
        writer.write("<h2>Roms</h2>");
        
        writer.write("<table>");
        writer.write("    <thead>");
        writer.write("        <tr>");
        writer.write("            <td>Box</td>");
        writer.write("            <td>Title</td>");
        writer.write("            <td>Roms</td>");
        writer.write("        </tr>");
        writer.write("    </thead>");
        writer.write("    <tbody>");
        for(Game g : games){
            writer.write("    <tr>");
            writer.write("            <td class=\"gameIcon\"></td>");
            writer.write("            <td class=\"gameTitle\">" + g.getName() + "</td>");
            writer.write("            <td>" + g.getRoms().toString().replace("\n", "<br/>") + "</td>");
            writer.write("    </tr>");
        }        
        writer.write("    </tbody>");
        writer.write("</table>");
        writer.write("</body>");
        writer.write("</html>");
       
        writer.close();
        
        return htmlFile;
    }
}
