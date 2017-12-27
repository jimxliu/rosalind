# read the fasta data into a list of sequences
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

first = fa_list[0] # first sequence
# longest shared sequence
longest, x_longest = 0, 0 # length and start index in the first seq
for x in range(len(first)):
	for y in range(x,len(first)):
		seq = first[x:y+1]
		recur = 1
		for i in range(1, len(fa_list)):
			if fa_list[i].find(seq) == -1:
				recur = 0
		if recur == 1 and len(seq) > longest:
			longest = len(seq)
			x_longest = x
		if recur == 0:
			break

print(first[x_longest: x_longest + longest])



	

