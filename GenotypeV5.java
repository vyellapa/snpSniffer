/**
 * Created with IntelliJ IDEA.
 * User: vyellapantula
 * Date: 6/26/13
 * Time: 11:58 AM
 * To change this template use File | Settings | File Templates.
 */
import java.io.*;
import java.util.*;
public class GenotypeV5 {
    //void Run(String pos, String ref, String bam) throws InterruptedException {
    void Run(String ref, String bam) throws IOException, InterruptedException {
        try
        {   //String positions=pos;
            String reference=ref;
            String fileName=bam;
            String sampleNameArray[]=fileName.split("/");
            String sampleName=sampleNameArray[(fileName.split("/").length)-1];
            String sample[]=sampleName.split(".bam");
            String path[]=fileName.split(sampleName);
            //String command="samtools mpileup -f "+reference+" -d 5000 -M 256 -l "+positions+" -g "+fileName+" | bcftools view - -g > "+ path[0] + sample[0]+".vcf";
            String command="geno "+reference+" "+fileName;
            // System.out.println(command);
            // System.out.println( System.getenv( "PATH" ));

            Process p = Runtime.getRuntime().exec(command);
            p.waitFor();
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }

    }
}

