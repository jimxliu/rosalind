with open("str_list.txt","r") as f:
	s = f.readline().strip()
	l = f.readline().strip().split(" ")
	a = int(l[0])
	b = int(l[1])
	c = int(l[2])
	d = int(l[3])
	print("{} {}".format(s[a:b+1],s[c:d+1]))
