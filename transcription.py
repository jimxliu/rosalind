myFile = open("dna.txt","r")
dna = myFile.read()
rna = dna.replace("T","U")
print(rna)
myFile.close()