
/**
 * Created with IntelliJ IDEA.
 * User: vyellapantula
 * Date: 1/16/13
 * Time: 12:00 AM
 * To change this template use File | Settings | File Templates.
 */
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Scanner;
import java.lang.Object;
import java.io.PrintWriter;
import java.io.*;

public class HetV5 {
    private int i,j=0,l=0,x=0;
    private double ratio=0.0,het=0.0,hom=0.0,total=0.0;
    String line1;
    String line1Tokens[];
    String delim ="\t";
    String tokens[];

    void find( String flatFile) throws IOException {
        Scanner ped=new Scanner(new FileReader(flatFile));
        for(i=0;i<1;i++){
            line1=ped.nextLine();
            line1Tokens=line1.split(delim);
            l=(line1.split(delim)).length;
            //System.out.println("length " + l);
        }

        //for(i=0;i<l;i++){
          //  if(line1Tokens[i].equals(Name)) {sample=i;}
        //}

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

        System.out.println("Sample"+ "\t" + "Homozygous"+ "\t" + "Heterozygous"+ "\t" +  "Total"+ "\t" +  "hetRatio");
        for(j=1;j<l;j++){
            for(i=1;i<388;i++){
                //System.out.println(i+"\t"+j+"\t"+data[0][j]+"\t"+data[i][j]);

                if(data[i][j].equals("AA") || data[i][j].equals("TT") || data[i][j].equals("CC") || data[i][j].equals("GG")){
                    hom++;
                    //System.out.println("HOM"+"\t"+data[0][j]+"\t"+data[i][j]);
                }

                else if(data[i][j].equals("AT") || data[i][j].equals("AC") || data[i][j].equals("AG")){
                    het++;
                    //System.out.println("HET"+"\t"+data[0][j]+"\t"+data[i][j]);
                }
                else if(data[i][j].equals("TA") || data[i][j].equals("TC") || data[i][j].equals("TG")){
                    het++;
                    //System.out.println("HET"+"\t"+data[0][j]+"\t"+data[i][j]);
                }
                else if(data[i][j].equals("CT") || data[i][j].equals("CT") || data[i][j].equals("CG")){
                    het++;
                    //System.out.println("HET"+"\t"+data[0][j]+"\t"+data[i][j]);
                }
                else if(data[i][j].equals("GT") || data[i][j].equals("GC") || data[i][j].equals("GA")){
                    het++;
                    //System.out.println("HET"+"\t"+data[0][j]+"\t"+data[i][j]);
                }

            }
            total=hom+het;
            ratio=het/total;

            if(!data[0][j].equals("Sample")){
            System.out.println(data[0][j] + "\t" +hom+"\t"+het+ "\t" +total+"\t"+ ratio);
            }

            hom=het=0.00;
        }

    }
    }

