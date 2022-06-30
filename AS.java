/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package as;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
*
*
        @ Bhomika Suthar
*
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashMap;

public class AS{

    public static void main(String[] args) throws IOException {
        Document document = Jsoup.connect("https://www.bbc.com/urdu").get();
        String categ[] = {"https://www.bbc.com/urdu/topics/cjgn7n9zzq7t","https://www.bbc.com/urdu/topics/cl8l9mveql2t", "https://www.bbc.com/urdu/topics/cw57v2pmll9t", "https://www.bbc.com/urdu/topics/c340q0p2585t", "https://www.bbc.com/urdu/topics/ckdxnx900n5t"};
        String categories[] = {"Pakistan", "Around", "World", "Game", "The Artist"};
        List<String> real_data = new ArrayList<>();
        int sum = 0;
        for (int i = 0; i < categ.length; i++) {
            sum = 0;
            String next = " ";
            int  sum0 = 1;
            for (int infinite = 0; infinite == 0 && sum != 100;) {
                Document content = Jsoup.connect(categ[i] + next).get();
                Elements contentclass = content.getElementsByClass("bbc-uk8dsi emimjbx0");
                for (Element e : contentclass) {
                    sum++;
                    String address = e.attr("href");
                    Document datalink = Jsoup.connect(address).get();
                    Elements data = datalink.getElementsByClass("bbc-4wucq3 essoxwk0");
                    String s = data.text();
                    real_data.add(categories[i] + "," + s);
                    if (sum == 100) {
                        break;
                    }
                }
                sum0++;
                next = "?page=";
                next = next + sum0;
            }
        }
        try {
            PrintWriter pw = new PrintWriter(new File("C:\\User\\Bhomika\\Desktop\\BBCNews.csv"));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < real_data.size(); i++) {
                sb.append(real_data.get(i));
                sb.append("\r\n");
            }
            pw.write(sb.toString());
            pw.close();
        } catch (Exception e) {
        }
          int sum1=0;
        int max=0,min=0;
        String s="C:\\User\\Bhomika\\Desktop\\BBCNews.csv";
        ArrayList<String> arr =new ArrayList<String>();
         HashMap<String,Integer> uniWords=new HashMap<String,Integer>();
        BufferedReader reader =null;
        String line="";
       int add1=0;
        try{
            reader=new BufferedReader(new FileReader(s));  
             line=reader.readLine();
            while((line=reader.readLine())!=null){
               max=Math.max(max,line.length());
               min=Math.min(min,line.length());
                String[] cells=line.split(",");
                String[] words=cells[0].split(" "); 
                if(uniWords.get(cells[1])==null){
                        uniWords.put(cells[1], 1);
                    }
                else{
                     add1=uniWords.get(cells[1]);
                    uniWords.put(cells[1], ++add1);
                }
                    
                for(String b:words){
                    if(uniWords.get(b)==null){
                        uniWords.put(b, 1);
                        continue;
                    }
                     add1=uniWords.get(b);
                    uniWords.put(b, ++add1);
                }
                
               if(!arr.contains(cells[1]))
               { 
               arr.add(cells[1]);}
            }
        }
        
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            } }
        for(String st:arr)  
        { System.out.println(st);  }
        System.out.println(arr);
        System.out.println("Maximun Story conatins :"+max + " Words ");
        System.out.println("Minimum Story contains :"+min + " Words");
        System.out.println("The number of unique words is :"+uniWords.size());
        
        
    }
}
