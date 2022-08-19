package com.example.Day16.utils;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    // Lấy đuôi file
    public static String getExtensionFile(String fileName) {
        int lastIndex = fileName.lastIndexOf(".");
        if(lastIndex == -1) {
            return "";
        }
        return fileName.substring(lastIndex + 1);
    }

    // Kiểm tra đuôi file
    public static boolean checkFileExtension(String fileExtension) {
        List<String> extensions = new ArrayList<>(List.of("png", "jpg", "jpeg"));
        return extensions.contains(fileExtension);
    }
}
