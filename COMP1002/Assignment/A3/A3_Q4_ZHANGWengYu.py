# ZHANG WengYu 21098431d A3-Q4
def mySplit(inStr, sepList):
    # function developed in ZHANG WengYu 21098431d A3-Q3
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

def mySort(inDict):
    """my sort funciton implements bubble sort to sort a dictionary

    Args:
        inDict (dictionary): a dictionary to sort

    Returns:
        outDict: a sorted dictionary
    """
    sortedList = list(inDict)
    outDict = dict()
    # Bubble Sort
    for i in range(len(sortedList)): 
        for j in range(len(sortedList)-i-1):
            if (inDict[sortedList[j]]<inDict[sortedList[j+1]]):
                sortedList[j], sortedList[j+1] = sortedList[j+1], sortedList[j]
            if (inDict[sortedList[j]]==inDict[sortedList[j+1]] and sortedList[j]>sortedList[j+1]): # with same frequency
                sortedList[j], sortedList[j+1] = sortedList[j+1], sortedList[j]
    
    for i in sortedList:
        outDict[i] = inDict[i]

    return outDict

def wordFeq(inStr):
    """function to check the frequency of words in a string and return the data in a sorted dictionary

    Args:
        inStr (string): the string to be checked

    Returns:
        [mySort(dictS)]: a sorted dictionary with words and their frequencies
    """
    dictS = dict()
    sepList = [" ",".","!","?","'","/","\\","&","(",")",";","[","]","\n",",",":"]
    s = mySplit(inStr,sepList)
    for i in s:
        if (i not in dictS):
            dictS[i] = 1
        elif (i in dictS):
            dictS[i] += 1
    return mySort(dictS)

def a3q4():
    """a fucntion to read file and test wordFeq() function and print out a table
    """
    fName = input("Please enter the text file name: ")
    f = open(fName,"r")
    fStr = f.read()
    print("Content in the file:")
    print(fStr)
    wordDict = wordFeq(fStr)

    maxSize = 0
    for item in wordDict:
        if (len(item)>maxSize):
            maxSize = len(item)

    print("Words and their Frequencies are shown as below")
    print("{0:^{3}}{1:}{2:^15}".format("Words","|","Frequencies",maxSize+6))
    print("{:->{}}".format("",maxSize+21))

    for k,v in wordDict.items():
        print("{0:^{3}}{1:}{2:^15}".format(k,"|",v,maxSize+6))

a3q4()
# ZHANG WengYu 21098431d A3-Q4