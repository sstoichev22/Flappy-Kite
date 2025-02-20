import java.io.*;

public class FileManager {
    File file;
    FileManager(){
        file = new File(System.getProperty("user.home") + File.separator + "Documents" + File.separator + "FlappyKite" + File.separator);
        try{
            if(file.createNewFile()) writeToFile(0);
            System.out.println(file.getCanonicalPath());
        } catch(IOException ignored){}
    }
    public void writeToFile(int highScore){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.append(""+highScore);
            writer.close();
        } catch (IOException ignored){}
    }
    public int readFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    try {
                        return Integer.parseInt(line);
                    } catch (NumberFormatException ignored) {}
                }
            }
        } catch (IOException ignored) {}
        return -1;
    }


}
