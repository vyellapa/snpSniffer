import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: vyellapantula
 * Date: 1/16/13
 * Time: 4:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class addFileV5 {

    void fileAdd(String fileName, String flatFile) throws IOException {

        Scanner vcfFile=new Scanner(new FileReader(fileName));
        String sampleNameArray[]=fileName.split("/");
        String sampleName=sampleNameArray[(fileName.split("/").length)-1];
        sampleNameArray=sampleName.split(".vcf");
        sampleName=sampleNameArray[0];
        System.out.println("Sample name is: " + sampleName);
        String delim ="\t";
        String zygoDelim = ":";
        String vcfMatrix[][] = new String[388][5];
        String database[][];
        String con=" ";
        int matrixx=0;
        int i,l=0,flag=0;
        float qual;


        try {
            //first use a Scanner to get each line
            while (vcfFile.hasNextLine() ){

                String line=vcfFile.nextLine();
                String[] tokens = line.split(delim);
                //String[] zygosity = tokens[9].split(zygoDelim); We hope vcf genotypes start with chr1 and change flag to begin reading
                if(tokens[0].equals("1")){flag=1;}
                if(flag==1){qual=(Float.valueOf(tokens[5])).floatValue();}
                if(flag==1) {
                    qual=(Float.valueOf(tokens[5])).floatValue();
                    if(qual>50) { //Use genotypes with qual>50
                        //Obtain the genotype zygosity and update all columns of matrix (chr:pos ALT REF QUAL ZYGOSITY)
                        String[] zygosity = tokens[9].split(zygoDelim);
                        //System.out.println(tokens[0]+ ":" + tokens[1]+"\t" + tokens[3]+ "\t" + tokens[4]+ "\t" + tokens[5]+ "\t" + zygosity[0]);
                        vcfMatrix[matrixx][0]=(tokens[0].concat(":")).concat(tokens[1]);
                        vcfMatrix[matrixx][1] =tokens[3];
                        vcfMatrix[matrixx][2] =tokens[4];
                        vcfMatrix[matrixx][3] =tokens[5];
                        vcfMatrix[matrixx][4] =zygosity[0];
                        matrixx++;

                    }
                }
            }
        }

        finally {
            vcfFile.close();
        }


        Scanner ped=new Scanner(new FileReader(flatFile));
        for(i=0;i<1;i++){l=((ped.nextLine()).split(delim)).length;}
        String[][] data=new String[388][(l+1)];
        for(i=0;i<388;i++){data[i][l]="NN";} //Initializing last column to "NN"
        data[0][l]=sampleName;          //Initializing sample name
        int x=0,y=0;

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

        for(x=0;x<388;x++){ for(y=0;y<388;y++){
            if(data[x][0].equals(vcfMatrix[y][0]) && vcfMatrix[y][4].equals("0/1")){data[x][l]=vcfMatrix[y][1].concat(vcfMatrix[y][2]); }
            if(data[x][0].equals(vcfMatrix[y][0]) && vcfMatrix[y][4].equals("1/1")){data[x][l]=vcfMatrix[y][2].concat(vcfMatrix[y][2]); }
            if(data[x][0].equals(vcfMatrix[y][0]) && vcfMatrix[y][4].equals("0")){data[x][l]=vcfMatrix[y][1].concat(vcfMatrix[y][1]); }
        }}

        PrintWriter pr = new PrintWriter(flatFile);
        for (i=0; i<388 ; i++){
            for(y=0;y<(l+1);y++){
                pr.print(data[i][y]+ "\t");
            } pr.print("\n");
        }
        pr.close();
        // for(i=0;i<180;i++){for(y=0;y<(l+1);y++){System.out.print(data[i][y]+ "\t");}System.out.print("\n");}
        System.out.println("Sample successfully added to " + flatFile);
    }

}
