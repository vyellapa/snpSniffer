import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: vyellapantula
 * Date: 1/25/13
 * Time: 3:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class snpSnifferV5 {
    public static void main(String[] args) throws IOException, InterruptedException {
        //adding a new vcf to flat file
        if(args[0].equals("-add")){
            addFileV5 cal = new addFileV5();
            cal.fileAdd(args[1], args[2]);

        }

        //Listing all Samples from flat file
        else if (args[0].equals("-check") && args[1].equals("Samples")){
            CheckV5 c1 = new CheckV5();
            c1.help(args[2]);
        }

        //Checking a specific sample
        else if (args[0].equals("-check")){
            CheckV5 c1 = new CheckV5();
            c1.read(args[1], args[2]);
        }

        //getting genotypes
        else if (args[0].equals("-genotype")){
            GenotypeV5 g1 = new GenotypeV5();
            g1.Run(args[1], args[2]);
        }

        //getting expected matches
        else if (args[0].equals("-expected")){
            ExpectedV5 g1 = new ExpectedV5();
            g1.Run(args[1], args[2], args[3]);
        }

        //getting not-expected matches
        else if (args[0].equals("-notExpected")){
            notExpectedV5 g1 = new notExpectedV5();
            g1.Run(args[1], args[2], args[3]);
        }

        //getting not-expected matches
        else if (args[0].equals("-het")){
            HetV5 g1 = new HetV5();
            g1.find(args[1]);
        }


        //Usage options
        else {
            System.out.println("Usage options are");
            System.out.println("-genotype:          generate genotypes from a bam: java -jar snpSniffer.jar -genotype <fullFilePath/reference.fa> <fullFilePath/sample.bam>");
            System.out.println("-add:               add genotypes from a vcf: java -jar snpSniffer.jar -add <fullFilePath/sample.vcf> <fullFilePath/database.ini>");
            System.out.println("-check Samples:     view all samples: java -jar snpSniffer.jar -check Samples <fullFilePath/database.ini>");
            System.out.println("-check:             check concordance of genotypes for a sample: java -jar snpSniffer.jar -check <sampleName> <fullFilePath/database.ini>");
            System.out.println("-expected:          identify expected matches: java -jar snpSniffer.jar -expected <delimiter> <# of delimited fields that define sample name> <fullFilePath/database.ini>");
            System.out.println("-notExpected:       identify not-expected matches check: java -jar snpSniffer.jar -notExpected <delimiter> <# of delimited fields that define sample name> <fullFilePath/database.ini>");
            System.out.println("-het:               calculate heterozygosity rate for all samples: java -jar snpSniffer.jar -het <fullFilePath/database.ini>");

        }


    }
}
