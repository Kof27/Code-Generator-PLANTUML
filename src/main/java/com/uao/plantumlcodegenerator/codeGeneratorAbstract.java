/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uao.plantumlcodegenerator;

/**
 * @author santiago
 */
public abstract class codeGeneratorAbstract implements codeGeneratorInterface {
    protected String plantumlText;
    public codeGeneratorAbstract(String plantumlText){
    this.plantumlText = plantumlText;}
}
