k = int(input("Enter homo dominant: "))
m = int(input("Enter hetero dominant: "))
n = int(input("Enter homo recessive: "))

def Pr_dominant_phen(k,m,n):
	s = k + m + n
	prob = 1 - (n * (n-1) + m * n + m * (m-1) / 4)/(s*(s-1))
	return round(prob, 5)

print(Pr_dominant_phen(k,m,n))

