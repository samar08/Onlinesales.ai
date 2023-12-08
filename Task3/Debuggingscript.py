'''
New script:
Identified bugs are

i)in the second elif I have made it <=20, covering n==20 as the third else is for greater than 20 and the range should be from (1,n-10+1)
ii) in the third else the out should be out+lim and if n>20 the returned output is not integer, I have added int(out/2) to type convert that to int.
'''

'''
edge cases covered:
i)
for n>20 -> let n=25, output is  (25-20)*(25-20+1)/2 => 15
ii)
and if (n>10 and n<=20)
the output should be 1*(2)*(3)*....(n-10)
so if n=15 the output is 5*4*3*2*1 => 120
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