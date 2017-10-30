# Creating Entry List Screenlet [](id=creating-entry-list-screenlet)

In the previous section, you created Guestbook List Screenlet to retrieve and
display guestbooks from the Guestbook portlet. You still need a way to retrieve
and display each guestbook's entries, though. You'll do this by creating another
list Screenlet: Entry List Screenlet. This section walks you through the steps
required to create it. 

Because you use a consistent development model to create Screenlets, similar 
Screenlets have similar code. As with guestbooks, it makes sense to display 
entries in a list using a list Screenlet. This means you can reuse most of
Guestbook List Screenlet's code in Entry List Screenlet. You'll therefore create 
Entry List Screenlet using the same sequence of steps you used to create 
Guestbook List Screenlet: 

1. Getting started: creating the Screenlet's package and model class. 
2. Creating the Screenlet's UI (its View). 
3. Creating the Screenlet's Interactor. 
4. Creating the Screenlet class. 

Although this Learning Path section presents complete code snippets, it only 
discusses the code unique to Entry List Screenlet. Refer back to the previous 
section for detailed explanations of the code shared with Guestbook List 
Screenlet. If you get confused or stuck, refer to the finished app that contains 
the Screenlet code 
[here in GitHub](https://github.com/liferay/liferay-docs/tree/master/develop/tutorials/code/mobile/android/screenlets-app/LiferayGuestbook). 
