table = {"UUU":"F", "UUC":"F", "UUA":"L", "UUG":"L",
         "UCU":"S", "UCC":"S", "UCA":"S", "UCG":"S",
         "UAU":"Y", "UAC":"Y", "UAA":"STOP", "UAG":"STOP",
         "UGU":"C", "UGC":"C", "UGA":"STOP", "UGG":"W",
         "CUU":"L", "CUC":"L", "CUA":"L", "CUG":"L",
         "CCU":"P", "CCC":"P", "CCA":"P", "CCG":"P",
         "CAU":"H", "CAC":"H", "CAA":"Q", "CAG":"Q",
         "CGU":"R", "CGC":"R", "CGA":"R", "CGG":"R",
         "AUU":"I", "AUC":"I", "AUA":"I", "AUG":"M",
         "ACU":"T", "ACC":"T", "ACA":"T", "ACG":"T",
         "AAU":"N", "AAC":"N", "AAA":"K", "AAG":"K",
         "AGU":"S", "AGC":"S", "AGA":"R", "AGG":"R",
         "GUU":"V", "GUC":"V", "GUA":"V", "GUG":"V",
         "GCU":"A", "GCC":"A", "GCA":"A", "GCG":"A",
         "GAU":"D", "GAC":"D", "GAA":"E", "GAG":"E",
         "GGU":"G", "GGC":"G", "GGA":"G", "GGG":"G",}

with open("dna.txt","r") as myFile:
	dna_seq = myFile.readline().strip()

p_seq = ""
locus = dna_seq.find("AUG")
if locus != -1:
	while locus + 2 < len(dna_seq):
		codon = table[dna_seq[locus:locus+3]]
		if codon == "STOP":
			break
		p_seq += codon
		locus += 3

print(p_seq)
			
