#############################################################################
#                                                                           #
# This is a sampler to use our Poly Smart Services                          #
# for hand landmark detection.                                              #
#                                                                           #
# Step 1: make sure your camera works properly;                             #
# Step 2: install OpenCV and requests-toolbelt for python(3);               #
# Step 3: change polysmart_svr_url to the one assigned for you.             #
#                                                                           #
# Note: It's only avaliable over PolyU intranet.                            #
# Contact Xiao-Yong Wei for more details.                                   #
#                                                                           #
#############################################################################

import cv2
import requests
from requests_toolbelt.multipart.encoder import MultipartEncoder
import numpy as np, random, time

cap = cv2.VideoCapture(0)
WindName = "Toy Program @ COMP 4423"
cv2.namedWindow(WindName)
cv2.resizeWindow(WindName, 1024, 768)

landmarks_on=True # draw landmarks or not
play_mode_on=False # put game in playing or testing mode

#polysmart_svr_url = 'http://127.0.0.1:8000/'
polysmart_svr_url = 'http://158.132.255.32:8088/'

polysmart_facerecg_svr = polysmart_svr_url+'handdetect/'


def detect(pic):
    _, im_buf = cv2.imencode(".jpg", pic)
    byte_im = im_buf.tobytes()

    data = MultipartEncoder(fields={'file': ('img.jpg', byte_im)})
    response = requests.post(polysmart_facerecg_svr, data=data, headers={
        'Content-Type': data.content_type})
# print((response.status_code,response.json()))
    retJson = response.json()
    return retJson['results'] if retJson['code'] >= 0 else []

connections = [[4, 3, 2, 1, 0],# thumb
               [8, 7, 6, 5],# index
               [12, 11, 10, 9],# middle
               [16, 15, 14, 13],# ring
               [20, 19, 18, 17, 0], #pinky
               [3, 5, 9, 13, 17]# palm
               ]

def draw_landmarks(image, landmarks):
    h, w, c = image.shape
    # print([h, w, c])
    id2cords = {}
    for lm in landmarks:
        idx, ftx, fty = lm['idx'], int(lm['x']*w), int(lm['y']*h)
        id2cords[idx] = [ftx, fty]
    if not landmarks_on: return image,id2cords
    for line in connections:
        pts = [[id2cords[idx][0], id2cords[idx][1]] for idx in line]
        pts = np.array(pts, np.int32)
        pts = pts.reshape((-1, 1, 2))
        image = cv2.polylines(image, [pts], False, (0, 128, 128), 4)
    for idx in id2cords:
        image = cv2.circle(
            image, (id2cords[idx][0], id2cords[idx][1]), 10, (224, 224, 0), 5)
    image = cv2.circle(
        image, (id2cords[8][0], id2cords[8][1]), 15, (0, 0, 128), 5)
    return image, id2cords

def extrac_feature(id2cords):
    feat=[]
    for id in range(21):
        a=np.array(id2cords[id])
        for tag in range(id+1,21):
            b=np.array(id2cords[tag])
            dist=np.linalg.norm(a-b)/800 # normalize the distane in the range of [0,1] by assuming the 800 is the maximum dist possible
            feat.append(dist)
    print('sum feat=',sum(feat))
    return feat

feat_x,feat_y=np.load('feat_x.npy'),np.load('feat_y.npy')

######################### run cross-validation #########################
from sklearn import svm
from sklearn.model_selection import cross_val_score
model = svm.SVC(kernel='rbf')
scores = cross_val_score(model, feat_x, feat_y, cv=10)
print('cross validation scores: ',scores)
#print(feat_x.shape)

######################### visualize the data #########################
from sklearn.manifold import TSNE
import seaborn as sns
import matplotlib.pyplot as plt
sns.set(rc={'figure.figsize':(10,8)})
palette = sns.color_palette("bright", 3)
tsne = TSNE()
X_embedded = tsne.fit_transform(feat_x)
label2str={1:'Paper',2:'Rock',3:'Scissor'}
markers=[label2str[feat_y[i]] for i in range(len(feat_y))]
sns.scatterplot(x=X_embedded[:,0], y=X_embedded[:,1], markers=markers, hue=markers, legend='full', palette=palette)
plt.show()

