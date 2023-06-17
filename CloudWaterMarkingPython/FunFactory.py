import base64

def convertToBase64(imagePath) :
    #message_bytes = message.encode('ascii')
    base64_message="NA"
    with open(imagePath, "rb") as imageFile:
        base64_message = base64.b64encode(imageFile.read())
    #print(base64_message)
    message = base64_message.decode('ascii')
    return message

def convertFromBase64(base64_message='NA',fileName="NA") :
    imgdata = base64.b64decode(base64_message)
    filename = fileName  # I assume you have a way of picking unique filenames
    with open(filename, 'wb') as f:
        f.write(imgdata)
    