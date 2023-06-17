from DBInsertion import convertToBase64
import XOREncryption 
import XORDecryption
import os 
import random
from Crypto.Hash import SHA256
import base64
import hashlib
import shutil
def encryption(infile="NA",ext="NA",seckey1="NA",docid=0):
    UPLOAD_DIR=os.getcwd()+"\\Documents\\temp\\" 
    UPLOAD_DIR1=os.getcwd()+"\\Documents\\"+str(docid)+"\\" 
    try:
        os.mkdir(UPLOAD_DIR1)
    except Exception as e:
        print('Error caught : ', Exception.__name__)
        print(e)  
     
    ifile = open(UPLOAD_DIR+infile,'rb')    
    byt=ifile.read()
    len1=len(byt)
    len2=int(len1/4)
    len3=len1-(len2+len2+len2)
    print(str(len1))
    ifile = open(UPLOAD_DIR+infile,'rb')
    data1 = ifile.read(len2)
    data2 = ifile.read(len2)
    data3 = ifile.read(len2)
    data4 = ifile.read(len3)

    skey1=random.randint(11,99)
    skey2=random.randint(11,99)
    skey3=random.randint(11,99)
    skey4=random.randint(11,99)


    print(str(len(data1)))
    print(str(len(data1)))
    with open(UPLOAD_DIR1+"//part1."+ext, 'wb') as ofile:
        ofile.write(data1) 
    with open(UPLOAD_DIR1+"//part2."+ext, 'wb') as ofile:
        ofile.write(data2) 
    with open(UPLOAD_DIR1+"//part3."+ext, 'wb') as ofile:
        ofile.write(data3) 
    with open(UPLOAD_DIR1+"//part4."+ext, 'wb') as ofile:
        ofile.write(data4) 
     
    XOREncryption.encryptXOR(UPLOAD_DIR1+"//"+"part1."+ext,skey1)
    XOREncryption.encryptXOR(UPLOAD_DIR1+"//"+"part2."+ext,skey2)
    XOREncryption.encryptXOR(UPLOAD_DIR1+"//"+"part3."+ext,skey3)
    XOREncryption.encryptXOR(UPLOAD_DIR1+"//"+"part4."+ext,skey4)

    hash1=getHash(UPLOAD_DIR1+"//"+"part1."+ext) 
    hash2=getHash(UPLOAD_DIR1+"//"+"part2."+ext) 
    hash3=getHash(UPLOAD_DIR1+"//"+"part3."+ext) 
    hash4=getHash(UPLOAD_DIR1+"//"+"part4."+ext) 
    str1=str(len2)+","+str(len3)+","+str(skey1)+","+str(skey2)+","+str(skey3)+","+str(skey4)
    str1=str1+","+hash1+","+hash2+","+hash3+","+hash4 
    str2=convertToBase64(str1) 
    '''with open(UPLOAD_DIR+"//test1.png", 'wb') as ofile:
        ofile.write(data1) 
        ofile.write(data2)
        '''
    return str2
def decrypt(key1,key2,key3,key4,dpath,docid=0,ext="na"):
    UPLOAD_DIR1=os.getcwd()+"\\Documents\\"
    UPLOAD_DIR=os.getcwd()+"\\Documents\\temp\\" 
    XORDecryption.decryptXOR(UPLOAD_DIR1+"//"+docid+"/part1."+ext,key1,UPLOAD_DIR+"//"+"dec_part1."+ext)
    XORDecryption.decryptXOR(UPLOAD_DIR1+"//"+docid+"/part2."+ext,key2,UPLOAD_DIR+"//"+"dec_part2."+ext)
    XORDecryption.decryptXOR(UPLOAD_DIR1+"//"+docid+"/part3."+ext,key3,UPLOAD_DIR+"//"+"dec_part3."+ext)
    XORDecryption.decryptXOR(UPLOAD_DIR1+"//"+docid+"/part4."+ext,key4,UPLOAD_DIR+"//"+"dec_part4."+ext)
    ifile = open(UPLOAD_DIR+"//"+"dec_part1."+ext,'rb')    
    data1=ifile.read()
    ifile = open(UPLOAD_DIR+"//"+"dec_part2."+ext,'rb')    
    data2=ifile.read()
    ifile = open(UPLOAD_DIR+"//"+"dec_part3."+ext,'rb')    
    data3=ifile.read()
    ifile = open(UPLOAD_DIR+"//"+"dec_part4."+ext,'rb')    
    data4=ifile.read()
     
    with open(UPLOAD_DIR+"\\combined_dec"+docid+"."+ext, 'wb') as ofile:
        ofile.write(data1) 
        ofile.write(data2) 
        ofile.write(data3) 
        ofile.write(data4) 
    
def getHash(filename):
   """"This function returns the SHA-1 hash
   of the file passed into it"""

   # make a hash object
   h = hashlib.sha1()

   # open file for reading in binary mode
   with open(filename,'rb') as file:

       # loop till the end of the file
       chunk = 0
       while chunk != b'':
           # read only 1024 bytes at a time
           chunk = file.read(1024)
           h.update(chunk)

   # return the hex representation of digest
   return h.hexdigest()

 