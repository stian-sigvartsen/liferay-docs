# Creating Entry List Screenlet's Screenlet Class [](id=creating-entry-list-screenlets-screenlet-class)

Recall that when using a Screenlet, the app developer primarily interacts with 
its Screenlet class. The Screenlet class contains attributes for configuring the 
Screenlet's behavior, a reference to the Screenlet's View, methods for invoking 
Interactor operations, and more. This article shows you how to create Entry List 
Screenlet's Screenlet class. 

As with most of Entry List Screenlet, its Screenlet class is almost identical to 
that of Guestbook List Screenlet. Besides working with entries instead of 
guestbooks, the only difference is that it must know its entries' guestbook ID. 
Even the Screenlets' attributes are the same. 

Therefore, this article doesn't explain all the code in detail. Focus is instead 
placed on the few parts that differ from Guestbook List Screenlet. For a full 
explanation of the code, 
[click here](/develop/tutorials/-/knowledge_base/7-0/creating-guestbook-list-screenlets-screenlet-class) 
to see the article on creating Guestbook List Screenlet's Screenlet class. 

You'll create Entry List Screenlet's class with the same steps you used to
create Guestbook List Screenlet's class: 

1. Define the Screenlet's attributes. These are the XML attributes the app 
   developer can set when inserting the Screenlet's XML. These attributes 
   control aspects of the Screenlet's behavior. 

2. Create the Screenlet class. This class implements the Screenlet's 
   functionality defined in the View and Interactor. It also reads the attribute 
   values and configures the Screenlet accordingly. 

First, you'll define Entry List Screenlet's attributes. 

## Defining Screenlet Attributes [](id=defining-screenlet-attributes)

Recall that before creating the Screenlet class, you must define the attributes 
the app developer needs to control the Screenlet's behavior. Entry List 
Screenlet, like Guestbook List Screenlet, only needs a `groupId` attribute. In 
your app's `res/values` directory, create `entry_attrs.xml` and replace its 
contents with this code: 

    <?xml version="1.0" encoding="utf-8"?>
    <resources>
        <declare-styleable name="EntryListScreenlet">
            <attr name="groupId"/>
        </declare-styleable>
    </resources>

Next, you'll create the Screenlet class.

## Creating the Screenlet Class [](id=creating-the-screenlet-class)

Entry List Screenlet's class must contain an instance variable for the ID of the
guestbook the Screenlet retrieves entries from. This is required to start the
Interactor. This is the only significant difference between the Screenlet
classes of Entry List Screenlet and Guestbook List Screenlet. The remaining
differences exist only because they handle different entities. 

Create the `EntryListScreenlet` class in the `entrylistscreenlet` package. 
Replace the class's content with this code: 

    package com.liferay.docs.entrylistscreenlet;

    import android.content.Context;
    import android.content.res.TypedArray;
    import android.util.AttributeSet;
    import android.view.View;

    import com.liferay.docs.entrylistscreenlet.interactor.EntryListInteractor;
    import com.liferay.docs.liferayguestbook.R;
    import com.liferay.docs.model.EntryModel;
    import com.liferay.mobile.screens.base.list.BaseListScreenlet;
    import com.liferay.mobile.screens.context.LiferayServerContext;

    public class EntryListScreenlet extends BaseListScreenlet<EntryModel, EntryListInteractor> {

        private long guestbookId;

        public EntryListScreenlet(Context context) {
            super(context);
        }

        public EntryListScreenlet(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public EntryListScreenlet(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }

        public EntryListScreenlet(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
            super(context, attrs, defStyleAttr, defStyleRes);
        }

        @Override
        public void error(Exception e, String userAction) {
            if (getListener() != null) {
                getListener().error(e, userAction);
            }
        }

        @Override
        protected View createScreenletView(Context context, AttributeSet attributes) {
            TypedArray typedArray = context.getTheme().obtainStyledAttributes(attributes,
                R.styleable.GuestbookListScreenlet, 0, 0);
            groupId = typedArray.getInt(R.styleable.GuestbookListScreenlet_groupId,
                (int) LiferayServerContext.getGroupId());
            typedArray.recycle();

            return super.createScreenletView(context, attributes);
        }

        @Override
        protected void loadRows(EntryListInteractor interactor) {
            interactor.start(guestbookId);
        }

        @Override
        protected EntryListInteractor createInteractor(String actionName) {
            return new EntryListInteractor();
        }

        public long getGuestbookId() {
            return guestbookId;
        }

        public void setGuestbookId(long guestbookId) {
            this.guestbookId = guestbookId;
        }
    }

The instance variable for the guestbook ID is `guestbookId`. The getter and 
setter methods `getGuestbookId` and `setGuestbookId` let the app developer get 
and set this variable, respectively. The `loadRows` method starts the Interactor 
by calling the `start` method with `guestbookId` as an argument. Behind the 
scenes, the list Screenlet framework passes `guestbookId` to the Interactor's 
`getPageRowsRequest` and `getPageRowCountRequest` methods via the `args` 
argument. This is why you were able to extract `guestbookId` from the `args` 
argument in these methods. For an explanation of how the rest of this Screenlet 
class works, 
[click here](/develop/tutorials/-/knowledge_base/7-0/creating-guestbook-list-screenlets-screenlet-class) 
to see the article on creating Guestbook List Screenlet's Screenlet class. 

That's it! Now you're ready to use Entry List Screenlet alongside Guestbook List 
Screenlet. The following section of this Learning Path concludes with both 
Screenlets working together in harmony. 
