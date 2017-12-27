with open("motif.txt","r") as f:
	s = f.readline().strip()
	t = f.readline().strip()

locus = s.find(t)
l = []
while locus != -1:
	l.append(locus+1)
	locus = s.find(t,locus + 1)

print(" ".join(str(n) for n in l))
