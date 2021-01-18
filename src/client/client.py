import socket

HOST = 'localhost'
PORT = 12345

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
  s.connect((HOST, PORT))
  while True:
    s.sendall(('%s\n' % input("Input: ")).encode())
    print("Sent")
    data = ''
    while (byte := s.recv(1)) != b'\n':
      data += byte.decode()
    print(data)