######################### fit the model #########################
model.fit(feat_x,feat_y)
print('Fitting done!')


######################### test or play the game #########################
# further split the status into 0: testing, 1; show hints, 2; counting on Paper; 3: counting on Rock; 
# 4: counting on Scissors; 5: show results
# Both User and Computer should shoot at status 4. 
status_texts=['Testing ...', 'Let''s count Paper-Rock-Scissors and shoot when we say Scissors', \
    'Paper ...','Rock ...','Scissors ...', 'Result ...']
status_durations=[0,4,1,1,2,4] # how long each status lasts (in seconds)
status_check_points=[sum(status_durations[:i+1]) for i in range(len(status_durations))]
status_id=0
game_start_time=time.time()
votes={} # user's move might be recoginized multiple times, we pick the dominate move as the final
computer_move=1
while True:
    success, image = cap.read()
    if not success:
        print("Open cam failed ....")
        continue
    image = cv2.flip(image, 1)
    overlay=image.copy()
    imageRGB = cv2.cvtColor(image, cv2.COLOR_BGR2RGB)
    #print('Reading frame done!')
    
    # show status text
    if status_id>0: # in game mode
        dur=time.time()-game_start_time
        status_id=next((i for i in range(len(status_check_points)) if dur < status_check_points[i]),-1)
        if status_id==-1:
            status_id, game_start_time, votes = 1, time.time(), {} # restart a game  
    overlay=cv2.rectangle(overlay, (125,40),(1500,120),color=(213, 231, 242),thickness=-1)
    image=cv2.addWeighted(overlay, 0.5, image, 0.5, 0)       
    image=cv2.putText(image,status_texts[status_id],(150,100),cv2.FONT_HERSHEY_SIMPLEX,1,(70, 62, 57),2)
    #print('Display heading done!')

    id2cords = {}
    if status_id in [0,4]:
        results = detect(imageRGB)
        #print('Detection done!')
        
        if len(results) == 0:
            print("Nothing detected ...")
        else:
            image, id2cords = draw_landmarks(image, results[0]['landmarks'])
            feat=np.array([extrac_feature(id2cords)])
            print(feat.shape)
            label=model.predict(np.array(feat))[0]
            print('label=',label)
            
            if status_id==0:
                image=cv2.putText(image,label2str[label],(250,120),cv2.FONT_HERSHEY_TRIPLEX,5,(134, 152, 109),3)
            if status_id==4:
                computer_move=random.randint(1,3)
                votes[label]=votes[label]+1 if label in votes else 1
                
    if status_id==5:
        print('votes=',votes)
        if votes=={}:
            moves_str="Yours: {}, Mine: {}".format('Nothing',label2str[computer_move])
            res= 'You Lost!'
        else:
            users_final=next(key for key in votes if votes[key]==max(votes.values()))
            #print(users_final,max(votes.values()),computer_move)
            moves_str="Yours: {}, Mine: {}".format(label2str[users_final],label2str[computer_move])
            draw=users_final == computer_move
            res='Draw!'
            if not draw:
                user_win = users_final<computer_move if abs(users_final-computer_move)==1 else users_final>computer_move
                res='You Won!' if user_win else 'You Lost!'
        image=cv2.putText(image,moves_str,(150,180),cv2.FONT_HERSHEY_SIMPLEX,2,(207, 233, 245),3)
        image=cv2.putText(image,res,(150,320),cv2.FONT_HERSHEY_TRIPLEX,5,(132, 152, 109),4)
    
       
    cv2.imshow(WindName, image)
    #print('Show image done!') 

    key=cv2.waitKey(1) & 0xFF
    if key == ord('q') or key==27:
        break
    
    if key == ord('l'):
        landmarks_on=not landmarks_on
        
    if key == ord('p'):
        play_mode_on=not play_mode_on
        if play_mode_on:
            status_id, game_start_time, votes = 1, time.time(), {}  # restart a game
        else:
            status_id=0

# release the cap object
cap.release()
# destroy all the windows
cv2.destroyAllWindows()