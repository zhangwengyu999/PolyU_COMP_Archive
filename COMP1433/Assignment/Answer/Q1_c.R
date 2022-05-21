# assume that the number of Agrippa and Ptolemy is 1 and 2, separately
numbers = 1:100

# calculate the situation where 1 and 2 are not in the samples
c <- 0

# simulate the experiment 10000 times
for (val in 1:1000000) {
  out<- sample(x=numbers, size=500, replace=TRUE)
  if(!(1 %in% out) && !(2 %in% out)) c <- c+1
}
print(c/1000000)

