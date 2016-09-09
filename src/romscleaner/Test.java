/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package romscleaner;

/**
 * Test.java
 * 
 * Todo
 *
 * @author Christophe Leblond
 */
public class Test {

    public static void main(String[] args){
        String rom = "Zelda no Densetsu - Kamigami no Triforce (J) (V1.0) [b1].smc";
        
        String tag = "[b]";
        
        System.out.println(rom.contains(tag));
    }
}
