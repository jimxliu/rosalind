with open("file.txt","r") as f:
	s_list = f.readlines()

for i in range(1,len(s_list)+1):
	if i % 2 == 0:
		print(s_list[i-1].strip())
