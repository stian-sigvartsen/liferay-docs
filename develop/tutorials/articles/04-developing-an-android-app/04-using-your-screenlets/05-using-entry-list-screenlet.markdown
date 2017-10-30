# Using Entry List Screenlet [](id=using-entry-list-screenlet)

You'll use Entry List Screenlet by following the same steps to use any 
Screenlet: insert the Screenlet's XML in an activity or fragment layout, and 
then implement the Screenlet's listener in the activity or fragment class. 
You'll follow these steps here to insert Entry List Screenlet in 
`EntriesFragment`. 

First, you'll insert Entry List Screenlet's XML in `EntriesFragment`'s layout, 
`fragment_entries.xml`. 

## Inserting the Screenlet in the Layout [](id=inserting-the-screenlet-in-the-layout)

Inserting Entry List Screenlet's XML is very simple. Since all you want 
`fragment_entries.xml` to do is display the Screenlet, it must only contain 
the Screenlet's XML. Replace the contents of `fragment_entries.xml` with the 
following markup: 

    <com.liferay.docs.entrylistscreenlet.EntryListScreenlet
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        android:id="@+id/entrylist_screenlet"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:layoutId="@layout/list_entries"/>

As with Guestbook List Screenlet, you'll use the `android:id` value to get a 
Screenlet reference. Next, you must implement the Screenlet's listener. 

## Implementing the Screenlet's Listener [](id=implementing-the-screenlets-listener)

