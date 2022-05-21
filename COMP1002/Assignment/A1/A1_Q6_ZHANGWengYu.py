# A1-Q6 ZHANG WengYu 21098431d
x, y, z = eval(input("Please enter the lengths of 3 sides of a triangle separated by 2 commas (e.g. 3,4,5): "))
s = (x+y+z)/2 # get the semi-perimeter 
outArea = (s*(s-x)*(s-y)*(s-z))**0.5 # get the area
if ((s-x)*(s-y)*(s-z) > 0): # check if the triangle is legel
    print("The area of this triangle is",outArea) # print the area
else: # if the triangle is illegel
    print("Error! Please try again!")
# A1-Q6 ZHANG WengYu 21098431d