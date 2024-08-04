# ChitChat
A simple group chat Application using Java Socket Programming. A simple GUI demonstration on localhost is shown below... This can be implemented over LAN connected machines by using their IP Address.. #socket #socketProgramming #chat #javaprogramminglanguage #groupchat
To watch how it is implemented click the link below:
 
https://www.linkedin.com/posts/deysarkarswarup_socket-socketprogramming-chat-activity-6581552689602236416-vMxm

Starting with the code as is, make additions to it to increase its functionality.

## Additions

- Add documentation about how to use this app.
- Add logging to the server to log all connections and messages to some loggin sink (a file or a DB).
- Add a current user list, and the UI required to show it (either a chat command or a UI widget)
- Add a chat bot of some kind which can supply stock market news or details, sports headlines, or weather info.
- Add a way to message images

//what i should be doing
1. be able to log - events
2. need a handler object, give it the parameter to the destination - file
3. throw an exception, file not found. user a try/catch
4. current userlist, managing users in casino, needs its own class. 
5. chat bot, make a class that handles the operations of requesting a stock market news. first put together a uml
6. socket client - server client - makes connection to socket - see what it gives you - gives you server - gives you thread
- gives you barebones, just need a plan to make it work via classes.
7. make a pom file so you can use junit