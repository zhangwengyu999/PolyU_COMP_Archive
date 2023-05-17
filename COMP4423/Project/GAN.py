# -*- coding: utf-8 -*-
from torchvision import datasets, transforms
import torch
import numpy as np
from torch import nn, optim
import matplotlib.pyplot as plt
import torch.nn.functional as F
torch.__version__

torch.manual_seed(42)

# You need to implement this by yourself
class FashionDataset(Dataset):
    def __init__(self, transform=None):
       {}
    def __len__(self):
       {}
    def __getitem__(self, idx):
       {}

transform = transforms.ToTensor()
train = FashionDataset(transform = transform)
train_loader = torch.utils.data.DataLoader(train, batch_size = 256, shuffle = True)

# construct 
class generator(nn.Module):
  def __init__(self):
    super().__init__()
    #100->32->64->128->784
    self.dense0 = nn.Linear(100, 32) #receive a vector of 100 numbers random
    self.dense1 = nn.Linear(32, 64)
    self.dense2 = nn.Linear(64, 128)
    self.dense3 = nn.Linear(128, 784)
    self.dropout = nn.Dropout(0.3)

  def forward(self, X):
    X = self.dropout(F.leaky_relu(self.dense0(X), 0.2))# leaky_relu (value * factor)
    X = self.dropout(F.leaky_relu(self.dense1(X), 0.2))
    X = self.dropout(F.leaky_relu(self.dense2(X), 0.2))
    X = torch.tanh(self.dense3(X)) # literature 
    X = X.view(X.shape[0], 28, 28) # convert to image (matrix)
    return X

class discriminator(nn.Module):
  def __init__(self):
    super().__init__()

    self.dense0 = nn.Linear(784, 128)
    self.dense1 = nn.Linear(128, 64)
    self.dense2 = nn.Linear(64, 32)
    self.dense3 = nn.Linear(32, 1)
    self.dropout = nn.Dropout(0.3)
  def forward(self, X):
    X = X.view(X.shape[0], 28*28)
    X = self.dropout(F.leaky_relu(self.dense0(X), 0.2))
    X = self.dropout(F.leaky_relu(self.dense1(X), 0.2))
    X = self.dropout(F.leaky_relu(self.dense2(X), 0.2))
    X = self.dense3(X)
    return X

G = generator()
D = discriminator()

G_optimizer = optim.Adam(G.parameters(), lr = 0.002)
D_optimizer = optim.Adam(D.parameters(), lr = 0.002)

criterion = nn.BCEWithLogitsLoss() # combine sigmoid with and BCELoss

device = torch.device('cuda') if torch.cuda.is_available else torch.device('cpu')
device

G.to(device)
D.to(device)

for epoch in range(100):
    D_running_loss = 0
    G_running_loss = 0
    
    for i, (images_real, _) in enumerate(train_loader):
        batch_size = images_real.size(0)
        images_real = images_real * 2 - 1
        images_real = images_real.to(device)

        # Train The Generator
        G_optimizer.zero_grad()
        random = np.random.uniform(low=-1., high=1., size=(batch_size, 100))
        random = torch.from_numpy(random).float().to(device)
        images_false = G.forward(random)
        output_false = D.forward(images_false)
        labels_false = torch.ones(batch_size).to(device)
        G_loss = criterion(output_false.view(*labels_false.shape), labels_false)
        G_loss.backward()
        G_optimizer.step()

        # Train The Discriminator
        D_optimizer.zero_grad()
        outputs_real = D.forward(images_real)
        labels_real = (torch.ones(batch_size) * 0.9).to(device)
        D_loss_real = criterion(outputs_real.view(*labels_real.shape), labels_real)

        random = np.random.uniform(-1., 1., (batch_size, 100))
        random = torch.from_numpy(random).float().to(device)
        images_false = G.forward(random)
        outputs_false = D.forward(images_false)
        labels_false = torch.zeros(batch_size).to(device)
        D_loss_false = criterion(outputs_false.view(*labels_false.shape), labels_false)

        D_loss = D_loss_real + D_loss_false
        D_loss.backward()
        D_optimizer.step()

        D_running_loss += D_loss.item()
        G_running_loss += G_loss.item()
  
  
    # print the loss after each epoch
    D_running_loss /= len(train_loader)
    G_running_loss /= len(train_loader)
    print('EPOCH {:03d} finalized: discriminator loss {:03.6f} - gererator loss {:03.6f}      '.format(epoch + 1, D_running_loss, G_running_loss))

    fig, ax = plt.subplots(1, 5, figsize=(10,5))
    for i in range(5):
      ax[i].imshow(images_false.cpu().detach().numpy()[i].reshape(28, 28), cmap='gray')
      ax[i].xaxis.set_visible(False)
      ax[i].yaxis.set_visible(False)
    plt.show()

random = np.random.uniform(-1., 1., (20, 100))


random = torch.from_numpy(random).float().to(device)

G.eval()
output  = G.forward(random)

output = outputs_false.cpu().detach().numpy()

for i in range(output.shape[0]):
  plt.imshow(output[i, :].squeeze(), cmap='gray')
  plt.show()