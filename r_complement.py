myFile = open("dna.txt","r")
seq = myFile.read()
r_seq = seq[-2::-1]
r_seq = r_seq.replace("T","U")
r_seq = r_seq.replace("A","T")
r_seq = r_seq.replace("U","A")
r_seq = r_seq.replace("C","B")
r_seq = r_seq.replace("G","C")
r_seq = r_seq.replace("B","G")
print(r_seq)
myFile.close()
																																																																																																																																																																										
