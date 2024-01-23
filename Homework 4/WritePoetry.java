import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class WritePoetry {
    public String WritePoem(String file, String chosenWord, int howManyWords, boolean printHashTable){
        HashTable<String, WordFreqInfo> newHashTable = getHashTable(file);
        StringBuilder poem = new StringBuilder();
        poem.append(chosenWord + " ");
        for (int i = 0; i < howManyWords - 1; i++){
            WordFreqInfo chosenWordInHash = newHashTable.find(chosenWord);
            chosenWord = chosenWordInHash.pickNextWord();
            poem.append(chosenWord + " ");
            if (Objects.equals(chosenWord, ".") || Objects.equals(chosenWord, ",") || Objects.equals(chosenWord, "!") || Objects.equals(chosenWord, "?")){
                poem.append("\n");
            }
        }
        if (!( Objects.equals(chosenWord, ".") || Objects.equals(chosenWord, ",") || Objects.equals(chosenWord, "!") || Objects.equals(chosenWord, "?")))
        {
            poem.append(".");
        }
        if (printHashTable == true){
            poem.append("\n");
            poem.append("---" + file.toUpperCase(Locale.ROOT) + "'s " + "HASH TABLE---");
            poem.append("\n");
            poem.append(newHashTable.toString(100));
        }
        System.out.println();
        return poem.toString();
    }

    public static HashTable<String, WordFreqInfo> getHashTable(String file) {
        HashTable<String, WordFreqInfo> hashTable = new HashTable<>();
        try {
            File testText = new File(file);
            Scanner myReader = new Scanner(testText);
            //currWord = read Word
            String currWord = myReader.next().toLowerCase(Locale.ROOT);

            // add currWord  (as a WordFreqInfo) to hash table
            WordFreqInfo currWordWF = new WordFreqInfo(currWord, 0);
            hashTable.insert(currWordWF.word, currWordWF);

            // While more words
            while (myReader.hasNext()) {

                // nextWord= Read word
                String nextWord = myReader.next().toLowerCase(Locale.ROOT);
                hashTable.find(currWord).updateFollows(nextWord);
                // If nextWord already exists in hash table, update its occurrence count
                if (!hashTable.contains(nextWord)) {
                    hashTable.insert(nextWord, new WordFreqInfo(nextWord, 0));
                }
                currWord = nextWord;
            }
            myReader.close();
        } catch (FileNotFoundException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return hashTable;
    }

}


