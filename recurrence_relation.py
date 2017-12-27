n = int(input("Enter n: "))
k = int(input("Enter k: "))
def rabbit(n):
	if n == 1:
		return 1
	if n == 2:
		return 1
	if n > 2:
		return rabbit(n-1) + k * rabbit(n-2)

print(rabbit(n))
