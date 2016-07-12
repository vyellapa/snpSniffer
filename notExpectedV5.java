/**
 * Created with IntelliJ IDEA.
 * User: vyellapantula
 * Date: 5/6/14
 * Time: 1:15 PM
 * To change this template use File | Settings | File Templates.
 */
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class notExpectedV5 {
    private int i,j=0,l=0,x=0,sample=0;
    private double match=0.0,count=0.0,ratio;
    String line1,s1,s2;
    String line1Tokens[];
    String delim ="\t";
    String tokens[];

    void Run(String userDelim, String number,String flatFile) throws IOException {
        //Get the list of all samples by reading first line of flatfile
        File f = new File("snpSniffer_output.txt");
        if(f.exists() && !f.isDirectory()) { f.delete(); }
        Scanner ped=new Scanner(new FileReader(flatFile));
        for(i=0;i<1;i++){
            line1=ped.nextLine();
            line1Tokens=line1.split(delim);
            l=(line1.split(delim)).length;
            //System.out.println("length " + l);
        }
        ped.close();


        for(i=0;i<l;i++){
            if(!line1Tokens[i].equals("Sample")) {
                CheckV5 o1 = new CheckV5();
                o1.output(line1Tokens[i], flatFile);
            }
        }
        String[][] data=new String[388][l];

        Scanner ped1=new Scanner(new FileReader("snpSniffer_output.txt"));
        while (ped1.hasNextLine()){

            String line=ped1.nextLine();
            String[] tokens = line.split(" ");
            String[] sample1=tokens[0].split(userDelim);
            String[] sample2=tokens[2].split(userDelim);
            ratio = Double.parseDouble((tokens[5].split("="))[1]);
            if(number.equals("1")) { s1= sample1[0]; s2=sample2[0];}
            else if(number.equals("2")) { s1= sample1[0].concat(sample1[1]); s2=sample2[0].concat(sample2[1]);}
            else if(number.equals("3")) { s1= (sample1[0].concat(sample1[1])).concat(sample1[2]); s2=(sample2[0].concat(sample2[1])).concat(sample2[2]);}


            if(s1.equals(s2) && ratio < 0.80){
                System.out.println(line);
            }
            if(!s1.equals(s2) && ratio > 0.80){
                System.out.println(line);
            }
        }
        ped1.close();

    }

}
