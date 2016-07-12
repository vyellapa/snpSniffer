/**
 * SnpSniffer Main Class
 *
 * @author
 *      Venkata Yellapantula (y.divyatej@gmail.com)
 *      Date: 5/6/15
 *      Time: 1:15 PM
 *
 */



import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class snpSniffer {

    @Option(name="-genotype",
            usage="generate genotypes in a VCF")
    private boolean genotype;

    @Option(name="-add",
            usage="add genotypes from a vcf:\njava -jar snpSniffer.jar -add <fullFilePath/sample.vcf> <fullFilePath/database.ini>")
    private boolean add;

    @Option(name="-check",
            usage="check concordance of genotypes for a sample:\njava -jar snpSniffer.jar -check <sampleName> <fullFilePath/database.ini>")
    private boolean check;

    @Option(name="-samples",
            usage="check concordance of genotypes for a sample:\njava -jar snpSniffer.jar -check <sampleName> <fullFilePath/database.ini>")
    private boolean samples;

    @Option(name="-expected",
            usage="identify expected matches:\njava -jar snpSniffer.jar -expected <delimiter> <# of delimited fields that define sample name> <fullFilePath/database.ini>")
    private boolean expected;

    @Option(name="-notExpected",
            usage="identify not-expected matches check:\njava -jar snpSniffer.jar -notExpected <delimiter> <# of delimited fields that define sample name> <fullFilePath/database.ini>")
    private boolean notExpected;

    @Option(name="-het",
            usage="calculate heterozygosity rate for all samples:\njava -jar snpSniffer.jar -het <fullFilePath/database.ini>")
    private boolean het;

    @Option(name="-help",
            usage="print options and usage")
    private boolean help;

    @Option(name="-ref",usage="Reference Fasta")
    private String ref = "hs37d5.fa";

    @Option(name="-db", usage="database flat file")
    private String db = "database.ini";

    @Option(name="-vcf", usage="genotype vcf file")
    private String vcf = "vcf.vcf";

    @Option(name="-name", usage="Sample Name")
    private String name = "samples";

    @Option(name="-delim", usage="Delimeter")
    private String delim = "_";

    @Option(name="-num", usage="number of delimeted fields")
    private String num = "1";

    // @Option(name="-hidden-str",hidden=true,usage="hidden option")
    //private String hiddenStr2 = "(default value)";


    //private boolean data;

    // receives other command line parameters than options
    @Argument
    private List<String> arguments = new ArrayList<String>();

    public static void main(String[] args) throws IOException {
        new SampleMain().doMain(args);
    }

    public void doMain(String[] args) throws IOException {
        CmdLineParser parser = new CmdLineParser(this);

        // if you have a wider console, you could increase the value;
        // here 80 is also the default
        parser.setUsageWidth(180);

        try {
            // parse the arguments.
            parser.parseArgument(args);




            if( arguments.isEmpty() )
                throw new CmdLineException(parser,"ERROR: No argument is given");

        } catch( CmdLineException e ) {

            System.err.println(e.getMessage());


            parser.printUsage(System.err);
            System.err.println();

            // print option sample. This is useful some time
            //System.err.println("  Example: java SampleMain"+parser.printExample(ALL));

            return;
        }


        if( add ) {
            addFile addNew = new addFile();
            addNew.fileAdd(vcf, db);

        }

        if( genotype ) {
            GenotypeV5 g1 = new GenotypeV5();
            g1.Run(ref,vcf);


        }

        if( check ) {
            CheckV5 c1 = new CheckV5();
            c1.read(name, db);
        }

        if( samples ) {
            CheckV5 c1 = new CheckV5();
            c1.help(db);
        }

        if( expected ) {
            ExpectedV5 g1 = new ExpectedV5();
            g1.Run(delim, num, db);
        }

        if( notExpected ) {
            notExpectedV5 g1 = new notExpectedV5();
            g1.Run(delim, num, db);
        }

        if( het ) {
            HetV5 g1 = new HetV5();
            g1.find( db );
        }



        if(help) {

            System.err.println("Usage options are");
            System.err.println("  -genotype:     generate genotypes from a bam:\n\t\t\t\t java -jar snpSniffer.jar -genotype <fullFilePath/reference.fa> <fullFilePath/sample.bam>\n");
            System.err.println("  -add:          add genotypes from a vcf:\n\t\t\t\t java -jar snpSniffer.jar -add <fullFilePath/sample.vcf> <fullFilePath/database.ini>\n");
            System.err.println("  -samples:      view all samples:\n\t\t\t\t java -jar snpSniffer.jar -check Samples <fullFilePath/database.ini>\n");
            System.err.println("  -check:        check concordance of genotypes for a sample:\n\t\t\t\t java -jar snpSniffer.jar -check <sampleName> <fullFilePath/database.ini>\n");
            System.err.println("  -expected:     identify expected matches:\n\t\t\t\t java -jar snpSniffer.jar -expected <delimiter> <# of delimited fields that define sample name> <fullFilePath/database.ini>\n");
            System.err.println("  -notExpected:  identify not-expected matches:\n\t\t\t\t java -jar snpSniffer.jar -notExpected <delimiter> <# of delimited fields that define sample name> <fullFilePath/database.ini>\n");
            System.err.println("  -het:          calculate heterozygosity rate for all samples:\n\t\t\t\t java -jar snpSniffer.jar -het <fullFilePath/database.ini>\n");
            System.err.println("  -help:         print options and usage");
        }


        // access non-option arguments
        //System.out.println("other arguments are:");
        //for( String s : arguments )
        //  System.out.println(s);
    }
}