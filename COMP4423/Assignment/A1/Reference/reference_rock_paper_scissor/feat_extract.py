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
import numpy as np, random

cap = cv2.VideoCapture(0)
WindName = "Toy Program @ COMP 4423"
cv2.namedWindow(WindName)
cv2.resizeWindow(WindName, 1024, 768)

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
 
feat_x,feat_y=[],[]    
while True:
    success, image = cap.read()
    if not success:
        continue
    image = cv2.flip(image, 1)
    imageRGB = cv2.cvtColor(image, cv2.COLOR_BGR2RGB)

    results = detect(imageRGB)
    id2cords = {}
    #print(['len=', len(results)])
    if len(results) == 0:
        print("Nothing detected ...")
    else:
        image, id2cords = draw_landmarks(image, results[0]['landmarks'])

    cv2.imshow(WindName, image)

    key=cv2.waitKey(1) & 0xFF
    if key == ord('q') or key==27:
        break
    
    if key == ord('p') and not id2cords =={}:
        # caputure a sample for the class 'paper'
        feat=extrac_feature(id2cords)
        feat_x.append(feat)
        feat_y.append(1)
    if key == ord('r') and not id2cords =={}:
        # caputure a sample for the class 'rock'
        feat=extrac_feature(id2cords)
        feat_x.append(feat)
        feat_y.append(2)
    if key == ord('x') and not id2cords =={}:
        # caputure a sample for the class 'rock'
        feat=extrac_feature(id2cords)
        feat_x.append(feat)
        feat_y.append(3)

    if key == ord('s') and len(feat_x)>0:
        np.save('feat_x.npy',np.array(feat_x))
        np.save('feat_y.npy',np.array(feat_y))
        print('feature saved ...')
    
    print('#features=',len(feat_x))

# release the cap object
cap.release()
# destroy all the windows
cv2.destroyAllWindows()