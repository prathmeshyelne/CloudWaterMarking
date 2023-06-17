
def encryptXOR(path="NA",key=0):
    try:
        # take path of image as a input
       #path = input("Enter path of Image : ")
        
        # taking encryption key as input
        #key = int(input('Enter Key for encryption of Image : '))
        
        # print path of image file and encryption key that
        # we are using
        print('The path of file : ', path)
        print('Key for encryption : ', key)
        
        # open file for reading purpose
        fin = open(path, 'rb')
        
        # storing image data in variable "image"
        image = fin.read()
        fin.close()
        
        # converting image into byte array to 
        # perform encryption easily on numeric data
        image = bytearray(image)
        k2=0
        cnt=0
        # performing XOR operation on each value of bytearray
        for index, values in enumerate(image):
            if cnt%2==0 :
                image[index] = values ^ key
                k2=values ^ key
            else:
                image[index] = values ^ k2
            #print(index)
        # opening file for writting purpose
        fin = open(path, 'wb')
        
        # writing encrypted data in image
        fin.write(image)
        fin.close()
        print('Encryption Done...')
    
        
    except Exception:
        print('Error caught : ', Exception.__name__)