# read the fasta data into a dictionary
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
		
# find all O3 overlap nodes
def is_overlap(x,y):
	if fa_dic.get(x)[-3:] == fa_dic.get(y)[0:3]:
		return True
	return False

seq_list = list(fa_dic.keys())
for i in range(len(seq_list)):
	for j in range(i+1, len(seq_list)):
		if is_overlap(seq_list[i],seq_list[j]):
			print(seq_list[i], seq_list[j])
		if is_overlap(seq_list[j],seq_list[i]):
			print(seq_list[j], seq_list[i])


	
