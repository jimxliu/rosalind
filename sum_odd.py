a = int(input("Enter min: "))
b = int(input("Enter max: "))

total = 0
for num in range(a,b+1):
	if num % 2 ==1:
		total = total + num

print(total)
