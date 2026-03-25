import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Installation {
    public static void main(String[] args) {

        String basePath = "C:\\Users\\1\\Desktop\\Games";

        File gamesDir = new File(basePath);

        if (!gamesDir.exists()) {
            System.out.println("Ошибка: папка Games не найдена по пути " + basePath);
            return;
        }

        StringBuilder log = new StringBuilder();
        log.append("Начало установки игры...\n");

        try {

            File srcDir = createDirectory(gamesDir, "src", log);
            File resDir = createDirectory(gamesDir, "res", log);
            File savegamesDir = createDirectory(gamesDir, "savegames", log);
            File tempDir = createDirectory(gamesDir, "temp", log);
            File mainDir = createDirectory(srcDir, "main", log);
            createDirectory(srcDir, "test", log);
            createFile(mainDir, "Main.java", log);
            createFile(mainDir, "Utils.java", log);
            createDirectory(resDir, "drawables", log);
            createDirectory(resDir, "vectors", log);
            createDirectory(resDir, "icons", log);

            File tempFile = new File(tempDir, "temp.txt");
            if (tempFile.createNewFile()) {
                log.append("Файл temp.txt успешно создан\n");
            } else {
                log.append("Ошибка: не удалось создать файл temp.txt\n");
            }
            writeLogToFile(tempFile, log.toString());
            log.append("Установка завершена успешно!\n");

        } catch (IOException e) {
            log.append("Произошла ошибка ввода‑вывода: ").append(e.getMessage()).append("\n");
            System.err.println("Ошибка: " + e.getMessage());
        }
        System.out.println(log.toString());
    }

    private static File createDirectory(File parent, String dirName, StringBuilder log) {
        File dir = new File(parent, dirName);
        if (dir.mkdir()) {
            log.append("Директория ").append(dir.getAbsolutePath()).append(" успешно создана\n");
        } else {
            log.append("Ошибка: не удалось создать директорию ").append(dir.getAbsolutePath()).append("\n");
        }
        return dir;
    }

    private static void createFile(File parent, String fileName, StringBuilder log) throws IOException {
        File file = new File(parent, fileName);
        if (file.createNewFile()) {
            log.append("Файл ").append(file.getAbsolutePath()).append(" успешно создан\n");
        } else {
            log.append("Ошибка: файл ").append(file.getAbsolutePath()).append(" уже существует или не удалось создать\n");
        }
    }

    private static void writeLogToFile(File logFile, String logContent) throws IOException {
        try (FileWriter writer = new FileWriter(logFile)) {
            writer.write(logContent);
        }
    }
}
