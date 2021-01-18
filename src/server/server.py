import socket
import sys

sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
try:
  server_address = ('localhost', 12345)
  sock.bind(server_address)
  sock.listen(5)
  conn, addr = sock.accept()
  try:
    print('Received connection from: ' + addr[0])
    while True:
      data = ''
      while (byte := conn.recv(1)) != b'\n':
        data += byte.decode()
      print(data)
      conn.sendall(('%s\n' % data).encode())
      print('Sent')
  except Exception as e:
    print("Server Error: %s" % str(e))
  finally: 
    conn.close()
except Exception as e:
  print("Server Error: %s" % str(e))
finally:
  sock.close()
