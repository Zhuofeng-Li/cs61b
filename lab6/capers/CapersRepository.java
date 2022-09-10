package capers;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import static capers.Utils.*;

/** A repository for Capers 
 * @author TODO
 * The structure of a Capers Repository is as follows:
 *
 * .capers/ -- top level folder for all persistent data in your lab12 folder
 *    - dogs/ -- folder containing all of the persistent data for dogs
 *    - story -- file containing the current story
 *
 * TODO: change the above structure if you do something different.
 */
public class CapersRepository {
    /** Current Working Directory. */
    static final File CWD = new File(System.getProperty("user.dir"));//注意:这里不会创建文件
    /** Main metadata folder. */
    static final File CAPERS_FOLDER = join(".capers"); // TODO Hint: look at the `join`
    //      function in Utils
    /**
     * Does required filesystem operations to allow for persistence.
     * (creates any necessary folders or files)
     * Remember: recommended structure (you do not have to follow):
     *
     * .capers/ -- top level folder for all persistent data in your lab12 folder
     *    - dogs/ -- folder containing all of the persistent data for dogs
     *    - story -- file containing the current story
     */
    public static void setupPersistence() throws IOException {
        //the right way to do
        CAPERS_FOLDER.mkdir();
        File dogs = join(CAPERS_FOLDER.toString(),"dogs");
        dogs.mkdir();
        File story = join(CAPERS_FOLDER.toString(),"story");
        story.createNewFile();
    }

    /**
     * Appends the first non-command argument in args
     * to a file called `story` in the .capers directory.
     * @param text String of the text to be appended to the story
     */
    public static void writeStory(String text) {
        // TODO
        File temp = join(CAPERS_FOLDER.toString(),"story");
        String temp_string = readContentsAsString(temp);
        if (temp_string == " ") {//notice kong hang
            temp_string = text;
        }
        temp_string = temp_string + "\n" + text;
        writeContents(temp, temp_string);
    }

    /**
     * Creates and persistently saves a dog using the first
     * three non-command arguments of args (name, breed, age).
     * Also prints out the dog's information using toString().
     */
    public static void makeDog(String name, String breed, int age) {
        // TODO
        Dog perment_dog = new Dog(name, breed, age);
        File temp = join(CAPERS_FOLDER,"dogs");
        perment_dog.saveDog();
        System.out.println(perment_dog.toString());
    }

    /**
     * Advances a dog's age persistently and prints out a celebratory message.
     * Also prints out the dog's information using toString().
     * Chooses dog to advance based on the first non-command argument of args.
     * @param name String name of the Dog whose birthday we're celebrating.
     */
    public static void celebrateBirthday(String name) {
        // TODO
        Dog temp = Dog.fromFile(name);//use API directly
        temp.haveBirthday();

    }
}
