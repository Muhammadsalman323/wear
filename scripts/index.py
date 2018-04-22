import http.server
import socketserver
import logging
import cgi

PORT = 8000

class ServerHandler(http.server.SimpleHTTPRequestHandler):

    def do_GET(self):
        logging.error(self.headers)
        http.server.SimpleHTTPRequestHandler.do_GET(self)

    def do_POST(self):
        content_length = int(self.headers['Content-Length']) # <--- Gets the size of data
        post_data = self.rfile.read(content_length) # <--- Gets the data itself
        print (post_data.decode("utf-8"))
        #logging.error(self.headers)
        #form = cgi.FieldStorage(
        #   fp=self.rfile,
        #    headers=self.headers,
        #    environ={'REQUEST_METHOD':'POST',
        #             'CONTENT_TYPE':self.headers['Content-Type'],
        #             })
        #for item in form.list:
        #    logging.error(item)
        #http.server.SimpleHTTPRequestHandler.do_GET(self)

Handler = ServerHandler

httpd = socketserver.TCPServer(("", PORT), Handler)

print ("serving at port", PORT)
httpd.serve_forever()