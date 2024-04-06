package util;
import java.io.File;
import java.io.Console;
import java.util.ArrayList;

public class ConsoleUtils {
    private Console console;

    public ConsoleUtils(){
        console = System.console();
    }

    public String readLine(){
        return console.readLine();
    }
    public String readLine(String fmt, Object args){
        return console.readLine(fmt, args);
    }

    public File promptForFilePath(String prompt){
        while (true){
            String filePath = readLine("%s File Path? ", prompt);
            File file = new File(filePath);
            return file;
        }
    }

    public ArrayList<String> promptForNewPlayers(String prompt){
        ArrayList<String> playerNames = new ArrayList<>();
        while (playerNames.size() < 5){
            for (int i = 1; i < 6; i++){
                if (i > 2){
                    String addAnother = readLine("%s [Y]es/[N]o ", "Add another?").toLowerCase().trim();
                    if (addAnother.equals("n")) {
                        return playerNames;
                    }
                }
                String newPlayerName = readLine("%sPlayer " + i + " name? ", "");
                playerNames.add(newPlayerName);

            }
        }
        return playerNames;
    }

    public boolean promptForStartLoad(String prompt){
        while (true){
            String value = readLine("%s [S]tart/[L]oad ", prompt).toLowerCase().trim();
            if (value.equals("s")){
                return true;
            }
            else if (value.equals("l")) {
                return false;
            }
        }
    }

    public boolean promptForYesNo(String prompt){
        while (true){
            String value = readLine("%s [Y]es/[N]o ", prompt).toLowerCase().trim();
            if (value.equals("y")){
                return true;
            }
            else if (value.equals("n")) {
                return false;
            }
        }
    }

}
