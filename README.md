## About snpSniffer
snpSniffer is a genotype based tool to ensure no cross sample contamination or swaps have occured using next generation sequencing data. It currently works on whole genome, exome and RNA-Seq data.

## Identifying mixups involves 3 steps:

1. Generate the genotypes in a vcf format at specific genomic loci

2. Adding the genotypes generated to a flat file "database.ini" provided

3. Compare the genotypes for samples of interest, examine the snpSniffer output and infer if any mixups occurred

All the above steps can be run using snpSniffer.


## Installation:

✓ Download snpSniffer.jar, geno, positions.txt and database.ini 

✓ Copy snpSniffer.jar, geno and positions.txt to a location in the PATH

✓ Copy database.ini to a location where you want to run snpSniffer; preferably where the aligned reads are present

✓ samtools and bcfutils should be installed at a location in the PATH


## Usage
### 1) To generate genotypes from a bam:
java -jar snpSniffer.jar -genotype <fullFilePath/reference> <fullFilePath/BAM> 
        Alternately, genotypes can be generated using:

geno <fullFilePath/reference> <fullFilePath/BAM>

 *Users should make sure bam is indexed

### 2) To add genotypes from a vcf:

java -jar snpSniffer.jar -add <fullFilePath/VCF fileName> <fullFilePath/database.ini>

### 3) To view all samples:

java -jar snpSniffer.jar -check Samples <fullFilePath/database.ini>

### 4) To check concordance of genotypes for a sample:
java -jar snpSniffer.jar -check <sampleName> <fullFilePath/database.ini>

### 4) To check for cross sample contamination:
java -jar snpSniffer.jar -het <fullFilePath/database.ini>

### 5) To identify expected matches:
java -jar snpSniffer.jar -expected <delimiter> <# of delimited fields that define sample name> <fullFilePath/database.ini>

### 6) To identify not-expected matches::

java -jar snpSniffer.jar -notExpected <delimiter> <# of delimited fields that define sample name> <fullFilePath/database.ini>

### 7) For help:

java -jar snpSniffer.jar -help


### Example usage:

Generate the genotypes in a vcf format at specific genomic loci:

	java -jar ~/local/bin/snpSniffer.jar -genotype /lustre/vyellapa/reference.fa /lustre/vyellapa/sample1.bam

Adding the genotypes generated to a flat file "database.ini," provided
Step 1, will generate a vcf having the same name as the bam in the same directory, this will be added to database.ini with same name:

	java -jar ~/local/bin/snpSniffer.jar -add /lustre/vyellapa/ALMC1_RNA.vcf /lustre/vyellapa/sample_database.ini

