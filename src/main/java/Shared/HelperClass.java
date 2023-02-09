/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Shared;

/**
 *
 * @author felipearango
 */
public class HelperClass {
    
    public int TryToParse(String text){
        
        try {
            return Integer.parseInt( text );
        } catch (NumberFormatException e) {
         return 0;
        }
    }
    
}
