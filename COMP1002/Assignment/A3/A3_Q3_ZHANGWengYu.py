# ZHANG WengYu 21098431d A3-Q3
def mySplit(inStr, sepList):
    """ my split function in Python

    Args:
        inStr (string): the string to be split
        sepList ([type]): list of separators

    Returns:
        outList: a list of words after split
    """
    outList = []
    left = 0
    right = 0
    for str in inStr:
        if str in sepList:
            if (left!=right):
                outList.append(inStr[left:right])
            left = right+1
        right += 1
    if (left!=right):
        outList.append(inStr[left:right])
    return outList

def a3q3():
    """
    test function for mySplit() function
    """
    sepList = [" ",".","!","?",";",","]
    strWord = input("Please input a paragraph: ")
    print("A list of words from your input:")
    print(mySplit(strWord, sepList))

a3q3()
# a for apple! b for boy; and c for cat, d for god. but e for egg? No grammar mistakes checking!!
# ZHANG WengYu 21098431d A3-Q3