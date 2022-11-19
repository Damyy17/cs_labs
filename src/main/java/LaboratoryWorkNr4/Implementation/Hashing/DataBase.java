package LaboratoryWorkNr4.Implementation.Hashing;

import java.util.ArrayList;
import java.util.List;

public class DataBase {
    private final List<String> passwords = new ArrayList<>();

    public void addToDB(String password){
        passwords.add(password);
    }

    public boolean checkDbIfContains(String password){
        return passwords.contains(password);
    }
}
