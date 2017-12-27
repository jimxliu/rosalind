# read the fasta data into a list
with open("seq.fa","r") as myFile:
	fa_list = []	
	seq = ""
	for line in myFile:
		line = line.strip()
		if line.startswith(">"):
			fa_list.append("")
			continue
		seq = line
		if fa_list[-1] == "":
			fa_list[-1] = seq
		else:
			fa_list[-1] = fa_list[-1] + seq

# make a 4xn list containing the profile
n = len(fa_list[0])
profile = []
for i in range(4):
	row = []
	for j in range(n):
		row.append(0)
	profile.append(row)

# count the number of occurences of each nucleotide 
for seq in fa_list:
	for k in range(n):
		if seq[k] == "A":
			profile[0][k] += 1
		if seq[k] == "C":
			profile[1][k] += 1
		if seq[k] == "G":
			profile[2][k] += 1
		if seq[k] == "T":
			profile[3][k] += 1

# assemble the consensus sequence 
# by adding together the nucleotide with the largest frequency 
# at one position
nuc_map = {0:"A", 1:"C", 2:"G", 3:"T"}
consensus = ""
for k in range(n):
	max_nuc = ""
	max_occur = 0
	for i in range(4):
		if profile[i][k] > max_occur:
			max_occur = profile[i][k]
			max_nuc = nuc_map[i]
	consensus += max_nuc

print(consensus)
print("A:", " ".join(str(x) for x in profile[0]))
print("C:", " ".join(str(x) for x in profile[1]))
print("G:", " ".join(str(x) for x in profile[2]))
print("T:", " ".join(str(x) for x in profile[3]))




