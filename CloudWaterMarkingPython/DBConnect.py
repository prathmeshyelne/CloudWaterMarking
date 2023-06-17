# import mysql.connector as mycon
#
# def connect() :
#     con=mycon.connect(host='localhost',user='root',password='Pass@1234',database='cloudwatermarkdb')
#     print("OK")
#     return con

import mysql.connector
from mysql.connector import Error

def connect() :
    try:
        connection = mysql.connector.connect(host='localhost', user='root', password='1234', database='cloudwatermarkdb')

        if connection.is_connected():
            db_Info = connection.get_server_info()
            print("Connected to MySQL Server version ", db_Info)
            cursor = connection.cursor()
            cursor.execute("select database();")
            record = cursor.fetchone()
            print("You're connected to database: ", record)

    except Error as e:
        print("Error while connecting to MySQL", e)
    # finally:
    #     if connection.is_connected():
    #         cursor.close()
    #         connection.close()
    #         print("MySQL connection is closed")
    return connection

connect()