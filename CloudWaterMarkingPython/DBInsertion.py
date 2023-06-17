from DBConnect import *
import base64
def insertDoc1(userid="NA",title="NA",docPath="NA",docDesc='NA',dt="NA",tm="NA",key='NA',) : 
    conn = connect()    
    cursor = conn.cursor()
    args = [userid,title,dt,tm,docDesc,key,docPath]
    args1=cursor.callproc('insertDoc', args)
    print("Return value:", args1)
    #for result in cursor.stored_results():
     #       print(result.fetchall())
    cnt=cursor.rowcount 
    conn.commit()


    #args = [userid,title,docPath,docDesc,dt,tm,key]
    #args1=cursor.callproc('insertDoc', args)
    #print("Return value:", args1)
    #for result in cursor.stored_results():
    #        print(result.fetchall())
    #cnt=cursor.rowcount
    conn.commit()
    #return cnt
 
def getMaxIdDoc1():
    conn = connect()
    #integrated security 
    cursor = conn.cursor() 
    cursor.execute('select (ifnull(max(docid),1000)+1) as mxid from documents;')
    mxid=0
    for row in cursor: 
        mxid=row[0]
        print(int(mxid)+1)
    return mxid

def convertToBase64(message='NA') :
    message_bytes = message.encode('ascii')
    base64_bytes = base64.b64encode(message_bytes)
    base64_message = base64_bytes.decode('ascii')
    print(base64_message)
    return base64_message

def convertFromBase64(base64_message='NA') :
    base64_bytes = base64_message.encode('ascii')
    message_bytes = base64.b64decode(base64_bytes)
    message = message_bytes.decode('ascii')
    print(message)
    return message