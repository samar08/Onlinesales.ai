'''
New script:
Identified bugs are

i)in the second elif I have made it <=20, covering n==20 as the third else is for greater than 20 and the range should be from (1,n-10+1)
ii) in the third else the out should be out+lim and if n>20 the returned output is not integer, I have added int(out/2) to type convert that to int.
'''

def compute(n):
    if n < 10:
        out = n ** 2
    elif n <= 20:
        out = 1
        for i in range(1, n-10+1):
            out *= i
    else:
        lim = n - 20
        out = (lim*lim)
        out=out+lim
        out=int(out/2)
        
    print(out)


n = int(input("Enter an integer: "))
compute(n)