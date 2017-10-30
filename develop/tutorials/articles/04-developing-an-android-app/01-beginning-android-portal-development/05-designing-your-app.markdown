# Designing Your App [](id=designing-your-app)

As a developer, you know that developing any kind of app without an overall 
design goal and plan to implement it is a recipe for disaster. To avoid this, 
you need to decide some things upfront. The Liferay Guestbook app needs a 
straightforward way to do three things: 

1. Authenticate users

2. Display guestbooks

3. Display entries

To authenticate users, all you need to do is insert and configure 
[Login Screenlet](/develop/reference/-/knowledge_base/7-0/loginscreenlet-for-android) 
in your app. Login Screenlet comes complete with its own UI. The design for 
authentication, therefore, like with @product@ itself, is done for you. 

You must, however, create the UI for displaying guestbooks and entries. What 
sort of UI would be best for this? Although the *best* UI for any purpose is a 
matter of opinion, displaying guestbooks and entries in a list is a good choice. 
Lists are common, compact design elements familiar to mobile users. Since most 
mobile devices don't have room to display a list of guestbooks and a list of 
entries at the same time, you also need a user-friendly way to display and 
manage these lists. It makes sense to show the first guestbook's entries 
automatically after the user authenticates. This is similar to the Guestbook 
portlet's design: it shows a list of the first guestbook's entries by default. 
When the user selects a different guestbook, you can then use the same UI to 
show the selected guestbook's entries instead. 

![Figure 1: By default, the first guestbook in the portlet is selected.](../../../images/guestbook-portlet.png)

You must also decide how the users can select different guestbooks. Showing the 
list of guestbooks in a navigation drawer that slides out from the left side of 
the screen is a good choice. A navigation drawer is easily hidden and is a 
common Android UI element. 

To display these lists of guestbooks and entries, you'll create your own 
Screenlets: Guestbook List Screenlet and Entry List Screenlet. Guestbook List 
Screenlet needs to retrieve guestbooks from the portlet and display them in a 
simple list. Once written, using this Screenlet is a simple matter of inserting 
it in the navigation drawer. Entry List Screenlet needs to retrieve and display 
a guestbook's entries in a similar list. You'll display the entries by inserting 
Entry List Screenlet in the UI element where you want it. 

Also note that these Screenlets are *list Screenlets*. You develop list 
Screenlets by using the list Screenlet framework, which sits on top of the core 
Screenlet framework. The list Screenlet framework makes it easy for developers 
to write Screenlets that display lists of entities from a @product@ instance. 

Awesome! Now you have a basic UI design and know the Screenlets you'll create to 
implement it. But where in the app can you use these Screenlets? The app only 
contains one empty activity, `MainActivity`, which you'll use for authentication 
with Login Screenlet. To use your custom list Screenlets, you'll need to create 
an additional activity and a fragment: `GuestbooksActivity` and 
`EntriesFragment`. You'll create the activity in a moment. 

![Figure 2: The Liferay Guestbook app's design uses two activities and a fragment. In this diagram, each activity and fragment is labeled, along with the Screenlets and the navigation drawer.](../../../images/android-app-design-screenlets.png)

In addition to showing the app's components, this diagram shows how the user 
navigates through the app. After sign in, the user transitions to 
`GuestbooksActivity`. This activity uses Entry List Screenlet in 
`EntriesFragment` to display the selected guestbook's entries (the first 
guestbook is selected by default). Pressing the hamburger button at the top-left 
of this screen opens the navigation drawer, where Guestbook List Screenlet 
displays the list of guestbooks. Selecting a guestbook closes the drawer to 
reveal that guestbook's entries. Note that you only need one activity, 
`GuestbooksActivity`, to display guestbooks and entries. The navigation drawer 
and `EntriesFragment` are part of this activity. 

Now you're ready to create `GuestbooksActivity`. Fortunately, Android Studio has 
a template for creating an activity that contains a navigation drawer. Follow 
these steps to create `GuestbooksActivity` with the navigation drawer template: 

1.  Right click the package `com.liferay.docs.liferayguestbook` and select *New*
    &rarr; *Activity* &rarr; *Navigation Drawer Activity* to launch the New
    Android Activity wizard. 

2.  Name the activity `GuestbooksActivity`, accept the defaults for the
    remaining fields, and click *Finish*. 

3.  After Android Studio creates the activity, the `GuestbooksActivity` class
    and `content_guestbooks.xml` layout open in the editor. Close them. You
    don't need to edit these files yet. 

Great! Now you understand the Liferay Guestbook app's design. You also have the
app structure in place. Next, you'll authenticate users by adding Login
Screenlet to `MainActivity`. 
