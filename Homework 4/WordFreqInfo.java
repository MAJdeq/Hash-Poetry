import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class WordFreqInfo {
    public String word;
    public int occurCt;
    ArrayList<Freq> followList;

    public WordFreqInfo(String word, int count) {
        this.word = word;
        this.occurCt = count;
        this.followList = new ArrayList<Freq>();
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Word :" + word+":");
        sb.append(" (" + occurCt + ") : ");
        for (Freq f : followList)
            sb.append(f.toString());

        return sb.toString();
    }

    public void updateFollows(String follow) {
       //System.out.println("updateFollows " + word + " " + follow);
        occurCt++;
        for (Freq f : followList) {
            if (follow.compareTo(f.follow) == 0) {
                f.followCt++;
                return;
            }
        }
        followList.add(new Freq(follow, 1));


    }

    public String pickNextWord(){
        Random random = new Random();
        int randInt = random.nextInt(0, occurCt);
        int count = 0;
        for (Freq f : followList) {
                count += f.followCt;
                if (randInt < count){
                    return f.follow;
                }
        }
        return "";
    }

    public static class Freq {
        String follow;
        int followCt;

        public Freq(String follow, int ct) {
            this.follow = follow;
            this.followCt = ct;
        }

        public String toString() {
            return follow + " [" + followCt + "] ";
        }

        public boolean equals(Freq f2) {
            return this.follow.equals(f2.follow);
        }
    }

    public static void main(String[] args){
        try {
            File testText = new File("green.txt");
            Scanner myReader = new Scanner(testText);
            while (myReader.hasNext()){
                System.out.println(myReader.nextLine().toLowerCase(Locale.ROOT));
            }
            myReader.close();
        } catch (FileNotFoundException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        WordFreqInfo wf = new WordFreqInfo("i", 0);
        wf.updateFollows("am");
        wf.updateFollows("am");
        wf.updateFollows("am");
        wf.updateFollows("am");
        wf.updateFollows("am");
        wf.updateFollows("am");
        wf.updateFollows("am");
        wf.updateFollows("am");
        wf.updateFollows("do");
        wf.updateFollows("do");
        wf.updateFollows("do");
        wf.updateFollows("do");
        wf.updateFollows("do");
        System.out.println(wf);

        System.out.println("pickNextWord " + wf + ">> picked" + " " + wf.pickNextWord());

    }


}