Compare the genotypes for samples of interest(after 2 or more vcf's are added), examine the snpSniffer output and infer if any mixups occurred:

	java -jar ~/local/bin/snpSniffer.jar -check ALMC1_RNA /lustre/vyellapa/sample_database.ini

Identify all expected matches using a 0.76 threshold value (a text file snpSniffer_output.txt with concordance ratio's 

for all samples will also be generated :

	java -jar ~/local/bin/snpSniffer.jar -expected "_" 1 /lustre/vyellapa/sample_database.ini

Identify all not-expected matches using a 0.76 threshold value (a text file snpSniffer_output.txt with concordance ratio's 

for all samples will also be generated :

	java -jar ~/local/bin/snpSniffer.jar -notExpected "_" 1 /lustre/vyellapa/sample_database.ini


### Example output:

Step 3 above should generate lines of output, depending on number of samples, similar to the one below. It shows that between ALMC1 and ALMC2, 121 genotypes were obtained with good quality out of which 116 positions match. The "ratio" field is the ratio of match to count and ratio>0.76 signifies that the two samples match.

In the output given below, ALMC1 and ALMC2 have a ratio of ~0.96 suggesting both sequences come from the same individual. However, ALMC1 and XG6 have have a ratio of ~0.35 suggesting that the sequences do not come from the same individual.

	ALMC1_RNA & ALMC1_EXOME count=121.0 match=116.0 ratio=0.9586776859504132

	ALMC1_RNA & XG6_EXOME count=114.0 match=40.0 ratio=0.3508771929824561




#### ACADEMIC AND RESEARCH LICENSE
Copyright (c) 2012, The Translational Genomics Research Institute.
All rights reserved

1. License. The Translational Genomics Research Institute (TGen) hereby
grants you a non-exclusive, non-transferable right to use the snpSniffer
software (the "Software"), subject to the terms and conditions of this
Academic and Research License (License). You should carefully read this
License before using the Software. YOUR DOWNLOAD AND USE OF THE SOFTWARE
INDICATES YOUR ACCEPTANCE OF THIS LICENSE AND ALL OF THE FOLLOWING TERMS
AND CONDITIONS.

2. Academic and Research Purposes. This License is limited to, and you
may use the Software solely for, your own internal and non-commercial
use for academic and research purposes. Without limiting the foregoing,
you may not use the Software as part of, or in any way in connection with
the production, marketing, sale or support of any commercial product or
service or for any governmental purposes.

3. Commercial Use. If you desire to use the Software for any commercial
or governmental purpose whatsoever, you agree to negotiate in good faith
to obtain an appropriate license from TGen prior to such commercial or
governmental use, unless TGen otherwise consents in writing. TGen shall
have no obligation to grant such license to you, and may grant exclusive
or non-exclusive licenses to third parties.  You agree to notify TGen of
any inquiries you may receive regarding commercial or governmental use
of the Software or its modifications. Any inquiries or notices regarding
commercial or governmental use of the Software should be directed to
jkeats@tgen.org.

4. Disclaimer of Warranty. Because the Software is licensed free of
charge, TGen makes no warranties or representations of any kind
regarding the Software or any modifications or documentation relating to
the Software, including without limitation, that the Software is free
from errors. TGen is under no obligation to provide any services, by way
of maintenance, update, or otherwise with respect to the Software. THE
SOFTWARE AND ANY MODIFICATIONS OR DOCUMENTATION RELATED THERETO ARE
LICENSED "AS IS" AND WITHOUT WARRANTIES AS TO PERFORMANCE, MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE OR ANY OTHER WARRANTIES WHETHER
EXPRESSED OR IMPLIED. THE ENTIRE RISK AS TO THE QUALITY AND PERFORMANCE
OF THE SOFTWARE IS WITH YOU. SHOULD THE SOFTWARE PROVE DEFECTIVE, YOU
ASSUME THE COST OF ALL NECESSARY SERVICING, REPAIR OR CORRECTION.

5. Limitation of Liability. IN NO EVENT WILL TGEN BE LIABLE TO YOU OR
ANY THIRD PARTIES FOR DAMAGES OF ANY KIND, INCLUDING WITHOUT LIMITATION,
GENERAL, SPECIAL, INCIDENTAL OR CONSEQUENTIAL DAMAGES, ARISING OUT OF
THE USE, OR INABILITY TO USE, THE SOFTWARE, INCLUDING WITHOUT
LIMITATION, ANY LOSS OF DATA, DATA BEING RENDERED INACCURATE, LOSSES
SUSTAINED BY YOU OR THIRD PARTIES, OR A FAILURE OF THE SOFTWARE TO
OPERATE WITH OTHER PROGRAMS, EVEN IF TGEN HAS BEEN ADVISED OF THE
POSSIBILITY OF SUCH DAMAGES.

6. No Other Rights Granted. Except as expressly stated herein, this
License does not constitute a grant to you, or an intention or
commitment to grant to you, any right, title or interest in or to the
Software or TGen's trade secrets or other intellectual property
related to the Software.

7. No Assignment. This License is personal to the person or entity
downloading and using the Software pursuant hereto and you may not sell,
assign or transfer it to any other party.

8. Use of Names. You may not use the names 'The Translational
Genomics Research Institute' or 'TGen', or any variations or
adaptations thereof, or any marks related thereto, in any publicity or
advertising without the prior written consent from TGen in each case,
which may be granted or withheld in TGen's sole discretion.  In any work
or product derived from the use of this Software, proper attribution of
the authors as the source of the Software or data must be made.

9. Governing Law. This License shall be governed by the laws of the
State of Arizona, without regard to choice of law principles.  Any legal
action or proceeding arising out of or relating to this License must be
instituted in a state or federal court in Phoenix, Arizona and you
irrevocably submit to such jurisdiction.

10. Termination. This License is effective until terminated. TGen may
terminate this License upon 60 days written notice to you.  You may
terminate this License at any time by destroying all copies of the
Software and any documentation or other associated materials in your
possession. This License will terminate immediately without notice from
TGen if (a) you fail to comply with any of the terms and conditions of
this License or (b) the Software (or any component thereof or any
documentation or modification) is found to infringe upon the
intellectual property rights of any third party. Upon termination, you
must destroy all copies of the Software and any documentation or other
associated materials.
