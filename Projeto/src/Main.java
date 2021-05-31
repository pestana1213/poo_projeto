
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Main
{

    public static void main(String[] args) throws Exception {
        Faztudo tudo = new Faztudo();
        Parser.parse(tudo);
        Menu.menu(tudo);
    }
}