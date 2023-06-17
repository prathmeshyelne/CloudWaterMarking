# Import Basic OS functions
 
import os
# Import modules for CGI handling
import cgi, cgitb, jinja2
import urllib.request
from Cryptography import decrypt
#from HaarWavelet import *
from FunFactory import *
from DBInsertion import *  
import numpy as np
from array import *
import random
#from HOG import * 
# enable debugging
cgitb.enable()
# print content type
print("Content-type:text/html\r\n\r\n")
print("path="+os.getcwd()) 
#print() 
#form=cgi.FieldStorage()
  
filename=""
ext=""
uploaded_file_path=""
inFileData = None
form = cgi.FieldStorage()  
UPLOAD_DIR=os.getcwd()+"\\Documents\\"

imgpath="NA"
id=form.getvalue("param")
print("param val="+id)
base64_bytes = id.encode('ascii')
message_bytes = base64.b64decode(base64_bytes)
message = message_bytes.decode('ascii')
key1,key2,key3,key4,docpath = message.split('|')
 
print(message)
print("key=")
print(key1.strip().isdigit())
print(key1.strip())
print("key=")
key1=key1.replace("\x00","")
key1=str(key1).lstrip()
key2=str(key2).lstrip()
key3=str(key3).lstrip()
key4=str(key4).lstrip()
#key=int(str(key).lstrip())
print("key=")
print(key1+" "+key2+" "+key3+" "+key4) 
#print("value="+form.getvalue("uid"))
# IF A FILE WAS UPLOADED (name=file) we can find it here.
#fid=form.getvalue("fid")
#print(form)
 
headers = {'User-Agent':'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:23.0) Gecko/20100101 Firefox/23.0'}
#print(jinja2.Environment().from_string(HTML).render(filedata=inFileData)) 
#w2d("E:\\python\\1.jpg",'haar','111')
#print(form.getvalue("fid"))
 
print("\n"+docpath+"\n key="+str(key1))
print("before enc")
docid,ext=docpath.split('.')
print(UPLOAD_DIR+docpath)
UPLOAD_DIR=os.getcwd()+"\\Documents\\"
UPLOAD_DIR1=os.getcwd()+"\\Documents\\temp\\"
uploaded_file_path = os.path.join(UPLOAD_DIR, docpath)
uploaded_file_path=uploaded_file_path.replace("\x00","")
docpath=docpath.replace("\x00","")
dpath1 = os.path.join(UPLOAD_DIR1, docpath) 
dpath1=dpath1.replace("\x00","")
#uploaded_file_path=uploaded_file_path.replace("\\", "/")
#dpath=dpath.replace("\\", "/")
"""
fin = open(r'C://xampp//htdocs//SecureDocumentsStorageUsingBC//Documents//F1002.jpg', 'rb')

doc = fin.read()
fin.close() 
doc1 = bytearray(doc)
"""
decrypt(int(key1),int(key2),int(key3),int(key4),dpath1,docid,ext)
 
print("<html>")
print("<head>")
print("<meta http-equiv='refresh' content='0;url=http://localhost:8080/FromPythonDec?sts=combined_dec"+docid+"."+ext+"'/>")
print("</head>")
print("</html>")
