import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class WikiGame {

    private int maxDepth;
    private ArrayList<String> path = new ArrayList<>();

    public static void main(String[] args) {
        WikiGame w = new WikiGame();
    }

    public WikiGame() {
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        String startLink = "https://en.wikipedia.org/wiki/Ryan_Reynolds";  // beginning link, where the program will start
        String endLink =   "https://en.wikipedia.org/wiki/Dwayne_Johnson";    // ending link, where the program is trying to get to
        maxDepth =2;           // start this at 1 or 2, and if you get it going fast, increase
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        if (findLink(startLink, endLink, 0)) {
            System.out.println("found it********************************************************************");
            path.add(startLink);
        } else {
            System.out.println("did not found it********************************************************************");
        }

        //print path

    }


    // recursion method
    public boolean findLink(String startLink, String endLink, int depth) {

        System.out.println("depth is: " + depth + ", link is: https://en.wikipedia.org //////" + startLink);



        // BASE CASE
        if (startLink.equals(endLink)){

            return true;
        } else if (depth == maxDepth){
            return false;

        }

        // GENERAL RECURSIVE CASE
        else {
            try {
                System.out.println();
                System.out.print("hello \n");
                URL url = new URL(startLink);
                url = new URL(startLink);
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(url.openStream())
                );
                String line;
                while ((line = reader.readLine()) != null) {


                    if (line.contains("/wiki/")) {
                        try {
                            int start = line.indexOf("/wiki/");
                            int end = line.indexOf("\"", start);

                            while (end >= 1) {
                                String link = line.substring(start, end);

                                if(link.contains(":") == false && link.contains("Ryan_Reynolds") == false && (link.charAt(6) != '%')) {
                                    System.out.println("https://en.wikipedia.org" + link);
                                    if (findLink("https://en.wikipedia.org" + link, endLink, depth +1)) {
                                        path.add("https://en.wikipedia.org" + link);
                                        return true;
                                    }
                                }

                                line = line.substring(end + 1);
                                start = line.indexOf("/wiki/");

                                int end1 = line.indexOf("\"", start);
                                int end2 = line.indexOf("<", start);

                                if(end1 < end2){
                                    end = end1;
                                }
                                else{
                                    end = end2;
                                }

                            }
                        } catch (Exception e) {
                        }
                    }
                }
                reader.close();
            } catch (Exception ex) {
                System.out.println(ex);
            }

        }

return false;


    }
}