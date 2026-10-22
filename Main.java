import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import model.*;

class DraftSimulator {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        //Ask Player to input each character in the format: Name,Division,Rating.
        System.out.println("Enter Superstar info in the following format: Name,Division,Rating (Example: Roman Reigns,Men,96)");
        System.out.println("Enter -1 to end Superstar input.");
        System.out.println("Note: Enter Average Overall Rating for tag teams.");

        ArrayList<Superstar> characterPool = new ArrayList<>();

        String input;
        while (true){
            System.out.print("Enter Superstar (Name,Division(Men, Women, Tag),Rating) or -1 to finish: ");
            input=sc.nextLine().trim();
            if(input.equals("-1")) break;
        
            String[] parts=input.split(",");
            if (parts.length!=3){
                System.out.println("Use the format: Name,Division,Rating");
                continue;
            }
        
            String name=parts[0].trim();
            String divisionString=parts[1].trim();
        
            Division division;
            if("Men".equalsIgnoreCase(divisionString)){
                division=Division.MEN;
            }else if("Women".equalsIgnoreCase(divisionString)){
                division=Division.WOMEN;
            }else if("Tag".equalsIgnoreCase(divisionString)){
                division = Division.TAG;
            }else{
                System.out.println("Division must be Men, Women or Tag.");
                continue;
            }
        
            int rating;
            try {
                rating = Integer.parseInt(parts[2].trim());
            } catch (NumberFormatException e) {
                System.out.println("Rating must be a whole number.");
                continue;
            }
        
            characterPool.add(new Superstar(name, division, rating));
        }
        
        System.out.println("Draft Pool Created!");

        //Ask Player his name & to choose a brand.
        System.out.print("Please enter your Username: ");
        String userName=sc.nextLine().trim();

        System.out.print("Enter your brand name(Example: Raw, Smackdown Live, AEW, WCW, TNA, ROH, NJPW): ");
        String userBrand=sc.nextLine().trim();

        //Ask Player to initialize quota for each division.
        System.out.print("Enter number of Superstars to draft for Division(Men): ");
        int quota=sc.nextInt();
        Division.MEN(quota);

        System.out.print("Enter number of Superstars to draft for Division(Women): ");
        quota=sc.nextInt();
        Division.WOMEN(quota);
        
        System.out.print("Enter number of Tag teams to draft for Division(Tag): ");
        quota=sc.nextInt();
        Division.TAG(quota);

        //TO-DO(Future Addition): Ask Player to choose AI GM Personality.
        
        //TO-DO: Initialize User and Comp Rosters.
        Roster userRoster=new Roster(); 
        Roster aiRoster=new Roster(); 
        DraftAI ai=new DraftAI();

        //TO-DO: Initialize GM AI.

        //TO-DO: For each Round, Player picks a Superstar. Computer GM evaluates each superstar and selects a superstar for the draft pick.
        while(!characterPool.isEmpty() && !(roster[0].isFull() && roster[1].isFull())){
            
            if(!userRoster.isFull()){
                System.out.println("----------------------------------------------------");
                System.out.println("Available Superstars");
                
                System.out.println("\nDivision: Men");
                for(Superstar superstar : characterPool){
                    if(superstar.getDivision()==Division.MEN){
                        System.out.println(superstar.getName() + "(Overall: " + getRating() + ")");
                    }
                }

                System.out.println("\nDivision: Women");
                for(Superstar superstar : characterPool){
                    if(superstar.getDivision()==Division.WOMEN){
                        System.out.println(superstar.getName() + "(Overall: " + getRating() + ")");
                    }
                }

                System.out.println("\nDivision: Tag Teams");
                for(Superstar superstar : characterPool){
                    if(superstar.getDivision()==Division.TAG){
                        System.out.println(superstar.getName() + "(Avg. Overall: " + getRating() + ")");
                    }
                }
                System.out.println("----------------------------------------------------");

                System.out.print(userName + "(" + userBrand + ") pick: ");
                String userPick=sc.nextLine().trim();

                Superstar chosen=null;

                for(Superstar s : characterPool){
                    if(s.getName().equalsIgnoreCase(userPick)){
                        chosen=s;
                        break;
                    }
                }

                if(chosen==null || !userRoster.hasRemainingSpots(chosen.getDivision())){
                    System.out.println("Invalid pick, try again.");
                    continue;
                }

                userRoster.addToRoster(chosen);
                characterPool.remove(chosen);
            }

        }

        System.out.println("\nDRAFT COMPLETE!");

        sc.close();
    }
}
