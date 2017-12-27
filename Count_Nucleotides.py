baseA = 0
baseT = 0
baseC = 0
baseG = 0
myFile = open("dna.txt","r")
seq = myFile.read()

for nuc in seq:
	if nuc == "A":
		baseA += 1
	if nuc == "T":
		baseT += 1
	if nuc == "C":
		baseC += 1
	if nuc == "G":
		baseG += 1

myFile.close()

print('{} {} {} {}'.format(baseA, baseC, baseG, baseT))