Recall that to use a Screenlet, you must implement its listener. The listener 
methods let the app developer respond to the Screenlet's behavior in the 
activity or fragment class that contains the Screenlet. Also recall that because 
Guestbook List Screenlet didn't need extra listener methods, you used it in 
`GuestbooksActivity` by implementing the 
[`BaseListListener` interface](https://github.com/liferay/liferay-screens/blob/2.1.0/android/library/src/main/java/com/liferay/mobile/screens/base/list/BaseListListener.java) 
with `GuestbookModel` as a type argument. Entry List Screenlet also doesn't need 
extra listener methods. Like Guestbook List Screenlet, you can use it by 
implementing `BaseListListener` with its model class as a type argument. 

Follow these steps to implement Entry List Screenlet's listener in 
`EntriesFragment`: 

1.  Change `EntriesFragment`'s class declaration to implement
    `BaseListListener<EntryModel>`. The class declaration should now look like
    this: 

        public class EntriesFragment extends Fragment implements BaseListListener<EntryModel> {...

    This requires that you add the following imports:

        import com.liferay.docs.model.EntryModel;
        import com.liferay.mobile.screens.base.list.BaseListListener;

2.  Now you must implement the listener's methods. Recall that this includes the
    [`BaseCacheListener` interface's](https://github.com/liferay/liferay-screens/blob/2.1.0/android/library/src/main/java/com/liferay/mobile/screens/base/list/BaseListListener.java), 
    `error` method, since `BaseListListener` extends `BaseCacheListener`. For a 
    full explanation of the methods in both listeners, see 
    [using Guestbook List Screenlet](/develop/tutorials/-/knowledge_base/6-2/using-guestbook-list-screenlet). 
    Note that in `EntriesFragment`, you don't need to take any action in these 
    methods. There are no UI elements or other parts of the fragment that must 
    be updated or processed in response to the Screenlet's behavior. All this 
    Screenlet must do is display its content, which it does regardless of 
    anything you do in the listener methods. The only thing you may want to add 
    is a toast message in `onListPageFailed` to notify the user if the server 
    call fails, but this isn't required. Implement these methods now: 

        @Override
        public void onListPageFailed(int startRow, Exception e) {
            Toast.makeText(getActivity(), "Page request failed", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onListPageReceived(int startRow, int endRow, List<EntryModel> entries, int rowCount) {

        }

        @Override
        public void onListItemSelected(EntryModel entry, View view) {

        }

        @Override
        public void error(Exception e, String userAction) {

        }

    This requires you to add the following imports:

        import android.widget.Toast;
        import java.util.List;

3.  Now you're ready to register `EntriesFragment` as the Screenlet's listener.
    You'll do this the same way you registered `GuestbooksActivity` as Guestbook
    List Screenlet's listener: get a reference to the Screenlet and call its
    `setListener` method. After doing this, you'll use the Entry List Screenlet
    reference's `setGuestbookId` method to set its guestbook ID. This sets the
    guestbook where the Screenlet gets its entries. You'll do these things in
    the `onCreateView` method. Replace the `onCreateView` method with this
    updated version: 

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View view = inflater.inflate(R.layout.fragment_entries, container, false);
            long guestbookId = getArguments().getLong("guestbookId");

            EntryListScreenlet screenlet = (EntryListScreenlet) view.findViewById(R.id.entrylist_screenlet);
            screenlet.setListener(this);
            screenlet.setGuestbookId(guestbookId);

            return view;
        }

    This requires you to import 
    `com.liferay.docs.entrylistscreenlet.EntryListScreenlet`. 

    As you can see, `onCreateView` now registers `EntriesFragment` as the 
    Screenlet's listener and sets the Screenlet's guestbook ID. The rest of 
    `onCreateView` is unchanged. 

Now run the app in the emulator and log in with your credentials when prompted. 
The app then presents you with the first guestbook's entries. Open the 
navigation drawer by pressing the hamburger button, then select a different 
guestbook. The drawer then closes to show the selected guestbook's entries. Nice 
work! Your app now uses Guestbook List Screenlet and Entry List Screenlet to 
show the same guestbooks and entries as the Guestbook portlet. The following 
screenshots show these Screenlets in action. 

![Figure 1: Entry List Screenlet displays guestbook entries in your app.](../../../images/android-guestbooks-entries-screenlets.png)

![Figure 2: Guestbook List Screenlet displays guestbooks in the navigation drawer.](../../../images/android-guestbook-screenlet-drawer-01.png)

Although your Screenlets work, you may have noticed something odd about the 
navigation drawer's header--it's hideous. The action bar is somewhere on the 
purple-blue spectrum, while the drawer header is green. You've probably seen 
more attractive finger paintings. Fortunately, it's simple to change the drawer 
header's color. Also, the drawer header contains the generic text 
*Android Studio*. You should change this to something more suitable for your 
app, like *Liferay Guestbook*. 

Follow these steps to apply these changes to the drawer header: 

1.  In `res/drawable/side_nav_bar.xml`, replace `android:centerColor`,
    `android:endColor`, and `android:startColor` with the following settings: 

        android:centerColor="@color/colorPrimary"
        android:endColor="@color/colorPrimaryDark"
        android:startColor="@color/colorPrimary"

    This sets the drawer header's colors to match the colors used in the rest of 
    the app.

2.  Define the following string resource in `res/values/strings.xml`: 

        <string name="liferay_guestbook">Liferay Guestbook</string>

3.  In `nav_header_guestbooks.xml`, find the `TextView` element that contains
    `android:text="Android Studio"`, and replace `Android Studio` with
    `@string/liferay_guestbook`. You can delete any other `TextView` elements in
    this file. Run the app again, and open the drawer after signing in. The
    drawer header now shows your greeting. It's a lot prettier too. 

![Figure 3: The drawer header looks a lot better after some light customization.](../../../images/android-guestbook-screenlet-drawer-02.png)

Congratulations! Now you know how to use Liferay Screens and create your own 
Screenlets. This opens up a world of possibilities for developing apps that 
leverage @product@. Although you learned a great deal in this Learning Path, 
there's still more. You can customize your Screenlet's appearance, package it 
for redistribution, and even configure it to receive push notifications. These 
topics, and more, are covered in the 
[tutorials on Android apps with Liferay Screens](/develop/tutorials/-/knowledge_base/7-0/android-apps-with-liferay-screens). 
