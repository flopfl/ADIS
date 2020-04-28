import java.util.Scanner; 
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;

public class test {
    public static void main( String args[] ) {
        System.out.println("Content-type: text/html\n\n");//required header, describes the type of the content in the response

        //Here the text that gets posted for the blog gets written into our text file
        try{
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
            LocalDateTime now = LocalDateTime.now();    //the current date is acquired and formated
              String query_string = System.getProperty("cgi.query_string");//reading of the query string that got set by the cgi script
              String[] input=query_string.split("=");
                if(input[1] !="" && input[1].length()<128){//testing if smaler then 128 chars like required
                    
                    try{
                        FileWriter writer = new FileWriter("texts.txt", true);

                        writer.write("<p>"+dtf.format(now)+" "+input[1]+"</p>");//write the text in proper html format
                        writer.close();
                    }catch (IOException e) {
                        e.printStackTrace();
                    }
                }
          }catch (Exception e) { }
      
        
        //here we read whats on the blog already

        String text="";
        try {
            FileReader reader = new FileReader("texts.txt");
            int character;
 
            while ((character = reader.read()) != -1) {
                text+= (char) character;
            }
            reader.close();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
        //after reading the whole file which consists of multiple paragraphs we return the page by printing it
        String page=new String("<html><head><title>Roar</title></head><body><form  action=\"test.cgi\" method=\"get\"><input type=\"text\" name=\"text\"><input type=\"submit\"></form><div>"+text+"</div></body></html>");
        System.out.println(page);
        
    }

}
