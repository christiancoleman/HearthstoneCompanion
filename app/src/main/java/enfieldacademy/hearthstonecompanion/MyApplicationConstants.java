package enfieldacademy.hearthstonecompanion;

public class MyApplicationConstants {

    public static String getJSONArrayName(int position){
        switch(position){
            case 0:
                return "Basic";
            case 1:
                return "Classic";
            case 2:
                return "Credits";
            case 3:
                return "Naxxramas";
            case 4:
                return "Debug";
            case 5:
                return "Goblins vs Gnomes";
            case 6:
                return "Missions";
            case 7:
                return "Promotion";
            case 8:
                return "Reward";
            case 9:
                return "System";
            case 10:
                return "Blackrock Mountain";
            case 11:
                return "Hero Skins";
            case 12:
                return "Tavern Brawl";
            default:
                return "Basic";
        }
    }

}
