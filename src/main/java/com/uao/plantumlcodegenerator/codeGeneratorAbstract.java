/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uao.plantumlcodegenerator;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author santiago
 */
public abstract class codeGeneratorAbstract implements codeGeneratorInterface {
    protected String plantumlText;
    protected Path filePath = Paths.get(System.getProperty("user.home"), "Documents", "Programas generados");
    protected StringBuilder generatedCode;
    
    public codeGeneratorAbstract(String plantumlText){
    this.plantumlText = plantumlText;}
}
