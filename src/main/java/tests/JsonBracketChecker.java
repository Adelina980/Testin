package tests;

import java.io.*;
import java.util.Stack;

public class JsonBracketChecker {

    public static int checkBrackets(String filename) throws IOException {
        if (filename == null) {
            throw new NullPointerException("Имя файла не может быть null");
        }

        File file = new File(filename);
        if (!file.exists()) {
            throw new FileNotFoundException("Файл не найден: " + filename);
        }

        int position = 1;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            Stack<Character> stack = new Stack<>();
            int ch;

            while ((ch = reader.read()) != -1) {
                char c = (char) ch;

                if (c == '{' || c == '[') {
                    stack.push(c);
                } else if (c == '}' || c == ']') {
                    if (stack.isEmpty()) {
                        return position;
                    }

                    char lastOpenBracket = stack.pop();
                    if ((c == '}' && lastOpenBracket != '{') || (c == ']' && lastOpenBracket != '[')) {
                        return position;
                    }
                }
                if (!Character.isLetterOrDigit(c) && !Character.isWhitespace(c)
                        && c != '{' && c != '}' && c != '[' && c != ']'
                        && c != ':' && c != ',' && c != '"' && c != '.') {
                    throw new IOException("Недопустимый символ в файле: " + c);
                }

                position++;
            }
            if (!stack.isEmpty()) {
                return position;
            }
        }finally {
            if (reader != null){
                reader.close();
            }
        }

        return 0;
    }
}
