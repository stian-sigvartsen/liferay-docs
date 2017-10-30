# Getting Started with Guestbook List Screenlet [](id=getting-started-with-guestbook-list-screenlet)

Before creating a Screenlet, you should know how you'll use it. If you plan to 
use it in only one app, then you can create it in that app's project. If you 
need to use it in several apps, however, then it's best to create it in a 
separate project for redistribution. For information on creating Screenlets for 
redistribution, see the tutorial 
[Packaging Your Android Screenlets](/develop/tutorials/-/knowledge_base/7-0/packaging-your-android-screenlets). 
Since you'll use Guestbook List Screenlet in only this app, you can create it in 
a new package inside the app's project. Create this package now:

1.  In Android Studio, right click the *java* folder and select *New* &rarr;
    *Package*. 

2.  Select *.../app/src/main/java* as the destination directory, and click *OK*. 

3.  Enter `com.liferay.docs.guestbooklistscreenlet` as the package's name and
    click *OK*. Android Studio lists the new package alongside the package that
    contains the app's activity and fragment (`liferayguestbook`).  If it
    doesn't appear at first, you may need to collapse and reopen the *java*
    folder. 

![Figure 1: Guestbook List Screenlet's new package is highlighted.](../../../images/android-guestbooks-screenlet-package.png)

Before getting started, you should understand how pagination works in list 
Screenlets.

## Pagination [](id=pagination)

To ensure that users can scroll smoothly through large lists of items, list 
Screenlets support fluent pagination. Support for this is built into the list 
Screenlet framework. You'll see this as you construct your list Screenlet. For 
example, several methods have parameters for the start row and end row of a page 
in the list. 

Now you're ready to begin! 

## Creating the Model Class for Guestbooks [](id=creating-the-model-class-for-guestbooks)

Entities come back from Liferay Portal in JSON. To work with these results 
efficiently in your app, you must convert them to model objects that represent 
the entity in the portal. Although Screens's 
[`BaseListInteractor`](https://github.com/liferay/liferay-screens/blob/master/android/library/src/main/java/com/liferay/mobile/screens/base/list/interactor/BaseListInteractor.java) 
transforms the JSON entities into `Map` objects for you, you still must 
convert these into proper entity objects for use in your app. You'll do this via 
a model class. 

The model class you'll create for Guestbook List Screenlet, `GuestbookModel`, 
creates `GuestbookModel` objects that represent guestbooks retrieved from the 
Guestbook portlet. You'll create this model class in a separate package outside 
of the `guestbooklistscreenlet` package. In this case, it makes sense to 
organize your code this way because other Screenlets may also use the model 
class. For example, if a Screenlet that edits guestbooks existed, it would also 
need `GuestbookModel` objects. Putting the model class in a separate package 
makes it clear that this class doesn't belong exclusively to a single Screenlet. 

Follow these steps to create `GuestbookModel`:

1.  Create a new package called `model` inside the `com.liferay.docs` package.
 
2.  Inside this new `model` package, create a new class called `GuestbookModel`.
 
3.  Replace `GuestbookModel`'s contents with this code: 

        package com.liferay.docs.model;

        import android.os.Parcel;
        import android.os.Parcelable;

        import java.util.Date;
        import java.util.Map;

        public class GuestbookModel implements Parcelable {

            private Map values;
            private long guestbookId;
            private long groupId;
            private long companyId;
            private long userId;
            private String userName;
            private long createDate;
            private long modifiedDate;
            private String name;

            public static final Creator<GuestbookModel> CREATOR = new Creator<GuestbookModel>() {
                @Override
                public GuestbookModel createFromParcel(Parcel in) {
                    return new GuestbookModel(in);
                }

                @Override
                public GuestbookModel[] newArray(int size) {
                    return new GuestbookModel[size];
                }
            };

            public GuestbookModel() {
                super();
            }

            protected GuestbookModel(Parcel in) {
                guestbookId = in.readLong();
                groupId = in.readLong();
                companyId = in.readLong();
                userId = in.readLong();
                userName = in.readString();
                createDate = in.readLong();
                modifiedDate = in.readLong();
                name = in.readString();
            }

            public GuestbookModel(Map<String, Object> stringObjectMap) {
                values = stringObjectMap;
                guestbookId = Long.parseLong((String) stringObjectMap.get("guestbookId"));
                groupId = Long.parseLong((String) stringObjectMap.get("groupId"));
                companyId = Long.parseLong((String) stringObjectMap.get("companyId"));
                userId = Long.parseLong((String) stringObjectMap.get("userId"));
                userName = (String) stringObjectMap.get("userName");
                createDate = (long) stringObjectMap.get("createDate");
                modifiedDate = (long) stringObjectMap.get("modifiedDate");
                name = (String) stringObjectMap.get("name");
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeLong(guestbookId);
                dest.writeLong(groupId);
                dest.writeLong(companyId);
                dest.writeLong(userId);
                dest.writeString(userName);
                dest.writeLong(createDate);
                dest.writeLong(modifiedDate);
                dest.writeString(name);
            }

            @Override
            public int describeContents() {
                return 0;
            }

            public long getGuestbookId() {
                return guestbookId;
            }

            public long getGroupId() {
                return groupId;
            }

            public long getCompanyId() {
                return companyId;
            }

            public long getUserId() {
                return userId;
            }

            public String getUserName() {
                return userName;
            }

            public Date getCreateDate() {
                return new Date(createDate);
            }

            public Date getModifiedDate() {
                return new Date(modifiedDate);
            }

            public String getName() {
                return name;
            }

            public Map getValues() {
                return values;
            }

            public void setValues(Map values) {
                this.values = values;
            }
        }

This class creates `GuestbookModel` objects that represent the Guestbook 
portlet's `Guestbook` objects. The constructor with the `Map<String, Object>` 
argument does the heavy lifting. Following a successful Mobile SDK call, the 
list Screenlet framework's 
[`BaseListInteractor` class](https://github.com/liferay/liferay-screens/blob/2.1.0/android/library/src/main/java/com/liferay/mobile/screens/base/list/interactor/BaseListInteractor.java) 
returns this `Map`, which contains the data of a guestbook retrieved from the 
portlet. To get the guestbook's data from the `Map`, the constructor uses the 
`get` method with each parameter of the portlet's `Guestbook` entity. To see how 
the portlet defines these parameters, 
[see the Liferay MVC Learning Path article on Service Builder](/develop/tutorials/-/knowledge_base/6-2/using-service-builder-to-generate-a-persistence-fr). 
For now, the only parameters you really need in `GuestbookModel` are 
`guestbookId` and `name`. Because you might need the rest later, however, it's 
best to add all of them now. 

Besides the getters and setter, the remaining code in `GuestbookModel` 
implements Android's `Parcelable` interface. For more information on this, see 
[Android's documentation on `Parcelable`](https://developer.android.com/reference/android/os/Parcelable.html). 

Great! Now you have a model class for guestbooks. Next, you'll create the 
Screenlet's UI. 
