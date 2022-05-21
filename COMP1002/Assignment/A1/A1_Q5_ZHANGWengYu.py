# test cases: 17-10001, 40-101000, 6-110
# A1-Q5 ZHANG WengYu 21098431d
inNum = eval(input("Enter a positive integer: "))  # an integer given by the user
outNum = 0 # store the output
exp = 1 # initial the exponent
if (inNum > 0): # Check inNum is a positive integer 
    while (inNum != 0): # repeat until inNum is equal to 0 
        rNum = inNum % 2 # Get the reminder 
        outNum = outNum + rNum * exp # move the reminder to the right position based on the exponent value
        exp *= 10 # let exponent points to next higher position
        inNum //= 2 # Get the floored quotient of inNum and 2
else: # If it is negative 
    print("Please enter a positive integer!") 
print("Its binary number is:", outNum) # print out the result in binary
# A1-Q5 ZHANG WengYu 21098431d