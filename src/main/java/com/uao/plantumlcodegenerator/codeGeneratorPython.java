/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uao.plantumlcodegenerator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class codeGeneratorPython extends codeGeneratorAbstract {
    private final Path fileName = filePath.resolve("generatedCode.py");
    private final Pattern patternClass = Pattern.compile("(class|interface|abstract)\\s+(\\w+)(\\s*\\{[\\s\\S]*?\\})");
    private final Pattern patternAttribute = Pattern.compile("([\\+\\-\\#])\\s*(\\w+\\s*<.*?>?|\\w+)\\s+(\\w+)\\s*;");
    private final Pattern patternMethod = Pattern.compile("([\\+\\-\\#])\\s*(\\w+\\s*<.*?>?|\\w+)\\s+(\\w+)\\s*\\((.*?)\\)");
    private String pathFile;

    public codeGeneratorPython(String plantumlText) {
        super(plantumlText);
    }

    @Override
    public void generateCode() {
        String text = plantumlText.trim();
        this.generatedCode = new StringBuilder();

        Matcher matcherClass = patternClass.matcher(text);
        Map<String, List<String>> classDetails = new LinkedHashMap<>();

        while (matcherClass.find()) {
            String classType = matcherClass.group(1);
            String className = matcherClass.group(2);
            String classBody = matcherClass.group(3);

            System.out.println("Class found: " + classType + " " + className);

            List<String> classDetailsList = new ArrayList<>();

            // Search for attributes within the class body
            Matcher matcherAttribute = patternAttribute.matcher(classBody);
            while (matcherAttribute.find()) {
                String dataType = convertType(matcherAttribute.group(2)); // Convert type to Python
                String attributeName = matcherAttribute.group(3);
                classDetailsList.add(attributeName + ": " + dataType);
                System.out.println("Attribute found: " + dataType + " " + attributeName);
            }

            // Search for methods within the class body
            Matcher matcherMethod = patternMethod.matcher(classBody);
            while (matcherMethod.find()) {
                String methodName = matcherMethod.group(3);
                String parameters = convertParameters(matcherMethod.group(4));
                String returnType = convertType(matcherMethod.group(2));
                classDetailsList.add("def " + methodName + "(self, " + parameters + ") -> " + returnType + ": pass");
                System.out.println("Method found: " + methodName + "(" + parameters + ")");
            }

            classDetails.put(className, classDetailsList);
        }

        for (Map.Entry<String, List<String>> entry : classDetails.entrySet()) {
            String className = entry.getKey();
            List<String> details = entry.getValue();

            generatedCode.append("class ").append(className).append(":\n");
            if (details.isEmpty()) {
                generatedCode.append("    pass\n");
            } else {
                for (String detail : details) {
                    generatedCode.append("    ").append(detail).append("\n");
                }
            }
            generatedCode.append("\n");
        }

        storageCode();
    }

    private String convertType(String type) {
        switch (type) {
            case "String": return "str";
            case "int": return "int";
            case "double": return "float";
            case "boolean": return "bool";
            case "List": return "List"; // Ensure `from typing import List` at the file header
            case "Map": return "Dict";  // Ensure `from typing import Dict` at the file header
            default: return "Any"; // Ensure `from typing import Any` if needed
        }
    }

    private String convertParameters(String parameters) {
        if (parameters.trim().isEmpty()) {
            return "";
        }
        String[] paramsArray = parameters.split(",");
        StringBuilder result = new StringBuilder();

        for (String param : paramsArray) {
            String[] paramParts = param.trim().split("\\s+");
            if (paramParts.length == 2) {
                String pythonType = convertType(paramParts[0]);
                String name = paramParts[1];
                result.append(name).append(": ").append(pythonType).append(", ");
            }
        }

        // Remove the last comma and space
        if (result.length() > 0) {
            result.setLength(result.length() - 2);
        }
        return result.toString();
    }

    @Override
    public void storageCode() {
        try {
            if (!Files.exists(filePath)) {
                Files.createDirectories(filePath);
                System.out.println("Directory created: " + filePath);
            }

            String baseFileName = "generatedCode";
            String fileExtension = ".py";
            int fileIndex = 1;
            Path newFileName;

            do {
                newFileName = filePath.resolve(baseFileName + fileIndex + fileExtension);
                fileIndex++;
            } while (Files.exists(newFileName));

            Files.writeString(newFileName, generatedCode.toString(), StandardOpenOption.CREATE);
            System.out.println("File created at: " + newFileName);
            this.pathFile = newFileName.toString();

        } catch (IOException ex) {
            System.out.println("Error creating file or directory: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

}

