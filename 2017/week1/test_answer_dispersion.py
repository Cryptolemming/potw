import solution

def run_tests():
    t = 0
    f = 0
    for i in range(1,101):
        answer = str(solution.main("./tests/"+str(i)+".test"))
        print(answer)
        if answer == "True":
            t += 1
        else:
            f += 1
    return [float(t/(t+f)), float(f/(t+f))]

def main():
    L = run_tests()
    print(str(L[0])+":"+str(L[1]))

main()
