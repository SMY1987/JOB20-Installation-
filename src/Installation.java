import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Installation {
    public static void main(String[] args) {

        String basePath = "C:\\Users\\1\\Desktop\\Games";

        StringBuilder log = new StringBuilder();

        List<String> directoryPaths = Arrays.asList(
                basePath + "\\src",
                basePath + "\\res",
                basePath + "\\savegames",
                basePath + "\\temp",
                basePath + "\\src\\main",
                basePath + "\\src\\test",
                basePath + "\\res\\drawables",
                basePath + "\\res\\vectors",
                basePath + "\\res\\icons"
        );
        List<String> filePaths = Arrays.asList(
                basePath + "\\src\\main\\Main.java",
                basePath + "\\src\\main\\Utils.java",
                basePath + "\\temp\\temp.txt"
        );
        try {

            for (String path : directoryPaths) {
                createDirectory(path, log);
            }


            for (String path : filePaths) {
                createFile(path, log);
            }


            String logFilePath = basePath + "\\temp\\temp.txt";
            try (FileWriter writer = new FileWriter(logFilePath)) {
                writer.write(log.toString());
            }

            System.out.println("Установка завершена успешно!");
            System.out.println("Проверьте папку Games и файл temp.txt для подробной информации.");

        } catch (IOException e) {
            System.err.println("Произошла ошибка ввода‑вывода: " + e.getMessage());
        }
    }

    private static void createDirectory(String fullPath, StringBuilder log) {
        File directory = new File(fullPath);
        boolean created = directory.mkdirs();

        if (created) {
            log.append("Создана директория: ").append(fullPath).append("\n");
        } else {
            log.append("Не удалось создать директорию: ").append(fullPath).append("\n");
        }
    }

    private static void createFile(String fullPath, StringBuilder log) throws IOException {
        File file = new File(fullPath);
        boolean created = file.createNewFile();

        if (created) {
            log.append("Создан файл: ").append(fullPath).append("\n");
        } else {
            log.append("Не удалось создать файл: ").append(fullPath).append("\n");
        }
    }
}