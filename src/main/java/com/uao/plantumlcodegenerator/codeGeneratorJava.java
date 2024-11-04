/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uao.plantumlcodegenerator;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author santiago
 */
public class codeGeneratorJava extends codeGeneratorAbstract{
    private final Path fileName = filePath.resolve("generatedCode.java");
    private final Pattern patternClass = Pattern.compile("(class|interface|abstract)\\s+(\\w+)(\\s*\\{[\\s\\S]*?\\})");
    private final Pattern patternAttribute = Pattern.compile("([\\+\\-\\#])\\s*(\\w+\\s*<.*?>?|\\w+)\\s+(\\w+);?"); // Captura modificador, tipo y nombre
    public String newFileName;
    private final Pattern patternMethod = Pattern.compile("([\\+\\-\\#])\\s*(\\w+\\s*<.*?>?|\\w+)\\s+(\\w+)\\s*\\((.*?)\\)"); // Captura modificador, tipo de retorno, nombre y parámetros
    String pathFile;
    public codeGeneratorJava(String plantumlText){
        super(plantumlText);}

    @Override
    public void generateCode() {
    String text = plantumlText.trim();
    this.generatedCode = new StringBuilder();

    Matcher matcherClass = patternClass.matcher(text);
    Map<String, List<String>> classDetails = new LinkedHashMap<>();

    while (matcherClass.find()) {
        String tipo = matcherClass.group(1);
        String nombreClase = matcherClass.group(2);
        String cuerpoClase = matcherClass.group(3);

        System.out.println("Clase encontrada: " + tipo + " " + nombreClase);

        List<String> detallesClase = new ArrayList<>();

        // Buscar atributos dentro del cuerpo de la clase
        Matcher matcherAttribute = patternAttribute.matcher(cuerpoClase);
        while (matcherAttribute.find()) {
            String modificador = matcherAttribute.group(1); // Modificador de acceso
            String tipoDato = matcherAttribute.group(2); // Tipo de dato
            String nombreAtributo = matcherAttribute.group(3); // Nombre del atributo
            String mod = modificadorToJava(modificador);
            detallesClase.add(mod + tipoDato + " " + nombreAtributo + ";");
            System.out.println("Atributo encontrado: " + modificador + " " + tipoDato + " " + nombreAtributo);
        }

        // Buscar métodos dentro del cuerpo de la clase
        Matcher matcherMethod = patternMethod.matcher(cuerpoClase);
        while (matcherMethod.find()) {
            String modificador = matcherMethod.group(1); // Modificador de acceso
            String tipoRetorno = matcherMethod.group(2); // Tipo de retorno
            String nombreMetodo = matcherMethod.group(3); // Nombre del método
            String parametros = matcherMethod.group(4); // Parámetros del método
            String mod = modificadorToJava(modificador);
            detallesClase.add(mod + tipoRetorno + " " + nombreMetodo + "(" + parametros + ") {}");
            System.out.println("Método encontrado: " + modificador + " " + tipoRetorno + " " + nombreMetodo + "(" + parametros + ")");
        }

        classDetails.put(nombreClase, detallesClase);
    }

    for (Map.Entry<String, List<String>> entry : classDetails.entrySet()) {
        String nombreClase = entry.getKey();
        List<String> detalles = entry.getValue();

        generatedCode.append("public class ").append(nombreClase).append(" {\n");
        for (String detalle : detalles) {
            generatedCode.append("    ").append(detalle).append("\n");
        }
        generatedCode.append("}\n\n");
    }

    storageCode();
}

    @Override
    public void storageCode() {
    try {
        // Crea la carpeta si no existe
        if (!Files.exists(filePath)) {
            Files.createDirectories(filePath);
            System.out.println("Directorio creado: " + filePath.toString());
        }

        // Generar un nuevo nombre de archivo con un número incremental
        String baseFileName = "generatedCode";
        String fileExtension = ".java";
        int fileIndex = 1;
        Path newFileName;

        do {
            newFileName = filePath.resolve(baseFileName + fileIndex + fileExtension);
            fileIndex++;
        } while (Files.exists(newFileName)); // Aumenta el índice hasta que se encuentre un nombre de archivo que no exista

        // Escribe el archivo en la carpeta
        Files.writeString(newFileName, generatedCode.toString(), StandardOpenOption.CREATE);
        System.out.println("Archivo creado en: " + newFileName.toString());
        String pathFile = newFileName.toString();

    } catch (IOException ex) {
        Logger.getLogger(codeGeneratorJava.class.getName()).log(Level.SEVERE, "Error al crear el archivo o directorio", ex);
    }
}
    
        
    private static String modificadorToJava(String modificador) {
        switch (modificador) {
            case "+":
                return "public ";
            case "-":
                return "private ";
            case "#":
                return "protected ";
            default:
                return "";
        }
    }   
}
