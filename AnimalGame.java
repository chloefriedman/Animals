import java.io.File;
import java.io.IOException;
import java.util.Scanner;
public class AnimalGame {
    /*
    The file Animals.txt found in the input folder contains a long list of animal
names, one per line. Create an application that reads that file and creates a collection
of animal names. Use the ArrayCollection class. Your application should then
generate a random character and challenge the user to repeatedly enter an animal
name that begins with that character, reading the names entered by the user until
they either enter a name that does not begin with the required character or is not
in the collection, or they enter a name they used before. Finally, your application
reports how many names they successfully entered.
     */
    public static void main(String[] args) throws IOException
    {
        File file = new File("Animals.txt");
        Scanner sc = new Scanner(file);
        ArrayCollection<String> animalCollection = new ArrayCollection<>(600);
        while(sc.hasNextLine())
        {
            try
            {
                String current = sc.nextLine();
                animalCollection.add(current);
            }
            
            catch(Exception e)
            {
                System.out.println("Error adding animal to collection");
            }
        }
        sc.close();

        String[] alphabet = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        boolean wantToPlay = true;
        while(wantToPlay)
        {
            boolean inRound = true;
            ArrayCollection<String> temp = animalCollection;
            int count = 0;
            int randomNum = (int)(Math.random() * alphabet.length);
            String randomLet = alphabet[randomNum];
            System.out.println("We are going to play a game! Enter animal names, one at a time, that start with the letter" + randomLet + ". If the animal is not in the list or you have already entered it or it doesn't start with" + randomLet + ", the game will end.");
            System.out.println();
            Scanner input = new Scanner(System.in);
            while(inRound)
            {
                boolean invalidInput = true;
                String userInput = "";
                while(invalidInput)
                {
                    System.out.println("Enter an animal name: ");
                    try
                    {
                        userInput = input.nextLine();
                        invalidInput = false;
                    }
                    catch(Exception e)
                    {
                        System.out.println("Not a valid animal name, try again");
                    }
                }
                if(userInput.toUpperCase().substring(0,1).equals(randomLet))
                {
                    boolean inList = temp.contains(userInput);
                    if(inList)
                    {
                        temp.remove(userInput);
                        System.out.println("Good job! You got an animal from the list");
                        count++;
                    }
                    else
                    {
                        System.out.println("Sorry this animal is not in the list.");
                        inRound = false;
                    }
                }
                else
                {
                    System.out.println("Sorry, this is the wrong letter");
                    inRound = false;
                }
            
            
            }
            System.out.println("GAME OVER! Your score is " + count);
            System.out.println("Want to play again? Respond true or false");
            wantToPlay = input.nextBoolean();
        }
            
    }
}
