/**
 * Created with IntelliJ IDEA.
 * User: vyellapantula
 * Date: 1/16/13
 * Time: 12:00 AM
 * To change this template use File | Settings | File Templates.
 */
import java.io.*;
import java.util.Scanner;

public class CheckV5 {
    private int i,j=0,l=0,x=0,sample=0;
    private double match=0.0,count=0.0,ratio;
    String line1;
    String line1Tokens[];
    String delim ="\t";
    String tokens[];

    void read(String Name, String flatFile) throws IOException {
        Scanner ped=new Scanner(new FileReader(flatFile));
        for(i=0;i<1;i++){
            line1=ped.nextLine();
            line1Tokens=line1.split(delim);
            l=(line1.split(delim)).length;
            //System.out.println("length " + l);
        }

        for(i=0;i<l;i++){
            if(line1Tokens[i].equals(Name)) {sample=i;}
        }
        String[][] data=new String[388][l];

        Scanner ped1=new Scanner(new FileReader(flatFile));
        while (ped1.hasNextLine()){

            //String line=ped1.nextLine();
            String[] tokens = ped1.nextLine().split(delim);
            for(i=0;i<tokens.length;i++){
                data[x][i]=tokens[i];
            }
            x++;
        }
        ped.close();
        ped1.close();

        for(j=1;j<l;j++){
            for(i=1;i<388;i++){

                if(!data[i][j].equals("NN") && !data[i][sample].equals("NN")){
                    count++;
                    if(data[i][j].equals(data[i][sample])){match++;}

                }
            }
            ratio=match/count;

            if(!data[0][j].equals(data[0][sample]) && !data[0][sample].equals("Sample") && !data[0][j].equals("Sample")){
                System.out.println(data[0][sample] + " & " + data[0][j] + " count="+count+" match="+match+ " ratio=" + ratio);
            }

            match=count=0.0;
        }




    }
    //Output all samples results to snpSniffer_output.txt
    void output(String Name, String flatFile) throws IOException {
        Scanner ped=new Scanner(new FileReader(flatFile));
        File outFile = new File ("snpSniffer_output.txt");


        for(i=0;i<1;i++){
            line1=ped.nextLine();
            line1Tokens=line1.split(delim);
            l=(line1.split(delim)).length;
            //System.out.println("length " + l);
        }

        for(i=0;i<l;i++){
            if(line1Tokens[i].equals(Name)) {sample=i;}
        }
        String[][] data=new String[388][l];

        Scanner ped1=new Scanner(new FileReader(flatFile));
        while (ped1.hasNextLine()){

            //String line=ped1.nextLine();
            String[] tokens = ped1.nextLine().split(delim);
            for(i=0;i<tokens.length;i++){
                data[x][i]=tokens[i];
            }
            x++;
        }
        ped.close();
        ped1.close();

        FileWriter fWriter = new FileWriter (outFile, true);
        PrintWriter pWriter = new PrintWriter (fWriter);

        for(j=1;j<l;j++){
            for(i=1;i<388;i++){

                if(!data[i][j].equals("NN") && !data[i][sample].equals("NN")){
                    count++;
                    if(data[i][j].equals(data[i][sample])){match++;}

                }
            }
            ratio=match/count;

            if(!data[0][j].equals(data[0][sample]) && !data[0][sample].equals("Sample") && !data[0][j].equals("Sample")){
                pWriter.println(data[0][sample] + " & " + data[0][j] + " count="+count+" match="+match+ " ratio=" + ratio);
            }

            match=count=0.0;
        }
        pWriter.close();

    }

    void help(String flatFile) throws IOException {
        Scanner ped3=new Scanner(new FileReader(flatFile));
        String sampleNames="?";
        for(i=0;i<1;i++){
            sampleNames=ped3.nextLine();
            tokens=sampleNames.split("\t");
            //l=(line1.split(delim)).length; System.out.println("length " + l);
        }
        for(i=0;i<tokens.length;i++){
            System.out.println(tokens[i]);
        }
        ped3.close();


    }
}
