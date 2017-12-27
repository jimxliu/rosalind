with open("offsprings.txt","r") as f:
	gen_list = f.readline().strip().split(" ")
	for i in range(len(gen_list)):
		gen_list[i] = int(gen_list[i])

def cal_dominant(l):
	num = 0
	num += l[0] + l[1] + l[2] + l[3] * 0.75 + l[4] * 0.5 
	num = num * 2
	return round(num, 1)

print(cal_dominant(gen_list))
