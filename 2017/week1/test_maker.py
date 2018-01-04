'''
Line 1:
    number of lines
Line 2:
    1 <= N <= 100 integer values of size 0 <= n <= 25
Line 3:
    1 <= C <= .8*N+1 = unique pair count requirement
    1 <= S <= 50 = pair sum requirement
'''

import random

for i in range(1,101):
    N = random.randint(0,100)
    c = int(.8*N+1)
    C = random.randint(1,c)
    S = random.randint(1,50)
    with open("./tests/"+str(i)+".test", 'w') as file:
        file.write(str(2) + '\n')
        L = [random.randint(0,25) for _ in range(N)]
        for n in L:
            file.write(str(n)+" ")
        file.write('\n' + str(C) + ' ' + str(S))
