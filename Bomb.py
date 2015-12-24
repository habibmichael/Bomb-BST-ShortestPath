#Gmail Bomb
import smtplib
def send_mail():
	server=smtplib.SMTP("smtp.gmail.com",587)
	username=
	password=
	FROM=
	server.starttls()
	server.login(username,password)
	#Prompt user for email
	TO = 
	#DA BOMB LOOP
	for i in range(50):
		SUBJECT='BOMB'+str(i)
		TEXT='LOLZ'
		#Actual message
		message= """From %s\nTo: %s\nSubject: %s\n\n%s"""%(FROM,TO,SUBJECT,TEXT)
		for j in range(len(TO)):
			server.sendmail(FROM,TO[j],message)
	
	
	server.close()
#run bo
sen
