# Import Basic OS functions
import os
# Import modules for CGI handling
import cgi, cgitb
import urllib.request
#from HaarWavelet import *
from FunFactory import *
from DBInsertion import *
from Cryptography import * 

#from HOG import *


# enable debugging
cgitb.enable()
# print content type
print("Content-type:text/html\r\n\r\n")
print("path="+os.getcwd()) 
#print() 
#form=cgi.FieldStorage()

fid="2.jpg"
# HTML INPUT FORM
HTML = """
<html>
<head>
<title></title>
</head>
<body>

  <h1>Upload File</h1>
  <form action="http://localhost:64203/Default.aspx" method="POST">
    File: <input name="file" type="file">
    <input name="submit" type="submit">
</form>

{% if filedata %}

<blockquote>

{{filedata}}

</blockquote>

{% endif %}  

</body>
</html>
"""
filename=""
ext=""
uploaded_file_path=""
inFileData = None
form = cgi.FieldStorage() 
docid=getMaxIdDoc1()
UPLOAD_DIR=os.getcwd()+"\\Documents\\" 
UPLOAD_DIR1=os.getcwd()+"\\Documents\\temp\\" 
#print("value="+form.getvalue("uid"))
# IF A FILE WAS UPLOADED (name=file) we can find it here.
#fid=form.getvalue("fid")
#print(form)
if "file" in form:
    form_file = form['file']
   
    # form_file is now a file object in python
    if form_file.filename:
        print("file name"+os.path.basename(form_file.filename))
        nm,ext=os.path.basename(form_file.filename).split('.')
        filename=os.path.basename(form_file.filename)
        print("original file name")
        print(filename)
        print(str(docid)+"."+ext)
        filename=str(docid)+"."+ext
        uploaded_file_path = os.path.join(UPLOAD_DIR1, filename)
        print(uploaded_file_path)
        with open(uploaded_file_path, 'wb') as fout:
            # read the file in chunks as long as there is data
            while True:
                chunk = form_file.file.read(100000)
                if not chunk:
                    break
                # write the file content on a file on the hdd
                fout.write(chunk)

        # load the written file to display it
        with open(uploaded_file_path, 'r',errors='ignore') as fin:
            inFileData = ""
            for line in fin:
                inFileData += line

    
headers = {'User-Agent':'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:23.0) Gecko/20100101 Firefox/23.0'}
#print(jinja2.Environment().from_string(HTML).render(filedata=inFileData)) 
#w2d("E:\\python\\1.jpg",'haar','111')
#print(form.getvalue("fid"))
title=form.getvalue("title")
userid=form.getvalue("userid")
docdesc=form.getvalue("docdesc")
dt=form.getvalue("dt")
tm=form.getvalue("tm")
seckey=form.getvalue("seckey")
print(docid)
print(userid+" "+title+" "+docdesc)
 
metadata=encryption(filename,ext,seckey,docid)

insertDoc1(userid,title,filename,docdesc,dt,tm,seckey)
print("<html>")
print("<head>")
print("<meta http-equiv='refresh' content='0;url=http://localhost:8080/datasetInsrtPython?sts=success&userid="+userid+"&s="+seckey+"&docid="+str(docid)+"&meta="+metadata+"'/>")
print("</head>")
print("</html>")