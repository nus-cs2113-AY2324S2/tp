package florizz.core;

import florizz.command.*;
import florizz.objects.Bouquet;

public class Parser {
    public static Command parse (String input) throws FlorizzException{
        switch (input.split(" ")[0]){
            case ("mybouquets"):
                return new ListBouquetCommand();
            case ("new"):
                return handleAddBouquet(input);
            case ("delete"):
                return handleDeleteBouquet(input);
            default:
                throw new FlorizzException("Unidentified input, type help to get a list of all commands!");
        }
    }

    private static AddBouquetCommand handleAddBouquet(String input){
        String newBouquetName = input.substring(input.indexOf(" ") + 1);
        return new AddBouquetCommand(new Bouquet(newBouquetName));
    }

    private static DeleteBouquetCommand handleDeleteBouquet(String input){
        String bouquetToDelete = input.substring(input.indexOf(" ") + 1);
        return new DeleteBouquetCommand(new Bouquet(bouquetToDelete));
    }
}
