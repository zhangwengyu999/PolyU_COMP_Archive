# Q2 (a)
q2.data <- read.csv("./train_tweets.txt",header=FALSE,sep = '\t')
tweets <- c("tweetID","userID","sentiment","text")
names(q2.data)<- make.names(tweets)

# Q2 (b)
library('tokenizers')
tokens_all <- c()
texts_all <- q2.data['text']
for (val in 1:lengths(texts_all)) {
  tokens <- tokenize_words(texts_all[val,])
  tokens_all <- append(tokens_all,tokens)
}

q2.data['tokens']  = list(tokens_all)

# Q2 (c)
# store all words in the file
vocab_init <- c()
# store the frequency of word in the vocab_init ()
count <- c()
token_data <- q2.data['tokens']
for (m in 1:lengths(texts_all)) {
  tokens <- token_data[m,]
  # if a token appears multiple times in one tweet, just count it by adding one
  unique_tokens <-unique(unlist(tokens))
  for (token in unique_tokens) {
    exist_flag = ifelse(token %in% vocab_init, TRUE, FALSE)
    if (exist_flag){
      count[token] <- count[token]+1
    } else{
      count[token] <- 1
      vocab_init <- append(vocab_init, token)
    }
  }
}

# based on the frequency, filter out the word which doesn't appear in >3 tweets
vocab <- c()
for (v in vocab_init){
  if (count[v]>3){
    #vocab_filter[v] <- count[v]
    vocab <- append(vocab, v)
  }
}
print(vocab)
print(length(vocab))

#Q2 (d)
# get the num of positive, negative, and neutral tweets
sent_all <- table(q2.data["sentiment"][,1])
num_neg <- sent_all["negative"]
num_pos <- sent_all["positive"]
num_neut <- sent_all["neutral"]
num_sent <- num_neg+num_pos+num_neut

# get the tokens of positive, negative, and neutral tweets
labels <- q2.data['sentiment']
token_data <- q2.data['tokens']
pos_tokens <- c()
neg_tokens <- c()
neut_tokens <- c()
for (m in 1:lengths(texts_all)) {
  tokens <- unlist(token_data[m,])
  label <- labels[m,]
  tmp_toknes <- c()
  for (token in tokens){
    # only consider the tokens in vocab
    if (token %in% vocab){
      tmp_toknes <- append(tmp_toknes, token)
    }
  }
  if (label=='positive'){
    pos_tokens <- append(pos_tokens, tmp_toknes)
  } else if (label=='negative'){
    neg_tokens <- append(neg_tokens, tmp_toknes)
  } else {
    neut_tokens <- append(neut_tokens, tmp_toknes)
  }
}


# calculate the word probability for each sentiment class
calc_Probs <- function(tokens, vocab) {
  #Use “add-1” smoothing 
  counts <- table(c(tokens,vocab))
  # map the word probabilities into the log space
  log(counts/(length(tokens)+length(vocab)))
}
pos_probs <- calc_Probs(pos_tokens, vocab)
neg_probs <- calc_Probs(neg_tokens, vocab)
neu_probs <- calc_Probs(neut_tokens, vocab)


calc_Sentiment <- function(tweet) {
  tokens <- unlist(tokenize_words(tweet))
  test <- c()
  for (token in tokens){
    # only consider the tokens in vocab
    if (token %in% vocab){
      test <- append(test, token)
    }
  }  
  
  pos_pred <-  log(num_pos/num_sent) + sum(pos_probs[test])
  neg_pred <-  log(num_neg/num_sent) + sum(neg_probs[test])
  neu_pred <-  log(num_neut/num_sent) + sum(neu_probs[test])
  print(pos_pred)
  print(neg_pred)
  print(neu_pred)
  print('result: ')
  if (pos_pred>neg_pred&&pos_pred>neu_pred)
    print("positive")
  else if (neg_pred>pos_pred&&neg_pred>neu_pred)
    print("negative")
  else
    print('neutral')
}
calc_Sentiment("I love the banner that was unfurled in the United end last night. It read: Chelsea - Standing up against racism since Sunday")
calc_Sentiment("So Clattenburg’s alleged racism may mean end of his career; Terry, Suarez, Rio use it and can't play for a couple of weeks?")
calc_Sentiment("In our busy lives in Dubai could we just spare a moment of silence this Friday morning for the people who still wear crocs.")

