# read the fasta data into a list of just sequences
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

# read the fasta data into a dictionary of id and sequences
with open("seq.fa","r") as myFile:
	fa_dic = {}
	seq_id = ""
	seq = ""
	for line in myFile:
		line = line.strip()
		if line.startswith(">"):
			seq_id = line[1:]
			fa_dic[seq_id] = ""
			continue
		seq = line
		if fa_dic[seq_id] == "":
			fa_dic[seq_id] = seq
		else:
			fa_dic[seq_id] = fa_dic[seq_id] + seq
		
