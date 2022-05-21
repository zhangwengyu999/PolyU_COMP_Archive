# A2-Q4 ZHANG WengYu 21098431d
def changeString(s,c,i):
    """
    A function to chenge the a character of a String to another character
    parameter:
     -s: a String to change
     -c: a character to be updated in that String
     -i: a location in the string to be changed
    return:
     a String s with being changed its ith character to character c
     OR an error when i is out of range
    """
    if (0 < i < len(s)-1):
        return s[0:i]+c+s[i+1:]
    else:
        return "Error! Location out of range!"

def main():
    """A function to test changeString() function"""
    inStr = input("Input a string: ")
    inIndex = eval(input("Input a zero-based location in a string to be changed: "))
    inChar = input("Input a character to be updated in that location: ")
    print("The string updated: "+changeString(inStr,inChar,inIndex))

main()
# A2-Q4 ZHANG WengYu 21098431d