def calc_gc(seq):
	length = len(seq)
	if len(seq) == 0:
		return 0
	num = 0
	for nuc in seq:
		if nuc == "C" or nuc == "G":
			num += 1
	return round(float(num/length*100),6)

# get the sequence with the largest GC content
def get_maxGC(dic):
	max_id = ""
	max_gc = 0
	for key in dic.keys():
		if calc_gc(dic.get(key)) > max_gc:
			max_id = key
			max_gc = calc_gc(dic.get(key))
	return (max_id, max_gc)
			
fa_dic = {}

# read the fasta data into a dictionary
with open("seq.fa","r") as myFile:
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
		
print("{}\n{}".format(get_maxGC(fa_dic)[0],get_maxGC(fa_dic)[1]))


