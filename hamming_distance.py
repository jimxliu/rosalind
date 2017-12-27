pm = 0
with open("dna.txt","r") as f:
	seq1 = f.readline()
	seq2 = f.readline()
	for i in range(len(seq1)):
		if seq1[i] != seq2[i]:
			pm += 1

print(pm)
