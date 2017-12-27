with open("dict.txt","r") as f:
	s = f.readline().strip()

l = s.split(" ")
my_dict = {}
for word in l:
	if word in my_dict:
		my_dict[word] += 1
	else:
		my_dict[word] = 1

for key, value in my_dict.items():
	print(key,value)
