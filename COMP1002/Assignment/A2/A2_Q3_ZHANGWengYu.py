# A2-Q3 ZHANG WengYu 21098431d
def mymin(l):
    """
    A function to find a minimum number in a list
    parameter:
     -l: a list
    retrun: 
     -minNum: the minimum number in l
     -minIndex: the index of the minimum number
    """
    minNum = l[0] # assume the first one is the minimum one
    minIndex = 0 # assume the first one is the minimum one
    i = 0 # store the index
    for k in l:
        if k < minNum: # chech whether k is smaller than the "minimum" number
            minNum = k # if yes, set k as the minimum one
            minIndex = i # set k's index as the minimum one's index
        i = i+1
    return minNum, minIndex # return the minimum number in l and its index

def mysort(l):
    """
    A function to sort a list in ascending order
    parameter:
     -l: a list
    return:
     -outList: a sorted list from list l
    """
    inList = list(l) # convert the input l to a list
    outList = [] # store the output list
    while(len(inList) > 0): # loop until the inList is empty
        outList.append(mymin(inList)[0]) # invoke the mymin() function to get the minimum value and append to the outList
        inList.remove(mymin(inList)[0]) # remove it from the inList
    return outList # return the sorted list

def partA():
    """A function to test mymin() function"""
    l = eval(input("Please enter a list of different numbers separated by \',\': "))
    print("The minimum number is ",mymin(l)[0],".",sep="")
    print("Its location is ",mymin(l)[1],".",sep="")
    
def partB():
    """A function to test mysort() function"""
    l = eval(input("Please enter a list of different numbers separated by \',\': "))
    print("A list of sorting values in ascending order: ",mysort(l),".",sep="")

partA() # call partA()
# partB() # call partB()
# A2-Q3 ZHANG WengYu 21098431d