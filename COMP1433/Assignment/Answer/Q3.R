#library for plot
require(ggplot2)

#load data
data <- read.table("points.txt", sep=",")

#number of cluster
cluster <- 3
# initialize the centroids
centroids_x <- c(40, 100, 0)
centroids_y <- c(40, 0, 100)
centroids <- cbind.data.frame(centroids_x,centroids_y)

#creating 0 matrix for distance of points to clusters
distance <- matrix(0, nrow = nrow(data), ncol = cluster)

#creating 0 vector for containing cluster number
c <- matrix(0, nrow = nrow(data), ncol = 1)

#creating 0 matrix for storing new cluster points
update_centroid <- matrix(0, nrow = nrow(centroid), ncol = ncol(data))

#stopping criteria for converged centroids
status <- 1

#iteration variable
iter <- 0

df <- data.frame(data)
initPlot <- ggplot(data,aes(x=V1, y=V2)) + geom_point()
print(initPlot)

# stop the algorithm when it reaches the Maximum number of iterations or model converges
while (iter <= 1000 && status==1) 
{
  iter <- iter + 1
  #calculate distance each data to each centroid
  for (j in 1:cluster)
  {
    for (i in 1:nrow(data))
    {
      distance[i,j] = sqrt(sum((data[i,1:ncol(data)] - centroid[j,1:ncol(centroid)])^2))
    }
  }
  
  #assign cluster number on each data
  for(i in 1:nrow(distance))
  {
    c[i] <- (which(distance[i,] == min(distance[i,]), arr.ind = T)) - 1
  }
  
  
  #calculate the new centroid based on new clustered data
  compare <- cbind(data, c)
  
  for (i in 1:cluster)
  {
    x <- subset(compare[,1:2], compare[,3] == i-1)
    
    for(j in 1:ncol(data))
    {
      update_centroid[i,j] <- mean(x[,j])
    }
  }
  
  #update the current centroid
  if(all(update_centroid == centroid)){
    status = 0
  }
  else {
    status = 1
    for (i in 1:cluster)
    {
      for (j in 1:ncol(centroid))
      {
        centroid[i,j] <- update_centroid[i,j]
      }
    } 
  }
}
plot <- ggplot(data, aes(V1, V2, color=factor(c))) + geom_point()
print(plot)

