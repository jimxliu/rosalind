n = int(input("Enter n: "))
m = int(input("Enter m: "))


def mortal_fr(x, y):
	bunnies_gen = [] # generations of bunnies
	for i in range(m):
		bunnies_gen.append(0)
	for j in range(n):
		if j == 0:
			bunnies_gen[0] = 1
		else:
			newly_born = 0
			for k in range(m-1,0,-1):
				newly_born += bunnies_gen[k]
				bunnies_gen[k] = bunnies_gen[k-1] # replacement problem
			bunnies_gen[0] = newly_born
	return sum(bunnies_gen)


print(mortal_fr(n,m))
